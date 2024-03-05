package Controllers;

import Services.reclamationservice;
import Services.reponseservice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Questions;
import models.Reclamation;
import models.Reponses;
import models.quiz;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class reclamationreponseC {
reclamationservice rs=new reclamationservice();
    @FXML
    private TableView<Reclamation> affiche;
    @FXML
    private TableColumn<Reclamation, String> titre;
    @FXML
    private TableColumn<Reclamation, String> nom;
    @FXML
    private TableColumn<Reclamation, String > prenom;
@FXML
    private TableColumn<Reclamation, String > reponse;

    @FXML
    private TableColumn<Reclamation, String > description;
    reponseservice rrs=new reponseservice();
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
   /* private int repodispo(){
       int i=affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex())
    }*/
   private void affichage() throws SQLException {
       ObservableList<Reclamation> qui = rs.get();
       affiche.setItems(qui);
       titre.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("titre"));
       description.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
       nom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("nom"));
       prenom.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("prenom"));





   }

    @FXML
    void emplacement(ActionEvent event) {

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/popup.fxml"));
            VBox popupContent = loader.load();
            Popup popup = new Popup();
            popup.getContent().add(popupContent);
            popupC controller = loader.getController();
          //  controller.setId_reponse(id_user_reponse);
            controller.setPopup(popup);
            Stage st = (Stage) affiche.getScene().getWindow();
            popup.show(st);
           popupC irc = loader.getController();
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

    public void rect(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin2.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void emplacement(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/map.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();


    }

    public void verspageadus(javafx.event.ActionEvent event) {
    }

    public void equipement(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipementManagement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void club(javafx.event.ActionEvent event) {
    }

    public void salle(javafx.event.ActionEvent event) {
    }

    public void remise(javafx.event.ActionEvent event) {
    }

    public void cours(javafx.event.ActionEvent event) {
    }

    public void formations(javafx.event.ActionEvent event) {
    }

    public void event(javafx.event.ActionEvent event) {
    }

    public void reclama(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin2.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }
}