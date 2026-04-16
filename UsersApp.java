import java.io.*;
import java.util.*;

/*
 * UsersApp reads a list of users from a file, validates each one,
 * and writes the valid users sorted alphabetically to program_out.txt.
 * Any invalid entries are reported to the console with a reason.
 */
public class UsersApp {
    public static void main(String[] args) throws IOException {
        ArrayList<User> users = new ArrayList<User>();

        // Look for the input file — try "users.txt" first, fall back to "Users.txt.txt"
        File readFile = new File("users.txt");
        if (!readFile.exists()) readFile = new File("Users.txt.txt");
        Scanner reader = new Scanner(readFile);

        // Go through the file line by line
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String trimmed = line.trim();

            // Skip blank lines
            if (trimmed.isEmpty()) continue;

            // Each line must have a username and a password separated by whitespace
            String[] parts = trimmed.split("\\s+");
            if (parts.length < 2) {
                System.err.println(trimmed + "\nmissing username or password\n");
                continue;
            }

            String username = parts[0];
            String password = parts[1];

            // Try to create a User — if the data is invalid, the constructor will
            // throw an exception with a friendly message explaining what went wrong
            try {
                User user = new User(username, password);
                users.add(user);
            } catch (IllegalArgumentException e) {
                System.err.println(trimmed + "\n" + e.getMessage() + "\n");
            }
        }
        reader.close();

        // Sort the valid users alphabetically by username
        Collections.sort(users, (u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));

        // Write the sorted valid users to program_out.txt
        PrintWriter writer = new PrintWriter(new FileWriter("program_out.txt"));
        for (User u : users) {
            writer.println(u);
        }
        writer.close();
    }
}
