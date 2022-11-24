import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int radius = 0;
        int height = 0;
        String input = "";
        int inputArray[] = { 0 };
        int temp = 0;

        final String INPUT_ERROR = "Input must be a positive real number!";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Read input
            input = scanner.nextLine();

            // If 'q' or 'Q' is entered, exit the program
            if (input.equals("q") || input.equals("Q")) {
                break;
            }

            // Parse input into array
            try {
                inputArray = parseInput(input, temp, inputArray);
            } catch (Exception e) {
                System.out.println(INPUT_ERROR);
                continue;
            }

            System.out.println("---------------------------------");
            System.out.println("# Test of area and volume methods");
            System.out.println("---------------------------------");
            System.out.print("> ");
            for (int i = 0; i < inputArray.length; i++) {
                System.out.print(inputArray[temp] + " ");
            }
            temp++; // Increment temp

            // Calculate base area
            AREA(radius);

            // Calculate surface area
            AREA(radius, height);

            // Calculate volume
            VOLUME(radius, height);
        }
        scanner.close();
    }

    private static int[] parseInput(String input, int temp, int[] inputArray) {
        inputArray[temp] = Integer.valueOf(input);

        return inputArray;
    }

    private static double AREA(int radius) {
        // Calculate base surface area (area of the circle)
        double base_area = Math.PI * Math.sqrt(radius);
        System.out.println("Base area: " + base_area);

        return base_area;
    }

    private static double AREA(int radius, int height) {
        // Calculate area of the tip of the cone
        double surface_area = Math.PI * radius * SLANT_HEIGHT(radius, height);
        System.out.println("Surface area: " + surface_area);

        return surface_area;
    }

    private static double SLANT_HEIGHT(int radius, int height) {
        // Calculate slant height of the cone
        double slant_height = Math.sqrt(radius) + Math.sqrt(height);

        return slant_height;
    }

    private static double VOLUME(int radius, int height) {
        // Calculate volume of the cone
        double volume = Math.PI * Math.sqrt(radius) * Math.sqrt(height) / 3;
        System.out.println("Volume: " + volume);

        return volume;
    }
}