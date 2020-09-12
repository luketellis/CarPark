import java.util.LinkedList;

/**
 * The CarPark class maintains a list of
 * Parking Slots for the CarPark System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.1
 */

public class CarPark {
    //List for maintaining a list of ParkingSlots for CarPark system
    private final LinkedList<ParkingSlot> carParkList;

    //Keeps track of staff and visitor parking slots, so that duplicate numbers are not added
    private static int staffParkingSlots = 0;
    private static int visitorParkingSlots = 0;

    /**
     * CarPark class constructor
     * Initialises a new carParkList when called for storing ParkingSlots
     */
    public CarPark() {
        carParkList = new LinkedList<ParkingSlot>();
    }

    /**
     * Adds new ParkingSlots into the CarPark list
     *
     * @param numOfSlots Number of new parking slots to be added to Car Park
     * @param type       Type of Parking Slots to be added, either staff or visitor
     */
    public void addParkingSlotsByNumberAndType(int numOfSlots, String type) {
        for(int i = 0; i < numOfSlots; i++) {
            carParkList.add(new ParkingSlot( type.toUpperCase().charAt(0) + String.format("%03d", getNewParkingSlotIncrementBasedOnType(type)),
                    type, false));
            incrementParkingSlotNumberBasedOnType(type);
        }
    }

    /**
     * Removes a ParkingSlot by id
     *
     * @param parkingSlotId Id of ParkingSlot Id to be removed
     */
    public void removeParkingSlotById(String parkingSlotId) {
        //If the specified ParkingSlot found by id has a carParked
        carParkList.removeIf(parkingSlot -> parkingSlotId.equals(parkingSlot.getId()) && !parkingSlot.getCarParked());
    }

    /**
     * Checks if a ParkingSlot exists by slotId
     *
     * @param slotId Id of ParkingSlot Id to be checked
     * @return       boolean referencing if ParkingSlot exists
     */
    public boolean isParkingSlotInList(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            if (slotId.equals(parkingSlot.getId()))
                return true;
        }
        return false;
    }

    /**
     * Returns the next valid new parking slot number based on type
     *
     * @param type Type of ParkingSlot to be checked
     * @return       number of next, unassigned ParkingSlot id by type
     */
    public int getNewParkingSlotIncrementBasedOnType(String type) {
        if (type.equalsIgnoreCase("staff")) {
            return staffParkingSlots + 1;
        }
        else {
            return visitorParkingSlots + 1;
        }
    }

    /**
     * Increments the parking slot numbers based on type
     *
     * @param type Type of ParkingSlot to be incremented, either staff or visitor
     */
    public void incrementParkingSlotNumberBasedOnType(String type) {
        if (type.equalsIgnoreCase("staff")) {
            staffParkingSlots++;
        }
        else if (type.equalsIgnoreCase("visitor")) {
            visitorParkingSlots++;
        }
    }

    /**
     * Decrements the parking slot numbers based on type
     *
     * @param type Type of ParkingSlot to be decremented, either staff or visitor
     */
    public void decrementParkingSlotNumberBasedOnType(String type) {
        if (type.equalsIgnoreCase("staff")) {
            staffParkingSlots--;
        }
        else if (type.equalsIgnoreCase("visitor")) {
            visitorParkingSlots--;
        }
    }

    /**
     * Lists the parking slots in the CarPark to the console
     */
    public void listCarParks() {
        for (ParkingSlot parkingSlot : carParkList) {
            System.out.println(parkingSlot.toString());
        }
    }

    /**
     * Checks if a parking slot is occupied
     *
     * @param slotId Id of ParkingSlot to be checked
     * @return       Boolean value representing if ParkingSlot if occupied
     */
    public boolean isParkingSlotOccupied(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            //If the specified ParkingSlot found by id has a carParked
            if (slotId.equals(parkingSlot.getId()) && parkingSlot.getCarParked()) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Parks a car in a parking slot by adding Car to ParkingSlot object and setting
     * carParked variable to true
     *
     * @param slotId Id of ParkingSlot to be checked
     * @param car    Car to be added to ParkingSlot
     */
    public void parkCar(String slotId, Car car) {
        for (ParkingSlot parkingSlot : carParkList) {
            if (slotId.equals(parkingSlot.getId())) {
                parkingSlot.setCar(car);
                parkingSlot.setCarParked(true);
                System.out.println("Parking Slot '" + slotId + "' is now occupied with " + car);
                return;
            }
        }
    }

    /**
     * Checks if a Car with a particular registration number is in CarPark
     *
     * @param registrationNumber registration number of Car to be found
     */
    public void findCarByRegistrationNumber(String registrationNumber) {
        String carRegistrationInformation = "Car with Registration Number '" + registrationNumber + "' ";

        for (ParkingSlot parkingSlot : carParkList) {
            //If parkingSlot has a car and the car's registration matches the requested registration
            if (parkingSlot.getCar() != null && registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber())) {
                    System.out.println(carRegistrationInformation + "is currently parked in Parking Slot '" + parkingSlot.getId() + "'");
                    System.out.println("The owner of the car is '" + parkingSlot.getCar().getOwner() + "'");
                    return;
            }
        }

        System.out.println(carRegistrationInformation + "is not currently parked in the Car Park");
    }

    /**
     * Removes a Car a particular registration number from CarPark
     *
     * @param registrationNumber registration number of Car to be removed
     */
    public void removeCarByRegistrationNumber(String registrationNumber) {
        String carRegistrationInformation = "Car with Registration Number '" + registrationNumber + "' ";

        for (ParkingSlot parkingSlot : carParkList) {
            //If parkingSlot has a car and the car's registration matches the requested registration
            if (parkingSlot.getCar() != null && registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber())) {
                parkingSlot.removeCar();
                System.out.println(carRegistrationInformation + "has been removed from '" + parkingSlot.getId() + "'");
                return;
            }
        }

        System.out.println(carRegistrationInformation + "is not currently parked in the Car Park");
    }
}
