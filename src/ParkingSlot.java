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

    public ParkingSlot(String type, String id, boolean carParked) {
        this.type = type;
        this.id = id;
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

    public String toStringDebug() {
        return "ParkingSlot{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", carParked='" + carParked + '\'' + '}';
    }

    @Override
    public String toString() {
        String occupied = carParked ? "not occupied" : "occupied";
        return "SlotId is: '" + id + '\'' +
                ", is for " + type +
                ", and is " + occupied;
    }
}
