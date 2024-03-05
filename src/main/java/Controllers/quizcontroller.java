package Controllers;

import Services.questionservice;
import Services.quizservice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.quiz;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class quizcontroller {

    private Stage stage;
    private Scene scene;
    private int lastInsertedId; // Ajoutez cette variable pour stocker l'ID du dernier quiz inséré

    @FXML
    private TableView<quiz> affiche;
    @FXML
    private TableColumn<quiz, String> nomtab;

    @FXML
    private TableColumn<quiz, Integer> nbrtab;
    private int id; // Variable pour stocker l'ID à transmettre

    // Méthode pour initialiser l'ID
    public void setId(int id) {
        this.id = id;
    }
    private Parent root;
    quizservice qs=new quizservice() {


    };
    questionservice qss=new questionservice();

    @FXML
    private TextField nom;
    private ToggleGroup radioGroup;
    @FXML
    private TextField nbrquest;
    public void select() throws SQLException {


        ///int num = affiche.getSelectionModel().getSelectedIndex();
        quiz q = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
        nom.setText(q.getNom());
        nbrquest.setText(String.valueOf(q.getNbrquest()));
    }

    private void affichage() throws SQLException {
        //quiz quiz=new quiz();
        ObservableList<quiz> qui = qs.getAll();
        affiche.setItems(qui);

        nomtab.setCellValueFactory(new PropertyValueFactory<quiz, String>("nom"));
        nbrtab.setCellValueFactory(new PropertyValueFactory<quiz, Integer>("nbrquest"));

    }
    @FXML
    void initialize() throws SQLException {affiche.setOnMouseClicked(event -> {
        try {
            select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
    affichage();
        //affiche.setItems(FXCollections.observableArrayList());
        affiche.getItems();

    }


    public void ajout(ActionEvent actionEvent) throws SQLException, IOException {
        String t = nom.getText();
        int n = Integer.parseInt(nbrquest.getText());
            if ((n < 10) && (n > 0)) {
                if (!(t.trim().isEmpty() || (nbrquest.getText().isEmpty()))) {
                    quiz qz = new quiz(t, n);
                    qs.add1(qz);
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("failed");
                    alert.setContentText("ce nom est vide ");
                    alert.showAndWait();
                    //System.out.println("nom existe ");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("failed");
                alert.setContentText("le nbr de questions est entre 1 et 9 ");
                alert.showAndWait();
                System.out.println("nom existe ");

                }
                affichage();
                // FXMLLoader loader = null;

        }

    @FXML
    void passer(ActionEvent event) throws IOException, SQLException {
        if (!selectyyy()) {
            affiche.getSelectionModel().getSelectedIndex();


            System.out.println("select quiz");}

        else{
           affiche.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/questions1.fxml"));
            Parent root = loader.load();
            Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();
            retourner();
            returnnbrquest();
            System.out.println(retourner());
            System.out.println(returnnbrquest());
            questions nb=loader.getController();
            nb.setNbrquestt(returnnbrquest());
            questions irc=loader.getController();
            irc.setIdd(retourner());

    }}

    @FXML
    void affich(ActionEvent event) throws SQLException {

            affichage();


    }
    public boolean selectyyy() throws SQLException {
        if (!affiche.getSelectionModel().getSelectedItems().isEmpty()) {
            return true;
        }
        return false;
    }
    @FXML
    void modifier(ActionEvent event) throws SQLException {
        quiz quii  = affiche.getSelectionModel().getSelectedItem();
        //select();
        if (selectyyy()) {
                String t = nom.getText();
        int d = Integer.parseInt(nbrquest.getText());
        quiz qu = new quiz(t, d);
        System.out.println(quii.getIdquiz());
        qs.update(qu, quii.getIdquiz());
        affichage();}
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("failed");
            alert.setContentText("selectionner une case ");
            alert.showAndWait();
        }



    }
   public int retourner(){


            // System.out.println(q.getIdquiz());
            return affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex()).getIdquiz();
        }
      // quiz q = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
       // System.out.println(q.getIdquiz());
public int returnnbrquest(){
    return affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex()).getNbrquest();

}

    @FXML
    void rec(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

    }
    public int retourn(int id){
        return id;
    }

    @FXML
    void supp(ActionEvent event) throws SQLException {
        if (selectyyy()) {
       quiz qu = affiche.getSelectionModel().getSelectedItem();
        System.out.println(qu.getIdquiz());
        qs.delete(qu.getIdquiz());
        affichage();}
         else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("failed");
            alert.setContentText("selectionner une ligne ");
            alert.showAndWait();
        }
    }

    public void quiz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void ajouter(ActionEvent event) {
    }

    public void reclama(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void event(ActionEvent event) {
    }

    public void formations(ActionEvent event) {
    }

    public void cours(ActionEvent event) {
    }

    public void remise(ActionEvent event) {
    }

    public void salle(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/salleManagement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void club(ActionEvent event) {
    }

    public void equipement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipementManagement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void verspageadus(ActionEvent event) {
    }
}