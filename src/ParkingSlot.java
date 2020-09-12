/**
 * The ParkingSlot class represents a Parking Slot in
 * the CarPark System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.1
 */

public class ParkingSlot {
    //Unique identifier of parking slot
    private String id;
    //Parking slot type, either 'visitor' or 'staff'
    private String type;
    //keeps track of if a car is parked, true if a car currently resides
    private boolean carParked;
    //The Car object, if a car is currently occupying the parking slot
    private Car car;

    /**
     * ParkingSlot class constructor
     *
     * @param id The unique identifier of the parking slot
     * @param type Whether the parking slot is for staff or visitors
     * @param carParked Boolean indicating if a car is occupying the parking slot
     */
    public ParkingSlot(String id, String type, boolean carParked) {
        this.id = id;
        this.type = type;
        this.carParked = carParked;
    }

    //Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getCarParked() {
        return carParked;
    }

    public void setCarParked(boolean carParked) {
        this.carParked = carParked;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Used in the event that a car is removed, sets the car variable to null,
     * and changing the carParked variable back to false
     * **/
    public void removeCar() {
        this.setCar(null);
        this.setCarParked(false);
    }

    /**
     * Returns a String object that is a concatenation of ParkingSlot details that can be printed to console.
     *
     * @return ParkingSlot details String
     */
    @Override
    public String toString() {
        String occupied = carParked ? "occupied with " + car : "not occupied";
        return "SlotId is: '" + id + '\'' +
                ", is for " + type +
                ", and is " + occupied;
    }
}
