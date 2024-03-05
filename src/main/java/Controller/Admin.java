package Controller;

import com.stripe.model.Application;
import entity.Paiement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ServicePaiement;
import test.MainFX;

import java.io.IOException;
import java.sql.SQLException;

import static javafx.application.Application.launch;

public  class Admin {
    @FXML
    private TableView<Paiement> afficher;

    @FXML
    private TableColumn<Paiement, String> modeColumn;

    @FXML
    private TableColumn<Paiement, Integer> montantColumn;
    //@FXML
    //private ListView<Paiement> afficher;
    @FXML
    private TextField mode;

    @FXML
    private TextField montant;
    @FXML
    private Button generatePDFButton;


    ServicePaiement rp =new ServicePaiement();

    @FXML
    void initialize() throws SQLException {
        modeColumn.setCellValueFactory(new PropertyValueFactory<>("modepaiement"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        afficherr();
    }
    public void afficherr() throws SQLException {
        ObservableList<Paiement> list = rp.afficher();
        afficher.setItems(rp.afficher());
    }
    public void refreshTableView() {
        try {
            afficherr();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void  selection() throws SQLException {
        Paiement p=afficher.getItems().get(afficher.getSelectionModel().getSelectedIndex());
        mode.setText(p.getModepaiement());
        montant.setText(String.valueOf(p.getMontant()));

    }


      @FXML
    void modif(ActionEvent event) throws SQLException {
        Paiement selectedPaiement = afficher.getSelectionModel().getSelectedItem();

        if (selectedPaiement != null) {
            // Assuming mode and montant are TextFields in your UI
            String updatedMode = mode.getText();
            int updatedMontant = Integer.parseInt(montant.getText());

            // Update the selected Paiement with the new values
            selectedPaiement.setModepaiement(updatedMode);
            selectedPaiement.setMontant(updatedMontant);

            // Perform the modification in the database
            try {
                rp.modifier(selectedPaiement, selectedPaiement.getIdpaiement());
                afficherr();
            } catch (SQLException e) {
                e.printStackTrace();  // Handle the exception appropriately
            }
        }
    }

    @FXML
    void supp(ActionEvent event) throws IOException, SQLException {
        Paiement p=afficher.getSelectionModel().getSelectedItem();
        System.out.println(p.getIdpaiement());
        rp.supprimer(p.getIdpaiement());
        afficherr();
        afficher.refresh();
    }



    @FXML
    void remise(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/remise.fxml"));
        Parent root = loader.load();
        Stage st=(Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();
    }


    public void quiz(ActionEvent event) {
    }

    public void ajouter(ActionEvent event) {
    }

    public void reclama(ActionEvent event) {
    }

    public void event(ActionEvent event) {
    }

    public void formations(ActionEvent event) {
    }

    public void cours(ActionEvent event) {
    }

    public void salle(ActionEvent event) {
    }

    public void club(ActionEvent event) {
    }

    public void equipement(ActionEvent event) {
    }

    public void verspageadus(ActionEvent event) {
    }
}