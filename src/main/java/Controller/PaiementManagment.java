package Controller;

import Util.MyDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entity.Paiement;
import javafx.stage.Stage;
import services.ServicePaiement;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaiementManagment {

    @FXML
    private TextField Montant;

    @FXML
    private TextField Mpaiement;
    @FXML
    private TextField Nformation;

    @FXML
    private TextField Iduser;
    @FXML
    private TextField numérodelacarte;

    @FXML
    private Button payButton;

    // Inject the instance of ServicePaiement
    // Assuming you have a method to get the database connection
    ServicePaiement SV = new ServicePaiement();


    @FXML
    private void ajouter(ActionEvent event) throws SQLException, IOException {



        // Appelez les méthodes appropriées de ServicePaiement pour effectuer le paiement
        SV.ajouter(new Paiement(Integer.parseInt(Montant.getText()),Mpaiement.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/affiche.fxml"));
        Parent root = loader.load();
        Stage st=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

        // Mettez à jour l'interface utilisateur ou effectuez d'autres actions nécessaires après le paiement
    }
    @FXML
    void remise(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/remise.fxml"));
        Parent root = loader.load();
        Stage st=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    // Méthode d'assistance pour afficher une alerte
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void initialize() {
        numérodelacarte.setOnKeyTyped(event -> {
            char inputChar = event.getCharacter().charAt(0);
            if (!Character.isDigit(inputChar)) {
                event.consume(); // Consume the event if it's not a digit
            }
        });

        // Add event handler for focusLost to validate the length
        numérodelacarte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                validateCardNumber();
            }
        });
    }

    private void validateCardNumber() {
        String cardNumber = numérodelacarte.getText();
        if (!cardNumber.matches("\\d{9,}")) {
            showErrorDialog("Invalid card number. It should be numeric and at least 9 digits.");
            // You might also choose to clear the field or take other actions based on your requirements.
        }
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
