package fr.univpau.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.univpau.util.Task;
import fr.univpau.util.TaskCategory;
import fr.univpau.util.TaskSQLiteHelper;

public class TaskDAO {

    TaskCategoryDAO     m_categoryDAO;
    TaskSQLiteHelper    m_sqlHelper;
    Context             m_context;

    public TaskDAO(Context context) {
        m_categoryDAO = new TaskCategoryDAO(context);
        m_sqlHelper = new TaskSQLiteHelper(context);
        m_context = context;
    }

    public void insertTask(Task task) {
        long id = m_sqlHelper.insertTask(task.getTitle(), task.getDate(), task.isDone(), task.getCategory());
        task.setId((int) id);
    }

    public void updateTask(Task task) {
        m_sqlHelper.updateTask(task.getId(), task.getTitle(), task.getDate(), task.isDone(), task.getCategory());
    }

    public List<Task> getAllTasks() {
        List<Task> objectList = new ArrayList<Task>();
        Cursor res =  m_sqlHelper.selectAllTasks();

        while(res.isAfterLast() == false) {
            TaskCategory category = null;
            if(!res.isNull(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_CATEGORY)))
            {
                category = m_categoryDAO.getTaskCategory(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_CATEGORY))).get(0);
            }
            Task task = new Task(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_ID)),
                    res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_TITLE)),
                    res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DATE)),
                    (res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DONE)) > 0) ? true : false,
                    category);
            objectList.add(task);
            res.moveToNext();
        }
        return objectList;
    }

    public List<Task> getTasks(TaskCategory categoryFilter) {
        List<Task> objectList = new ArrayList<Task>();
        Cursor res =  m_sqlHelper.selectTasks(categoryFilter);

        while(res.isAfterLast() == false) {
            TaskCategory category = null;
            if(!res.isNull(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_CATEGORY)))
            {
                category = m_categoryDAO.getTaskCategory(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_CATEGORY))).get(0);
            }
            Task task = new Task(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_ID)),
                    res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_TITLE)),
                    res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DATE)),
                    (res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DONE)) > 0) ? true : false,
                    category);
            objectList.add(task);
            res.moveToNext();
        }
        return objectList;
    }

    public void deleteTask(int id) {
        m_sqlHelper.deleteTask(id);
    }

}
