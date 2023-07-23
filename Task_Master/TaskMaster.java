import java.text.SimpleDateFormat;
import java.util.*;

class Task {
    private String name;
    private String description;
    private Date dueDate;
    private boolean isCompleted;

    public Task(String name, String description, Date dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    // Getters and setters for attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "Task: " + name + "\nDescription: " + description + "\nDue Date: " + sdf.format(dueDate)
                + (isCompleted ? " (Completed)" : " (Pending)");
    }
}

public class TaskMaster {
    private static List<Task> tasks = new ArrayList<>();
    private static Timer timer = new Timer();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the Task Master App");
            System.out.println("1. Add Task");
            System.out.println("2. Update Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Set Task Reminder");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addTask(scanner);
                    break;
                case 2:
                    updateTask(scanner);
                    break;
                case 3:
                    deleteTask(scanner);
                    break;
                case 4:
                    viewAllTasks();
                    break;
                case 5:
                    setTaskReminder(scanner);
                    break;
                case 6:
                    timer.cancel();
                    System.out.println("\nExiting the Task Master. Till Next Time!");
                    scanner.close();
                    return;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("\nEnter Task Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Due Date (yyyy-MM-dd HH:mm): ");
        String dueDateStr = scanner.nextLine();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dueDate = sdf.parse(dueDateStr);
            Task task = new Task(name, description, dueDate);
            tasks.add(task);
            System.out.println("\nTask added successfully!");
        } catch (Exception e) {
            System.out.println("\nInvalid date format. Task not added.");
        }
    }

    private static void updateTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks found.");
            return;
        }

        System.out.print("\nEnter the Task Name to update: ");
        String taskNameToUpdate = scanner.nextLine();

        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskNameToUpdate)) {
                System.out.println("\nExisting Task Details:");
                System.out.println(task);
                System.out.print("\nEnter updated Task Description: ");
                String updatedDescription = scanner.nextLine();
                task.setDescription(updatedDescription);
                System.out.println("\nTask updated successfully!");
                return;
            }
        }

        System.out.println("\nTask with name '" + taskNameToUpdate + "' not found.");
    }

    private static void deleteTask(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        System.out.print("\nEnter the Task Name to delete: ");
        String taskNameToDelete = scanner.nextLine();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getName().equalsIgnoreCase(taskNameToDelete)) {
                tasks.remove(i);
                System.out.println("\nTask with name '" + taskNameToDelete + "' deleted successfully!");
                return;
            }
        }

        System.out.println("\nTask with name '" + taskNameToDelete + "' not found.");
    }

    private static void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks found.");
        } else {
            System.out.println("\nAll Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("----------------------------");
            }
        }
    }

    private static void setTaskReminder(Scanner scanner) {
        if (tasks.isEmpty()) {
            System.out.println("\nNo tasks found.");
            return;
        }

        System.out.print("\nEnter the Task Name for the reminder: ");
        String taskName = scanner.nextLine();

        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                Date dueDate = task.getDueDate();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("\nReminder: Task '" + taskName + "' is due now!");
                    }
                }, dueDate);
                System.out.println("\nReminder set successfully!");
                return;
            }
        }

        System.out.println("\nTask with name '" + taskName + "' not found.");
    }
}
