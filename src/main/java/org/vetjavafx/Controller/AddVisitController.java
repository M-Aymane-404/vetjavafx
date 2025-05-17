package org.vetjavafx.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.vetjavafx.model.Owner;
import org.vetjavafx.model.Pet;
import org.vetjavafx.model.Visite;
import org.vetjavafx.model.DataManager;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class AddVisitController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    private Owner owner;
    private Pet pet;

    @FXML
    public void initialize() {
        // Set French locale for the DatePicker
        datePicker.setPromptText("JJ/MM/AAAA");
    }

    public void setOwnerAndPet(Owner owner, Pet pet) {
        this.owner = owner;
        this.pet = pet;
    }

    @FXML
    private void handleSaveButtonClick() {
        try {
            // Validate inputs
            if (datePicker.getValue() == null) {
                throw new IllegalArgumentException("La date est requise");
            }
            if (descriptionField.getText().isEmpty()) {
                throw new IllegalArgumentException("La description est requise");
            }

            // Format the date
            String formattedDate = datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Create new visit
            Visite newVisit = new Visite(formattedDate, descriptionField.getText());
            
            // Add visit to pet using the new method
            DataManager.addVisitToPet(owner, pet, newVisit);

            // Show success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("La visite a été ajoutée avec succès.");
            alert.showAndWait();

            // Navigate back to owner details
            navigateToOwnerDetails();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de la visite: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBackButtonClick() throws IOException {
        navigateToOwnerDetails();
    }

    private void navigateToOwnerDetails() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/vetjavafx/view/ownerDetails.fxml"));
        AnchorPane root = loader.load();
        OwnerDetailsController controller = loader.getController();
        controller.setOwner(owner);

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
} 