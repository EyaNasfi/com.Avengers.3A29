package Controllers;

import Services.reponseservice;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Questions;
import models.Reclamation;
import models.Reponses;
import models.quiz;

import java.io.IOException;
import java.sql.SQLException;

public class affreclamationreponseC {
    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getIid() {
        return iid;
    }

    private int iid;
    @FXML
    private TextArea description;

    @FXML
    private ListView<Reponses> affiche;

    reponseservice rs = new reponseservice();
    @FXML
    void initialize() throws SQLException {affiche.setOnMouseClicked(event -> {

        try {
          // affichage();
            select();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    });
        affichage();}
    public void select() throws SQLException {


        ///int num = affiche.getSelectionModel().getSelectedIndex();
        Reponses r = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
        description.setText(r.getContenu());


    }
    public void quiz(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }



    public void ajouter(ActionEvent actionEvent) throws SQLException {

        getIid();
        System.out.println(getIid());
        affichage();
        String qu = this.description.getText();
        if (!qu.trim().isEmpty()) {

            int j = getIid();
            System.out.println(getIid());
            Reponses qq = new Reponses(j, qu);

            rs.add1(qq);
            rs.getAll();
            affiche.getItems();
            affichage();
            System.out.println("ajout avec success");

        } else {
            System.out.println("ilyaa un champs vide");

        }
        affichage();
    }

    private void affichage() throws SQLException {
        affiche.setItems(rs.getAll());}



    public void eee(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterreclamation.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }


    public void butt(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterreclamation.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

    }
    public boolean selectyyy() throws SQLException {
        if (!affiche.getSelectionModel().getSelectedItems().isEmpty()) {
            return true;
        }
        return false;
    }
    public void modifier(ActionEvent actionEvent) throws SQLException {
        if (!selectyyy()){
            affiche.getSelectionModel().getSelectedIndex();


            System.out.println("select le reclamation a modifier");}
        else {
            Reponses q = affiche.getSelectionModel().getSelectedItem();
            //select();

            String qu=description.getText();
            int j =getIid();
            System.out.println(getIid());
            //String nomquiz, int nbques, int idquiz, String question, String op1, String op2, String op3, String answer, int idquest
            Reponses quuu=new Reponses(j,qu);
            System.out.println(j);
            System.out.println(q.getIdrep());
            rs.update(quuu,q.getIdrep());
            affichage();
    }}

    public void supp(ActionEvent actionEvent) throws SQLException {
        if (!selectyyy()){
            affiche.getSelectionModel().getSelectedIndex();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("il faut selectionner");
            alert.showAndWait();
            System.out.println("select le reclamation a supprimer");}
        else {
        Reponses qu = affiche.getSelectionModel().getSelectedItem();
        System.out.println(qu.getIdrep());
        rs.delete(qu.getIdrep());
        affichage();
    }}
}


