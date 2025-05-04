package org.vetjavafx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.vetjavafx.model.Owner;
import javafx.stage.Stage;

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

    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleSaveOwnerButtonClick() {
        // Get the owner details from the fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        int phone = Integer.parseInt(phoneField.getText());

        // Create a new Owner object
        Owner newOwner = new Owner(firstName, lastName, address, city, phone);

        // You can now add this owner to the list or database, or notify the main controller
        System.out.println("Owner Added: " + newOwner.getFirstName() + " " + newOwner.getLastName());

        // Close the dialog
        dialogStage.close();
    }
}
