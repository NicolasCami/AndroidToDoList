package fr.univpau.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.univpau.util.TaskCategory;
import fr.univpau.util.TaskCategorySQLiteHelper;

public class TaskCategoryDAO {

    TaskCategorySQLiteHelper    m_sqlHelper;
    Context                     m_context;

    public TaskCategoryDAO(Context context) {
        m_sqlHelper = new TaskCategorySQLiteHelper(context);
        m_context = context;
    }

    public void insertTaskCategory(TaskCategory taskCategory) {
        long id = m_sqlHelper.insertTaskCategory(taskCategory.getTitle());
        taskCategory.setId((int) id);
    }

    public void updateTaskCategory(TaskCategory taskCategory) {
        m_sqlHelper.updateTaskCategory(taskCategory.getId(), taskCategory.getTitle());
    }

    public List<TaskCategory> getAllTaskCategory() {
        List<TaskCategory> objectList = new ArrayList<TaskCategory>();
        Cursor res =  m_sqlHelper.selectAllTaskCategory();

        while(res.isAfterLast() == false) {
            TaskCategory taskCategory = new TaskCategory(res.getInt(res.getColumnIndex(TaskCategorySQLiteHelper.COLUMN_TASK_CATEGORY_ID)),
                    res.getString(res.getColumnIndex(TaskCategorySQLiteHelper.COLUMN_TASK_CATEGORY_TITLE)));
            objectList.add(taskCategory);
            res.moveToNext();
        }
        return objectList;
    }

    public List<TaskCategory> getTaskCategory(int id) {
        List<TaskCategory> objectList = new ArrayList<TaskCategory>();

        Cursor res =  m_sqlHelper.selectTaskCategory(id);

        while(res.isAfterLast() == false) {
            TaskCategory taskCategory = new TaskCategory(res.getInt(res.getColumnIndex(TaskCategorySQLiteHelper.COLUMN_TASK_CATEGORY_ID)),
                    res.getString(res.getColumnIndex(TaskCategorySQLiteHelper.COLUMN_TASK_CATEGORY_TITLE)));
            objectList.add(taskCategory);
            res.moveToNext();
        }

        return objectList;
    }

    public void deleteTaskCategory(int id) {
        m_sqlHelper.deleteTaskCategory(id);
    }

}
