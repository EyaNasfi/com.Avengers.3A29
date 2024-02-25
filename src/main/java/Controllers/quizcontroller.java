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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    @FXML
    void initialize() {affiche.setOnMouseClicked(event -> {
        try {
            select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
        affiche.setItems(FXCollections.observableArrayList());

    }
        private void affichage() throws SQLException {
            //quiz quiz=new quiz();
            ObservableList<quiz> qui = qs.getAll();
            affiche.setItems(qui);

            nomtab.setCellValueFactory(new PropertyValueFactory<quiz, String>("nom"));
            nbrtab.setCellValueFactory(new PropertyValueFactory<quiz, Integer>("nbrquest"));

        }

    public void ajout(ActionEvent actionEvent) throws SQLException, IOException {
        String t = nom.getText();
        int n = Integer.parseInt(nbrquest.getText());


        if (n > 10) {
            System.out.println("entrer une nbr moins de 10");

        } else {

               // FXMLLoader loader = null;
                if (!t.trim().isEmpty() ) {

                    quiz qz = new quiz(t, n);
                    qs.add1(qz);
                }}
            if (t.trim().isEmpty()) {

                System.out.println("le nom de quiz svp");
            }

        }
    @FXML
    void passer(ActionEvent event) throws IOException {
        if(!affiche.getItems().isEmpty()){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/questions1.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
        retourner();
        System.out.println(retourner());
            questions irc=loader.getController();
            irc.setIdd(retourner());
    }
    else {
            System.out.println("problemaaaaaaaaaa");}
    }

    @FXML
    void affich(ActionEvent event) throws SQLException {

            affichage();


    }
    @FXML
    void modifier(ActionEvent event) throws SQLException {
        quiz quii  = affiche.getSelectionModel().getSelectedItem();
        //select();

                String t = nom.getText();
        int d = Integer.parseInt(nbrquest.getText());
        quiz qu = new quiz(t, d);
        System.out.println(quii.getIdquiz());
        qs.update(qu, quii.getIdquiz());
        affichage();



    }
   public int retourner(){


            // System.out.println(q.getIdquiz());
            return affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex()).getIdquiz();
        }
      // quiz q = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
       // System.out.println(q.getIdquiz());



    public int retourn(int id){
        return id;
    }

    @FXML
    void supp(ActionEvent event) throws SQLException {
       quiz qu = affiche.getSelectionModel().getSelectedItem();
        qs.delete(qu.getIdquiz());
        affichage();
    }

}