package tn.esprit.crud.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.crud.models.User;
import tn.esprit.crud.utils.MyDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IServices<User> {

    private Connection connection;
    private Statement ste;



    public UserService(){
        connection = MyDatabase.getInstance().getConnection();
    }


    @Override
    public void ajouter(User user) throws SQLException {
        String req = "INSERT INTO user(nom , prenom , adresse , email , mdp,role) VALUES( '" + user.getNom() + "' , '" + user.getPrenom() + "' , '" + user.getAdresse() + "' , '" + user.getEmail() + "' , '" + user.getMdp() + "','" + user.getrole() + "')";
        Statement st = connection.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(User user) throws SQLException {
        String req = "UPDATE user SET nom = ?, prenom = ?, adresse = ?, email = ? , mdp = ? WHERE id = ?";
        PreparedStatement us = connection.prepareStatement(req);
        us.setString(1, user.getNom());
        us.setString(2, user.getPrenom());
        us.setString(3, user.getAdresse());
        us.setString(4, user.getEmail());
        us.setString(5, user.getMdp());
        us.setInt(6, user.getId());
        us.executeUpdate();
    }

    @Override
    public void supprimer(User user) {

    }

    @Override
    public void supprimer(int id) throws SQLException {

        String req = "DELETE FROM user WHERE id = ? ";
        PreparedStatement us = connection.prepareStatement(req);
        us.setInt(1, id);
        us.executeUpdate();
    }


    @Override
    public ObservableList<User> recupperer() throws SQLException {
        ObservableList<User> users = FXCollections.observableArrayList();
        String req = "SELECT * FROM user";
        PreparedStatement us = connection.prepareStatement(req);
        ResultSet rs = us.executeQuery(req);


        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNom(rs.getString("nom"));
            user.setPrenom(rs.getString("prenom"));
            user.setAdresse(rs.getString("adresse"));
            user.setEmail(rs.getString("email"));
            user.setMdp(rs.getString("mdp"));
            user.setrole(rs.getString("role"));

            users.add(user);

        }
        return users;


    }

    public boolean authenticateUser(String email, String pass) {
        String query = "SELECT * FROM user WHERE email = ? AND mdp = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, pass);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String role(int id) {
        try {

            PreparedStatement stmt1 = connection.prepareStatement("SELECT role FROM user where id=?");
            stmt1.setInt(1, id);

            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                return rs.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "not found";
    }

    public List<User> displayAll() {
        String requete="select * from utilisateur";
        List<User> list=new ArrayList<>();

        try {
            ste=connection.createStatement();
            ResultSet et=ste.executeQuery(requete);
            while (et.next()){
                list.add(new User(et.getInt("id"),et.getString("nom"),et.getString("prenom"),et.getString("adresse"),et.getString("email"),et.getString("mdp"),et.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public User getUserByEmail(String email) throws SQLException {

        String req = "SELECT * FROM user WHERE email = ?";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setString(1, email);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            User p = new User(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("adresse"),
                    rs.getString("mdp"),rs.getString("role"));
            return p;
        } else {
            return null; // no person found with this id
        }
    }
    public int getIdByEmail(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int userId = -1; // Default value if user is not found or there's an error

        try {
            conn = MyDatabase.getInstance().getConnection();
            String query = "SELECT id FROM users WHERE email = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("id");
            }
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        } finally {
            // Close resources
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return userId;
    }
    public int idg(String email) {
        try {

            PreparedStatement stmt1 = connection.prepareStatement("SELECT id FROM user where email=?");
            stmt1.setString(1, email);

            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String emailg(int id) {
        try {

            PreparedStatement stmt1 = connection.prepareStatement("SELECT email FROM user where id=?");
            stmt1.setInt(1, id);

            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) {
                return rs.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
