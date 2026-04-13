import java.io.*;
import java.util.*;

public class UsersApp {
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<User>();

        // try both common file names just in case
        File readFile = new File("users.txt");
        if (!readFile.exists()) readFile = new File("Users.txt.txt");
        Scanner reader = new Scanner(readFile);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;

            // each line should have exactly username and password separated by whitespace
            String[] parts = trimmed.split("\\s+");
            if (parts.length < 2) {
                System.err.println(trimmed + " - missing username or password");
                continue;
            }

            String username = parts[0];
            String password = parts[1];

            // the constructor throws if anything is invalid, so we just catch and print
            try {
                User user = new User(username, password);
                users.add(user);
            } catch (IllegalArgumentException e) {
                System.err.println(trimmed + " - " + e.getMessage());
            }
        }
        reader.close();

        // sort alphabetically by username before printing
        Collections.sort(users, (u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));

        for (User u : users) {
            System.out.print(u + "\n");
        }
    }
}
