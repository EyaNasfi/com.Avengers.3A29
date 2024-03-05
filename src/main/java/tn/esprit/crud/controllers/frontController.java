package tn.esprit.crud.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tn.esprit.crud.models.User;
import tn.esprit.crud.services.UserService;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import tn.esprit.crud.test.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class frontController implements Initializable {

    private ToggleGroup toggleGroup;
    private RadioButton selectedRadioButton;

    @FXML
    private TextField idMod;

    @FXML
    private TextField nomNouv;

    @FXML
    private TextField prenomNouv;

    @FXML
    private TextField adresseNouv;

    @FXML
    private TextField emailNouv;

    @FXML
    private TextField mdpNouv;

    @FXML
    private Button modifier;

    @FXML
    private RadioButton et;

    @FXML
    private RadioButton fo;

    @FXML
    private RadioButton ad;
    private UserService userService = new UserService();

    @FXML
    void Modification(ActionEvent event) {
        try {
            // Récupérer les données saisies dans les champs de texte

            int id = Integer.parseInt(idMod.getText());
            String nouveauNom = nomNouv.getText();
            String nouveauPrenom = prenomNouv.getText();
            String nouvelleAdresse = adresseNouv.getText();
            String nouveauEmail = emailNouv.getText();
            String nouveauMdp = mdpNouv.getText();


            // Créer un objet User avec les nouvelles valeurs
            User user = new User(id, nouveauNom, nouveauPrenom, nouvelleAdresse, nouveauEmail, nouveauMdp,selectedRadioButton.getText()  );

            // Mettre à jour l'utilisateur dans la base de données
            userService.modifier(user);

            // Afficher un message de confirmation
            afficherMessage("Succès", "L'utilisateur a été modifié avec succès.");
        } catch (NumberFormatException e) {
            afficherErreur("Erreur", "Veuillez saisir un ID valide.");
        } catch (SQLException e) {
            afficherErreur("Erreur", "Erreur lors de la modification de l'utilisateur : " + e.getMessage());
        }
    }


    @FXML
    void VersSupprimer(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/crud/SupprimerUser.fxml"));
        try {
            idMod.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
    private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
    private void afficherMessage(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroup = new ToggleGroup();
        et.setToggleGroup(toggleGroup);
        fo.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedRadioButton = (RadioButton) newValue;
        });

    }
}