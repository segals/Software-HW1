public class User {
    private String username;
    private String password;

    // valid email: part1@part2.part3 — part1 allows letters/digits/._-+%,
    // part2 must start with letter/digit and allows only letters, digits, - and .,  part3 allows only letters (min 2)
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

    // password must have at least one letter, digit, and special char (!@#$%^&*)(  only)
    private boolean isValidPassword(String pwd) {
        boolean hasLetter = false, hasDigit = false, hasSpecial = false;
        for (char c : pwd.toCharArray()) {
            if (Character.isLetter(c)) hasLetter = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if ("!@#$%^&*)(".indexOf(c) >= 0) hasSpecial = true;
            else return false;
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
