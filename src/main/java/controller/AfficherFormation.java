package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.formation;
import services.serviceFormation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.formation;
import services.serviceFormation;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.*;


public class AfficherFormation {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<formation, Date> DD;

    @FXML
    private TableColumn<formation, Date> DF;

    @FXML
    private TableColumn<formation, String> cout;

    @FXML
    private TableColumn<formation, String> description;

    @FXML
    private TableColumn<formation, String> duree;

    @FXML
    private TableColumn<formation, String> niveau;

    @FXML
    private TableColumn<formation, String> nomc;

    @FXML
    private TableColumn<formation, String> nomf;

    @FXML
    private TableView<formation> tableviewaffichage;
    serviceFormation fs=new serviceFormation();

    @FXML
    void AfficherDB(ActionEvent event) throws SQLException {
        // Récupérer les données de la base de données
        List<formation> formations = fs.afficher();

        // Créer une ObservableList à partir des données
        ObservableList<formation> formationList = FXCollections.observableArrayList(formations);

        // Ajouter les données à la TableView
        tableviewaffichage.setItems(formationList);
    }


    @FXML
    void supprimer(ActionEvent event) throws SQLException {
        // Récupérer la formation sélectionnée dans la TableView
        formation formationSelectionnee = tableviewaffichage.getSelectionModel().getSelectedItem();

        // Supprimer la formation de la base de données et de la TableView
        if (formationSelectionnee != null) {
            fs.supprimer(formationSelectionnee);
            tableviewaffichage.getItems().remove(formationSelectionnee);
        }
    }

    @FXML
    void AccederCours(ActionEvent event) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/Cours.fxml"));
        try {
            Parent root = loader1.load();
            Cours controller = loader1.getController();
            tableviewaffichage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void modifier(ActionEvent event) {
        // Récupérer la formation sélectionnée dans la TableView
        formation formationSelectionnee = tableviewaffichage.getSelectionModel().getSelectedItem();

        if (formationSelectionnee != null) {
            // Afficher une boîte de dialogue de modification pour le champ nomFormation
            TextInputDialog dialog = new TextInputDialog(formationSelectionnee.getNom_de_Formation());
            dialog.setTitle("Modifier Nom de Formation");
            dialog.setHeaderText(null);
            dialog.setContentText("Nouveau nom de formation :");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newNomFormation -> {
                formationSelectionnee.setNom_de_Formation(newNomFormation);
                // Répercuter les changements dans la TableView
                tableviewaffichage.refresh();
                // Enregistrer les modifications dans la base de données
                try {
                    fs.modifier(formationSelectionnee);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }




    @FXML
    void initialize() {
        // ...

        // Définition des cell value factories pour chaque colonne
        nomc.setCellValueFactory(new PropertyValueFactory<formation,String>("nomCategorie"));
        nomf.setCellValueFactory(new PropertyValueFactory<formation,String>("Nom_de_Formation"));
        description.setCellValueFactory(new PropertyValueFactory<formation,String>("Description"));
        duree.setCellValueFactory(new PropertyValueFactory<formation,String>("Durée"));
        niveau.setCellValueFactory(new PropertyValueFactory<formation,String>("Niveau"));
        DD.setCellValueFactory(new PropertyValueFactory<formation,Date>("Date_Deb"));
        DF.setCellValueFactory(new PropertyValueFactory<formation,Date>("Date_Fin"));
        cout.setCellValueFactory(new PropertyValueFactory<formation,String>("Coût"));


        // ...
    }


}
