package registrationandlogin;

import java.util.List;

class Task {
    private static int taskCounter = 0;

    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskID;
    private String taskStatus;

    public Task(String taskName, String taskDescription, String developerDetails, int taskDuration, String taskStatus) {
        this.taskName = taskName;
        this.taskNumber = taskCounter++;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
        this.taskID = createTaskID();
    }

    public boolean checkTaskDescription() {
        return taskDescription.length() <= 50;
    }

    private String createTaskID() {
        String taskID = taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" +
                developerDetails.substring(developerDetails.length() - 3).toUpperCase();
        return taskID;
    }

    public String printTaskDetails() {
        return "Task Details:\n" +
                "1. Task Status: " + taskStatus + "\n" +
                "2. Developer Details: " + developerDetails + "\n" +
                "3. Task Number: " + taskNumber + "\n" +
                "4. Task Name: " + taskName + "\n" +
                "5. Task Description: " + taskDescription + "\n" +
                "6. Task ID: " + taskID + "\n" +
                "7. Duration: " + taskDuration + " hours";
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public static int returnTotalHours(List<Task> tasks) {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getTaskDuration();
        }
        return totalHours;
    }
}
