package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

/** This class is the controller for the Modify Product screen.
 It handles user interactions with the screen's UI elements and updates the product and associated parts data in the Inventory model.
 */

public class ModifyProductController {
//    private Product productToModify;
    @FXML
    private TextField ModifyProductId;
    @FXML private TextField ModifyProductName;
    @FXML private TextField ModifyProductInv;
    @FXML private TextField ModifyProductPriceCost;
    @FXML private TextField ModifyProductMax;
    @FXML private TextField ModifyProductMin;
    @FXML private TableView<Part> ModifyAddPartsTableView;
    @FXML private TableColumn<Part, Integer> ModifyAddPartIdCol;
    @FXML private TableColumn<Part, String> ModifyAddPartNameCol;
    @FXML private TableColumn<Part, Integer> ModifyAddPartInvCol;
    @FXML private TableColumn<Part, Double> ModifyAddPartPriceCol;
    @FXML private TableView<Part> AssociatedPartsTableView;
    @FXML private TableColumn<Part, Integer> AssociatedPartIdCol;
    @FXML private TableColumn<Part, String> AssociatedPartNameCol;
    @FXML private TableColumn<Part, Integer> AssociatedPartInvCol;
    @FXML private TableColumn<Part, Double> AssociatedPartPriceCol;
    @FXML private TextField ModifyProductSearchField;

    int productIndex;

    private ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();


    /**
     Handles the event when the user clicks the "Save" button.
     @param actionEvent The event object generated when the button is clicked.
     */
    public void OnSaveButtonClick(ActionEvent actionEvent) throws IOException {
        try {
            int productID = Integer.parseInt(ModifyProductId.getText());
            String productName = ModifyProductName.getText();
            int productInventory = Integer.parseInt(ModifyProductInv.getText());
            double productPrice = Double.parseDouble(ModifyProductPriceCost.getText());
            int productMax = Integer.parseInt(ModifyProductMax.getText());
            int productMin = Integer.parseInt(ModifyProductMin.getText());

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
                Product updatedProduct = new Product(productID, productName, productPrice, productInventory, productMin, productMax);

                if (updatedProduct != associatedPartsList) {
                    Inventory.updateProduct(productIndex, updatedProduct);
                }

                for (Part part : associatedPartsList) {
                    if (part != associatedPartsList) {
                        updatedProduct.addAssociatedPart(part);
                    }
                }

//                Inventory.getAllProducts().add(updatedProduct);
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
     Handles the event when the user clicks the "Cancel" button.
     @param actionEvent The event object generated when the button is clicked.
     */
    public void OnCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent cancel = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        Scene scene = new Scene(cancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     Handles the event when the user clicks the "Search" button.
     Filters the list of available parts based on the search query and updates the table view.
     @param actionEvent The event object generated when the button is clicked.
     */
    public void OnSearchButtonClick(ActionEvent actionEvent) {
        String query = ModifyProductSearchField.getText().toLowerCase();

        // Clear any existing selection
        ModifyAddPartsTableView.getSelectionModel().clearSelection();

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
            ModifyAddPartsTableView.setItems(filteredData);
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
                ModifyAddPartsTableView.setItems(filteredData);
            }
        }
    }


    /**
     Handles the event when the user clicks the "Add" button.
     Adds the selected part to the list of associated parts for the product being modified.
     If the selected part is already associated with the product, shows a warning dialog.
     If no part is selected, shows a warning dialog.
     @param actionEvent The event object generated when the button is clicked.
     */
    public void OnAddButtonClick(ActionEvent actionEvent) {
        Part selectedProduct = ModifyAddPartsTableView.getSelectionModel().getSelectedItem();

        if(selectedProduct != null && !associatedPartsList.contains(selectedProduct)) {
            associatedPartsList.add(selectedProduct);
            AssociatedPartsTableView.setItems(associatedPartsList);
        } else if(associatedPartsList.contains(selectedProduct)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Already Selected");
            alert.setContentText("This part is already associated with this product.");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid Input");
            alert.setContentText("Select part from list.");
            alert.showAndWait();
        }
    }

    /**
     Handles the event when the user clicks the "Delete" button.
     Removes the selected part from the list of associated parts for the product being modified.
     If no part is selected, shows a warning dialog.
     @param actionEvent The event object generated when the button is clicked.
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
     Sets the product data to be modified in the UI fields.
     @param product The selected product being modified in the list of products.

     I Ran into issue where adding an associated part to a product in the modifyProduct screen reset the display of the previous parts. I realized it was because the associated parts were not being saved correctly.
     I added the for loop to save and display each associated part correctly.
     */
    public void setProductFields(Product product) {
        productIndex = Inventory.getAllProducts().indexOf(product);

        ModifyProductId.setText(Integer.toString(product.getId()));
        ModifyProductName.setText(product.getName());
        ModifyProductInv.setText(Integer.toString(product.getStock()));
        ModifyProductPriceCost.setText(Double.toString(product.getPrice()));
        ModifyProductMax.setText(Integer.toString(product.getMax()));
        ModifyProductMin.setText(Integer.toString(product.getMin()));

        for (Part part: product.getAllAssociatedParts()) {
            associatedPartsList.add(part);
        }
        System.out.println(associatedPartsList);

    }

    /**
     Initializes the Modify Product screen with the given product object and sets up the table views for adding anddisplaying associated parts.
     Also sets up the search field for searching available parts.
     @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     @param resourceBundle The resource bundle used to localize the root object, or null if the root object was not localized.
     @param product The product object to be modified.
     */
    public void initialize(URL url, ResourceBundle resourceBundle, Product product) {
        setProductFields(product);

        ModifyAddPartsTableView.setItems(Inventory.getAllParts());
        ModifyAddPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyAddPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModifyAddPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ModifyAddPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //add parts to associated table (bottom)
        AssociatedPartsTableView.setItems(associatedPartsList);
        AssociatedPartsTableView.setItems(FXCollections.observableArrayList(product.getAllAssociatedParts()));
        AssociatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedPartInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        ModifyProductSearchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                OnSearchButtonClick(new ActionEvent());
            }
        });

    }
}
