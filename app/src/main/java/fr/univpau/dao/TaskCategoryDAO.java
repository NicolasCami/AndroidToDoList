package fr.univpau.dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import fr.univpau.util.TaskCategory;
import fr.univpau.util.TaskCategorySQLiteHelper;

public class TaskCategoryDAO {

    TaskCategorySQLiteHelper _sqlHelper;
    Context _context;

    public TaskCategoryDAO(Context context) {
        _sqlHelper = new TaskCategorySQLiteHelper(context);
        _context = context;
    }

    public void insertTaskCategory(TaskCategory taskCategory) {
        long id = _sqlHelper.insertTaskCategory(taskCategory.getTitle());
        taskCategory.setId((int) id);
    }

    public void updateTaskCategory(TaskCategory taskCategory) {
        _sqlHelper.updateTaskCategory(taskCategory.getId(), taskCategory.getTitle());
    }

    public List<TaskCategory> getAllTaskCategory() {
        List<TaskCategory> objectList = new ArrayList<TaskCategory>();
        Cursor res =  _sqlHelper.selectAllTaskCategory();

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

        Cursor res =  _sqlHelper.selectTaskCategory(id);

        while(res.isAfterLast() == false) {
            TaskCategory taskCategory = new TaskCategory(res.getInt(res.getColumnIndex(TaskCategorySQLiteHelper.COLUMN_TASK_CATEGORY_ID)),
                    res.getString(res.getColumnIndex(TaskCategorySQLiteHelper.COLUMN_TASK_CATEGORY_TITLE)));
            objectList.add(taskCategory);
            res.moveToNext();
        }

        return objectList;
    }

    public void deleteTaskCategory(int id) {
        _sqlHelper.deleteTaskCategory(id);
    }

}
