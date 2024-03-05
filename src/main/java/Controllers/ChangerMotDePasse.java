package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import Services.UserService;
import test.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

public class ChangerMotDePasse {
    @FXML
    private Button login;
    @FXML
    private TextField confirmenewMdp;

    @FXML
    private TextField emailModif;

    @FXML
    private TextField newMdp;

    private UserService userService;

    public ChangerMotDePasse() {
        userService = new UserService();
    }

    @FXML
    void ModifierNewMdp(ActionEvent event) {
        String email = emailModif.getText();
        String nouveauMdp = newMdp.getText();
        String confirmationMdp = confirmenewMdp.getText();

        if (!nouveauMdp.equals(confirmationMdp)) {
            afficherErreur("Erreur", "Les mots de passe ne correspondent pas.");
            return; // Sortir de la méthode si les mots de passe ne correspondent pas
        }

        try {
            // Vérifier si l'utilisateur existe
            if (userService.getByEmail(email) != null) {
                // Hasher le nouveau mot de passe
                String hashedPassword = BCrypt.hashpw(nouveauMdp, BCrypt.gensalt());
                // Mettre à jour le mot de passe de l'utilisateur
                userService.modifierMotDePasse(email, hashedPassword);
                afficherMessage("Succès", "Mot de passe mis à jour avec succès.");
            } else {
                afficherErreur("Erreur", "Utilisateur non trouvé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void afficherMessage(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);

        // Ajouter un bouton personnalisé pour naviguer vers LOGIN.fxml
        ButtonType loginButton = new ButtonType("Se connecter");
        alert.getButtonTypes().setAll(loginButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == loginButton) {
            // L'utilisateur a cliqué sur "Se connecter"
            URL loginURL = getClass().getResource("/tn/esprit/crud/LOGIN.fxml");
            if (loginURL != null) {
                Stage stage = (Stage) confirmenewMdp.getScene().getWindow();
                stage.close();
                try {
                    FXMLLoader loader = new FXMLLoader(loginURL);
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Erreur : Fichier LOGIN.fxml introuvable.");
            }
        }
    }

    private void afficherErreur(String titre, String contenu) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }

    public void login(ActionEvent event) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/login.fxml"));
            try {
                login.getScene().setRoot(fxmlLoader.load());
            } catch (IOException e) {
                System.err.println(e.getMessage());
                throw new RuntimeException(e);
            }
    }
}