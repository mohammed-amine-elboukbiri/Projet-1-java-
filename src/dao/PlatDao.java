package dao;

import dao.Connexion;
import model.Plat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class PlatDao implements Idao<Plat> {

    @Override
    public Plat findById(int id) throws Exception {
        String sql = "SELECT * FROM plat WHERE id_plat=?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Plat(
                            rs.getInt("id_plat"),
                            rs.getString("nom_plat"),
                            rs.getString("categorie_plat"),
                            rs.getDouble("prix_plat")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Plat> findAll() throws Exception {
        String sql = "SELECT * FROM plat";
        List<Plat> list = new ArrayList<>();
        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                list.add(new Plat(
                        rs.getInt("id_plat"),
                        rs.getString("nom_plat"),
                        rs.getString("categorie_plat"),
                        rs.getDouble("prix_plat")
                ));
            }
        }
        return list;
    }

    @Override
    public int insert(Plat p) throws Exception {
        String sql = "INSERT INTO plat(nom_plat, categorie_plat, prix_plat) VALUES(?, ?, ?)";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNom_plat());
            ps.setString(2, p.getCategorie_plat());
            ps.setDouble(3, p.getPrix_plat());

            int rows = ps.executeUpdate();

            if (rows == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        p.setId_plat(id);
                        return id;
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public boolean update(Plat p) throws Exception {
        String sql = "UPDATE plat SET nom_plat=?, categorie_plat=?, prix_plat=? WHERE id_plat=?";
        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getNom_plat());
            ps.setString(2, p.getCategorie_plat());
            ps.setDouble(3, p.getPrix_plat());
            ps.setInt(4, p.getId_plat());

            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM plat WHERE id_plat=?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }

    public List<Plat> filtrageCategorie(String categorie) throws Exception {
        String sql = "SELECT * FROM plat WHERE categorie_plat=?";
        List<Plat> list = new ArrayList<>();

        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, categorie);

            try(ResultSet rs = ps.executeQuery()) {
                while(rs.next()) {
                    list.add(new Plat(
                            rs.getInt("id_plat"),
                            rs.getString("nom_plat"),
                            rs.getString("categorie_plat"),
                            rs.getDouble("prix_plat")
                    ));
                }
            }
        }
        return list;
    }
}
