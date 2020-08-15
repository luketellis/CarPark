/**
 * The Car class represents a Vehicle to be parked in
 * the Parking Spot System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.0
 */

public class Car {
    private String registrationNumber;
    private String owner;
    private boolean isStaff;

    public Car(String registrationNumber, String owner, boolean isStaff) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.isStaff = isStaff;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        owner = owner;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", Owner='" + owner + '\'' +
                ", isStaff=" + isStaff + '}';
    }
}
