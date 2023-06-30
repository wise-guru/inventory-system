package models;

/**
 This class represents a part that is purchased from an external supplier.
 */
public class OutsourcedPart extends Part {

    private String companyName;

    /**
     Constructs an OutsourcedPart object with the specified attributes.
     @param id the ID of the part
     @param name the name of the part
     @param price the price of the part
     @param stock the current stock level of the part
     @param min the minimum stock level of the part
     @param max the maximum stock level of the part
     @param companyName the name of the company that supplies the part
     */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     Sets the name of the company that supplies the part.
     @param companyName the name of the company that supplies the part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     Gets the name of the company that supplies the part.
     @return the name of the company that supplies the part
     */
    public String getCompanyName() {
        return companyName;
    }
}

