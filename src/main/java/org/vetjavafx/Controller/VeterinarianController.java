package org.vetjavafx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.vetjavafx.HelloApplication;
import org.vetjavafx.model.Veterinarian;

public class VeterinarianController {

    @FXML
    private TableView<Veterinarian> veterinarianTable;
    @FXML
    private TableColumn<Veterinarian, String> nameColumn;
    @FXML
    private TableColumn<Veterinarian, String> specializationColumn;
    @FXML
    private Button veterinariansButton;
    @FXML
    private Button ownersButton;



    private ObservableList<Veterinarian> veterinarianData = FXCollections.observableArrayList();

    public void initialize() {

      // Table column binding
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        specializationColumn.setCellValueFactory(cellData -> cellData.getValue().specializationProperty());

        // Adding data to the ObservableList
        veterinarianData.add(new Veterinarian("Dr. Emma Lefevre", "Médecine interne"));
        veterinarianData.add(new Veterinarian("Dr. Martin Dubois", "Chirurgie"));
        veterinarianData.add(new Veterinarian("Dr. Sophie Bernard", "Dermatologie"));
        veterinarianData.add(new Veterinarian("Dr. Julien Lambert", "Dentisterie"));

        // Set data in TableView
        veterinarianTable.setItems(veterinarianData);
    }

    // Event handler for button click (optional)
    @FXML
    private void handleVeterinariansButtonClick() {
        // Handle button click, e.g., navigate to another screen or perform other actions
        System.out.println("Vétérinaires Button Clicked");
    }

    @FXML
    private void handleOwnersButtonClick() {
        // Handle button click for owners button
        System.out.println("Propriétaires Button Clicked");
    }
}
