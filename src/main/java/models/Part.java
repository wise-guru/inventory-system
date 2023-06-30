package models;

/**
 The Part class represents a part in an inventory management system.
 It is an abstract class that defines the common properties of all parts.
 */
public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     Constructs a Part object with the specified attributes.
     @param id the ID of the part
     @param name the name of the part
     @param price the price of the part
     @param stock the current stock level of the part
     @param min the minimum stock level of the part
     @param max the maximum stock level of the part
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     Sets the ID of the part.
     @param id the ID of the part
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     Sets the name of the part.
     @param name the name of the part
     */
    public void setName(String name) {
        this.name = name;
    }

    /**

     Sets the price of the part.
     @param price the price of the part
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     Sets the current stock level of the part.
     @param stock the current stock level of the part
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     Sets the minimum stock level of the part.
     @param min the minimum stock level of the part
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     Sets the maximum stock level of the part.
     @param max the maximum stock level of the part
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     Gets the ID of the part.
     @return the ID of the part
     */
    public int getId() {
        return id;
    }

    /**
     Gets the name of the part.
     @return the name of the part
     */
    public String getName() {
        return name;
    }

    /**
     Gets the price of the part.
     @return the price of the part
     */
    public double getPrice() {
        return price;
    }

    /**
     Gets the current stock level of the part.
     @return the current stock level of the part
     */
    public int getStock() {
        return stock;
    }

    /**
     Gets the minimum stock level of the part.
     @return the minimum stock level of the part
     */
    public int getMin() {
        return min;
    }

    /**
     Gets the maximum stock level of the part.
     @return the maximum stock level of the part
     */
    public int getMax() {
        return max;
    }
}

