package fr.univpau.util;

import java.io.Serializable;

public class TaskCategory implements Serializable {

    final public static String  DEFAULT_CATEGORY_TITLE = "Default";

    private int                 m_id;
    private String              m_title;

    public TaskCategory(String title) {
        m_title = title;
    }

    public TaskCategory(int id, String title) {
        m_id = id;
        m_title = title;
    }

    public int getId() {
        return m_id;
    }

    public void setId(int id) {
        m_id = id;
    }

    public String getTitle() {
        return m_title;
    }

    public void setTitle(String title) {
        m_title = title;
    }
}
