import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Add scanner
        Scanner scanner = new Scanner(System.in);

        SETUP_EX1(scanner);
    }

    private static void SETUP_EX1(Scanner scanner) {
        CLEAR_CONSOLE();
        PRINT_MENU_EX1();
        EXEC_EX1(scanner);
    }

    private static void CLEAR_CONSOLE() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void EXEC_EX1(Scanner scanner) {
        // Read input into string array
        String[] input = scanner.nextLine().split(" ");

        // DEBUG: Print whole array
        for (String s : input) {
            System.out.println(s);
        }

        // If 'q' or 'Q' is entered, proceed to next exercise
        if (input[0].equals("q") || input[0].equals("Q")) {
            System.out.println("DEBUG: NEXT EXCERCISE LOADING...");
        }

        // Assign input[0] to radius
        int radius = Integer.parseInt(input[0]);

        // Calculate area
        AREA(radius);

        // Assign input[1] to height
        int height = Integer.parseInt(input[1]);

        // Calculate surgaface area
        AREA(radius, height);

        // Calculate volume
        VOLUME(radius, height);
    }

    private static double VOLUME(int radius, int height) {
        // Calculate volume of the cone
        double volume = Math.PI * Math.sqrt(radius) * Math.sqrt(height) / 3;
        System.out.println("Volume: " + volume);

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
        System.out.println("Surface area: " + surface_area);

        return surface_area;
    }

    private static double AREA(int radius) {
        // Calculate base surface area (area of the circle)
        double base_area = Math.PI * Math.sqrt(radius);
        System.out.println("Base area: " + base_area);

        return base_area;
    }

    private static void PRINT_MENU_EX1() {
        System.out.println("---------------------------------");
        System.out.println("# Test of area and volume methods");
        System.out.println("---------------------------------");
        System.out.print("> ");
    }
}