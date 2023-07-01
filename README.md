# Inventory Management System

The Inventory Management system is a JavaFX-based program designed to help a small manufacturing organization efficiently manage their inventory. The application replaces the existing manual system and provides a more sophisticated solution for inventory tracking and control.

![Main Screen](readmeImages/MainScreenScreenshot.png?raw=true "Main Screen")
![Modify Product Screen](readmeImages/ModifyProductScreenshot.png?raw=true "Add Part Screen")
![Add Part Screen](readmeImages/AddPartScreenshot.png?raw=true "Add Part Screen")

## Features
The application offers the following key features:

**User-friendly Interface**: The graphical user interface (GUI) provides an intuitive layout that closely matches the organization's requirements. The UI includes main forms for viewing parts and products, as well as dedicated forms for adding and modifying parts and products.

**Parts Management**: The Parts pane allows users to add new parts, modify existing parts, and delete parts from the inventory. Users can search for parts by ID or name, and the application highlights or filters matching results. The UI includes error messages and confirmation dialogs for appropriate actions.

**Products Management**: The Products pane enables users to add new products, modify product details, and delete products from the inventory. Users can search for products by ID or name, with the application providing highlighting and filtering functionality. Users can associate parts with products and remove associated parts as needed.

**Input Validation**: The application implements input validation and logical error checks to ensure data integrity and prevent inappropriate user data entry. Error messages are displayed in the UI or through dialog boxes, covering scenarios such as invalid minimum and maximum values, associations with products, and confirmation for delete and remove actions.

**Auto-generation of IDs**: The application automatically generates unique IDs for parts and products, simplifying the data entry process for users.

## Documentation
Detailed documentation in the form of Javadoc comments is provided throughout the codebase. The Javadoc comments describe each class member and include explanations of corrected logical or runtime errors and suggestions for future enhancements.


## How to Run
To run the Inventory Management Application, follow these steps:

1. Ensure you have Java Development Kit (JDK) installed on your system. You can download the latest JDK version from the official Oracle website.

2. Clone this repository to your local machine or download the source code as a ZIP file and extract it.

3. Open your preferred Integrated Development Environment (IDE) or a command-line interface.

4. Import the project into your IDE or navigate to the project directory in the command-line interface.

5. Locate the main class file, Main.java, and execute the main method.

6. The application's graphical user interface (GUI) should launch, displaying the main form with options to manage parts and products.

7. Interact with the GUI to add, modify, and delete parts and products as needed. Use the search functionality to locate specific items by ID or name.

8. To exit the application, click the "Exit" button or close the application window.

Note: Make sure that you have the necessary dependencies and libraries configured in your IDE or build system.