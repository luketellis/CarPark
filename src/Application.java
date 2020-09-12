/**
 * The Application class is the console interface
 * that handles all input and output for the
 * Parking Spot System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.4
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    //Object for handling user input from console
    private static BufferedReader br;
    //Overlying Car Park object for system, should only be one
    private static CarPark carPark;

    //Hardcoded validation strings for notifying users
    private static final String INVALID_OPTION_MESSAGE = "Invalid Input: Please enter a value between 1-7";
    private static final String INVALID_NUMBER_VALIDATION = "Invalid Input: Please enter a positive number with 4 or less digits";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        carPark = new CarPark();

        System.out.println("Car Park created, please establish the number of parking slots for staff and visitors");
        System.out.println("Please enter the number of slots for staff: ");

        //Number of staffSlots to be added to CarPark at initial execution of program
        int staffSlots = receiveAndValidateIntegerInput();
        carPark.addParkingSlotsByNumberAndType(staffSlots, "staff");

        //Number of visitorSlots to be added to CarPark at initial execution of program
        System.out.println("Please enter the number of slots for visitors: ");
        int visitorSlots = receiveAndValidateIntegerInput();
        carPark.addParkingSlotsByNumberAndType(visitorSlots, "visitor");

        System.out.println("The " + staffSlots + " slots for staff have been created successfully");
        System.out.println("The " + visitorSlots + " slots for visitors have been created successfully");

        readInput();
    }

    /**
     * Main application loop of program, handles user input, and execution of requested functionality
     */
    private static void readInput() {
        //Used to store userInput from console
        String userInput;

        while (true) {
            displayMainMenu();

            try {
                userInput = br.readLine();

                //if userInput isn't null and is a valid menu number
                if (userInput != null && isStringValidMenuNumber(userInput)) {
                    checkAndExecuteOption(userInput);
                } else {
                    System.out.println(INVALID_OPTION_MESSAGE);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    /**
     * Repeats asking user for an integer input and repeats until input passes validation
     *
     * @return valid integer input for further use in calling method
     */
    public static int receiveAndValidateIntegerInput() throws IOException {
        String potentialNumber = br.readLine();

        //While potentialNumber isn't a valid number, ask user for another number
        while (potentialNumber == null || !isStringNumber(potentialNumber)) {
            System.out.println(INVALID_NUMBER_VALIDATION);
            potentialNumber = br.readLine();
        }
        return Integer.parseInt(potentialNumber);
    }

    /**
     * Checks if String input is also a valid number
     *
     * @param   potentialNumber   String that represents a potential number
     * @return  boolean value if potentialNumber String is also an integer
     */
    protected static boolean isStringNumber(String potentialNumber) {
        return potentialNumber.matches("\\d[0-4]*");
    }

    /**
     * Checks if String input is also a valid Menu number[0-7]
     *
     * @param   potentialNumber   String that represents a potential number
     * @return  boolean value if potentialNumber String is also a valid menu integer
     */
    protected static boolean isStringValidMenuNumber(String potentialNumber) {
        return potentialNumber.matches("-?(|[1-7]\\d*)");
    }

    /**
     * Checks if String input is also a valid Car Registration
     *
     * @param   potentialCarRegistration   String that represents a potential number
     * @return  boolean value if potentialCarRegistration String is valid car registration
     */
    protected static boolean isValidCarRegistration(String potentialCarRegistration) {
        return potentialCarRegistration.matches("^[A-Z][0-9]{5}$");
    }

    /**
     * Validates if proposed potential parking slot type matches correct format, either visitor or staff
     *
     * @param   potentialParkingSlotType    the potential parking slot type String
     * @return  Boolean representing if parkingSlotId is in correct format
     */
    protected static boolean isValidParkingSlotType(String potentialParkingSlotType) {
        return potentialParkingSlotType.equalsIgnoreCase("staff") || potentialParkingSlotType.equalsIgnoreCase("visitor");
    }

    /**
     * Validates if proposed parkingSlotId matches correct format
     *
     * @return Boolean representing if parkingSlotId is in correct format
     */
    protected static boolean isValidParkingSlotId(String potentialParkingSlot) {
        return potentialParkingSlot.matches("^[SV][0-9]{3}$");
    }

    /**
     * Asks and receives user input for parking a car in a parking slot
     */
    protected static void retrieveInformationForParkingCar() throws IOException {
        System.out.println("Enter the name of the car owner that you wish to park");
        String carOwnerName = br.readLine();

        String potentialCarRegistration = receiveAndValidateCarRegistration();

        if (!isValidCarRegistration(potentialCarRegistration)) {
            System.out.println("Car Registration is invalid");
            return;
        }

        if (carPark.isCarWithRegistrationAlreadyParked(potentialCarRegistration)) {
            String carRegistrationInformation = "Car with Registration Number '" + potentialCarRegistration + "' ";
            System.out.println(carRegistrationInformation + "is already parked in the Car Park");
            return;
        }

        boolean isStaff = false;
        System.out.println("Is the owner a staff member (yes / otherwise no)");
        String staffAnswer = br.readLine();

        if (staffAnswer.equalsIgnoreCase("yes") || staffAnswer.equalsIgnoreCase("y")) {
            isStaff = true;
        }

        System.out.println("Enter the Parking Slot Id");
        String potentialParkingSlot = br.readLine();

        if (!isValidParkingSlot(potentialParkingSlot, isStaff)) {
            return;
        }

        carPark.parkCar(potentialParkingSlot, new Car(potentialCarRegistration, carOwnerName, isStaff));
    }

    /**
     * Asks and receives user input for parking slot registration number
     *
     * @return User input string for Car Registration Number
     */
    public static String receiveAndValidateCarRegistration() throws IOException {
        System.out.println("Enter the car registration");
        return br.readLine();
    }

    /**
     * Asks and receives user input for parking slot id
     *
     * @return User input string for Parking Slot Id
     */
    public static String receiveAndValidateParkingSlotId() throws IOException {
        System.out.println("Enter the parking slot id");
        return br.readLine();
    }

    /**
     * Asks and receives user input for parking slot type
     *
     * @return User input string for Parking Slot Type
     */
    public static String receiveAndValidateParkingSlotTypeString() throws IOException {
        System.out.println("Is the parking slot for a staff member or visitor (staff/visitor)");
        return br.readLine();
    }

    /**
     * Retrieves and validates information for a potential parking slot when trying to park a car in system
     *
     * @param potentialParkingSlot      The potentialParkingSlot String to be validated against
     * @param isStaff                   The boolean value representing if a parking slot is for a staff or visitor
     */
    public static boolean isValidParkingSlot(String potentialParkingSlot, boolean isStaff) {
        //if parking slot id isn't a valid format or if the parking slot id isn't in the car park system
        if (!isValidParkingSlotId(potentialParkingSlot) || !carPark.isParkingSlotInList(potentialParkingSlot)) {
            System.out.println("Parking Slot Id is invalid");
            return false;
        }

        if (isStaff && potentialParkingSlot.charAt(0) != 'S') {
            System.out.println("Staff may only park in Staff Parking Slots");
            return false;
        } else if (!isStaff && potentialParkingSlot.charAt(0) != 'V') {
            System.out.println("Visitors may only park is Visitor Parking Slots");
            return false;
        }

        if (carPark.isParkingSlotOccupied(potentialParkingSlot)) {
            System.out.println("Parking Slot is already full");
            return false;
        }

        return true;
    }

    /**
     * Retrieves and validates information for finding Car in CarPark system before trying to find car in system
     */
    protected static void retrieveInformationForFindingCar() throws IOException {
        String potentialCarRegistration = receiveAndValidateCarRegistration();

        if (!isValidCarRegistration(potentialCarRegistration)) {
            System.out.println("Car Registration is invalid");
            return;
        }

        carPark.findCarByRegistrationNumber(potentialCarRegistration);
    }

    /**
     * Retrieves and validates user information for adding ParkingSlot into CarPark system before trying to
     * add ParkingSlot into CarPark system
     */
    protected static void retrieveInformationForAddingParkingSlot() throws IOException {
        String potentialParkingSlotType = receiveAndValidateParkingSlotTypeString();

        if (!isValidParkingSlotType(potentialParkingSlotType)) {
            System.out.println("Specified Parking Slot Type is Invalid");
            return;
        }

        carPark.addParkingSlotsByNumberAndType(1, potentialParkingSlotType);
        System.out.println("A new Parking Slot of type '" + potentialParkingSlotType + "' has been added");
    }

    /**
     * Retrieves and validates user information for deleting ParkingSlot from CarPark system
     */
    protected static void retrieveInformationFoDeletingParkingSlot() throws IOException {
        String potentialParkingSlotId = receiveAndValidateParkingSlotId();

        if (!isValidParkingSlotId(potentialParkingSlotId)) {
            System.out.println("Specified Parking Slot Id is invalid.");
            return;
        }

        if (!carPark.isParkingSlotInList(potentialParkingSlotId)) {
            System.out.println("Specified Parking Slot Id does not exist in system.");
            return;
        }

        if (carPark.isParkingSlotOccupied(potentialParkingSlotId)) {
            System.out.println("Parking Slot is occupied, cannot be removed");
            return;
        }

        carPark.removeParkingSlotById(potentialParkingSlotId);
    }

    /**
     * Retrieves and validates user information for removing Car by registration number
     */
    protected static void retrieveInformationFoRemovingCarByRegistrationNumber() throws IOException {
        String potentialCarRegistrationNumber = receiveAndValidateCarRegistration();

        if (!isValidCarRegistration(potentialCarRegistrationNumber)) {
            System.out.println("Specified Car Registration Number is invalid.");
            return;
        }

        carPark.removeCarByRegistrationNumber(potentialCarRegistrationNumber);
    }

    /**
     * Displays Main menu to user
     */
    public static void displayMainMenu() {
        System.out.println("\nMain Menu");
        System.out.println("-----------");
        System.out.println("1: List all car parking slots");
        System.out.println("2: Park a car");
        System.out.println("3: Find a car");
        System.out.println("4: Add a car parking slot");
        System.out.println("5: Delete a parking slot by id");
        System.out.println("6: Remove a car by registration number");
        System.out.println("7: Exit\n");
        System.out.println("Please select an option (1-7): ");
    }

    /**
     * Executes menu option based on passed in String
     *
     * @param option    The menu option to be executed
     */
    private static void checkAndExecuteOption(String option) throws IOException {
        switch (option) {
            case "1":
                carPark.listCarParks();
                break;
            case "2":
                retrieveInformationForParkingCar();
                break;
            case "3":
                retrieveInformationForFindingCar();
                break;
            case "4":
                retrieveInformationForAddingParkingSlot();
                break;
            case "5":
                retrieveInformationFoDeletingParkingSlot();
                break;
            case "6":
                retrieveInformationFoRemovingCarByRegistrationNumber();
                break;
            case "7":
                System.out.println("Exiting Program");
                System.exit(0);
                break;
            default:
                System.out.println(INVALID_OPTION_MESSAGE);
                break;
        }
    }
}
