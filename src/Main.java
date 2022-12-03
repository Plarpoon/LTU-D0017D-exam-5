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
        EXECUTE_PHASE_1(numbers, PROCEED);
    }

    private static void PHASE_2() {
        // TODO: Implement phase 2
    }

    private static void EXECUTE_PHASE_1(ArrayList<Integer> numbers, final int PROCEED) {

        for (int number : numbers) {

            // Check if the user entered 'q'
            if (!numbers.contains(PROCEED)) {
                System.out.println("You must have the value 'q' at the end of the input.");
            }

            // If the user entered 'q', proceed to phase 2
            if (number == PROCEED) {
                System.out.println("PHASE 2");
                PHASE_2();
            }

            // Check if the user entered any numbers
            if (numbers.isEmpty()) {
                System.out.println("No numbers were entered.");
            }

            // Check if the user entered only one number
            if (numbers.size() == 1) {
                System.out.println("r = " + number);
                System.out.println("Area: " + String.format("%.2f", AREA(number)));

                // Remove the first element in the array
                numbers.remove(0);
            }

            // Check if the user entered two or more numbers
            if (numbers.size() < 1) {
                System.out.println("r = " + numbers.get(0) + " h = " + numbers.get(1));
                System.out.println("Area: " + String.format("%.2f", AREA(numbers.get(0))));
                System.out.println("Surface area: " + String.format("%.2f", AREA(numbers.get(0), numbers.get(1))));
                System.out.println("Volume: " + String.format("%.2f", VOLUME(numbers.get(0), numbers.get(1))));
                System.out.println("\n");

                // Remove the first two elements in the array
                numbers.remove(0);
                numbers.remove(1);
            }
        }
    }

    private static double VOLUME(int radius, int height) {
        // Calculate volume of the cone
        double volume = Math.PI * Math.sqrt(radius) * Math.sqrt(height) / 3;

        return volume;
    }

    private static double SLANT_HEIGHT(int radius, int height) {
        // Calculate slant height of the cone
        double slant_height = Math.sqrt(radius) + Math.sqrt(height);

        return slant_height;
    }

    private static double AREA(int radius, int height) {
        // Calculate area of the tip of the cone
        double surface_area = Math.PI * radius * SLANT_HEIGHT(radius, height);

        return surface_area;
    }

    private static double AREA(int radius) {
        // Calculate base surface area (area of the circle)
        double base_area = Math.PI * Math.sqrt(radius);

        return base_area;
    }

    private static ArrayList<Integer> READ_INPUT_1(final int PROCEED, final String TOO_SMALL,
            ArrayList<Integer> numbers) {
        while (true) {
            int value = 0;

            value = INPUT(PROCEED);

            // Exit loop if user enters 'q'
            if (value == PROCEED) {
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