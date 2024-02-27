package Controller;

import entity.Paiement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServicePaiement;

import java.io.IOException;
import java.sql.SQLException;

public class affichage {
    @FXML
    private ListView<Paiement> afficher;
    @FXML
    private TextField mode;

    @FXML
    private TextField montant;

    @FXML
    void modif(ActionEvent event) throws SQLException {
        Paiement p= afficher.getSelectionModel().getSelectedItem();
        String mod=mode.getText();
        int mo= Integer.parseInt(montant.getText());
        Paiement po=new Paiement(mod,mo);
        rp.modifier(po,p.getIdpaiement());
        afficherr();
    }
    ServicePaiement rp =new ServicePaiement();
    @FXML
    void initialize() throws SQLException {
        afficherr();
    }
    public void afficherr() throws SQLException {
        afficher.setItems(rp.afficher());
    }

    @FXML
    void supp(ActionEvent event) throws IOException, SQLException {
        Paiement p=afficher.getSelectionModel().getSelectedItem();
        System.out.println(p.getIdpaiement());
        rp.supprimer(p.getIdpaiement());
        afficherr();
        afficher.refresh();
    }
    public void  selection() throws SQLException {
        Paiement p=afficher.getItems().get(afficher.getSelectionModel().getSelectedIndex());
        mode.setText(p.getModepaiement());
        montant.setText(String.valueOf(p.getMontant()));

    }
}