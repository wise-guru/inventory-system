package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InHousePart;
import models.Inventory;
import models.OutsourcedPart;
import models.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**

 This class is the controller for the "Add Part" screen in the Inventory Management System.
 It handles user input and validation, creates and adds new Part objects to the Inventory, and allows the user to navigate back to the main screen.
 */

public class AddPartController implements Initializable {

    public Label MachineIdLabel;
    public RadioButton addPartInHouse;
    public RadioButton addPartOutsourced;

    @FXML private TextField addPartId;
    @FXML private TextField addPartName;
    @FXML private TextField addPartInv;
    @FXML private TextField addPartPriceCost;
    @FXML private TextField addPartMin;
    @FXML private TextField addPartMax;
    @FXML private TextField addPartMachineId;
    @FXML private TextField addPartCompanyName;


    /**
     This method is called when the "Add" button is clicked on the "Add Part" screen.
     It retrieves the user input from the text fields, performs validation, creates a new Part object, and adds it to the Inventory.
     If there is invalid input, an error message will be displayed.
     @param actionEvent The ActionEvent triggered by the "Add" button.
     */

    public void OnAddButtonClick(ActionEvent actionEvent) {
        try {
            System.out.println("Add product save button clicked!");
            int partId = (int) (Math.random() * 100);
            String partName = addPartName.getText();
            int partInventory = Integer.parseInt(addPartInv.getText());
            double partPrice = Double.parseDouble(addPartPriceCost.getText());
            int partMin = Integer.parseInt(addPartMin.getText());
            int partMax = Integer.parseInt(addPartMax.getText());

//        String partMachineId = addPartMachineId.getText();
            int partMachineId = 0;
            String partCompanyName;

            if (partMax < partMin) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The part maximum must be greater than the minimum.");
                alert.showAndWait();
                return;
            } else if (partInventory < partMin || partInventory > partMax) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Inventory must be within the min and max.");
                alert.showAndWait();
                return;
            } else if(partInventory < 0 || partMin < 0 || partMax < 0 ) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "All numbers must be greater than 0.");
                alert.showAndWait();
                return;
            }
            else {

                if (addPartInHouse.isSelected()) {
                    try {
                        partMachineId = Integer.parseInt(addPartMachineId.getText());
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID must be a number.");
                        alert.showAndWait();
                        return;
                    }
                    Part newPart = new InHousePart(partId, partName, partPrice, partInventory, partMin, partMax, partMachineId);
                    Inventory.addPart(newPart);
                }
                if (addPartOutsourced.isSelected()) {
                    partCompanyName = addPartMachineId.getText();
                    Part newPart = new OutsourcedPart(partId, partName, partPrice, partInventory, partMin, partMax, partCompanyName);
                    Inventory.addPart(newPart);
                }
            }
            Parent backToMain = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
            Scene scene = new Scene(backToMain);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }   catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid input");
            alert.setContentText("Value must be a number.");
            alert.showAndWait();
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     This method is called when the user clicks the "In-House" button and updates the label for the machine ID field.
     @param actionEvent The action event that triggered the method.
     */
    public void OnInHouseButtonClick(ActionEvent actionEvent) {
        MachineIdLabel.setText("Machine ID");
    }

    /**
     This method is called when the user clicks the "Outsourced" button and updates the label for the machine ID field.
     @param actionEvent The action event that triggered the method.
     */
    public void OnOutsourcedButtonClick(ActionEvent actionEvent) {
        MachineIdLabel.setText("Company Name");
    }

    /**
     This method is called when the user clicks the "Cancel" button and returns to the main screen.
     @param actionEvent The action event that triggered the method.
     */
    public void OnCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent cancel = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        Scene scene = new Scene(cancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
