package fr.univpau.util;

import java.io.Serializable;

public class TaskCategory implements Serializable {

    final public static String  DEFAULT_CATEGORY_TITLE = "Default";

    private int                 id;
    private String              title;

    public TaskCategory(String title) {
        this.title = title;
    }

    public TaskCategory(int id, String title) {
        this.id = id;
        this.title = title;
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
}