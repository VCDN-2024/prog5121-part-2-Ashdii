package registrationandlogin;

public class Login {
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public String returnLoginStatus(String username, String password) {
        if (user == null) {
            return "No user registered.";
        }
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return "Welcome " + username + "!";
        }
        return "Invalid username or password.";
    }
}
