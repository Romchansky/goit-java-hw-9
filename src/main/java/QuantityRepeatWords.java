import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class QuantityRepeatWords {

    private static final String ABSOLUTE_PATH_WORDS = "src/main/resources/words.txt";

    public static void main(String[] args) {
        File file = new File(ABSOLUTE_PATH_WORDS);
        checkIfFileAvailable(file);
        readFile(file);
    }

    private static void readFile(File file) {

        StringBuilder builder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line.trim());
                builder.append(" ");
            }

            String wordBuilder = builder.toString();
            sortedAndUniqueWords(getStrings(wordBuilder));

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String[] getStrings(String text) {
        return text.split(" ");
    }

    private static void sortedAndUniqueWords(String[] line) {

        List<String> wordList = Arrays.asList(line);
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String uniqueWord : wordList) {
            wordsMap.put(uniqueWord, Collections.frequency(wordList, uniqueWord));
        }
        wordsMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(System.out::println);
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


