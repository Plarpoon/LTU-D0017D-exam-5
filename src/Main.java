
/**
 * ===============================================================
 * What does the program do:
 * Must execute various types of calculations based on user input
 * It's fundamental that I use various different methods to do this
 * The first part will be based on Mathematical calculations
 * While the second part will be on fractions
 * ===============================================================
 * Pseudocode steps:
 * 1. Print the menu
 * 2. Read the input
 * 3. Execute the program passing the array list
 * 4. Print the results
 * 5. If the user entered 'q', proceed to phase 2
 * 6. Print the menu
 * 7. Read the input
 * 8. Execute the program passing the array list
 * 9. Print the results
 * 10. If the user entered 'q', exit the program
 * (NOTE: reuse the array list from phase 1)
 * ===============================================================
 * Alessandro Suha
 * alesuh-1
 */

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        final int PROCEED = -1;

        // Create a new array list
        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<Integer>();

        PRINT_MENU_1();
        READ_INPUT(PROCEED, numbers);
        EXECUTE_PHASE_1(numbers, PROCEED);
    }

    // Allow user to read the output before clearing the console
    private static void PHASE_2(final int PROCEED, CopyOnWriteArrayList<Integer> numbers) {

        PRINT_MENU_2();
        READ_INPUT(PROCEED, numbers);
        EXECUTE_PHASE_2(numbers, PROCEED);
    }

    private static void EXECUTE_PHASE_2(CopyOnWriteArrayList<Integer> numbers, final int PROCEED) {
        Iterator<Integer> iterator = numbers.iterator();
        int num = 0;
        int denom = 0;
        int[] parts = { 0, 0, 0 };

        while (iterator.hasNext()) {

            // If only 'q' is entered, then num and denom are 0
            if (numbers.size() == 1) {
                num = 0;
                denom = 0;
            }

            // If only one number is entered, then denom is 0
            if (numbers.size() == 2) {
                num = numbers.get(0);
                denom = 0;
            }

            // If array size is greater than 2, then num and denom are the first two
            // elements
            if (numbers.size() > 2) {
                num = numbers.get(0);
                denom = numbers.get(1);
            }

            // Calculate the fraction
            if (denom != 0) {
                parts = FRACTION(num, denom);
            }

            // Print the fraction
            printFraction(num, denom, parts);

            // Clear parts array
            for (int i = 0; i < 3; i++) {
                parts[i] = 0;
            }

            // Remove the first two elements in the array
            numbers.remove(0);
            numbers.remove(0);

            // If the user entered 'q', exit the program
            if (numbers.get(0) == PROCEED) {

                // Exit the program
                System.exit(0);
            }
        }
    }

    private static void printFraction(int num, int denom, int[] parts) {

        // If the first element is 0, then print only the fraction
        if (parts[0] < 1 && parts[1] > 1 && parts[2] > 1) {
            System.out.println(num + "/" + denom + " = " + parts[1] + "/" + parts[2]);

            // If the second element is 0, then there is no fraction
        } else if (parts[1] < 1 && parts[2] > 1 && parts[0] > 1) {
            System.out.println(num + "/" + denom + " = " + parts[0]);

            // If the third element is 0, just print a 0
        } else if (parts[2] < 1 && parts[1] > 1 && parts[0] > 1) {
            System.out.println(num + "/" + denom + " = " + parts[2]);

            // If all elements are 0, then print an error
        } else if (parts[0] < 1 && parts[1] < 1 && parts[2] < 1) {
            System.out.println("Error");

            // If all elements are greater than 0, then print the whole number and fraction
        } else {
            System.out.println(num + "/" + denom + " = " + parts[0] + " " + parts[1] + "/" + parts[2]);
        }
    }

    private static void EXECUTE_PHASE_1(CopyOnWriteArrayList<Integer> numbers, final int PROCEED) {
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {

            // Despite seemingly looking like it checks for two elements
            // In reality it checks if there is only one element in the array
            // As the last element is always 'q'
            if (numbers.size() == 2) {
                System.out.println("r = " + numbers.get(0));
                System.out.println("Area: " + String.format("%.2f", AREA(numbers.get(0))));

                // Remove the first element in the array
                numbers.remove(0);
            }

            // Check if the user entered two or more numbers
            if (numbers.size() > 2 && (numbers.get(0) != PROCEED || numbers.get(1) != PROCEED)) {
                System.out.println("r = " + numbers.get(0) + " h = " + numbers.get(1));
                System.out.println("Base area: " + String.format("%.2f", AREA(numbers.get(0))));
                System.out.println("Mantle area: " + String.format("%.2f", AREA(numbers.get(0), numbers.get(1))));
                System.out.println("Volume: " + String.format("%.2f", VOLUME(numbers.get(0), numbers.get(1))));
                System.out.println("\n");

                // Remove the first two elements in the array
                numbers.remove(0);
                // The previous element (1) is now at index (0)
                // So we remove it again
                numbers.remove(0);
            }

            // If the user entered 'q', proceed to phase 2
            if (numbers.get(0) == PROCEED) {

                // Clear the array
                numbers.clear();

                // Proceed to phase 2
                PHASE_2(PROCEED, numbers);
            }
        }
    }

    private static int[] FRACTION(int num, int denom) {

        int[] array = new int[3];

        // Declare variables
        int integer_part;
        int remainder;
        int gcd;
        int short_fraction;
        int short_denom;

        // Calculate the fraction
        integer_part = num / denom;
        remainder = denom - integer_part;
        gcd = GCD(remainder, denom);
        short_fraction = remainder / gcd;
        short_denom = denom / gcd;

        // Store the values in an array
        array[0] = integer_part;
        array[1] = short_fraction;
        array[2] = short_denom;

        // If the GCD is denom, then the fraction is already in its simplest form
        if (gcd == denom) {
            array[1] = num;
            array[2] = denom;
        }

        return array;
    }

    private static int GCD(int num, int denom) {

        // Find the smaller number
        int i;
        if (num < denom)
            i = num;
        else
            i = denom;

        // Check if the current value of i divides both
        for (int index = i; index > 1; index--) {

            // If the current value of i divides both, then it is the GCD
            if (num % index == 0 && denom % index == 0)
                return index;
        }

        // If no value of i divides both, then the GCD is 1
        return 1;
    }

    private static double VOLUME(int radius, int height) {

        // Calculate volume of the cone
        double volume = (Math.PI * Math.pow(radius, 2) * height) / 3.0;

        return volume;
    }

    private static double PYHTAGORAS(int radius, int height) {

        // Calculate the hypotenuse of the cone
        double hypotenuse = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));

        return hypotenuse;
    }

    private static double AREA(int radius, int height) {

        // Calculate area of the tip of the cone
        double area = Math.PI * radius * PYHTAGORAS(radius, height);

        return area;
    }

    private static double AREA(int radius) {

        // Calculate base surface area (area of the circle)
        double area = Math.PI * radius * radius;

        return area;
    }

    private static CopyOnWriteArrayList<Integer> READ_INPUT(final int PROCEED,
            CopyOnWriteArrayList<Integer> numbers) {

        while (true) {

            int value = 0;

            value = INPUT(PROCEED);

            // Add -1 to the array if the user enters 'q'
            if (value == PROCEED) {
                numbers.add(value);
                break;
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
                // Return absolute value of input
                return Math.abs(input.nextInt());
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

    private static void PRINT_MENU_2() {
        System.out.println("---------------------------------");
        System.out.println("# Test of the fractional methods");
        System.out.println("---------------------------------");
        System.out.print("> ");
    }
}