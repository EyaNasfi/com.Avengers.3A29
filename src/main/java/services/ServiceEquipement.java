package services;

import entities.equipement;  // Import the modified entity class
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceEquipement implements IEquipement<equipement> {  // Rename the service class

    private Connection connection;

    public ServiceEquipement() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(equipement equip) throws SQLException {
        String req = "INSERT INTO equipements (categorie, idsalle) VALUES (?, ?)";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, equip.getCategorie());
        pre.setInt(2, equip.getIdsalle());
        pre.executeUpdate();
    }

    @Override
    public void modifier(equipement equip) throws SQLException {
        String req = "UPDATE equipements SET categorie=?, idsalle=? WHERE idequip=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, equip.getCategorie());
        pre.setInt(2, equip.getIdsalle());
        pre.setInt(3, equip.getIdequip());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(equipement equip) throws SQLException {
        try {
            String req = "DELETE FROM equipements WHERE idequip=?";
            PreparedStatement pre = connection.prepareStatement(req);

            // Add this check to ensure equip is not null
            if (equip != null) {
                pre.setInt(1, equip.getIdequip());
            } else {
                System.out.println("Error: equip is null");
                return;
            }

            pre.executeUpdate();
        } catch (SQLException e) {
            // Print the exception details for debugging
            e.printStackTrace();
            throw e; // Rethrow the exception after printing details
        }
    }

    @Override
    public List<equipement> afficher() throws SQLException {
        String req = "SELECT * FROM equipements";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<equipement> list = new ArrayList<>();
        while (res.next()) {
            equipement e = new equipement();
            e.setIdequip(res.getInt("idequip"));
            e.setCategorie(res.getString("categorie"));
            e.setIdsalle(res.getInt("idsalle"));

            list.add(e);
        }
        System.out.println("list " + list);
        return list;
    }
}
