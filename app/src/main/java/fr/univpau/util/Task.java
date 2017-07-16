package fr.univpau.util;

public class Task {

    final public static String DEFAULT_CATEGORY = "Default";

    private int             id;
    private String          title;
    private String          date;
    private boolean         done;
    private TaskCategory    category;

    public Task() {
        this.title = "";
        this.date = "";
        this.done = false;
        this.category = null;
    }

    public Task(String title, String date, boolean done, TaskCategory category) {
        this.title = title;
        this.date = date;
        this.done = done;
        this.category = category;
    }

    public Task(int id, String title, String date, boolean done) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.done = done;
        this.category = null;
    }

    public Task(int id, String title, String date, boolean done, TaskCategory category) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.done = done;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}
