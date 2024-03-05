package tn.esprit.crud.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import tn.esprit.crud.models.User;
import tn.esprit.crud.services.UserService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class inscritController implements Initializable {
    private ToggleGroup toggleGroup;
    private RadioButton selectedRadioButton;


    private UserService userService=new UserService();

    @FXML
    private TextField nomTF;

    @FXML
    private TextField prenomTF;

    @FXML
    private TextField adresseTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField mdpTF;

    @FXML
    private RadioButton et;

    @FXML
    private RadioButton fo;
    @FXML
    private TextField roleTF;

    @FXML
    void ajouterUser(ActionEvent event) throws SQLException {
        UserService userService = new UserService();
        User user = new User();
        user.setNom(nomTF.getText());
        user.setPrenom(prenomTF.getText());
        user.setAdresse(adresseTF.getText());
        user.setEmail(emailTF.getText());
        user.setMdp(mdpTF.getText());
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
            userService.ajouter(new User(nomTF.getText(),prenomTF.getText(),adresseTF.getText(),emailTF.getText(),mdpTF.getText(),roleTF.getText()));}

        if(fo.isSelected()){
            userService.ajouter(new User(nomTF.getText(),prenomTF.getText(),emailTF.getText(),mdpTF.getText(),mdpTF.getText(),roleTF.getText()));}

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toggleGroup = new ToggleGroup();
        et.setToggleGroup(toggleGroup);
        fo.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            selectedRadioButton = (RadioButton) newValue;
        });
    }
}
