package fr.univpau.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import fr.univpau.util.Task;
import fr.univpau.util.TaskSQLiteHelper;

public class TaskDAO {
	
	TaskSQLiteHelper _sqlHelper;
	Context _context;
	
	public TaskDAO(Context context) {
		_sqlHelper = new TaskSQLiteHelper(context);
		_context = context;
	}
	
	public void insertTask(Task task) {
		long id = _sqlHelper.insertTask(task.getTitle(), task.getDate(), task.isDone());
		task.setId((int) id);
	}
	
	public void updateTask(Task task) {
		_sqlHelper.updateTask(task.getId(), task.getTitle(), task.getDate(), task.isDone());
	}
	
	public List<Task> getAllTasks() {
		List<Task> objectList = new ArrayList<Task>();
		Cursor res =  _sqlHelper.selectAllTasks();
		  
		while(res.isAfterLast() == false) {
			Task task = new Task(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_ID)),
					res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_TITLE)),
					res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DATE)),
					(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DONE)) > 0) ? true : false);
			objectList.add(task);
			res.moveToNext();
		}
		return objectList;
	}
	
	public void deleteTask(int id) {
		_sqlHelper.deleteTask(id);
	}
}
