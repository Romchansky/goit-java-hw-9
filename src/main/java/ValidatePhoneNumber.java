import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidatePhoneNumber {

    private static String ABSOLUTE_PATH = "C:\\Users\\romka\\IdeaProjects\\goit-java-hw-9\\src\\main\\resources\\file.txt";
    private static String VALIDATE_ONE = "\\d{3}-\\d{3}-\\d{4}";
    private static String VALIDATE_TWO = "\\(\\d{3}\\) \\d{3}-\\d{4}";

    public static void main(String[] args) {
        File file = new File(ABSOLUTE_PATH);
        checkIfFileAvailable(file);
        readFile(file);
    }

    private static void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String number = reader.readLine();
            while (number != null) {
                if (isCorrectlyNumber(number)) {
                    System.out.println(number);
                }
                number = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean isCorrectlyNumber(String number) {
        boolean validateOne = Pattern.matches(VALIDATE_ONE, number);
        boolean validateTwo = Pattern.matches(VALIDATE_TWO, number);

        return validateOne || validateTwo;
    }

    private static void checkIfFileAvailable(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
