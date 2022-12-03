import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        final int PROCEED = -1;
        final String TOO_SMALL = "Value must be greater than 0. Please try again.";

        ArrayList<Integer> numbers = new ArrayList<Integer>();

        PRINT_MENU_1();
        READ_INPUT_1(PROCEED, TOO_SMALL, numbers);
        CALCULATE_PHASE_1(numbers);
    }

    private static void CALCULATE_PHASE_1(ArrayList<Integer> numbers) {
        System.out.println("DEBUG: CALCULATING PHASE 1");

        // Print all numbers for debugging
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    private static ArrayList<Integer> READ_INPUT_1(final int PROCEED, final String TOO_SMALL,
            ArrayList<Integer> numbers) {
        while (true) {
            int value = 0;

            value = INPUT(PROCEED);

            // TODO: Move this to execution phase when it read this value in the array
            if (value == PROCEED) {
                System.out.println("DEBUG: PROCEEDING TO PART TWO");
                break;
            }

            if (value < 0) {
                System.out.println(TOO_SMALL);
                System.out.print("> ");
                continue;
            }

            numbers.add(value);
        }
        return (numbers);
    }

    private static int INPUT(final int PROCEED) {
        String in;
        final String LINE_MARKER = "> ";

        while (true) {
            // Check if input is an integer
            if (input.hasNextInt()) {
                return input.nextInt();
            } else {
                // Check if input is equal to 'q'
                in = input.next();
                if (in.equals("q")) {
                    return PROCEED;
                } else {
                    System.out.println("Invalid input. Please try again.");
                    System.out.print(LINE_MARKER);
                }
            }
        }
    }

    private static void PRINT_MENU_1() {
        System.out.println("---------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("---------------------------------");
        System.out.print("> ");
    }
}