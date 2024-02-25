package Controllers;

import Services.questionservice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Questions;
import models.quiz;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class affquestions {
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
    private TableView<Questions> affiche;
    @FXML
    private TableColumn<Questions,String> question;

    @FXML
    private TableColumn<Questions,String> option1;

    @FXML
    private TableColumn<Questions,String> option2;

    @FXML
    private TableColumn<Questions,String> option3;

    @FXML
    private TableColumn<Questions,String> answer;
    questionservice qs= new questionservice();
    @FXML
    void initialize() {affiche.setOnMouseClicked(event -> {
        try {
            select();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });}
    private void affichage() throws SQLException {
        //quiz quiz=new quiz();
        ObservableList<Questions> qui = qs.getAll();
        affiche.setItems(qui);

        question.setCellValueFactory(new PropertyValueFactory<Questions, String>("question"));
        option1.setCellValueFactory(new PropertyValueFactory<Questions, String>("op1"));
        option2.setCellValueFactory(new PropertyValueFactory<Questions, String>("op2"));
        option3.setCellValueFactory(new PropertyValueFactory<Questions, String>("op3"));
        answer.setCellValueFactory(new PropertyValueFactory<Questions, String>("answer"));

    }

    public void select() throws SQLException {


        ///int num = affiche.getSelectionModel().getSelectedIndex();
        Questions q = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
        quest.setText(q.getQuestion());
        op1.setText(q.getOp1());
        op2.setText(q.getOp2());
        op3.setText(q.getOp3());
        an.setText(q.getAnswer());


    }


    public void afficher(javafx.event.ActionEvent actionEvent) throws SQLException {
        affichage();
    }

    public void modif(javafx.event.ActionEvent actionEvent) throws SQLException {
        Questions q = affiche.getItems().get(affiche.getSelectionModel().getSelectedIndex());
        //select();
        q.setIdquest(q.getIdquest());
        question.setText(q.getQuestion());
        option1.setText(q.getOp1());
        option2.setText(q.getOp2());
        option3.setText(q.getOp3());
        answer.setText(q.getAnswer());

        int id = q.getIdquest();
        q.setIdquest(id);
        String opp1=op1.getText();
        String opp2=op2.getText();
        String opp3=op3.getText();
        String ann=an.getText();
        String qu=quest.getText();

        Questions quuu=new Questions(opp1, opp2, opp3, ann, qu);
        System.out.println(q.getIdquest());
        qs.update(quuu,q.getIdquest());
        affichage();
    }

    public void supp(javafx.event.ActionEvent actionEvent) throws SQLException {
        Questions qu = affiche.getSelectionModel().getSelectedItem();
        System.out.println(qu.getIdquest());
        qs.delete(qu.getIdquest());
        affichage();
    }
}
