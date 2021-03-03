import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class WriteToJSON {

    private static final String ABSOLUTE_PATH = "src/main/resources/fileGson.txt";
    private static final String ABSOLUTE_PATH_TO_JSON = "src/main/resources/fileToJson.json";

    public static void main(String[] args) {

        File fileGson = new File(ABSOLUTE_PATH);
        File fileJson = new File(ABSOLUTE_PATH_TO_JSON);
        checkIfFileAvailable(fileGson);
        checkIfFileAvailable(fileJson);

        List<User> users = new LinkedList<>();

        createUser(fileGson,users);
        saveUserToJson(users,fileJson);
    }

    private static void saveUserToJson(List<User> users, File fileJson) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileJson)) ){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(users);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createUser(File fileGson, List<User> users) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileGson))) {
            String line = reader.readLine();
            while (line != null) {
                String[] column = line.split(" ");
                if(!column[0].equals("name") && !column[1].equals("age")) {
                    users.add(new User(column[0],Integer.parseInt(column[1])));
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
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

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

