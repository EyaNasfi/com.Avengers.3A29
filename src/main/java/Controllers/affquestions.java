package Controllers;

import Services.questionservice;
import Services.quizservice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Questions;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

public class affquestions {
    public void setIdqui(int idqui) {
        this.idqui = idqui;
    }

    public int getIdqui() {
        return idqui;
    }

    private  int idqui;

    @FXML
    private TextField an;

    @FXML
    private TextField quest;

    @FXML
    private TextField op1;

    @FXML
    private TextField op2;

    @FXML
    private TextField op3;

    @FXML
    private ListView<Questions> affiche;
    questionservice qs= new questionservice();
    quizservice qss= new quizservice() ;
    @FXML
    void initialize() throws SQLException {affiche.setOnMouseClicked(event -> {
        try {
            select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });
        affichage();

        //affichage();
       //affiche.getItems();
    }

    private void affichage() throws SQLException {
        ObservableList<Questions> qui = qs.getAll();
        affiche.setItems(qui);
        }



       // System.out.println(nom);




    public void select() throws SQLException {


        ///int num = affiche.getSelectionModel().getSelectedIndex();
        Questions q = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
        quest.setText(q.getQuestion());
        op1.setText(q.getOp1());
        op2.setText(q.getOp2());
        op3.setText(q.getOp3());
        an.setText(q.getAnswer());

    }



public int returne() throws SQLException {
    int i=affiche.getSelectionModel().getSelectedItem().getIdquiz();;
    System.out.println(i);
    return i;
}
    public void modif(javafx.event.ActionEvent actionEvent) throws SQLException {
        Questions q = affiche.getSelectionModel().getSelectedItem();
        //select();
        String opp1=op1.getText();
        String opp2=op2.getText();
        String opp3=op3.getText();
        String ann=an.getText();
        String qu=quest.getText();
        int j =returne();
        System.out.println(getIdqui());
        //String nomquiz, int nbques, int idquiz, String question, String op1, String op2, String op3, String answer, int idquest
        Questions quuu=new Questions(opp1, opp2, opp3, ann,qu,returne());
        System.out.println(j);
        System.out.println(q.getIdquest());
        qs.update(quuu,q.getIdquest());
        affichage();
    }


    @FXML
    void quiz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();


    }

    public void supp(javafx.event.ActionEvent actionEvent) throws SQLException {
        Questions qu = affiche.getSelectionModel().getSelectedItem();
        System.out.println(qu.getIdquest());
        qs.delete(qu.getIdquest());
        affichage();
    }

    public void rec(javafx.event.ActionEvent actionEvent) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ajouterreclamation.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
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

    public void quest(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/questions1.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }

    public void afficher(javafx.event.ActionEvent actionEvent) throws SQLException {
        affichage();
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
