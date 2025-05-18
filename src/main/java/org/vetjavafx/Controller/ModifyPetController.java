package org.vetjavafx.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.vetjavafx.model.Owner;
import org.vetjavafx.model.Pet;
import org.vetjavafx.model.DataManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ModifyPetController {
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private TextField birthDateField;
    @FXML
    private Button saveButton;
    @FXML
    private Button addVisitButton;
    @FXML
    private Button backButton;

    private Owner owner;
    private Pet pet;

    private static final List<String> PET_TYPES = Arrays.asList(
        "Chien", "Chat", "Oiseau", "Rongeur", "Reptile"
    );

    @FXML
    public void initialize() {
        // Initialize the pet types ComboBox
        typeComboBox.getItems().addAll(PET_TYPES);
    }

    public void setOwnerAndPet(Owner owner, Pet pet) {
        this.owner = owner;
        this.pet = pet;
        updateFields();
    }

    private void updateFields() {
        nameField.setText(pet.getName());
        typeComboBox.setValue(pet.getType());
        birthDateField.setText(pet.getBirthDate());
    }

    @FXML
    private void handleSaveButtonClick() throws IOException {
        // Update pet information
        pet.setName(nameField.getText());
        pet.setType(typeComboBox.getValue());
        pet.setBirthDate(birthDateField.getText());

        // Save the changes
        DataManager.saveOwners(DataManager.loadOwners());

        // Navigate back to owner details
        navigateToOwnerDetails();
    }

    @FXML
    private void handleAddVisitButtonClick() throws IOException {
        // Load the add visit scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/vetjavafx/view/addVisit.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        // Get the controller and set the owner and pet
        AddVisitController controller = loader.getController();
        controller.setOwnerAndPet(owner, pet);

        // Get the current stage and set the new scene
        Stage stage = (Stage) addVisitButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleBackButtonClick() throws IOException {
        navigateToOwnerDetails();
    }

    private void navigateToOwnerDetails() throws IOException {
        // Load the owner details scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/vetjavafx/view/ownerDetails.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        // Get the controller and set the owner
        OwnerDetailsController controller = loader.getController();
        controller.setOwner(owner);

        // Get the current stage and set the new scene
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
} 