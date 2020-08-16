import java.util.LinkedList;

/**
 * The CarPark class maintains a list of
 * Parking Slots for the Parking Spot System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.0
 */

public class CarPark {
    private final LinkedList<ParkingSlot> carParkList;

    public CarPark() {
        carParkList = new LinkedList<ParkingSlot>();
    }

    public void addCarPark(int numOfSlots, String type) {
        for(int i = 1; i <= numOfSlots; i++) {
            carParkList.add(new ParkingSlot(type, type.toUpperCase().charAt(0) + String.format("%03d", i), false));
        }
    }

    public void listCarParks() {
        for (ParkingSlot parkingSlot : carParkList) {
            System.out.println(parkingSlot.toString());
        }
    }

    public boolean isParkingSlotInList(String slotId) {
        for (ParkingSlot parkingSlot : carParkList) {
            if (slotId.equals(parkingSlot.getId()))
                return true;
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
}
