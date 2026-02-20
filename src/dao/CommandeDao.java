package dao;

import dao.Connexion;
import model.Client;
import model.Plat;
import model.Commande;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class CommandeDao implements Idao<Commande> {

    private final ClientDao cdao = new ClientDao();
    private final PlatDao pdao = new PlatDao();

    @Override
    public Commande findById(int id) throws Exception {
        String sql = "SELECT * FROM commande WHERE id_commande=?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int pId = rs.getInt("plat_id");
                    Plat p = pdao.findById(pId);

                    int cId = rs.getInt("client_id");
                    Client c = cdao.findById(cId);

                    return new Commande(
                            rs.getInt("id_commande"),
                            rs.getDate("date_commande").toLocalDate(),
                            rs.getInt("quantite_commande"),
                            p,
                            c
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Commande> findAll() throws Exception {
        String sql = "SELECT * FROM commande";
        List<Commande> list = new ArrayList<>();

        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {

                int pId = rs.getInt("plat_id");
                Plat p = pdao.findById(pId);

                int cId = rs.getInt("client_id");
                Client c = cdao.findById(cId);

                list.add(new Commande(
                        rs.getInt("id_commande"),
                        rs.getDate("date_commande").toLocalDate(),
                        rs.getInt("quantite_commande"),
                        p,
                        c
                ));
            }
        }
        return list;
    }

    @Override
    public int insert(Commande cm) throws Exception {
        String sql = "INSERT INTO commande(date_commande, quantite_commande, plat_id, client_id) VALUES(?, ?, ?, ?)";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(cm.getDate_commande()));
            ps.setInt(2, cm.getQuantite_commande());
            ps.setInt(3, cm.getPlat().getId_plat());
            ps.setInt(4, cm.getClient().getId_client());


            int rows = ps.executeUpdate();

            if (rows == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        cm.setId_commande(id);
                        return id;
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public boolean update(Commande cm) throws Exception {
        String sql = "UPDATE commande SET date_commande=?, quantite_commande=?, plat_id=?, client_id=? WHERE id_commande=?";
        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(cm.getDate_commande()));
            ps.setInt(2, cm.getQuantite_commande());
            ps.setInt(3, cm.getPlat().getId_plat());
            ps.setInt(4, cm.getClient().getId_client());
            ps.setInt(5, cm.getId_commande());


            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM commande WHERE id_commande=?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }

    public Map<String, Double> chiffreAffiarePlat() throws Exception {
        String sql = "SELECT p.nom_plat, SUM(cm.quantite_commande * p.prix_plat) AS chiffre_affaire FROM commande cm " +
                "JOIN plat p ON cm.plat_id=p.id_plat GROUP BY nom_plat";

        Map<String, Double> map = new HashMap<>();
        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
                    while(rs.next()) {
                        map.put(
                            rs.getString("nom_plat"),
                            rs.getDouble("chiffre_affaire")
                        );
                    }
                }
        return map;
    }


    public double calculerRecettes() throws Exception {
        String sql = "SELECT SUM(cm.quantite_commande * p.prix_plat) AS recette_totale " +
                "FROM commande cm JOIN plat p ON cm.plat_id = p.id_plat";

        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble("recette_totale");
            }
            return 0.0;
        }
    }


    public List<Map<String, Object>> platsPopulaires() throws Exception {
        String sql = "SELECT p.nom_plat, p.categorie_plat, p.prix_plat, " +
                "SUM(cm.quantite_commande) AS total_commande " +
                "FROM commande cm JOIN plat p ON cm.plat_id = p.id_plat " +
                "GROUP BY p.id_plat, p.nom_plat, p.categorie_plat, p.prix_plat " +
                "ORDER BY total_commande DESC";

        List<Map<String, Object>> list = new ArrayList<>();

        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("nom_plat",       rs.getString("nom_plat"));
                map.put("categorie_plat", rs.getString("categorie_plat"));
                map.put("prix_plat",      rs.getDouble("prix_plat"));
                map.put("total_commande", rs.getInt("total_commande"));
                list.add(map);
            }
        }
        return list;
    }
}
