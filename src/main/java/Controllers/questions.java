package Controllers;

import Services.questionservice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Questions;
import Controllers.quizcontroller.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
//package Controllers;
import Controllers.quizcontroller;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.quiz;

public class questions {

        @FXML
        private TextField ecrire1;

        @FXML
        private TextField ecrire2;

        @FXML
        private TextField ecrire3;

        @FXML
        private TextField question;

    public int getIdd() {
        return idd;
    }



    public void setIdd(int idd) {
        this.idd = idd;
    }

    public int idd;
    quizcontroller qz=new quizcontroller();

    @FXML
        private TextField ecrire11;
      //  private ArrayList<Questions>questions=new ArrayList<>();
      questionservice qs=new questionservice();
    //public class questions {

        @FXML
        void initialize () throws NoSuchFieldException {


        ///question.setText(String.valueOf(quizcontroller.class.getField(question.getId())));
        }



        public void submit (ActionEvent actionEvent) throws SQLException, IOException {
            //System.out.println(getIdd());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/questions25.fxml"));
            Parent root = loader.load();
            Stage st = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            st.setScene(scene);
            st.show();
        }
  /*  @FXML
    void ajout( ActionEvent event) throws SQLException {


        }
    }
*/
    @FXML
    public void ajout(ActionEvent actionEvent) throws SQLException, IOException {

        quiz qui=new quiz();

        for (int i=0;i<=qui.getNbrquest();i++){
            String qu = this.question.getText();
            String op1 = this.ecrire1.getText();
            String op2 = this.ecrire2.getText();
            String op3 = this.ecrire3.getText();
            String an = this.ecrire11.getText();

            //setId_quiz(Integer.parseInt(String.valueOf(qz.retourner())));
           if (!qu.trim().isEmpty()  && !op1.trim().isEmpty() && !op2.trim().isEmpty() && !op3.trim().isEmpty() &&!an.trim().isEmpty()) {   //  boolean valid = valider();
                // Questions q = new Questions(question.getText(), ecrire1.getText(), ecrire2.getText(), ecrire3.getText(), ecrire11.getText());
int j=getIdd();
            System.out.println(getIdd());
                        Questions qq=new Questions(op1, op2, op3, an, qu,j);
                      //  cont.retourner();
              //  System.out.println(cont.retourner());
              // System.out.println(qq.getIdquiz());

                qs.add1(qq);
                    System.out.println("ajout avec success");
                    //System.out.println(id);

                // questions.add(q);
                question.clear();
                ecrire1.clear();
                ecrire2.clear();
                ecrire3.clear();
                ecrire11.clear();
            }}
            // System.out.println(questions);
            //System.out.println(qu);

        }}








