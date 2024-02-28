package services;

import entities.salleee;
import utils.MyDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceSalle2 implements ISalle<salleee> {

    private Connection connection;

    public ServiceSalle2() {
        connection = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(salleee salle) throws SQLException {
        try {
            // Check if the user with the provided ID exists in the user table
            if (!userExists(salle.getIduser())) {
                System.out.println("Error: User with ID " + salle.getIduser() + " does not exist.");
                return;
            }

            String req = "INSERT INTO salle (matiere, iduser) VALUES (?, ?)";
            PreparedStatement pre = connection.prepareStatement(req);
            pre.setString(1, salle.getMatiere());
            pre.setInt(2, salle.getIduser());
            pre.executeUpdate();

            System.out.println("Salle added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());

            // Handle the exception according to your needs
            if (e.getSQLState().equals("23000") && e.getErrorCode() == 1452) {
                System.out.println("Foreign Key Constraint Violation: The provided ID User does not exist in the User table.");
            } else {
                System.out.println("Error: An error occurred while adding the Salle. See console for details.");
            }

            throw e; // Rethrow the exception after printing details
        }
    }

    // Helper method to check if a user with the given ID exists
    private boolean userExists(int userId) throws SQLException {
        String query = "SELECT * FROM user WHERE iduser = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if user with given ID exists
            }
        }
    }


    @Override
    public void modifier(salleee salle) throws SQLException {
        String req = "UPDATE salle SET matiere=?, iduser=? WHERE idsalle=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setString(1, salle.getMatiere());
        pre.setInt(2, salle.getIduser());
        pre.setInt(3, salle.getIdsalle());
        pre.executeUpdate();
    }

    @Override
    public void supprimer(salleee salle) throws SQLException {
        try {
            String req = "DELETE FROM salle WHERE idsalle=?";
            PreparedStatement pre = connection.prepareStatement(req);

            // Add this check to ensure salle is not null
            if (salle != null) {
                pre.setInt(1, salle.getIdsalle());
            } else {
                System.out.println("Error: salle is null");
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
    public List<salleee> afficher() throws SQLException {
        String req = "SELECT * FROM salle";
        Statement ste = connection.createStatement();
        ResultSet res = ste.executeQuery(req);
        List<salleee> list = new ArrayList<>();
        while (res.next()) {
            salleee s = new salleee();
            s.setIdsalle(res.getInt("idsalle"));
            s.setMatiere(res.getString("matiere"));
            s.setIduser(res.getInt("iduser"));

            list.add(s);
        }
        System.out.println("list " + list);
        return list;
    }
}
