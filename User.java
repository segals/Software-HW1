/*
 * User represents a single user with a username (email) and a password.
 * Both are validated on creation — if anything is wrong, an exception is
 * thrown with a message explaining what the problem is.
 */
public class User {
    private String username;
    private String password;

    // The username must be a valid email in the format: part1@part2.part3
    //   part1 — letters, digits, and any of: . _ - + %
    //   part2 — must start with a letter or digit, then allows letters, digits, - and .
    //   part3 — letters only, at least 2 characters
    private static final String EMAIL_REGEX =
        "^[a-zA-Z0-9._\\-+%]+@[a-zA-Z0-9][a-zA-Z0-9.\\-]*\\.[a-zA-Z]{2,}$";

    public User(String username, String password) throws IllegalArgumentException {
        // Validate username — check length first so we give the more specific error
        if (username.length() > 50) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }
        if (!username.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }

        // Validate password length
        if (password.length() < 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }
        if (password.length() > 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }

        // Validate password content (must have a letter, digit, and special char)
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Please enter a valid password");
        }

        this.username = username;
        this.password = password;
    }

    // Checks that the password contains at least one letter, one digit, and one
    // special character. The only allowed special characters are: ! @ # $ % ^ & * ) (
    // Any other character (space, dot, dash, etc.) will cause this to return false.
    private boolean isValidPassword(String pwd) {
        boolean hasLetter = false, hasDigit = false, hasSpecial = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if ("!@#$%^&*)(".indexOf(c) >= 0) hasSpecial = true;
            else return false; // character is not allowed at all
        }
        return hasLetter && hasDigit && hasSpecial;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    // Prints the user as "username password" — used when writing to the output file
    @Override
    public String toString() {
        return username + " " + password;
    }
}
