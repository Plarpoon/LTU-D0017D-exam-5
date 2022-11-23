import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int radius = 0;
        int height = 0;
        String input = "";

        final String INPUT_ERROR = "Input must be a positive real number!";

        Scanner scanner = new Scanner(System.in);

        System.out.println("# Test of area and volume");

        while (true) {
            System.out.print("Enter radius and height (q to quit): ");
            // try to read radius and height
            try {
                input = scanner.next();
                if (input.equals("q")) {
                    // TODO: do stuff
                    System.out.println("RUN ANOTHER METHOD");
                    break;
                }
                radius = Integer.parseInt(input);
                if (radius <= 0) {
                    throw new NumberFormatException(INPUT_ERROR);
                }
                input = scanner.next();
                if (input.equals("q")) {
                    // TODO: do stuff
                    System.out.println("RUN ANOTHER METHOD");
                    break;
                }
                height = Integer.parseInt(input);
                if (height <= 0) {
                    throw new NumberFormatException(INPUT_ERROR);
                }
            } catch (NumberFormatException e) {
                System.out.println(INPUT_ERROR);
                continue;
            }

            // Calculate base area
            AREA(radius);

            // Calculate surface area
            AREA(radius, height);

            // Calculate volume
            VOLUME(radius, height);
        }
        scanner.close();
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