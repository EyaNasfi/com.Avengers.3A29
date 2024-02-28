package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceSalle2;
import entities.salleee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.ListView;

public class SalleManagement {

    public Button afficherDB;
    public ImageView photo;

    @FXML
    void equipement(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EquipementManagement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

    }

    @FXML
    private TextField matiere;

    @FXML
    private TextField iduser;  // Changer de PasswordField à TextField pour iduser

    @FXML
    private ListView<String> sallelistview;

    private ServiceSalle2 SS = new ServiceSalle2();

    private salleee selectedSalle; // Store the selected salle

    @FXML
    void modifier(ActionEvent event) {
        // Get the selected salleee object from the ListView
        String selectedItem = sallelistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the idsalle from the selected item
            int idsalle = parseIdFromSelectedItem(selectedItem);

            // Check if idsalle is a valid integer
            if (idsalle != -1) {
                try {
                    // Find the salleee from the list using the idsalle
                    salleee salleeeToModify = findSalleById(idsalle);

                    if (salleeeToModify != null) {
                        // Retrieve the updated values from the TextFields
                        String newMatiere = matiere.getText();
                        String newIduserText = iduser.getText();

                        // Check if iduser is not empty
                        if (newIduserText.isEmpty()) {
                            showAlert("ID User Empty", "Please enter a valid ID User.");
                            return; // Exit the method if iduser is empty
                        }

                        // Parse iduser as int
                        int newIduserValue;
                        try {
                            newIduserValue = Integer.parseInt(newIduserText);
                        } catch (NumberFormatException e) {
                            showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
                            return; // Exit the method if iduser is not a valid integer
                        }

                        // Update the salleee object with new values
                        salleeeToModify.setMatiere(newMatiere);
                        salleeeToModify.setIduser(newIduserValue);

                        // Call the modifier method in ServiceSalle to update the database
                        SS.modifier(salleeeToModify);

                        // Refresh the ListView
                        afficherDB(event);
                    } else {
                        System.out.println("Salleee not found for idsalle: " + idsalle);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid idsalle");
            }
        } else {
            System.out.println("No item selected.");
        }
    }
/*
    @FXML
   void ajouter(ActionEvent event) {
       // Check if iduser is injected
       if (iduser == null) {
           showAlert("ID User Field Missing", "ID User field is not injected.");
           return;
       }

       // Retrieve values from the TextFields
       String matiereValue = matiere.getText();
       String iduserValueText = iduser.getText();

       // Check if iduser is not empty
       if (iduserValueText.isEmpty()) {
           showAlert("ID User Empty", "Please enter a valid ID User.");
           return; // Exit the method if iduser is empty
       }

       // Parse iduser as int
       int iduserValue;
       try {
           iduserValue = Integer.parseInt(iduserValueText);
       } catch (NumberFormatException e) {
           showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
           return; // Exit the method if iduser is not a valid integer
       }

       // Create a new salleee object
       salleee nouvelleSalle = new salleee(0, iduserValue, matiereValue); // 0 is a placeholder for idsalle

       try {
           // Add the new salleee to the database using ServiceSalle
           SS.ajouter(nouvelleSalle);

           // You may add additional logic here based on your requirements

       }  catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        System.out.println("Message: " + e.getMessage());
        // Handle the exception according to your needs
    }


    // Handle the exception according to your needs
       }

*/
@FXML
void ajouter(ActionEvent event) {
    // Check if iduser is injected
    if (iduser == null) {
        showAlert("ID User Field Missing", "ID User field is not injected.");
        return;
    }

    // Retrieve values from the TextFields
    String matiereValue = matiere.getText();
    String iduserValueText = iduser.getText();

    // Check if iduser is not empty
    if (iduserValueText.isEmpty()) {
        showAlert("ID User Empty", "Please enter a valid ID User.");
        return; // Exit the method if iduser is empty
    }

    // Parse iduser as int
    int iduserValue;
    try {
        iduserValue = Integer.parseInt(iduserValueText);
    } catch (NumberFormatException e) {
        showAlert("Invalid ID User", "Please enter a valid numeric ID User.");
        return; // Exit the method if iduser is not a valid integer
    }

    // Validate the matiere
    if (!isValidMatiere(matiereValue)) {
        showAlert("Invalid Matiere", "Please enter a valid matiere (java, web, marketing, design).");
        return; // Exit the method if matiere is not valid
    }

    // Create a new salleee object
    salleee nouvelleSalle = new salleee(0, iduserValue, matiereValue); // 0 is a placeholder for idsalle

    try {
        // Add the new salleee to the database using ServiceSalle
        SS.ajouter(nouvelleSalle);

        // You may add additional logic here based on your requirements

    }  catch (SQLException e) {
        e.printStackTrace();
        System.out.println("SQL State: " + e.getSQLState());
        System.out.println("Error Code: " + e.getErrorCode());
        System.out.println("Message: " + e.getMessage());
        // Handle the exception according to your needs
    }
}

    // Helper method to validate matiere
    private boolean isValidMatiere(String matiere) {
        // List of valid matiere values
        String[] validMatieres = {"java", "web", "marketing", "design"};

        // Check if matiere is in the list of valid values
        for (String validMatiere : validMatieres) {
            if (validMatiere.equalsIgnoreCase(matiere)) {
                return true;
            }
        }

        return false;
    }




    @FXML
    void supprimer(ActionEvent event) {
        // Get the selected salleee object from the ListView
        String selectedItem = sallelistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the idsalle from the selected item
            int idsalle = parseIdFromSelectedItem(selectedItem);

            // Check if idsalle is a valid integer
            if (idsalle != -1) {
                try {
                    // Find the salleee from the list using the idsalle
                    salleee salleToDelete = findSalleById(idsalle);

                    if (salleToDelete != null) {
                        // Delete the salleee from the database
                        SS.supprimer(salleToDelete);

                        // Refresh the ListView
                        afficherDB(event);
                    } else {
                        System.out.println("Salleee not found for idsalle: " + idsalle);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid idsalle");
            }
        } else {
            System.out.println("No item selected.");
        }
    }

    // Helper method to find salleee by idsalle
    private salleee findSalleById(int idsalle) {
        try {
            List<salleee> salles = SS.afficher();
            for (salleee salle : salles) {
                if (salle.getIdsalle() == idsalle) {
                    return salle;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null;
    }


    // Helper method to parse the idsalle from the string representation of a salleee object
    private int parseIdFromSelectedItem(String selectedItem) {
        try {
            // Assuming your string representation is in the format "Idsalle: <idsalle>, ..."
            int startIndex = selectedItem.indexOf("Idsalle:") + "Idsalle:".length();
            int endIndex = selectedItem.indexOf(",", startIndex);

            if (startIndex != -1 && endIndex != -1) {
                // Extract the substring containing idsalle
                String idString = selectedItem.substring(startIndex, endIndex);

                // Convert the extracted substring to an integer
                return Integer.parseInt(idString.trim());
            } else {
                // Handle the case where the substring does not contain valid idsalle information
                throw new NumberFormatException("Invalid idsalle format");
            }
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException, e.g., log the error or show an alert
            e.printStackTrace();
            return -1; // Return a default value or handle appropriately
        }
    }





    // Method to set the selected salleee
    public void setSelectedSalle(salleee selectedSalle) {
        this.selectedSalle = selectedSalle;
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
            List<salleee> salles = SS.afficher();

            // Clear any existing items in the ListView
            sallelistview.getItems().clear();

            // Add each salleee object to the ListView with a custom string representation
            for (salleee salle : salles) {
                // Customize the string representation to include idsalle, matiere, and iduser
                String displayString = "Idsalle: " + salle.getIdsalle() + ", Matiere: " + salle.getMatiere() + ", ID User: " + salle.getIduser();

                System.out.println(salle);
                sallelistview.getItems().add(displayString);
            }
            System.out.println(salles);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /* @FXML
    void afficherDB(ActionEvent event) {
        try {
            List<salleee> salles = SS.afficher();

            // Clear any existing items in the ListView
            sallelistview.getItems().clear();

            // Add each salleee object to the ListView with a custom string representation
            for (salleee salle : salles) {
                // Personnalisez la représentation de chaîne pour exclure l'id
                String displayString = "Matiere: " + salle.getMatiere() + ", ID User: " + salle.getIduser();

                System.out.println(salle);
                sallelistview.getItems().add(displayString);
            }
            System.out.println(salles);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    */


}
