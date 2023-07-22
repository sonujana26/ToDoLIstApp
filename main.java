import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class Task {
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description;
    }
}

class ToDoList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(String title) {
        tasks.removeIf(task -> task.getTitle().equals(title));
    }

    public void viewTasks() {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(tasks);
            System.out.println("ToDo list saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            tasks = (ArrayList<Task>) ois.readObject();
            System.out.println("ToDo list loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class ToDoListApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Save ToDo List");
            System.out.println("5. Load ToDo List");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character after reading the integer input.

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    Task newTask = new Task(title, description);
                    toDoList.addTask(newTask);
                    System.out.println("Task added successfully!");
                    break;
                case 2:
                    System.out.print("Enter the title of the task to remove: ");
                    String titleToRemove = scanner.nextLine();
                    toDoList.removeTask(titleToRemove);
                    System.out.println("Task removed successfully!");
                    break;
                case 3:
                    System.out.println("------- ToDo List -------");
                    toDoList.viewTasks();
                    System.out.println("-------------------------");
                    break;
                case 4:
                    toDoList.saveToFile("ToDoList.txt");
                    break;
                case 5:
                    toDoList.loadFromFile("ToDoList.txt");
                    break;
                case 6:
                    System.out.println("Exiting ToDo List App. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
                      }
