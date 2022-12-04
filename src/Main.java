import java.util.concurrent.CopyOnWriteArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        final int PROCEED = -1;

        CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<Integer>();

        PRINT_MENU_1();
        READ_INPUT_1(PROCEED, numbers);
        EXECUTE_PHASE_1(numbers, PROCEED);
    }

    private static void PHASE_2() {
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
                PHASE_2();
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

    private static CopyOnWriteArrayList<Integer> READ_INPUT_1(final int PROCEED,
            CopyOnWriteArrayList<Integer> numbers) {

        while (true) {
            int value = 0;

            value = INPUT(PROCEED);

            // Exit loop if user enters 'q'
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

    private static void CLEAR_CONSOLE() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}