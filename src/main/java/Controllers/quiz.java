package Controllers;

import Services.quizservice;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class quiz implements Initializable {

   private static quizservice rs=new quizservice();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox pnitems;

    @FXML
    private Label lancer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<models.quiz> R= null;
        try {
            R = rs.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < R.size(); i++) {
            try {
                final int j = i;
                models.quiz quiz = R.get(j);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/itemRec.fxml"));
                Node node = loader.load();

                ((Label) node.lookup("#nom")).setText(quiz.getNom());
                ((Label) node.lookup("#nbrqst")).setText(String.valueOf(quiz.getNbrquest()));
                System.out.println();
                ItemRecController irc=loader.getController();
                irc.setId(quiz.getIdquiz());
                System.out.println(quiz.getIdquiz());
                ///irc.setId_user_reponse(Reclamation.getId());
                // Give the items some effect
                node.setOnMouseEntered(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });
                node.setOnMouseExited(event -> {
                    node.setStyle("-fx-background-color : #ffffff");
                });

                pnitems.getChildren().add(node);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
