package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
 This class is a controller for the Modify Part screen.
 It contains methods for handling user input, updating part information, and navigating back to the main screen.
 The class uses FXML to create the user interface and interacts with the Inventory class to update part information.
 */
public class ModifyPartController {
    public Label ModifyPartMachineIdLabel;
    public RadioButton ModifyPartInHouse;
    public RadioButton ModifyPartOutsourced;

    @FXML
    private TextField ModifyPartId;
    @FXML private TextField ModifyPartName;
    @FXML private TextField ModifyPartInv;
    @FXML private TextField ModifyPartPriceCost;
    @FXML private TextField ModifyPartMin;
    @FXML private TextField ModifyPartMax;
    @FXML private TextField ModifyPartMachineId;

    @FXML private int partIndex;

    /**
      The OnInHouseButtonClick method is called when the user selects the in-house radio button.
      It sets the ModifyPartMachineId label to display "Machine ID".
      @param actionEvent The action event triggered by the button click.
     */
    public void OnInHouseButtonClick(ActionEvent actionEvent) {
        ModifyPartMachineIdLabel.setText("Machine ID");
    }

    /**
     The OnOutsourcedButtonClick method is called when the user selects the outsourced radio button.
     It sets the ModifyPartMachineId label to display "Company Name".
     @param actionEvent The action event triggered by the button click.
     */
    public void OnOutsourcedButtonClick(ActionEvent actionEvent) {
        ModifyPartMachineIdLabel.setText("Company Name");
    }

    /**
     The OnSaveButtonClick method handles the user's request to save changes made to the selected part.
     It retrieves the new values entered by the user and updates the part in the inventory system with the new values.
     If any of the input values are invalid, an appropriate error message is displayed to the user.
     If the update is successful, the user is returned to the main screen.
     @param actionEvent The action event triggerred by the button click.
     */
    public void OnSaveButtonClick(ActionEvent actionEvent) {
        try {
            System.out.println("Add product save button clicked!");
            int partId = Integer.parseInt(ModifyPartId.getText());
            String partName = ModifyPartName.getText();
            int partInventory = Integer.parseInt(ModifyPartInv.getText());
            double partPrice = Double.parseDouble(ModifyPartPriceCost.getText());
            int partMin = Integer.parseInt(ModifyPartMin.getText());
            int partMax = Integer.parseInt(ModifyPartMax.getText());


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

                if (ModifyPartInHouse.isSelected()) {
                    try {
                        partMachineId = Integer.parseInt(ModifyPartMachineId.getText());
                    } catch (NumberFormatException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Machine ID must be a number.");
                        alert.showAndWait();
                        return;
                    }
                    Part updatedPart = new InHousePart(partId, partName, partPrice, partInventory, partMin, partMax, partMachineId);
                    Inventory.updatePart(partIndex, updatedPart);
                }
                if (ModifyPartOutsourced.isSelected()) {
                    partCompanyName = ModifyPartMachineId.getText();
                    Part updatedPart = new OutsourcedPart(partId, partName, partPrice, partInventory, partMin, partMax, partCompanyName);
                    Inventory.updatePart(partIndex, updatedPart);
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

    /**
     The OnCancelButtonClick method handles the user's request to cancel the modification process and return to the main screen.
     @param actionEvent The action event triggered by the button click.
     */
    public void OnCancelButtonClick(ActionEvent actionEvent) throws IOException {
        Parent cancel = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        Scene scene = new Scene(cancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     The setFields method sets the fields in the UI with the current values of the selected part.
     @param part The selected part being modified.
     */
    private void setFields(Part part) {
        partIndex = Inventory.getAllParts().indexOf(part);
        ModifyPartId.setText(String.valueOf(part.getId()));
        ModifyPartName.setText(part.getName());
        ModifyPartInv.setText(String.valueOf(part.getStock()));
        ModifyPartPriceCost.setText(String.valueOf(part.getPrice()));
        ModifyPartMin.setText(String.valueOf(part.getMin()));
        ModifyPartMax.setText(String.valueOf(part.getMax()));

        if (part instanceof InHousePart) {
            ModifyPartMachineIdLabel.setText("Machine ID");
            ModifyPartInHouse.setSelected(true);
            System.out.println("Machine ID: " + ((InHousePart) part).getMachineId());
            ModifyPartMachineId.setText(String.valueOf(((InHousePart) part).getMachineId()));
        } else {
            ModifyPartMachineIdLabel.setText("Company Name");
            ModifyPartOutsourced.setSelected(true);
            System.out.println("Company Name: " + ((OutsourcedPart) part).getCompanyName());
            ModifyPartMachineId.setText(((OutsourcedPart) part).getCompanyName());
        }
    }



    /**
     The setFields method sets the fields in the UI with the current values of the selected part.
     The initialize method initializes the UI with the current values of the selected part when the Modify Part screen is loaded.
     @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     @param resourceBundle The resource bundle used to localize the root object, or null if the root object was not localized.
     @param part The part object to be modified.
     */
    public void initialize(URL url, ResourceBundle resourceBundle, Part part) {
        setFields(part);
    }


}
