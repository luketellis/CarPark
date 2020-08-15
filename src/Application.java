/**
 * The Application class is the console interface
 * that handles all input and output for the
 * Parking Spot System
 *
 * @author      Luke Tellis <6478611>
 * @version     1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    private static BufferedReader br;
    private static CarPark carPark;
    private static final String INVALID_OPTION_MESSAGE = "Invalid Input: Please enter a value between 1-7";
    private static final String INVALID_NUMBER_VALIDATION = "Invalid Input: Please enter a positive number";


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        carPark = new CarPark();

        System.out.println("Start to establish a car parking with slots for staff and visitors");
        System.out.println("Please enter the number of slots for staff: ");
        int staffSlots = receiveAndValidateIntegerInput();
        carPark.addCarPark(staffSlots, "staff");

        System.out.println("Please enter the number of slots for visitors: ");
        int visitorSlots = receiveAndValidateIntegerInput();
        carPark.addCarPark(visitorSlots, "visitor");

        System.out.println("The " + staffSlots + " slots for staff have been created successfully");
        System.out.println("The " + visitorSlots + " slots for visitors have been created successfully");

        readInput();
    }

    private static void readInput() {
        String userInput = "";

        while (true) {
            displayMainMenu();

            try {
                userInput = br.readLine();

                //if userInput isn't null and is a valid menu number
                if (userInput!= null && isStringValidMenuNumber(userInput)) {
                    checkAndExecuteOption(userInput);
                }
                else
                {
                    System.out.println(INVALID_NUMBER_VALIDATION);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static int receiveAndValidateIntegerInput() throws IOException {
        String potentialNumber = "";
        potentialNumber = br.readLine();

        while (potentialNumber == null || !isStringNumber(potentialNumber))
        {
            System.out.println(INVALID_NUMBER_VALIDATION);
            potentialNumber = br.readLine();
        }
            return Integer.parseInt(potentialNumber);
    }

    protected static boolean isStringNumber(String potentialNumber) {
        return potentialNumber.matches("\\d*");
    }

    protected static boolean isStringValidMenuNumber(String potentialNumber) {
        return potentialNumber.matches("-?(|[1-7]\\d*)");
    }

    public static void displayMainMenu() {
        System.out.println("\n1: List all car slots");
        System.out.println("2: Park a car");
        System.out.println("3: Find a car");
        System.out.println("4: Remove a car");
        System.out.println("5: Add a car slot");
        System.out.println("6: Delete a car slot");
        System.out.println("7: Exit\n");
        System.out.println("Please select an option (1-7): ");
    }

    private static void checkAndExecuteOption(String option) {
        switch (option) {
            case "1":
                carPark.listCarParks();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                System.out.println("Exiting Program");
                System.exit(1);
                break;
            default:
                System.out.println(INVALID_OPTION_MESSAGE);
                break;
        }
    }
}
