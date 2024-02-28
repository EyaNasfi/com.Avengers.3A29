package test;

import controllers.eventmangment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.eventservice;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        eventservice eventservice = new eventservice();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EventMangement.fxml"));

        // Pass the ServiceSalle instance to the controller
       /// loader.setController(new eventmangment(eventservice));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setTitle("gerer Salle");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}