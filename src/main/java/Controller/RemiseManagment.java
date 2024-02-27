package Controller;

import entity.Paiement;
import entity.Remise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import services.ServciceRemise;
import services.ServicePaiement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemiseManagment {

    @FXML
    private Button Ajouter;

    @FXML
    private TextField Maprèsp;

    @FXML
    private TextField Premise;


    @FXML
    private ListView<Remise> afficher;

    ServciceRemise rs=new ServciceRemise();
    private void selection(){
        Remise p=afficher.getItems().get(afficher.getSelectionModel().getSelectedIndex());
        Maprèsp.setText(String.valueOf(p.getMontantaprespourcentage()));
        Premise.setText(String.valueOf(p.getPourcentage()));
    }
    @FXML
    void initialize() throws SQLException { afficher.setOnMouseClicked(
            event -> {
                selection();
            });

        afficherr();


    }


    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        int i =Integer.parseInt(Premise.getText());
        int j=Integer.parseInt(Maprèsp.getText());
        // Appelez les méthodes appropriées de ServicePaiement pour effectuer le paiement
        rs.ajouter(new Remise(i,j));
        afficherr();

    }
    private void afficherr() throws SQLException {
        afficher.setItems(rs.afficher());
    }

    @FXML
    void modifier(ActionEvent event) throws SQLException {
        Remise p= afficher.getSelectionModel().getSelectedItem();
        int i= Integer.parseInt(Maprèsp.getText());
        int mo= Integer.parseInt(Premise.getText());
        System.out.println(p.getIdremise());
        System.out.println(i);
        System.out.println(mo);
        Remise po=new Remise(mo,i);
        rs.modifier(po,p.getIdremise());
        afficherr();
    }



    public void supp(ActionEvent actionEvent) throws SQLException {
        Remise p=afficher.getSelectionModel().getSelectedItem();
        System.out.println(p.getIdpaiement());
        rs.supprimer(p.getIdpaiement());
        afficherr();
        afficher.refresh();
    }
}
