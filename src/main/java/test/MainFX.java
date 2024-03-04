package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFX extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin.fxml"));
        ///8alta b9yyt 3h nlwj aaliha
     // loader.setRoot(new AnchorPane());
        Parent root =loader.load();
        //  Parent root =loader.load();
        Scene scene =new Scene(root);
        primaryStage.setTitle("Reclamation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

=======
import javafx.stage.Stage;
import services.ServiceSalle2;

import java.io.IOException;

public class MainFX extends Application {

>>>>>>> gestionSalles
    public static void main(String[] args) {
        launch(args);
    }

<<<<<<< HEAD
=======
    @Override
    public void start(Stage primaryStage) throws IOException {
        ServiceSalle2 serviceSalle = new ServiceSalle2();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/salleManagement.fxml"));

        // Pass the ServiceSalle instance to the controller
        //loader.setController(new SalleManagement(serviceSalle));

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

>>>>>>> gestionSalles
}
