package fr.univpau.util;

public class Task {

    final public static String DEFAULT_CATEGORY = "Default";

    private int             m_id;
    private String          m_title;
    private String          m_date;
    private boolean         m_done;
    private TaskCategory    m_category;

    public Task() {
        m_title = "";
        m_date = "";
        m_done = false;
        m_category = null;
    }

    public Task(String title, String date, boolean done, TaskCategory category) {
        m_title = title;
        m_date = date;
        m_done = done;
        m_category = category;
    }

    public Task(int id, String title, String date, boolean done) {
        m_id = id;
        m_title = title;
        m_date = date;
        m_done = done;
        m_category = null;
    }

    public Task(int id, String title, String date, boolean done, TaskCategory category) {
        m_id = id;
        m_title = title;
        m_date = date;
        m_done = done;
        m_category = category;
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

    public String getDate() {
        return m_date;
    }

    public void setDate(String date) {
        m_date = date;
    }

    public TaskCategory getCategory() {
        return m_category;
    }

    public void setCategory(TaskCategory category) {
        m_category = category;
    }

    public boolean isDone() {
        return m_done;
    }

    public void setDone(boolean done) {
        m_done = done;
    }

}
