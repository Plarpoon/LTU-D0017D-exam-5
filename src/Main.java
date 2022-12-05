import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        final int PROCEED = -1;

        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<Integer>();

        PRINT_MENU_1();
        READ_INPUT(PROCEED, numbers);
        EXECUTE_PHASE_1(numbers, PROCEED);
    }

    // Allow user to read the output before clearing the console
    private static void PHASE_2(final int PROCEED, CopyOnWriteArrayList<Integer> numbers) {
        System.out.println("\n---------------------------------");
        System.out.println("Phase 2 is about to be initiated.");
        System.out.println("Press enter to continue...");
        System.out.println("---------------------------------");

        // Wait for user to press enter
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        CLEAR_CONSOLE();
        PRINT_MENU_2();
        READ_INPUT(PROCEED, numbers);
        EXECUTE_PHASE_2(numbers, PROCEED);
    }

    private static void EXECUTE_PHASE_2(CopyOnWriteArrayList<Integer> numbers, final int PROCEED) {
        Iterator<Integer> iterator = numbers.iterator();
        int num = 0;
        int denom = 0;
        int[] fraction = { 0, 0, 0 };

        while (iterator.hasNext()) {

            // If only one number is entered, then denom is 0
            if (numbers.size() == 2) {
                num = numbers.get(0);
                denom = 0;
            }

            // If only 'q' is entered, then num and denom are 0
            if (numbers.size() == 1) {
                num = 0;
                denom = 0;
            }

            if (numbers.size() >= 3
                    && (numbers.get(0) != PROCEED || numbers.get(1) != PROCEED || numbers.get(2) != PROCEED)) {

                num = numbers.get(0);
                denom = numbers.get(1);
                fraction = FRACTION(num, denom);
                PRINT_PHASE_2(num, denom, fraction);
            }
        }

        // If the user entered 'q', exit the program
        if (numbers.get(0) == PROCEED) {

            System.out.println("Program terminated.");

            // Exit the program
            System.exit(0);
        }
    }

    private static void PRINT_PHASE_2(int num, int denom, int[] fraction) {

        // Print the fraction
        if (fraction[0] == 0) {
            System.out.println(num + "/" + denom + " = " + fraction[1] + "/" + fraction[2]);
        } else if (fraction[1] == 0) {
            System.out.println(num + "/" + denom + " = " + fraction[0]);
        } else if (fraction[2] == 0) {
            System.out.println("0");
        } else if (fraction[0] == 0 && fraction[1] == 0 && fraction[2] == 0) {
            System.out.println("Error");
        } else {
            System.out.println(num + "/" + denom + " = " + fraction[0] + " " + fraction[1] + "/" + fraction[2]);
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
                System.out.println("Area: " + String.format("%.2f", AREA(numbers.get(0))));
                System.out.println("Surface area: " + String.format("%.2f", AREA(numbers.get(0), numbers.get(1))));
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

        int[] temporary = new int[3];

        // Calculate the fraction
        int whole = num / denom;
        int remainder = num % denom;
        int gcd = GCD(num, denom);

        // Store the values in an array
        temporary[0] = whole;
        temporary[1] = remainder;
        temporary[2] = gcd;

        return temporary;
    }

    private static int GCD(int num, int denom) {

        int gcd = 0;

        // Make sure the numerator is greater than the denominator
        if (num < denom) {
            int temp = num;
            num = denom;
            denom = temp;
        }

        // Calculate the greatest common divisor
        for (int i = 1; i <= denom; i++) {
            if (num % i == 0 && denom % i == 0) {
                gcd = i;
            }
        }

        return gcd;
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

    private static void CLEAR_CONSOLE() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}