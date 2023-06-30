package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;

/**
 Future enhancement: A possible future enhancement could be implementing an automated reordering system. This feature could monitor inventory quantities of parts and products
 and generate reorder suggestions, or even automatically create purchase orders when certain thresholds are reached.

 Logical error: When switching between the two radio buttons on the ModifyPart Screen, the value would become company name or machine ID,
 which made me realize I set the text to change in the Text field instead of the label.
 The buttons also had the option to both be selected at once. To fix this, I added a toggle group.

 The Javadoc folder is included separately in the submission.
 */

/** The Main class is the entry point for this application.  */
public class Main extends Application {

    /** Loads the main screen of this application. */
    @Override
    public void start(Stage stage) throws IOException {
        Parent MainScreen = FXMLLoader.load(getClass().getResource("/views/MainScreen.fxml"));
        Scene scene = new Scene(MainScreen, 905, 494);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        Part fretboard = new InHousePart(1, "Fretboard", 10.0, 5, 1, 10, 123);
        Part strings = new OutsourcedPart(2, "Strings", 20.0, 10, 1, 20, "Elixir Inc.");
        Product guitar = new Product(3, "Guitar", 15.0, 7, 1, 15);
        Product violin = new Product(4, "Violin", 250, 10, 1, 20 );

        Inventory.addPart(fretboard);
        Inventory.addPart(strings);
        Inventory.addProduct(guitar);
        Inventory.addProduct(violin);

        launch();
    }
}