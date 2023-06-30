module inventory.mylainventorysystem {
    requires javafx.controls;
    requires javafx.fxml;


//    opens inventory.mylainventorysystem to javafx.fxml;
//    exports inventory.mylainventorysystem;
    exports controllers;
    opens controllers to javafx.fxml;
    opens models;
    exports main;
    opens main to javafx.fxml;
//    exports controllers;
//    opens controllers to javafx.fxml;
}