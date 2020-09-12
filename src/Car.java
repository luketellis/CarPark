/**
 * The Car class represents a Vehicle to be parked in
 * a Parking Spot System in the CarPark system
 *
 * @author Luke Tellis <6478611>
 * @version 1.0
 */

public class Car {
    //Car unique identifier
    private String registrationNumber;
    //The owner of the car
    private String owner;
    //Used to note if a car is a staffMember or not
    private boolean isStaff;

    /**
     * Car class constructor
     *
     * @param registrationNumber The registration number of the car
     * @param owner The name of the car owner
     * @param isStaff Boolean indicating if the car belongs to a staff member
     */
    public Car(String registrationNumber, String owner, boolean isStaff) {
        this.registrationNumber = registrationNumber;
        this.owner = owner;
        this.isStaff = isStaff;
    }

    //Getters and Setters
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
        this.owner = owner;
    }

    public boolean isStaff() {
        return isStaff;
    }

    public void setStaff(boolean staff) {
        this.isStaff = staff;
    }

    /**
     * Returns a String object that is a concatenation of car details that can be printed to console.
     *
     * @return Car details String
     */
    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", Owner='" + owner + '\'' +
                ", isStaff=" + isStaff + '}';
    }
}
