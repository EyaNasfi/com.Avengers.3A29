package services;

import entity.Paiement;
import Util.MyDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePaiement  implements IPaiement<Paiement>{
    private Connection connection;

    public ServicePaiement() {
        connection = MyDB.getCon();
    }


    public void ajouter(Paiement paiement) throws SQLException {
        String req = "INSERT INTO paiement (IDformation,iduser,montant, modepaiement) VALUES (1,1,?, ?)";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            //pre.setInt(1, paiement.getIDformation());
            //pre.setInt(2, paiement.getIduser());
            pre.setInt(1, paiement.getMontant());
            pre.setString(2, paiement.getModepaiement());
            pre.executeUpdate();
            System.out.println("Paiement added successfully!");
        }
    }

    @Override
    public void modifier(Paiement paiement,int id) throws SQLException {
        String req = "UPDATE paiement SET IDformation=?, iduser=?, montant=?, modepaiement=? WHERE idpaiement=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, 1);
            pre.setInt(2,1);
            pre.setInt(3, paiement.getMontant());
            pre.setString(4, paiement.getModepaiement());
            pre.setInt(5, id);
            pre.executeUpdate();
            System.out.println("Paiement updated successfully!");
        }
    }
    public void supprimer(int idpaiement) throws SQLException {
        String req = "DELETE FROM paiement WHERE idpaiement=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idpaiement);
            pre.executeUpdate();
            System.out.println("Paiement deleted successfully!");
        }
    }

    public ObservableList<Paiement> afficher() throws SQLException {
        String req = "SELECT * FROM paiement WHERE iduser=1 AND IDformation=1";
        ObservableList<Paiement> list = FXCollections.observableArrayList();
        PreparedStatement pre= connection.prepareStatement(req);
        ResultSet res = pre.executeQuery();

        while (res.next()) {
            Paiement paiement = new Paiement();
            paiement.setIdpaiement(res.getInt("idpaiement"));
            // res.getInt("IDformation"),
            // res.getInt("iduser"),
            paiement.setMontant( res.getInt("montant"));
            paiement.setModepaiement( res.getString("modepaiement"));
            list.add(paiement);


        }

        return list ;
    }
}
