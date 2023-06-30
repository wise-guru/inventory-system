package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 The Inventory class that manages the collection of parts and products.
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     Adds a part to the inventory.
     @param part the part to add to the inventory
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     Adds a product to the inventory.
     @param product the product to add to the inventory
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     Looks up a part in the inventory by ID.
     @param partId the ID of the part to look up
     @return the part with the given ID, or null if no such part exists
     */
    public static Part lookupPart(int partId) {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }

    /**
     Looks up a product in the inventory by ID.
     @param productId the ID of the product to look up
     @return the product with the given ID, or null if no such product exists
     */

    public static Product lookupProduct(int productId) {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /**
     Looks up parts in the inventory by name.
     @param partName the name of the part(s) to look up
     @return a list of all parts whose names contain the given string
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    /**
     Looks up products in the inventory by name.
     @param productName the name of the product(s) to look up
     @return a list of all products whose names contain the given string
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().contains(productName)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    /**
     Updates a part in the inventory.
     @param index the index of the part to update
     @param selectedPart the updated part object
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     Updates a product in the inventory.
     @param index the index of the product to update
     @param product the updated product object
     */
    public static void updateProduct(int index, Product product) {
        allProducts.set(index, product);
    }

    /**
     Deletes a part from the inventory.
     @param part the part to delete
     */
    public static void deletePart(Part part) {
        allParts.remove(part);
    }

    /**
     * Deletes a product from the inventory.
     * @param product the product to delete
     */
    public static void deleteProduct(Product product) {
        allProducts.remove(product);
    }

    /**
     * Returns a list of all parts in the inventory.
     *
     * @return a list of all parts in the inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     Returns a list of all products in the inventory.
     @return a list of all products in the inventory
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}

