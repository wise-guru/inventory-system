package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import models.Inventory;
import models.Part;
import models.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 The AddProductController class handles user input and UI updates for the Add Product screen in the Inventory Management application.
 */
public class AddProductController implements Initializable {

    @FXML private TextField AddProductSearchField;
    @FXML private TextField AddProductId;
    @FXML private TextField AddProductName;
    @FXML private TextField AddProductInv;
    @FXML private TextField AddProductPriceCost;
    @FXML private TextField AddProductMax;
    @FXML private TextField AddProductMin;

    @FXML private TableView<Part> AddPartsTableView;
    @FXML private TableColumn<Part, Integer>  AddPartIdCol;
    @FXML private TableColumn<Part, String> AddPartNameCol;
    @FXML private TableColumn<Part, Integer> AddPartInvCol;
    @FXML private TableColumn<Part, Double> AddPartPriceCol;

    @FXML private  TableView<Part> AssociatedPartsTableView;
    @FXML private TableColumn<Part, Integer>  AssociatedPartsIdCol;
    @FXML private TableColumn<Part, String> AssociatedPartsNameCol;
    @FXML private TableColumn<Part, Integer> AssociatedPartsInvCol;
    @FXML private TableColumn<Part, Double> AssociatedPartsPriceCol;
    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     This method is called when the user clicks the "Save" button on the Add Product screen.
     It creates a new Product object and adds it to the Inventory, along with its associated Parts.
     It then navigates the user back to the Main Screen.
     @param actionEvent The ActionEvent that triggered this method call.
     */
    public void OnSaveButtonClick(ActionEvent actionEvent) throws IOException {
        try {
            int productID = (int) (Math.random() * 1000);

            String productName = AddProductName.getText();
            int productInventory = Integer.parseInt(AddProductInv.getText());
            double productPrice = Double.parseDouble(AddProductPriceCost.getText());
            int productMax = Integer.parseInt(AddProductMax.getText());
            int productMin = Integer.parseInt(AddProductMin.getText());

            if (productMax < productMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The product maximum must be greater than the minimum.");
                alert.showAndWait();
                return;
            } else if (productInventory < productMin || productInventory > productMax) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within the min and max.");
                alert.showAndWait();
                return;
            } else if(productInventory < 0 || productMin < 0 || productMax < 0 || productPrice < 0 ) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "All numbers must be greater than 0.");
                alert.showAndWait();
                return;
            } else {
                Product newProduct = new Product(productID, productName, productPrice, productInventory, productMin, productMax);

                for (Part part : associatedPartsList) {
                    if (part != associatedPartsList)
                        newProduct.addAssociatedPart(part);
                }

                Inventory.getAllProducts().add(newProduct);
            }
            Parent backToMain = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
            Scene scene = new Scene(backToMain);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Input Error");
            alert.setContentText("Incorrect value");
            alert.showAndWait();
            return;
        }
    }

    /**
     Handles the action of clicking on the Cancel button, which takes the user back to the Main Screen.
     @param actionEvent the event triggered by clicking the Cancel button
     @throws IOException if there is an error loading the Main Screen FXML file
     */
    public void OnCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent cancel = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        Scene scene = new Scene(cancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     Handles the action of clicking on the Search button, which filters the parts data based on the user's search query.
     */
    public void OnSearchButtonClick(ActionEvent actionEvent) {
        String query = AddProductSearchField.getText().toLowerCase();

        // Clear any existing selection
        AddPartsTableView.getSelectionModel().clearSelection();

        // Filter the parts data based on the search query
        ObservableList<Part> filteredData = FXCollections.observableArrayList();
        boolean foundById = false; // Flag to keep track if a matching ID is found
        for (Part part : Inventory.getAllParts()) {
            if (part.getName().toLowerCase().contains(query)) {
                filteredData.add(part);
            }
            if (Integer.toString(part.getId()).equals(query)) {
                filteredData.add(part);
                foundById = true; // Set the flag to true if a matching ID is found
            }
        }

        if (foundById) {
            // Update the tableview with the filtered data
            AddPartsTableView.setItems(filteredData);
        } else {
            // Show a warning dialog box if no match is found for the search query
            if (filteredData.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Part Found");
                alert.setHeaderText(null);
                alert.setContentText(query + " was not found, please search again.");
                alert.showAndWait();
            } else {
                // Update the tableview with the filtered data
                AddPartsTableView.setItems(filteredData);
            }
        }
    }


    /**
     Handles the action of clicking on the Add button, which adds the selected part to the associated parts list.
     @param actionEvent the event triggered by clicking the Add button
     */
    public void OnAddButtonClick(ActionEvent actionEvent) throws IOException {
        Part selectedPart = AddPartsTableView.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            associatedPartsList.add(selectedPart);
            AssociatedPartsTableView.setItems(associatedPartsList);
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setContentText("Select part from list");
            alert.showAndWait();
            return;
        }

    }

    /**
     Handles the action of clicking on the Delete button, which removes the selected part from the associated parts list.
     @param actionEvent the event triggered by clicking the Delete button
     */
    public void OnDeleteButtonClick(ActionEvent actionEvent) {
        Part selectedPart = AssociatedPartsTableView.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Do you want to delete this associated part from the product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedPartsList.remove(selectedPart);
                AssociatedPartsTableView.setItems(associatedPartsList);
            }
        }

        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No part chosen");
            alert.setContentText("Please select part from list.");
            alert.showAndWait();
            return;
        }
    }

    /**
     Initializes the view by setting up the table views for displaying the parts and associated parts data.
     @param url the URL of the FXML file for the view
     @param resourceBundle the resource bundle for the view
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddPartsTableView.setItems(Inventory.getAllParts());
        AddPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AddPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //add parts to associated table (bottom)
        AssociatedPartsTableView.setItems(associatedPartsList);
        AssociatedPartsIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        AddProductSearchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                OnSearchButtonClick(new ActionEvent());
            }
        });
    }
}
