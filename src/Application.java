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

        System.out.println("Start to establish a car parking with slots for staff and visitors");
        System.out.println("Please enter the number of slots for staff: ");
        int staffSlots = receiveAndValidateIntegerInput();

        System.out.println("Please enter the number of slots for visitors: ");
        int visitorSlots = receiveAndValidateIntegerInput();

        System.out.println("The " + staffSlots + " slots for staff have been created successfully");
        System.out.println("The " + visitorSlots + " slots for visitors have been created successfully");

        readInput();
    }

    private static void readInput() {
        String currentLine = "";
        displayMainMenu();
        System.out.println("Ready to Receive Input: ");

        while (true) {
            try {
                currentLine = br.readLine();
                if (currentLine!= null && isStringValidMenuNumber(currentLine)) {
                    checkAndExecuteOption(currentLine);
                }
                else
                {
                    System.out.println(INVALID_OPTION_MESSAGE);
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
        System.out.println("1: List all car slots");
        System.out.println("2: Park a car");
        System.out.println("3: Find a car");
        System.out.println("4: Remove a car");
        System.out.println("5: Add a car slot");
        System.out.println("6: Delete a car slot");
        System.out.println("7: Exit");
    }

    private static void checkAndExecuteOption(String option) {
        switch (option) {
            case "1":
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
                System.out.println("Unsupported Option Detected");
                break;
        }
    }
}
