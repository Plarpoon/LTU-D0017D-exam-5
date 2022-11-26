import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Add scanner
        Scanner scanner = new Scanner(System.in);

        SETUP_EX1(scanner);
    }

    private static void SETUP_EX1(Scanner scanner) {
        PRINT_MENU_EX1();
        EXEC_EX1(scanner);
    }

    private static void CLEAR_CONSOLE() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void EXEC_EX1(Scanner scanner) {

        while (scanner.hasNext()) {
            // Read input into string array
            String[] input = scanner.nextLine().split(" ");

            // While loop until array is empty
            while (input.length > 0) {
                // If 'q' or 'Q' is entered, proceed to next exercise
                if (input[0].equals("q") || input[0].equals("Q")) {
                    System.out.println("DEBUG: NEXT EXCERCISE LOADING...");
                    CLEAR_CONSOLE();
                    System.exit(0);
                }

                // Assign input[0] to radius
                int radius = Integer.parseInt(input[0]);

                // Assign input[1] to height
                int height = 0;
                if (input.length > 1) {
                    height = Integer.parseInt(input[1]);
                }

                // Print results
                PRINT_RESULTS_EX1(radius, height);

                // Remove first element from array
                if (input.length >= 2) {
                    input = REMOVE_FIRST_TWO_ELEMENTS(input);
                }
            }
        }
    }

    private static void PRINT_RESULTS_EX1(int radius, int height) {
        // Print results to console with rounding
        System.out.println("r = " + radius + " h = " + height);
        System.out.println("Area: " + String.format("%.2f", AREA(radius)));
        System.out.println("Surface area: " + String.format("%.2f", AREA(radius, height)));
        System.out.println("Volume: " + String.format("%.2f", VOLUME(radius, height)));
        System.out.println("\n");
    }

    private static String[] REMOVE_FIRST_TWO_ELEMENTS(String[] input) {
        // Create new array with length - 2
        String[] newArray = new String[input.length - 2];

        // Copy all elements from input to newArray
        for (int i = 2; i < input.length; i++) {
            newArray[i - 2] = input[i];
        }

        return newArray;
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

    private static void PRINT_MENU_EX1() {
        System.out.println("---------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("---------------------------------");
        System.out.print("> ");
    }
}