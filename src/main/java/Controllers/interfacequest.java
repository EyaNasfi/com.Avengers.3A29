package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Controllers.Itemq.*;
import Services.questionservice;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Questions;
import models.quiz;

public class interfacequest {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private HBox item;
    @FXML
    private VBox pnitems;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }

    private int n;
    private int id;
questionservice rs=new questionservice();
Itemq s=new Itemq();
    private void handleButtonPress(Button button, String selectedOption, String correctAnswer) {
        if (selectedOption.equals(correctAnswer)) {
            System.out.println("Bonne réponse : " + button.getText());
        } else {
            System.out.println("Mauvaise réponse : " + button.getText());
        }
    }
    @FXML
    void initialize() throws IOException {
        ObservableList<Questions> R = null;
        try {
            R = rs.get();


            for (int i = 0; i < R.size(); i++) {

                //  final int j = i;
                Questions q = R.get(i);
                q.setIdquiz(getId());
                // System.out.println(getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemquest.fxml"));
                Node node = loader.load();


                ((Label) node.lookup("#quest")).setText(q.getQuestion());
                ((Button) node.lookup("#op1")).setText(q.getOp1());
                ((Button) node.lookup("#op2")).setText(q.getOp2());
                ((Button) node.lookup("#op3")).setText(q.getOp3());
                pnitems.getChildren().add(node);


              /*  if (button1.isPressed()) {
                    System.out.println("button1");
                    if (op1.equals(q.getAnswer())) {
                        System.out.println("repose1 correcte");
                    } else {
                        System.out.println("reponse1 fausse ");
                    }
                } else if (button2.isPressed()) {
                    if (op2.equals(q.getAnswer())) {
                        System.out.println("repose2 correcte");
                    } else {
                        System.out.println("reponse2 fausse ");
                    }
                } else if (button3.isPressed()) {
                    if (op3.equals(q.getAnswer())) {
                        System.out.println("repose3 correcte");
                    } else {
                        System.out.println("reponse3 fausse ");
                    }
                }*/

                Button button1 = (Button) node.lookup("#op1");
                Button button2 = (Button) node.lookup("#op2");
                Button button3 = (Button) node.lookup("#op3");

                button1.setOnAction(event -> handleButtonPress(button1, q.getOp1(), q.getAnswer()));
                button2.setOnAction(event -> handleButtonPress(button2, q.getOp2(), q.getAnswer()));
                button3.setOnAction(event -> handleButtonPress(button3, q.getOp3(), q.getAnswer()));





            }
                  /*  if ((s.opchoi() == 1)) {
            if (q.getAnswer() == q.getOp1()) {
                System.out.println("reponse 1 vrai");

            } else {
                System.out.println("reponse 1 fausse");
            }
        }
        if ((s.opchoi() == 2)) {
            if (q.getAnswer() == q.getOp2()) {
                System.out.println("reponse 2 vrai");

            } else {
                System.out.println("reponse 2 fausse");
            }
        }
        if ((s.opchoi() == 3)) {
            if (q.getAnswer() == q.getOp3()) {
                System.out.println("reponse 3 vrai");

            } else {
                System.out.println("reponse 3 fausse");
            }
        }*/

            }   catch (SQLException e) {
            throw new RuntimeException(e);
        }}




    public void yraja3(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nvquiz.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }
}


