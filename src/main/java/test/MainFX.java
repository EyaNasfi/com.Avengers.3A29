package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainFX extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/reclamationadmin.fxml"));
        ///8alta b9yyt 3h nlwj aaliha
        // loader.setRoot(new AnchorPane());
        Parent root = loader.load();
        //  Parent root =loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Reclamation");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}