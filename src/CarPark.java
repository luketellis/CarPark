import java.util.LinkedList;

/**
 * The CarPark class maintains a list of
 * Parking Slots for the Parking Spot System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.1
 */

public class CarPark {
    private final LinkedList<ParkingSlot> carParkList;

    private static int staffParkingSlots = 0;
    private static int visitorParkingSlots = 0;


    public CarPark() {
        carParkList = new LinkedList<ParkingSlot>();
    }

    public void addParkingSlotsByNumberAndType(int numOfSlots, String type) {
        for(int i = 0; i < numOfSlots; i++) {
            carParkList.add(new ParkingSlot( type.toUpperCase().charAt(0) + String.format("%03d", getNewParkingSlotIncrementBasedOnType(type)),
                    type, false));
            incrementParkingSlotNumberBasedOnType(type);
        }

        //todo remove debug print statements
        System.out.println("staffParkingSlots: " + staffParkingSlots);
        System.out.println("visitor parking slots: " + visitorParkingSlots);
    }

    public void removeParkingSlotById(String parkingSlotId) {
        //If the specified ParkingSlot found by id has a carParked
        carParkList.removeIf(parkingSlot -> parkingSlotId.equals(parkingSlot.getId()) && !parkingSlot.getCarParked());

        //todo remove debug print statements
        System.out.println("staffParkingSlots: " + staffParkingSlots);
        System.out.println("visitor parking slots: " + visitorParkingSlots);
    }

    public boolean isParkingSlotInList(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            if (slotId.equals(parkingSlot.getId()))
                return true;
        }
        return false;
    }

    public int getNewParkingSlotIncrementBasedOnType(String type) {
        if (type.equalsIgnoreCase("staff")) {
            return staffParkingSlots + 1;
        }
        else {
            return visitorParkingSlots + 1;
        }
    }

    public void incrementParkingSlotNumberBasedOnType(String type) {
        if (type.equalsIgnoreCase("staff")) {
            staffParkingSlots++;
        }
        else if (type.equalsIgnoreCase("visitor")) {
            visitorParkingSlots++;
        }
    }

    public void decrementParkingSlotNumberBasedOnType(String type) {
        if (type.equalsIgnoreCase("staff")) {
            staffParkingSlots--;
        }
        else if (type.equalsIgnoreCase("visitor")) {
            visitorParkingSlots--;
        }
    }

    public void listCarParks() {
        for (ParkingSlot parkingSlot : carParkList) {
            System.out.println(parkingSlot.toString());
        }
    }

    public boolean isParkingSlotOccupied(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            //If the specified ParkingSlot found by id has a carParked
            if (slotId.equals(parkingSlot.getId()) && parkingSlot.getCarParked()) {
                    return true;
                }
        }
        return false;
    }

    public boolean isParkingSlotFull(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            if (slotId.equals(parkingSlot.getId()) && parkingSlot.getCarParked())
                return true;
        }
        return false;
    }

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
