package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.formation;
import services.serviceFormation;

public class AjouterFormation {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker DD;

    @FXML
    private DatePicker DF;

    @FXML
    private TextField cout;

    @FXML
    private TextField description;

    @FXML
    private TextField duree;

    @FXML
    private TextField niveau;

    @FXML
    private TextField nomc;

    @FXML
    private TextField nomf;
    private final serviceFormation FS= new serviceFormation();

    private Date convertToUtilDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    @FXML
    void ajouter(ActionEvent event) throws SQLException {
        if (nomc.getText().isEmpty() || nomf.getText().isEmpty() || description.getText().isEmpty() || duree.getText().isEmpty()
                || niveau.getText().isEmpty()  || DD.getValue() == null || DF.getValue() == null|| cout.getText().isEmpty()) {
            // Display error message to user
            System.out.println("Please fill in all fields");
            return;
        }

        // Convert date pickers to Date objects
        LocalDate startDate = DD.getValue();
        LocalDate endDate = DF.getValue();

        // Check if the selected dates are from today onwards
        LocalDate today = LocalDate.now();
        if (startDate.isBefore(today) || endDate.isBefore(today)) {
            // Display error message to user
            // Display success message to user
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Wrong date");
            successAlert.setHeaderText("Please select dates from today onwards");
            successAlert.showAndWait();
            return;
        }

        // Check if the start date is after the end date
        if (startDate.isAfter(endDate)) {
            // Display error message to user
            System.out.println("Start date cannot be after end date");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Wrong date");
            successAlert.setHeaderText("Start date cannot be after end date");
            successAlert.showAndWait();
            return;
        }

        Date dateDebut = convertToUtilDate(startDate);
        Date dateFin = convertToUtilDate(endDate);

        // Validate the durationText and Prix fields


            FS.ajouter(new formation(
                    nomc.getText(),
                    nomf.getText(),
                    description.getText(),
                    duree.getText(),
                    niveau.getText(),
                    dateDebut,
                    dateFin,
                    cout.getText()
            ));

            // Display success message to user
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText("Formation added successfully");
            successAlert.showAndWait();


    }


    @FXML
    void aficher(ActionEvent event) {

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/AfficherFormation.fxml"));
        try {
            Parent root = loader1.load();
            AfficherFormation controller = loader1.getController();
            nomc.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void initialize() {
        assert DD != null : "fx:id=\"DD\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert DF != null : "fx:id=\"DF\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert cout != null : "fx:id=\"cout\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert description != null : "fx:id=\"description\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert duree != null : "fx:id=\"duree\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert niveau != null : "fx:id=\"niveau\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert nomc != null : "fx:id=\"nomc\" was not injected: check your FXML file 'AjouterFormation.fxml'.";
        assert nomf != null : "fx:id=\"nomf\" was not injected: check your FXML file 'AjouterFormation.fxml'.";

    }

}
