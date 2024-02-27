package services;

import Util.MyDB;
import entity.Paiement;
import entity.Remise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServciceRemise implements IPaiement<Remise> {
    private  Connection connection;

    public ServciceRemise() {
        connection= MyDB.getCon();
    }

    @Override
    public void ajouter(Remise remise) throws SQLException {
        String req = "INSERT INTO remise (idpaiement,iduser,pourcentage, montantapresremise) VALUES (1,1,?, ?)";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            //pre.setInt(1, paiement.getIDformation());
            //pre.setInt(2, paiement.getIduser());
            pre.setInt(1, remise.getPourcentage());
            pre.setInt(2, remise.getMontantaprespourcentage());
            pre.executeUpdate();
            System.out.println("Paiement added successfully!");
        }
    }

    @Override
    public void modifier(Remise remise, int id) throws SQLException {
        String req = "UPDATE remise SET idpaiement=?, iduser=?, pourcentage=?, montantapresremise=? WHERE idremise=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, 1);
            pre.setInt(2,1);
            pre.setInt(3, remise.getPourcentage());
            pre.setInt(4, remise.getMontantaprespourcentage());
            pre.setInt(5, id);
            pre.executeUpdate();
            System.out.println("Paiement updated successfully!");
        }
    }

    @Override
    public void supprimer(int idpaiement) throws SQLException {
        String req = "DELETE FROM remise WHERE idremise=?";
        try (PreparedStatement pre = connection.prepareStatement(req)) {
            pre.setInt(1, idpaiement);
            pre.executeUpdate();
            System.out.println("Paiement deleted successfully!");
        }
    }

    @Override
    public ObservableList<Remise> afficher() throws SQLException {
        String req = "SELECT * FROM remise WHERE iduser=1 AND idpaiement=1";
        ObservableList<Remise> list = FXCollections.observableArrayList();
        PreparedStatement pre= connection.prepareStatement(req);
        ResultSet res = pre.executeQuery();

        while (res.next()) {
            Remise r = new Remise();
            r.setIdremise(res.getInt("idremise"));
            r.setIdpaiement( res.getInt(1));
            r.setIduser( res.getInt(1));
            r.setPourcentage(res.getInt("pourcentage"));
            r.setMontantaprespourcentage(res.getInt("montantapresremise"));
            list.add(r);
        }

        return list ;
    }
}