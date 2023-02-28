import java.util.*;

public class NumberPlateGenerator {
    // This map keeps track of generated number plates, organized by memory code.
    private static final Map<String, List<String>> GENERATED_PLATES = new HashMap<>();

    // This array contains all the valid letters for a number plate.
    private static final String[] RANDOM_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M",
            "N", "P", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] args) {
        // Generate a number plate for the vehicle with the code "YC" and the registration date "04/07/2019".
        String plate = generateNumberPlate("YC", "04/07/2019");

        // Print out the generated number plate to the console.
        System.out.println("Generated number plate for YC:04/07/2019 -> " + plate);

        // Generate a number plate for the vehicle with the code "LT" and the registration date "23/01/2003".
        plate = generateNumberPlate("LT", "23/01/2003");

        // Print out the generated number plate to the console.
        System.out.println("Generated number plate for LT:23/01/2003 -> " + plate);

        // Generate a number plate for the vehicle with the code "FF" and the registration date "30/05/2032".
        plate = generateNumberPlate("FF", "30/05/2032");

        // Print out the generated number plate to the console.
        System.out.println("Generated number plate for FF:30/05/2032 -> " + plate);

    }

    /**
     * Generates a new number plate based on the given memory code and date.
     *
     * @param memoryCode a 2-character code for the memory tag
     * @param date a date string in the format "dd/mm/yyyy"
     * @return a new number plate string, or null if invalid input was provided
     */

    private static String generateNumberPlate(String memoryCode, String date) {
        // Check if the memory code is valid.
        if (memoryCode == null || memoryCode.length() != 2 || !memoryCode.matches("[A-Z]{2}")) {
            return null;
        }

        // Get the age identifier for the given year and date.
        int ageIdentifier = getAgeIdentifier(getYear(date), date);

        // Check if the age identifier is valid.
        if (ageIdentifier == 0) {
            return null;
        }

        // Generate the new number plate.
        String plate = memoryCode + "-" + ageIdentifier + "-" + generateRandomLetters();

        // Check if the number plate has already been generated for this memory code and date.
        if (GENERATED_PLATES.containsKey(memoryCode) && GENERATED_PLATES.get(memoryCode).contains(plate)) {
            return null;
        }

        // Add the new number plate to the generated plates map.
        GENERATED_PLATES.putIfAbsent(memoryCode, new ArrayList<>());
        GENERATED_PLATES.get(memoryCode).add(plate);

        return plate;
    }

    /**
     Extracts the year from the provided date string in the format "dd/mm/yyyy".
     @param date a date string in the format "dd/mm/yyyy"
     @return an integer representing the year
     */

    private static int getYear(String date) {
        return Integer.parseInt(date.split("/")[2]);
    }

    /**
     * Gets the age identifier for a given year and date string in the format "dd/mm/yyyy".
     * The age identifier is used to encode the age of the car in the number plate.
     *
     * @param year the year of the car
     * @param date a date string in the format "dd/mm/yyyy"
     * @return an age identifier integer
     */

    private static int getAgeIdentifier(int year, String date) {
        // Extract the month from the date string.
        int month = Integer.parseInt(date.split("/")[1]);
        // Extract the day from the date string.
        int day = Integer.parseInt(date.split("/")[0]);

        // Check if the month is between March and August (inclusive).
        if (month >= 3 && month <= 8) {
            // If so, return the last two digits of the year.
            return year % 100;
        } else if (month == 2 && day <= 27) {
            // Check if the month is February and the day is 27 or earlier.
            return year % 100 + 50;
            // If so, return the last two digits of the year plus 50.
        } else if (month == 9 || month <= 1) {
            // Check if the month is September or earlier (inclusive).
            return year % 100 + 50;
        }
        // If the date provided is invalid, return 0 as the age identifier.
        return 0;
    }

    /**
     Generates a random string of 3 letters.
     @return a random string of 3 letters
     */

    // This method generates a string of three random letters
    private static String generateRandomLetters() {
        // Create a new instance of Random class
        Random random = new Random();
        // Get a random index from 0 to the length of the array RANDOM_LETTERS
        int randomIndex = random.nextInt(RANDOM_LETTERS.length);
        // Concatenate three random letters from the RANDOM_LETTERS array
        return RANDOM_LETTERS[randomIndex] + RANDOM_LETTERS[randomIndex + 1] + RANDOM_LETTERS[randomIndex + 2];
    }

}
