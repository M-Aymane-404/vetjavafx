package org.vetjavafx.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.vetjavafx.model.Owner;

import java.io.IOException;

public class AddOwnerController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button saveOwnerButton;

    // When the save button is clicked
    @FXML
    private void handleSaveOwnerButtonClick() {
        try {
            // Get the owner details from the fields
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            int phone = Integer.parseInt(phoneField.getText());

            // Create a new Owner object
            Owner newOwner = new Owner(firstName, lastName, address, city, phone);

            // You can add this owner to a list or database here
            System.out.println("Owner Added: " + newOwner.getFirstName() + " " + newOwner.getLastName());




            // Load the new scene (after saving the new owner)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/vetjavafx/listerOwners.fxml"));
            Scene newScene = new Scene(loader.load());
            // Une fois que le propriétaire est ajouté dans AddOwnerController
            OwnerController ownerController = loader.getController();
            ownerController.addOwnerToList(newOwner);

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) saveOwnerButton.getScene().getWindow();
            currentStage.setScene(newScene);

            // Show the new scene
            currentStage.show();
        } catch (IOException | NumberFormatException e) {
            // Show an error message or log the issue
            System.err.println("Error while saving the owner: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for debugging
        }
    }
}
