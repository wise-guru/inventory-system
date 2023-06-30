package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**

 A class representing a product in an inventory management system.
 */
public class Product {

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     Creates a new Product object with the specified ID, name, price, stock, minimum stock, and maximum stock.
     @param id the unique ID of the product
     @param name the name of the product
     @param price the price of the product
     @param stock the current stock level of the product
     @param min the minimum stock level allowed for the product
     @param max the maximum stock level allowed for the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = FXCollections.observableArrayList();
    }

    /**
     Sets the ID of the product.
     @param id the new ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     Sets the name of the product.
     @param name the new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**

     Sets the price of the product.
     @param price the new price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     Sets the current stock level of the product.
     @param stock the new stock level to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     Sets the minimum stock level allowed for the product.
     @param min the new minimum stock level to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     Sets the maximum stock level allowed for the product.
     @param max the new maximum stock level to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     Retrieves the ID of the product.
     @return the ID of the product
     */
    public int getId() {
        return id;
    }

    /**
     Retrieves the name of the product.
     @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     Retrieves the price of the product.
     @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     Retrieves the current stock level of the product.
     @return the current stock level of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     Retrieves the minimum stock level allowed for the product.
     @return the minimum stock level allowed for the product
     */
    public int getMin() {
        return min;
    }

    /**
     Retrieves the maximum stock level allowed for the product.
     @return the maximum stock level allowed for the product
     */
    public int getMax() {
        return max;
    }

    /**
     Adds a part to the list of associated parts for the product.
     @param part the part to add
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     Removes a part from the list of associated parts for the product.
     @param part the part to remove
     */
    public void deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
    }

    /**

     Returns an observable list of all the associated parts for this product.
     @return all associated parts for this product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }


}

