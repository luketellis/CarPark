package carpark.code;

import java.util.LinkedList;

/**
 * The carpark.code.CarPark class maintains a list of
 * Parking Slots for the carpark.code.CarPark System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.2
 */

public class CarPark {
    //List for maintaining a list of ParkingSlots for carpark.code.CarPark system
    public final LinkedList<ParkingSlot> carParkList;

    //Keeps track of staff and visitor parking slots, so that duplicate numbers are not added
    private static int staffParkingSlots = 0;
    private static int visitorParkingSlots = 0;

    /**
     * carpark.code.CarPark class constructor
     * Initialises a new carParkList when called for storing ParkingSlots
     */
    public CarPark() {
        carParkList = new LinkedList<ParkingSlot>();
    }

    /**
     * Adds new ParkingSlots into the carpark.code.CarPark list
     *
     * @param numOfSlots Number of new parking slots to be added to carpark.code.Car Park
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
     * Removes a carpark.code.ParkingSlot by id if it exists
     *
     * @param parkingSlotId     Id of carpark.code.ParkingSlot Id to be removed
     */
    public void removeParkingSlotById(String parkingSlotId) {
        //If the specified carpark.code.ParkingSlot found by id has a carParked
        carParkList.removeIf(parkingSlot -> {
            boolean parkingSlotExisted = parkingSlotId.equals(parkingSlot.getId()) && !parkingSlot.getCarParked();
            if (parkingSlotExisted) {
                System.out.println("Parking Slot with id '" + parkingSlotId + "' has been removed from the system");
            }
            return parkingSlotExisted;
        });
    }

    /**
     * Checks if a carpark.code.ParkingSlot exists by slotId
     *
     * @param slotId Id of carpark.code.ParkingSlot Id to be checked
     * @return       boolean referencing if carpark.code.ParkingSlot exists
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
     * @param type Type of carpark.code.ParkingSlot to be checked
     * @return     Integer of the next unassigned carpark.code.ParkingSlot id by type
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
     * @param type     Type of carpark.code.ParkingSlot to be incremented, either staff or visitor
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
     * @param type     Type of carpark.code.ParkingSlot to be decremented, either staff or visitor
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
     * Lists the parking slots in the carpark.code.CarPark to the console
     */
    public void listCarParks() {
        System.out.println("Current Parking Slots");
        System.out.println("---------------------");
        for (ParkingSlot parkingSlot : carParkList) {
            System.out.println(parkingSlot.toString());
        }
    }

    /**
     * Checks if a parking slot is occupied
     *
     * @param slotId Id of carpark.code.ParkingSlot to be checked
     * @return       Boolean value representing if carpark.code.ParkingSlot if occupied
     */
    public boolean isParkingSlotOccupied(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            //If the specified carpark.code.ParkingSlot found by id has a carParked
            if (slotId.equals(parkingSlot.getId()) && parkingSlot.getCarParked()) {
                    return true;
                }
        }
        return false;
    }

    /**
     * Parks a car in a parking slot by adding carpark.code.Car to carpark.code.ParkingSlot object and setting
     * carParked variable to true
     *
     * @param slotId Id of carpark.code.ParkingSlot to be checked
     * @param car    carpark.code.Car to be added to carpark.code.ParkingSlot
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
     * Checks if a carpark.code.Car with a particular registration number is in carpark.code.CarPark
     *
     * @param registrationNumber    registration number of carpark.code.Car to be found
     */
    public void findCarByRegistrationNumber(String registrationNumber) {
        String carRegistrationInformation = "carpark.code.Car with Registration Number '" + registrationNumber + "' ";

        for (ParkingSlot parkingSlot : carParkList) {
            //If parkingSlot has a car and the car's registration matches the requested registration
            if (parkingSlot.getCar() != null && registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber())) {
                    System.out.println(carRegistrationInformation + "is currently parked in Parking Slot '" + parkingSlot.getId() + "'");
                    System.out.println("The owner of the car is '" + parkingSlot.getCar().getOwner() + "'");
                    return;
            }
        }

        System.out.println(carRegistrationInformation + "is not currently parked in the carpark.code.Car Park");
    }

    /**
     * Removes a carpark.code.Car a particular registration number from carpark.code.CarPark
     *
     * @param registrationNumber    registration number of carpark.code.Car to be removed
     */
    public void removeCarByRegistrationNumber(String registrationNumber) {
        String carRegistrationInformation = "carpark.code.Car with Registration Number '" + registrationNumber + "' ";

        for (ParkingSlot parkingSlot : carParkList) {
            //If parkingSlot has a car and the car's registration matches the requested registration
            if (parkingSlot.getCar() != null && registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber())) {
                parkingSlot.removeCar();
                System.out.println(carRegistrationInformation + "has been removed from '" + parkingSlot.getId() + "'");
                return;
            }
        }

        System.out.println(carRegistrationInformation + "is not currently parked in the carpark.code.Car Park");
    }

    /**
     * Checks to see if a car is already parked in the carpark.code.Car Park system
     *
     * @param registrationNumber     registration number of carpark.code.Car to be checked
     */
    public boolean isCarWithRegistrationAlreadyParked(String registrationNumber) {
            for (ParkingSlot parkingSlot : carParkList) {
                //If parkingSlot has a car and the car's registration matches the requested registration
                if (parkingSlot.getCar() != null && registrationNumber.equals(parkingSlot.getCar().getRegistrationNumber())) {
                    return true;
                }
            }

            return false;
    }
}
