package tn.esprit.crud.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.crud.models.User;
import tn.esprit.crud.services.UserService;
import tn.esprit.crud.test.HelloApplication;

import java.io.IOException;
import java.sql.SQLException;

public class AfficherUsers {


    private ToggleGroup toggleGroup;
    private RadioButton selectedRadioButton;


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
    private RadioButton et;

    @FXML
    private RadioButton fo;

    @FXML
    private RadioButton ad;

        @FXML
        private Button auth;

        @FXML
        private ListView<User> affiche;
    UserService userService = new UserService();

    @FXML
    private TextField roleTF;


    @FXML
    void auth(ActionEvent event) {
        System.out.println("btn");
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn/esprit/crud/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


public void affichage () throws SQLException {
    ObservableList<User> us = userService.recupperer();
    affiche.setItems(us);
    affiche.getItems();
}
    @FXML
    void PageSupprimer(ActionEvent event) throws SQLException {
affiche.getSelectionModel().getSelectedItem();
userService.supprimer(affiche.getSelectionModel().getSelectedItem().getId());
        affichage();      }


    @FXML
    void PageModifier(ActionEvent event) throws IOException, SQLException {
        toggleGroup = new ToggleGroup();
        et.setToggleGroup(toggleGroup);
        fo.setToggleGroup(toggleGroup);
        ad.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedRadioButton = (RadioButton) newValue;


        });

        nomNouv.setVisible(true);
        prenomNouv.setVisible(true);
        emailNouv.setVisible(true);
        adresseNouv.setVisible(true);
        mdpNouv.setVisible(true);
        et.setVisible(true);
        fo.setVisible(true);
        ad.setVisible(true);
        String nouveauNom = nomNouv.getText();
        String nouveauPrenom = prenomNouv.getText();
        String nouvelleAdresse = adresseNouv.getText();
        String nouveauEmail = emailNouv.getText();
        String nouveauMdp = mdpNouv.getText();

int k=affiche.getSelectionModel().getSelectedItem().getId();

        // Créer un objet User avec les nouvelles valeurs
        User user = new User(k, nouveauNom, nouveauPrenom, nouvelleAdresse, nouveauEmail, nouveauMdp);

        // Mettre à jour l'utilisateur dans la base de données
        userService.modifier(user);
affichage();
        // Afficher un message de confirmation

}

    @FXML
    void ReturnToAjouter(ActionEvent event) throws SQLException {
        nomNouv.setVisible(true);
        prenomNouv.setVisible(true);
        emailNouv.setVisible(true);
        adresseNouv.setVisible(true);
        mdpNouv.setVisible(true);
        et.setVisible(true);
        fo.setVisible(true);
        ad.setVisible(true);
        ///FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/tn/esprit/crud/AjouterUser.fxml"));
        User user = new User();
        user.setNom(nomNouv.getText());
        user.setPrenom(prenomNouv.getText());
        user.setAdresse(adresseNouv.getText());
        user.setEmail(emailNouv.getText());
        user.setMdp(mdpNouv.getText());
        user.setrole(selectedRadioButton.getText());
        try {
            userService.ajouter(user);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Success");
            alert.setContentText("Personne Ajouter");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            throw new RuntimeException(e);
        }
        if(et.isSelected()){
            userService.ajouter(new User(nomNouv.getText(),prenomNouv.getText(),adresseNouv.getText(),emailNouv.getText(),mdpNouv.getText(),et.getText()));}

        if(fo.isSelected()){
            userService.ajouter(new User(nomNouv.getText(),prenomNouv.getText(),adresseNouv.getText(),emailNouv.getText(),mdpNouv.getText(),fo.getText()));}

        if(ad.isSelected()){
            userService.ajouter(new User(nomNouv.getText(),prenomNouv.getText(),adresseNouv.getText(),emailNouv.getText(),mdpNouv.getText(),ad.getText()));}
affichage();


    }

    public void selecter(){
        User u =affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
        nomNouv.setText(u.getNom());
        prenomNouv.setText(u.getPrenom());
        emailNouv.setText(u.getEmail());
        adresseNouv.setText(u.getAdresse());
        mdpNouv.setText(u.getMdp());


    }

    @FXML
    void initialize() {affiche.setOnMouseClicked(event -> {
        selecter();
    } );
nomNouv.setVisible(false);
prenomNouv.setVisible(false);
emailNouv.setVisible(false);
adresseNouv.setVisible(false);
mdpNouv.setVisible(false);
et.setVisible(false);
fo.setVisible(false);
ad.setVisible(false);
        try {
            affichage();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
