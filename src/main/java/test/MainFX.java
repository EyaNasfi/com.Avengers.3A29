package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.ServiceSalle2;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

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

}
