package test;

<<<<<<< HEAD
import controllers.eventmangment;
=======
<<<<<<< HEAD


=======
>>>>>>> 51081faf48ce072297e4ec47b84ec88930bae1bc
>>>>>>> b54136c8f2dad0583e8b43ddb2e1a1e1886781ac
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
<<<<<<< HEAD
import javafx.stage.Stage;
import services.eventservice;

import java.io.IOException;

=======
<<<<<<< HEAD
=======
import javafx.scene.layout.AnchorPane;
>>>>>>> 51081faf48ce072297e4ec47b84ec88930bae1bc
import javafx.stage.Stage;

import java.io.IOException;

<<<<<<< HEAD
>>>>>>> b54136c8f2dad0583e8b43ddb2e1a1e1886781ac
public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
<<<<<<< HEAD
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
=======


        FXMLLoader loader = new FXMLLoader(MainFX.class.getResource("hello-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Acheter formation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


=======

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
>>>>>>> 51081faf48ce072297e4ec47b84ec88930bae1bc
>>>>>>> b54136c8f2dad0583e8b43ddb2e1a1e1886781ac
