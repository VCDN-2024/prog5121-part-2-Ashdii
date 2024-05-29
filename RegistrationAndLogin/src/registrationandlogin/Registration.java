package registrationandlogin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Registration {
    private static final String USERNAME_PATTERN = "^(?=.*[_])(?=.*[0-9]).{5}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[0-9]).{8,}$";

    private User user;

    public boolean checkUsername(String username) {
        return username.matches(USERNAME_PATTERN);
    }

    public boolean checkPasswordComplexity(String password) {
        return password.matches(PASSWORD_PATTERN);
    }

    public String registerUser(String username, String password, String firstName, String lastName) {
        if (!checkUsername(username)) {
            return "Username is not correctly formatted. It must contain an underscore and a number, and be exactly 5 characters.";
        } else if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. It must have a minimum of 8 characters, at least one capital letter, and one number.";
        } else {
            user = new User(username, password, firstName, lastName);
            return "User has been registered successfully.";
        }
    }

    public User getUser() {
        return user;
    }

    public static void main(String[] args) {
        Registration registration = new Registration();
        Login login = new Login();
        List<Task> tasks = new ArrayList<>();

        while (true) {
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome! Please choose an option:",
                    "User Registration and Login",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0:
                    // Registration process
                    String firstName = JOptionPane.showInputDialog("Enter first name:");
                    String lastName = JOptionPane.showInputDialog("Enter last name:");
                    String username = JOptionPane.showInputDialog("Enter username (must contain an underscore and a number, exactly 5 characters):");
                    String password = JOptionPane.showInputDialog("Enter password (minimum 8 characters, at least one capital letter and one number):");

                    String regMsg = registration.registerUser(username, password, firstName, lastName);
                    JOptionPane.showMessageDialog(null, regMsg);

                    // Store the user for login
                    if (registration.getUser() != null) {
                        login.setUser(registration.getUser());
                    }
                    break;
                case 1:
                    // Login process
                    if (registration.getUser() == null) {
                        JOptionPane.showMessageDialog(null, "No registered user found. Please register first.");
                    } else {
                        String loginUsername = JOptionPane.showInputDialog("Enter username to login:");
                        String loginPassword = JOptionPane.showInputDialog("Enter password to login:");

                        String loginMsg = login.returnLoginStatus(loginUsername, loginPassword);
                        JOptionPane.showMessageDialog(null, loginMsg);

                        if (loginMsg.startsWith("Welcome")) {
                            manageTasks(tasks);
                        }
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Exiting...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please choose either Register, Login, or Exit.");
                    break;
            }
        }
    }

    private static void manageTasks(List<Task> tasks) {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        while (true) {
            String[] taskOptions = {"Add tasks", "Show report", "Quit"};
            int taskChoice = JOptionPane.showOptionDialog(null, "Please choose an option:",
                    "Task Management",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, taskOptions, taskOptions[0]);

            switch (taskChoice) {
                case 0:
                    // Add tasks
                    int numberOfTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter number of tasks to add:"));
                    for (int i = 0; i < numberOfTasks; i++) {
                        String taskName = JOptionPane.showInputDialog("Enter task name:");
                        String taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
                        if (taskDescription.length() > 50) {
                            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters");
                            i--;
                            continue;
                        }
                        String developerDetails = JOptionPane.showInputDialog("Enter developer details (first and last name):");
                        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration (in hours):"));
                        String[] statusOptions = {"To Do", "Done", "Doing"};
                        String taskStatus = (String) JOptionPane.showInputDialog(null, "Select task status:",
                                "Task Status", JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);

                        Task task = new Task(taskName, taskDescription, developerDetails, taskDuration, taskStatus);
                        tasks.add(task);
                        JOptionPane.showMessageDialog(null, task.printTaskDetails());
                        int totalHours = Task.returnTotalHours(tasks);
                        JOptionPane.showMessageDialog(null, "Total number of hours across all tasks: " + totalHours);
                    }
                    break;
                case 1:
                    // Show report
                    JOptionPane.showMessageDialog(null, "Coming soon");
                    break;
                case 2:
                    // Quit
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please choose either Add tasks, Show report, or Quit.");
                    break;
            }
        }
    }
}
