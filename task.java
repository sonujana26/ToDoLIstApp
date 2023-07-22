import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description;
    }
}
