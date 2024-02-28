package Controllers;

import Services.reclamationservice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Questions;
import models.Reclamation;
import models.Reponses;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class reclamationreponseC {
reclamationservice rs=new reclamationservice();
    @FXML
    private ListView<Reclamation> affiche;
    @FXML
    void initialize() throws SQLException {affiche.setOnMouseClicked(event -> {
        select();

    });
        affichage();

        //affiche.setItems(FXCollections.observableArrayList());


    }

    private void select() {
        Reclamation r = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());

    }
    private void affichage() throws SQLException {
        ObservableList<Reclamation> qui = rs.getAll();
        affiche.setItems(qui);
    }
    public int retourne(){
      return affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex()).getId();
    }





    public boolean selectyyy() throws SQLException {
        if (!affiche.getSelectionModel().getSelectedItems().isEmpty()) {
            return true;
        }
        return false;
    }

    public void ajouter(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        if (!selectyyy()) {
            affiche.getSelectionModel().getSelectedIndex();


            System.out.println("select reclamation pour le repondre");}
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin2.fxml"));
            Parent root = loader.load();
            Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();
            affreclamationreponseC irc = loader.getController();
            irc.setIid(retourne());
        }
        affiche.refresh();
    }

    @FXML
    void eee(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterreclamation.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void quiz(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void disc(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterreclamation.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }
}