package org.vetjavafx.Controller;

import com.almasb.fxgl.scene.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.vetjavafx.model.Owner;

public class OwnerController {

    @FXML
    private TableView<Owner> ownerTable;
    @FXML
    private TableColumn<Owner, String> nameColumn;
    @FXML
    private TableColumn<Owner, String> addressColumn;
    @FXML
    private TableColumn<Owner, String> cityColumn;
    @FXML
    private TableColumn<Owner, Integer> phoneColumn;
    @FXML
    private TableColumn<Owner, Button> detailsColumn;
    @FXML
    private TextField searchField;  // Search bar

    private ObservableList<Owner> ownerData = FXCollections.observableArrayList();

    public void initialize() {

        // Set up TableColumns
        nameColumn.setCellValueFactory(cellData -> {
            // Wrap the full name in SimpleStringProperty for TableColumn to work
            return new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFullName());
        });
        addressColumn.setCellValueFactory(cellData -> {
            return new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress());
        });
        cityColumn.setCellValueFactory(cellData -> {
            return new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCity());
        });
        phoneColumn.setCellValueFactory(cellData -> {
            return new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getPhone()).asObject();
        });

        // Button for details in each row
        detailsColumn.setCellFactory(column -> {
            return new TableCell<Owner, Button>() {
                private final Button detailsButton = new Button("Details");

                {
                    detailsButton.setOnAction(event -> {
                        Owner owner = getTableView().getItems().get(getIndex());
                        showOwnerDetails(owner);
                    });
                }

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null); // Don't show anything for empty rows
                    } else {
                        setGraphic(detailsButton);
                    }
                }
            };
        });

        // Sample data
        ownerData.add(new Owner("John", "Doe", "123 Main St", "City", 123456789));
        ownerData.add(new Owner("Jane", "Smith", "456 Oak St", "Town", 987654321));
        ownerData.add(new Owner("Michael", "Johnson", "789 Pine St", "Village", 555123456));

        // Set data to table
        ownerTable.setItems(ownerData);

        // Set up search filter
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData(newValue);
        });
    }

    // Method to filter data based on search input
    private void filterData(String query) {
        ObservableList<Owner> filteredData = FXCollections.observableArrayList();
        for (Owner owner : ownerData) {
            if (owner.getFullName().toLowerCase().contains(query.toLowerCase())) {
                filteredData.add(owner);
            }
        }
        ownerTable.setItems(filteredData);
    }


    // Method to show owner details
    private void showOwnerDetails(Owner owner) {
        System.out.println("Owner Details: " + owner.getFirstName() + " " + owner.getLastName());
        // Implement the navigation or other actions for the details
    }

    }


