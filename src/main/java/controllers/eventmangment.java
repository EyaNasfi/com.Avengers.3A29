
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.util.List;

import javafx.stage.Stage;
import models.event;


import services.eventservice;

public class eventmangment {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private Button afficher;

    @FXML
    private ListView<String> eventlistview;

    @FXML
    private DatePicker date;

    @FXML
    private TextField heure;

    @FXML
    private TextField id;

    @FXML
    private TextField lieu;

    @FXML
    private TextField nom;

    private eventservice services;

    private event selectedevent; // Store the selected event
    @FXML
    void club(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ClubMangement.fxml"));
        Parent root = loader.load();
        Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        st.setScene(scene);
        st.show();

    }
    eventservice es = new eventservice();


    // Inject EventService into the controller
    eventservice service = new eventservice();

    @FXML
    void ajouter(ActionEvent event) {
        // Récupérer les valeurs des champs
        String nomValue = nom.getText();
        String lieuValue = lieu.getText();
        LocalDate dateValue = date.getValue();
        String heureValue = heure.getText();

        // Vérifier que tous les champs sont remplis
        if (nomValue.isEmpty() || lieuValue.isEmpty() || dateValue == null || heureValue.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs.");
            return;
        }

        // Vérifier que la première lettre du nom est majuscule dans l'intervalle [A..Z]
        if (!Character.isUpperCase(nomValue.charAt(0))) {
            showAlert("Erreur", "Le nom de l'événement doit commencer par une majuscule.");
            return;
        }

        // Convertir l'heure en LocalTime
        LocalTime timeValue = LocalTime.parse(heureValue);

        // Créer un nouvel objet event avec les valeurs saisies
        event nouvelEvent = new event(nomValue, lieuValue, dateValue, timeValue);

        try {
            // Ajouter le nouvel événement à la base de données en utilisant l'eventservice
            es.add(nouvelEvent);
            showAlert("event ajouté", "L'event a été ajouté avec succès.");


            // Vous pouvez ajouter une logique supplémentaire ici en fonction de vos besoins

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception en fonction de vos besoins
        }
    }

    private event findeventbyId(int id) {
        try {
            List<event> events = es.afficher();
            for (event event : events) {
                if (event.getId() == id) {
                    return event;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null;
    }
    @FXML
    void modifier(ActionEvent event) {
        // Get the selected event object from the ListView
        String selectedItem = eventlistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int eventId = parseIdFromSelectedItem(selectedItem);
            // Check if eventId is a valid integer
            if (eventId != -1) {
                try {
                    // Find the event from the list using the eventId
                    event eventToModify = findeventbyId(eventId);
                    if (eventToModify != null) {
                        // Retrieve the updated values from the TextFields
                        String newName = nom.getText();
                        String newLocation = lieu.getText();
                        LocalDate newDate = date.getValue();
                        String newTime = heure.getText();

                        // Convert newTime to LocalTime
                        LocalTime newTimeValue = LocalTime.parse(newTime);

                        // Update the event object with new values
                        eventToModify.setName(newName);
                        eventToModify.setLieu(newLocation);
                        eventToModify.setDate(newDate);
                        eventToModify.setHeure(newTimeValue);

                        // Call the modifier method in EventService to update the database
                        es.modifier(eventToModify);

                        // Refresh the ListView
                        afficher(event);
                    } else {
                        System.out.println("Event not found for ID: " + eventId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid event ID.");
            }
        } else {
            System.out.println("No item selected.");
        }
    }


    @FXML
    void supprimer(ActionEvent event) {
        // Get the selected event object from the ListView
        String selectedItem = eventlistview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            // Parse the ID from the selected item
            int eventId = parseIdFromSelectedItem(selectedItem);

            // Check if eventId is a valid integer
            if (eventId != -1) {
                try {
                    // Find the event from the list using the eventId
                    event eventToDelete = findeventbyId(eventId);

                    if (eventToDelete != null) {
                        // Delete the event from the database
                        es.delete(eventToDelete);

                        // Refresh the ListView
                        afficher(event);
                    } else {
                        System.out.println("Event not found for ID: " + eventId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception appropriately
                }
            } else {
                System.out.println("Invalid event ID");
            }
        } else {
            System.out.println("No item selected.");
        }
    }


    private int parseIdFromSelectedItem(String selectedItem) {
        try {
            // Split the selectedItem by comma to extract the part containing ID
            String[] parts = selectedItem.split(",");

            // Iterate through the parts to find the one containing ID
            for (String part : parts) {
                if (part.trim().startsWith("ID:")) {
                    // Extract the substring containing ID
                    String idString = part.trim().substring("ID:".length());

                    // Convert the extracted substring to an integer
                    return Integer.parseInt(idString.trim());
                }
            }

            // Handle the case where ID is not found in the selected item
            throw new IllegalArgumentException("ID not found in the selected item.");
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException, e.g., log the error or show an alert
            e.printStackTrace();
            return -1; // Return a default value or handle appropriately
        } catch (IllegalArgumentException e) {
            // Handle the case where ID is not found in the selected item
            e.printStackTrace();
            return -1; // Return a default value or handle appropriately
        }
    }





    // Method to set the selected salle
    public void setSelectedSalle(event selectedevent) {
        this.selectedevent = selectedevent;
    }

    // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

/*
    // existing code...
    @FXML
    void afficher(ActionEvent event) {
        try {
            List<event> events = es.afficher();

            // Clear any existing items in the ListView
            eventlistview.getItems().clear();

            // Add each event object to the ListView with a custom string representation
            for (event eventt  : events) {
                // Personnalisez la représentation de chaîne pour l'événement
                String displayString = "Nom de l'événement: " + eventt.getName() + ", Date: " + eventt.getDate();

                eventlistview.getItems().add(displayString);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    */


    @FXML
    void afficher(ActionEvent event) {
        try {
            List<event> events = es.afficher();

            // Clear any existing items in the ListView
            eventlistview.getItems().clear();

            // Add each event object to the ListView with a custom string representation
            for (event eventt : events) {
                // Personnalisez la représentation de chaîne pour l'événement
                String displayString = "ID: " + eventt.getId() + ", Nom de l'événement: " + eventt.getName() + ", Lieu: " + eventt.getLieu() + ", Date: " + eventt.getDate() + ", Heure: " + eventt.getHeure();

                // Ajouter la représentation de chaîne à la ListView
                eventlistview.getItems().add(displayString);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des événements: " + e.getMessage());
        }
    }


}



