package models;

/**
 The InHousePart class represents a part that is produced in-house.
 It extends the Part class and adds a machineId field to represent the machine that produces the part.
 */
public class InHousePart extends Part {

    private int machineId;

    /**
     Constructs a new InHousePart object with the specified id, name, price, stock, min, max, and machineId.
     @param id the unique identifier of the part.
     @param name the name of the part.
     @param price the price of the part.
     @param stock the current stock level of the part.
     @param min the minimum stock level of the part.
     @param max the maximum stock level of the part.
     @param machineId the unique identifier of the machine that produces the part.
     */
    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     Sets the unique identifier of the machine that produces the part.
     @param machineId the new unique identifier of the machine.
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     Returns the unique identifier of the machine that produces the part.
     @return the unique identifier of the machine.
     */
    public int getMachineId() {
        return machineId;
    }
}

