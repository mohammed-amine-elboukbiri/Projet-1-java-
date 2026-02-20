package dao;

import dao.Connexion;
import model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;


public class ClientDao implements Idao<Client> {

    @Override
    public Client findById(int id) throws Exception {
        String sql = "SELECT * FROM client WHERE id_client=?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                    rs.getInt("id_client"),
                    rs.getString("nom_client"),
                    rs.getString("telephone_client")
                    );
                }
                return null;
            }
        }
    }

    @Override
    public List<Client> findAll() throws Exception {
        String sql = "SELECT * FROM client";
        List<Client> list = new ArrayList<>();
        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while(rs.next()) {
                list.add(new Client(
                        rs.getInt("id_client"),
                        rs.getString("nom_client"),
                        rs.getString("telephone_client")
                ));
            }
        }
        return list;
    }

    @Override
    public int insert(Client c) throws Exception {
        String sql = "INSERT INTO client(nom_client, telephone_client) VALUES(?, ?)";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNom_client());
            ps.setString(2, c.getTelephone_client());
            int rows = ps.executeUpdate();
            if (rows == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        c.setId_client(id);
                        return id;
                    }
                }
            }
            return -1;
        }
    }

    @Override
    public boolean update(Client c) throws Exception {
        String sql = "UPDATE client SET nom_client=?, telephone_client=? WHERE id_client=?";
        try(PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, c.getNom_client());
            ps.setString(2, c.getTelephone_client());
            ps.setInt(3, c.getId_client());

            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean delete(int id) throws Exception {
        String sql = "DELETE FROM client WHERE id_client=?";
        try (PreparedStatement ps = Connexion.getInstance().getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }
}
