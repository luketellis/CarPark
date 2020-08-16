/**
 * The ParkingSlot class represents a Parking Slot in
 * the Parking Spot System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.0
 */

public class ParkingSlot {
    private String type;
    private String id;
    private boolean carParked;
    private Car car;

    public ParkingSlot(String id, String type, boolean carParked) {
        this.id = id;
        this.type = type;
        this.carParked = carParked;
    }

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

    public String toStringDebug() {
        return "ParkingSlot{" +
                ", id='" + id + '\'' +
                "type='" + type + '\'' +
                ", carParked='" + carParked + '\'' +
                ", car='" + car + '\'' + '}';
    }

    @Override
    public String toString() {
        String occupied = carParked ? "occupied with " + car : "not occupied";
        return "SlotId is: '" + id + '\'' +
                ", is for " + type +
                ", and is " + occupied;
    }
}
