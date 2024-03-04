package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import services.ServiceEquipement;  // Change the service class
import entities.equipement;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.ListView;

public class EquipementManagement {

    public Button afficherDB;
    @FXML
    void salles(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/salleManagement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

    }


    @FXML
    private TextField categorie;

    @FXML
    private TextField idsalle;

    @FXML
    private ListView<String>  equiplistview;

    private ServiceEquipement SE = new ServiceEquipement();  // Change the service class
/*
    @FXML
    void ajouter(ActionEvent event) {
        // Check if idsalle is injected
        if (idsalle == null) {
            showAlert("ID Salle Field Missing", "ID Salle field is not injected.");
            return;
        }

        // Retrieve values from the TextFields
        String categorieValue = categorie.getText();
        String idsalleValueText = idsalle.getText();

        // Check if idsalle is not empty
        if (idsalleValueText.isEmpty()) {
            showAlert("ID Salle Empty", "Please enter a valid ID Salle.");
            return; // Exit the method if idsalle is empty
        }

        // Parse idsalle as int
        int idsalleValue;
        try {
            idsalleValue = Integer.parseInt(idsalleValueText);
        } catch (NumberFormatException e) {
            showAlert("Invalid ID Salle", "Please enter a valid numeric ID Salle.");
            return; // Exit the method if idsalle is not a valid integer
        }

        // Create a new equipement object
        equipement nouvelEquipement = new equipement(0, idsalleValue, categorieValue); // 0 is a placeholder for idequip

        try {
            // Add the new equipement to the database using ServiceEquipement
            SE.ajouter(nouvelEquipement);

            // You may add additional logic here based on your requirements

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception according to your needs
        }
    }
    */
@FXML
void ajouter(ActionEvent event) {
    // Check if idsalle is injected
    if (idsalle == null) {
        showAlert("ID Salle Field Missing", "ID Salle field is not injected.");
        return;
    }

    // Retrieve values from the TextFields
    String categorieValue = categorie.getText();
    String idsalleValueText = idsalle.getText();

    // Check if idsalle is not empty
    if (idsalleValueText.isEmpty()) {
        showAlert("ID Salle Empty", "Please enter a valid ID Salle.");
        return; // Exit the method if idsalle is empty
    }

    // Parse idsalle as int
    int idsalleValue;
    try {
        idsalleValue = Integer.parseInt(idsalleValueText);
    } catch (NumberFormatException e) {
        showAlert("Invalid ID Salle", "Please enter a valid numeric ID Salle.");
        return; // Exit the method if idsalle is not a valid integer
    }

    // Validate the categorie
    if (!isValidCategorie(categorieValue)) {
        showAlert("Invalid Categorie", "Please enter a valid categorie (électronique, pédagogique, meubles).");
        return; // Exit the method if categorie is not valid
    }

    // Create a new equipement object
    equipement nouvelEquipement = new equipement(0, idsalleValue, categorieValue); // 0 is a placeholder for idequip

    try {
        // Add the new equipement to the database using ServiceEquipement
        SE.ajouter(nouvelEquipement);

        // You may add additional logic here based on your requirements

    } catch (SQLException e) {
        e.printStackTrace();
        // Handle the exception according to your needs
    }
}

    // Helper method to validate categorie
    private boolean isValidCategorie(String categorie) {
        // List of valid categorie values
        String[] validCategories = {"électronique", "pédagogique", "meubles"};

        // Check if categorie is in the list of valid values
        for (String validCategorie : validCategories) {
            if (validCategorie.equalsIgnoreCase(categorie)) {
                return true;
            }
        }

        return false;
    }


    @FXML
    void modifier(ActionEvent event) {
        // Get the selected equipement object from the ListView
        String selectedItem = equiplistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the idequip from the selected item
            int idequip = parseIdFromSelectedItem(selectedItem);

            // Check if idequip is a valid integer
            if (idequip != -1) {
                try {
                    // Find the equipement from the list using the idequip
                    equipement equipementToModify = findEquipementById(idequip);

                    if (equipementToModify != null) {
                        // Retrieve the updated values from the TextFields
                        String newCategorie = categorie.getText();
                        String newIdsalleText = idsalle.getText();

                        // Check if idsalle is not empty
                        if (newIdsalleText.isEmpty()) {
                            showAlert("ID Salle Empty", "Please enter a valid ID Salle.");
                            return; // Exit the method if idsalle is empty
                        }

                        // Parse idsalle as int
                        int newIdsalleValue;
                        try {
                            newIdsalleValue = Integer.parseInt(newIdsalleText);
                        } catch (NumberFormatException e) {
                            showAlert("Invalid ID Salle", "Please enter a valid numeric ID Salle.");
                            return; // Exit the method if idsalle is not a valid integer
                        }

                        // Update the equipement object with new values
                        equipementToModify.setCategorie(newCategorie);
                        equipementToModify.setIdsalle(newIdsalleValue);

                        // Call the modifier method in ServiceEquipement to update the database
                        SE.modifier(equipementToModify);

                        // Refresh the ListView
                        afficherDB(event);
                    } else {
                        System.out.println("Equipement not found for idequip: " + idequip);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid idequip");
            }
        } else {
            System.out.println("No item selected.");
        }
    }


    @FXML
    void supprimer(ActionEvent event) {
        // Get the selected equipement object from the ListView
        String selectedItem = equiplistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the idequip from the selected item
            int idequip = parseIdFromSelectedItem(selectedItem);

            // Check if idequip is a valid integer
            if (idequip != -1) {
                try {
                    // Find the equipement from the list using the idequip
                    equipement equipementToDelete = findEquipementById(idequip);

                    if (equipementToDelete != null) {
                        // Delete the equipement from the database
                        SE.supprimer(equipementToDelete);

                        // Refresh the ListView
                        afficherDB(event);
                    } else {
                        System.out.println("Equipement not found for idequip: " + idequip);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid idequip");
            }
        } else {
            System.out.println("No item selected.");
        }
    }

    // Helper method to find equipement by idequip
    private equipement findEquipementById(int idequip) {
        try {
            List<equipement> equipements = SE.afficher();
            for (equipement equip : equipements) {
                if (equip.getIdequip() == idequip) {
                    return equip;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null;
    }
    @FXML
    void initialize(URL location, ResourceBundle resources) {
        // Set a custom cell factory to display salleee objects in a more organized way
        equiplistview.setCellFactory(param -> new javafx.scene.control.ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    // Assuming your salleee objects have a toString method
                    setText(item);
                }
            }
        });
    }

    // Helper method to parse the idequip from the string representation of an equipement object
    private int parseIdFromSelectedItem(String selectedItem) {
        int startIndex = selectedItem.indexOf("idequip:") + "idequip:".length();
        int endIndex = selectedItem.indexOf(",", startIndex);

        if (endIndex == -1) {
            // Si la virgule n'est pas trouvée, prenez la partie restante de la chaîne
            endIndex = selectedItem.length();
        }

        String idequipString = selectedItem.substring(startIndex, endIndex).trim();

        try {
            return Integer.parseInt(idequipString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins
            return -1; // ou une valeur par défaut appropriée
        }
    }

    // Method to set the selected equipement
    public void setSelectedEquipement(equipement selectedEquipement) {
        // Store the selected equipement
    }

    // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void afficherDB(ActionEvent event) {
        try {
            List<equipement> equipements = SE.afficher();

            // Clear any existing items in the ListView
            equiplistview.getItems().clear();

            // Add each equipement to the ListView
            for (equipement equip : equipements) {
                String displayString = "idequip: " + equip.getIdequip() +
                        ", idsalle: " + equip.getIdsalle() +
                        ", categorie: " + equip.getCategorie();
                equiplistview.getItems().add(displayString);
            }

            System.out.println(equipements);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void quiz(ActionEvent actionEvent) {
    }

    public void reclama(ActionEvent actionEvent) {
    }

    public void event(ActionEvent actionEvent) {
    }

    public void formations(ActionEvent actionEvent) {
    }

    public void cours(ActionEvent actionEvent) {
    }

    public void remise(ActionEvent actionEvent) {
    }

    public void salle(ActionEvent actionEvent) {
    }

    public void club(ActionEvent actionEvent) {
    }

    public void equipement(ActionEvent actionEvent) {
    }

    public void verspageadus(ActionEvent actionEvent) {
    }
}






