package Controller;

import entity.Paiement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import services.ServicePaiement;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class affichage {
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

   /* @FXML
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
    }*/
    ServicePaiement rp =new ServicePaiement();

    @FXML
    void initialize() throws SQLException {
        modeColumn.setCellValueFactory(new PropertyValueFactory<>("modepaiement"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        generatePDFButton.setOnAction(event -> generatePDF());
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

    /*@FXML
    void supp(ActionEvent event) throws IOException, SQLException {
        Paiement p=afficher.getSelectionModel().getSelectedItem();
        System.out.println(p.getIdpaiement());
        rp.supprimer(p.getIdpaiement());
        afficherr();
        afficher.refresh();
    }*/
    /*public void  selection() throws SQLException {
        Paiement p=afficher.getItems().get(afficher.getSelectionModel().getSelectedIndex());
        mode.setText(p.getModepaiement());
        montant.setText(String.valueOf(p.getMontant()));

    }*/




    private void generatePDF() {
        Paiement selectedPaiement = afficher.getSelectionModel().getSelectedItem();

        if (selectedPaiement != null) {
            // Use the existing PDF generation logic
            String fileName = "Paiement_" + selectedPaiement.getIdpaiement() + ".pdf";

            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50, 700);
                    contentStream.showText("Paiement Details");
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Mode: " + selectedPaiement.getModepaiement());
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Montant: " + selectedPaiement.getMontant());
                    contentStream.endText();
                }

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save PDF");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
                File file = fileChooser.showSaveDialog(new Stage());

                if (file != null) {
                    document.save(file);
                    System.out.println("PDF generated and saved to: " + file.getAbsolutePath());

                    // Open the generated PDF file with the default PDF viewer
                    try {
                        if (Desktop.isDesktopSupported()) {
                            Desktop.getDesktop().open(file);
                        } else {
                            System.out.println("Desktop is not supported on this platform.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Unable to open the PDF file with the default viewer.");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void profil(ActionEvent event) {
    }

    public void reclama(ActionEvent event) {
    }

    public void salles(ActionEvent event) {
    }

    public void formation(ActionEvent event) {
    }

    public void event(ActionEvent event) {
    }

    public void dispo(ActionEvent event) {
    }

    public void quiz(ActionEvent event) {
    }
}