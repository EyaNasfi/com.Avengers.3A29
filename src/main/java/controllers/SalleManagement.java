package Controllers;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javax.mail.Message;

import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;

import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import Services.ServiceSalle2;
import entities.salleee;

import java.io.IOException;
import javax.mail.Authenticator;

import java.sql.SQLException;
import java.util.Properties;

import javafx.scene.control.ListView;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

public class SalleManagement {

    @FXML
    private TextField userEmail; // Add email TextField
    @FXML
    private TextField matiere;
    @FXML
    private PasswordField password;

    @FXML
    private TextField iduser;  // Changer de PasswordField à TextField pour iduser

    @FXML
    private ListView<String> sallelistview;

    private ServiceSalle2 SS = new ServiceSalle2();

    private salleee selectedSalle; // Store the selected salle
    @FXML
    private TextField searchTextField;

    public Button afficherDB;
    public ImageView photo;
    @FXML
    void initialize(URL location, ResourceBundle resources) {
        // Set a custom cell factory to display salleee objects in a more organized way
        sallelistview.setCellFactory(param -> new javafx.scene.control.ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    // Assuming your salleee objects have a toString method
                    setText(item);
                }
            }
        });
    }

    @FXML
    void afficherDB() {
    }



    @FXML
    void ajouter() {

    }

    @FXML
    void club() {

    }

    @FXML
    void cours() {

    }

    @FXML
    void equipement() {

    }

    @FXML
    void event() {

    }

    @FXML
    void formations() {

    }

    @FXML
    void modifier() {

    }

    @FXML
    void quiz() {

    }

    @FXML
    void reclama() {

    }

    @FXML
    void remise() {

    }

    @FXML
    void salles() {

    }

    @FXML
    void search() {

    }



    @FXML
    void supprimer( ) {

    }

    @FXML
    void verspageadus( ) {

    }


    @FXML
    void equipement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipementManagement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

    }


    @FXML
    void modifier(ActionEvent event) {
        // Get the selected salleee object from the ListView
        String selectedItem = sallelistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the idsalle from the selected item
            int idsalle = parseIdFromSelectedItem(selectedItem);

            // Check if idsalle is a valid integer
            if (idsalle != -1) {
                try {
                    // Find the salleee from the list using the idsalle
                    salleee salleeeToModify = findSalleById(idsalle);

                    if (salleeeToModify != null) {
                        // Retrieve the updated values from the TextFields
                        String newMatiere = matiere.getText();
                        String newIduserText = iduser.getText();

                        // Check if iduser is not empty
                        if (newIduserText.isEmpty()) {
                            showAlert("ID User Empty", "Please enter a valid ID User.");
                            return; // Exit the method if iduser is empty
                        }

                        // Parse iduser as int
                        int newIduserValue;
                        try {
                            newIduserValue = Integer.parseInt(newIduserText);
                        } catch (NumberFormatException e) {
                            showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
                            return; // Exit the method if iduser is not a valid integer
                        }

                        // Update the salleee object with new values
                        salleeeToModify.setMatiere(newMatiere);
                        salleeeToModify.setIduser(newIduserValue);

                        // Call the modifier method in ServiceSalle to update the database
                        SS.modifier(salleeeToModify);

                        // Refresh the ListView
                        afficherDB(event);
                    } else {
                        System.out.println("Salleee not found for idsalle: " + idsalle);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid idsalle");
            }
        } else {
            System.out.println("No item selected.");
        }
    }
/*
    @FXML
   void ajouter(ActionEvent event) {
       // Check if iduser is injected
       if (iduser == null) {
           showAlert("ID User Field Missing", "ID User field is not injected.");
           return;
       }

       // Retrieve values from the TextFields
       String matiereValue = matiere.getText();
       String iduserValueText = iduser.getText();

       // Check if iduser is not empty
       if (iduserValueText.isEmpty()) {
           showAlert("ID User Empty", "Please enter a valid ID User.");
           return; // Exit the method if iduser is empty
       }

       // Parse iduser as int
       int iduserValue;
       try {
           iduserValue = Integer.parseInt(iduserValueText);
       } catch (NumberFormatException e) {
           showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
           return; // Exit the method if iduser is not a valid integer
       }

       // Create a new salleee object
       salleee nouvelleSalle = new salleee(0, iduserValue, matiereValue); // 0 is a placeholder for idsalle

       try {
           // Add the new salleee to the database using ServiceSalle
           SS.ajouter(nouvelleSalle);

           // You may add additional logic here based on your requirements

       }  catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        System.out.println("Message: " + e.getMessage());
        // Handle the exception according to your needs
    }


    // Handle the exception according to your needs
       }


*/
private void sendEmailWithQRCode(String recipientEmail, BufferedImage qrCodeImage) {
    // Email configuration
    String host = "smtp.office365.com";
    String port = "587";
    String username = "cherif.benhassine@esprit.tn";
    String password = "Achraf2006+";

    Properties properties = new Properties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");

    // Session for email
    Session session = Session.getInstance(properties, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        // Create a MIME message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("QR Code for salleee Information");

        // Create the message body
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Please find attached QR Code for salleee information.");

        // Create the attachment with the QR code
        BodyPart attachmentBodyPart = new MimeBodyPart();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrCodeImage, "png", baos);
        byte[] imageData = baos.toByteArray();
        DataSource source = new ByteArrayDataSource(imageData, "image/png");
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName("QRCode.png");

        // Add the parts to the message body
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        // Set the message body
        message.setContent(multipart);

        // Send the message
        Transport.send(message);

        System.out.println("Email sent successfully with QR Code attachment!");

    } catch (MessagingException | IOException e) {
        e.printStackTrace();
        // Handle the exception as needed
    }
}

//this is ajouter
/*
    @FXML
void ajouter(ActionEvent event) {
    if (iduser == null ) {
        showAlert("ID User or Password Field Missing", "ID User or Password field is not injected.");
        return;
    }

    String matiereValue = matiere.getText();
    String iduserValueText = iduser.getText();
    String userEmailValue = userEmail.getText();


    if (iduserValueText.isEmpty()) {
        showAlert("ID User or  Empty", "Please enter valid ID User .");
        return;
    }

    int iduserValue;
    try {
        iduserValue = Integer.parseInt(iduserValueText);
    } catch (NumberFormatException e) {
        showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
        return;
    }

    if (!isValidMatiere(matiereValue)) {
        showAlert("Invalid Matiere", "Please enter a valid matiere (java, web, marketing, design).");
        return;
    }

    salleee nouvelleSalle = new salleee(0, iduserValue, matiereValue, userEmailValue);

    try {
        SS.ajouter(nouvelleSalle);
        sendEmailWithQRCode(userEmailValue);

    } catch (SQLException e) {
        handleSQLException(e);
    }
} */
@FXML
void ajouter(ActionEvent event) {
    if (iduser == null) {
        showAlert("ID User or Password Field Missing", "ID User or Password field is not injected.");
        return;
    }

    String matiereValue = matiere.getText();
    String iduserValueText = iduser.getText();
    String userEmailValue = userEmail.getText();

    if (iduserValueText.isEmpty()) {
        showAlert("ID User or Empty", "Please enter a valid ID User.");
        return;
    }

    int iduserValue;
    try {
        iduserValue = Integer.parseInt(iduserValueText);
    } catch (NumberFormatException e) {
        showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
        return;
    }

    if (!isValidMatiere(matiereValue)) {
        showAlert("Invalid Matiere", "Please enter a valid matiere (java, web, marketing, design).");
        return;
    }

    salleee nouvelleSalle = new salleee(0, iduserValue, matiereValue, userEmailValue);

    try {
        SS.ajouter(nouvelleSalle);

        // Generate QR Code and get BufferedImage
        BufferedImage qrCodeImage = generateQRCodeImage(nouvelleSalle.toString());

        // Send email with QR Code
        sendEmailWithQRCode(userEmailValue, qrCodeImage);

    } catch (SQLException e) {
        handleSQLException(e);
    }
}
    private BufferedImage generateQRCodeImage(String text) {
        try {
            // Create a QR Code writer
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Set QR Code parameters
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

            // Create a BufferedImage from the BitMatrix
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
            return null;
        }
    }


    private void handleSQLException(SQLException e) {
    }

    @FXML

    // Helper method to validate matiere

    private boolean isValidMatiere(String matiere) {
        String[] validMatieres = {"java", "web", "marketing", "design"};

        for (String validMatiere : validMatieres) {
            if (validMatiere.equalsIgnoreCase(matiere)) {
                return true;
            }
        }

        return false;
    }







    @FXML
    void supprimer(ActionEvent event) {
        // Get the selected salleee object from the ListView
        String selectedItem = sallelistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the idsalle from the selected item
            int idsalle = parseIdFromSelectedItem(selectedItem);

            // Check if idsalle is a valid integer
            if (idsalle != -1) {
                try {
                    // Find the salleee from the list using the idsalle
                    salleee salleToDelete = findSalleById(idsalle);

                    if (salleToDelete != null) {
                        // Delete the salleee from the database
                        SS.supprimer(salleToDelete);

                        // Refresh the ListView
                        afficherDB(event);
                    } else {
                        System.out.println("Salleee not found for idsalle: " + idsalle);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid idsalle");
            }
        } else {
            System.out.println("No item selected.");
        }
    }

    // Helper method to find salleee by idsalle
    private salleee findSalleById(int idsalle) {
        try {
            List<salleee> salles = SS.afficher();
            for (salleee salle : salles) {
                if (salle.getIdsalle() == idsalle) {
                    return salle;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null;
    }


    // Helper method to parse the idsalle from the string representation of a salleee object
    private int parseIdFromSelectedItem(String selectedItem) {
        try {
            // Assuming your string representation is in the format "Idsalle: <idsalle>, ..."
            int startIndex = selectedItem.indexOf("Idsalle:") + "Idsalle:".length();
            int endIndex = selectedItem.indexOf(",", startIndex);

            if (startIndex != -1 && endIndex != -1) {
                // Extract the substring containing idsalle
                String idString = selectedItem.substring(startIndex, endIndex);

                // Convert the extracted substring to an integer
                return Integer.parseInt(idString.trim());
            } else {
                // Handle the case where the substring does not contain valid idsalle information
                throw new NumberFormatException("Invalid idsalle format");
            }
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException, e.g., log the error or show an alert
            e.printStackTrace();
            return -1; // Return a default value or handle appropriately
        }
    }





    // Method to set the selected salleee
    public void setSelectedSalle(salleee selectedSalle) {
        this.selectedSalle = selectedSalle;
    }

    // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

/*
    @FXML
    void afficherDB(ActionEvent event) {
        try {
            List<salleee> salles = SS.afficher();
            sallelistview.getItems().clear();

            for (salleee salle : salles) {
                String displayString = "Idsalle: " + salle.getIdsalle() +
                        ", Matiere: " + salle.getMatiere() +
                        ", ID User: " + salle.getIduser() +
                        ", Email: " + salle.getEmail() ;

                sallelistview.getItems().add(displayString);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    */
    /*
@FXML
void afficherDB(ActionEvent event) {
    try {
        List<salleee> salles = SS.afficher();
        sallelistview.getItems().setAll(String.valueOf(salles));
    } catch (SQLException e) {
        showAlert("Error", "An error occurred while fetching the data from the database.");
        System.out.println(e.getMessage());
    }
} */


/*
    void sendConfirmationEmail(String recipientEmail) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.office365.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Configure the sending account
        String senderEmail = "cherif.benhassine@esprit.tn";
        String password = "Achraf2006+"; // Replace "actual_password" with the real password

        // Create a mail session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }

        });

        try {
            // Create a message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("Confirmation de d ajout d un salle");
            message.setText("Votre salle a été  ajouté.");

            // Send the message
            Transport.send(message);
            System.out.println("E-mail de confirmation envoyé avec succès !");

            // Show an alert dialog
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("E-mail de confirmation envoyé avec succès !");
            alert.showAndWait();
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
            // Show an alert dialog in case of an error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de l'envoi de l'e-mail de confirmation : " + e.getMessage());
            alert.showAndWait();
        }
    }

 */


    public void userEmail(ActionEvent actionEvent) {
    }

    public void sendConfirmationEmail(ActionEvent actionEvent) {
    }

    public void password(ActionEvent actionEvent) {
    }

    @FXML
    void search(ActionEvent actionEvent) {
        String searchTerm = searchTextField.getText().trim().toLowerCase();

        if (searchTerm.isEmpty()) {
            afficherDB(actionEvent);  // Utilisez votre méthode d'affichage par défaut pour restaurer les données initiales
            return;
        }

        ObservableList<String> allSalleStrings = sallelistview.getItems();
        ObservableList<String> searchResults = FXCollections.observableArrayList();

        for (String salleString : allSalleStrings) {
            if (salleString.toLowerCase().contains(searchTerm)) {
                searchResults.add(salleString);
            }
        }

        sallelistview.getItems().clear();
        sallelistview.setItems(searchResults);
    }

    @FXML
    void sort(ActionEvent actionEvent) {
        System.out.println("Tri en cours...");

        ObservableList<String> salles = sallelistview.getItems();
        salles.sort(Comparator.comparing(salle -> {
            int matiereIndex = salle.indexOf("Matiere: ");
            if (matiereIndex != -1) {
                return salle.substring(matiereIndex + 9);
            }
            return salle;
        }));

        // Affichez les salles triées dans la console
        salles.forEach(System.out::println);

        // Mettez à jour la ListView après le tri
        sallelistview.setItems(FXCollections.observableArrayList(salles));

        System.out.println("Tri terminé.");
    }



    @FXML
    void afficherDB(ActionEvent event) {
        try {
            List<salleee> salles = SS.afficher();

            // Clear any existing items in the ListView
            sallelistview.getItems().clear();

            // Add each salleee object to the ListView with a custom string representation
            for (salleee salle : salles) {
                // Personnalisez la représentation de chaîne pour exclure l'id
                String displayString ="IDSalle" + salle.getIdsalle() + ", ID User: " + salle.getIduser()+ "Matiere: " + salle.getMatiere() + ", Email: " + salle.getEmail();

                System.out.println(salle);
                sallelistview.getItems().add(displayString);
            }
            System.out.println(salles);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



}

    public void quiz(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void reclama(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void event(ActionEvent actionEvent) {
    }

    public void formations(ActionEvent actionEvent) {
    }

    public void cours(ActionEvent actionEvent) {
    }

    public void remise(ActionEvent actionEvent) {
    }

    public void salles(ActionEvent actionEvent) {
    }

    public void club(ActionEvent actionEvent) {
    }

    public void verspageadus(ActionEvent actionEvent) {
    }
}
