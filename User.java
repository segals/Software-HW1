public class User {
    private String username;
    private String password;

    // valid email: part1@part2.part3 — part1 allows letters/digits/._-+%,
    // part2 must start with letter/digit and allows .-,  part3 is at least 2 letters
    private static final String EMAIL_REGEX =
        "^[a-zA-Z0-9._\\-+%]+@[a-zA-Z0-9][a-zA-Z0-9.\\-]*\\.[a-zA-Z]{2,}$";

    public User(String username, String password) throws IllegalArgumentException {
        // check length before format so we give the more specific error
        if (username.length() > 50) {
            throw new IllegalArgumentException("Username is too long, try something shorter");
        }
        if (!username.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Please enter a valid Email as username");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("Your password is too short, add more characters");
        }
        if (password.length() > 12) {
            throw new IllegalArgumentException("Your password is too long, try a shorter one");
        }
        if (!isValidPassword(password)) {
            throw new IllegalArgumentException("Please enter a valid password");
        }

        this.username = username;
        this.password = password;
    }

    // password must have at least one letter, digit, and special char,
    // and every character must be a printable ASCII character (no spaces)
    private boolean isValidPassword(String pwd) {
        boolean hasLetter = false, hasDigit = false, hasSpecial = false;
        for (char c : pwd.toCharArray()) {
            if (c < 33 || c > 126) return false;
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        return hasLetter && hasDigit && hasSpecial;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return username + " " + password;
    }
}
