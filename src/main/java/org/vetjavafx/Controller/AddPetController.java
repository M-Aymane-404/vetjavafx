package org.vetjavafx.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.vetjavafx.model.Owner;
import org.vetjavafx.model.Pet;
import org.vetjavafx.model.DataManager;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AddPetController {

    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;

    private Owner owner;
    private static final List<String> PET_TYPES = Arrays.asList(
        "Chien", "Chat", "Oiseau", "Rongeur", "Reptile", "Autre"
    );

    @FXML
    public void initialize() {
        // Initialize the pet types ComboBox
        typeComboBox.getItems().addAll(PET_TYPES);
        
        // Set French locale for the DatePicker
        birthDatePicker.setPromptText("JJ/MM/AAAA");
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @FXML
    private void handleSaveButtonClick() {
        try {
            // Validate inputs
            if (nameField.getText().isEmpty()) {
                throw new IllegalArgumentException("Le nom de l'animal est requis");
            }
            if (typeComboBox.getValue() == null) {
                throw new IllegalArgumentException("Le type d'animal est requis");
            }
            if (birthDatePicker.getValue() == null) {
                throw new IllegalArgumentException("La date de naissance est requise");
            }

            // Format the date
            String formattedDate = birthDatePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Create new pet
            Pet newPet = new Pet(
                nameField.getText(),
                formattedDate,
                typeComboBox.getValue()
            );

            // Add pet to owner using the new method
            DataManager.addPetToOwner(owner, newPet);

            // Show success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("L'animal a été ajouté avec succès.");
            alert.showAndWait();

            // Navigate back to owner details
            navigateToOwnerDetails();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de l'animal: " + e.getMessage());
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