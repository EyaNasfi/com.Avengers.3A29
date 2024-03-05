package Controller;

import entity.Paiement;
import entity.Remise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ServciceRemise;
import services.ServicePaiement;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemiseManagment {
    @FXML
    private TextField montant;
    @FXML
    private Button gotoemail;

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
        Premise.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                // Calculate the new amount based on the percentage
                double percentage = Double.parseDouble(newValue);
                double montantValue = Double.parseDouble(montant.getText());
                double newAmount = (percentage / 100) * montantValue;


                int newAmountInteger = (int) newAmount;
                // Update the Maprèsp TextField with the new amount
                Maprèsp.setText(String.valueOf(newAmountInteger));
            }
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

    @FXML
    private void handleGoToEmail(ActionEvent event) {
        try {
            // Load the FXML file for the email page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/email.fxml"));
            Parent root = loader.load();

            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Email Page");

            // Set the scene with the email page content
            Scene scene = new Scene(root);
            stage.setScene(scene);

            // Show the stage
            stage.show();

            // Close the current stage (optional)
            Stage currentStage = (Stage) gotoemail.getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }


    public void quiz(ActionEvent event) {
    }

    public void reclama(ActionEvent event) {
    }

    public void event(ActionEvent event) {
    }

    public void formations(ActionEvent event) {
    }

    public void cours(ActionEvent event) {
    }



    public void salle(ActionEvent event) {
    }

    public void club(ActionEvent event) {
    }

    public void equipement(ActionEvent event) {
    }

    public void verspageadus(ActionEvent event) {
    }

    public void remise(ActionEvent event) {
    }
}
