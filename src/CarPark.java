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
            carParkList.add(new ParkingSlot(type, type.charAt(0) + String.format("%03d", i), false));
        }
    }

    public void listCarParks() {
        for (ParkingSlot parkingSlot : carParkList) {
            System.out.println(parkingSlot.toString());
        }
    }
}
