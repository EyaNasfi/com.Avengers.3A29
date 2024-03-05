package Controller;

import Util.MyDB;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import entity.Remise;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import entity.Paiement;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.controlsfx.control.Notifications;
import services.ServciceRemise;
import services.ServicePaiement;

import java.awt.*;
import java.io.File;
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
    private void formation(ActionEvent event) throws SQLException, IOException {



        // Appelez les méthodes appropriées de ServicePaiement pour effectuer le paiement
        SV.ajouter(new Paiement(Integer.parseInt(Montant.getText()),Mpaiement.getText()));
        showNotification("Payment Added", "Payment has been added successfully!");
        generatePDF(Integer.parseInt(Montant.getText()), Mpaiement.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/affiche.fxml"));
        Parent root = loader.load();
        Stage st=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

        // Mettez à jour l'interface utilisateur ou effectuez d'autres actions nécessaires après le paiement
    }
    /*@FXML
    void remise(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/remise.fxml"));
        Parent root = loader.load();
        Stage st=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }*/

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

    private void showNotification(String title, String message) {
        Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))  // Set the duration for how long the notification should be displayed
                .position(Pos.BOTTOM_RIGHT)
                .show();
    }

//Stripe api
    @FXML
    private void ajouter(){
        try {
            showAlert("Payment successful.");
// Set your secret key here
            Stripe.apiKey = "sk_test_51OpGXoBSHXNDJBCJpZtYJsUSzkGbzkbzeW9KnL1QBitIxv88KMKJwvefU8Uc7F0eWtUqyMqIYxN9h9JG3NFT90nr003COdR8fE";

// Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(1000L) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

// If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
// If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Status");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    private void generatePDF(int montant, String mpaiement) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("Paiement Details");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Montant: " + montant);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Mode de Paiement: " + mpaiement);
                contentStream.endText();
            }

            // Specify the directory where you want to save the PDF file
            String pdfFilePath = "Paiement_" + System.currentTimeMillis() + ".pdf";

            // Save the PDF file
            document.save(pdfFilePath);

            // Open the generated PDF file with the default PDF viewer
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(pdfFilePath));
            } else {
                System.out.println("Desktop is not supported on this platform.");
            }
        }
    }

    public void quiz(ActionEvent event) {
    }

    public void dispo(ActionEvent event) {
    }

    public void event(ActionEvent event) {
    }

    public void profil(ActionEvent event) {
    }

    public void reclama(ActionEvent event) {
    }

    public void salles(ActionEvent event) {
    }
}
