package fr.univpau.todolist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.listener.NewTaskListener;
import fr.univpau.util.Task;

public class MainActivity extends Activity {
	
	List<Task> _tasksList;
	TaskAdapter _taskAdaper;
	TaskDAO _tasksDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _tasksDAO = new TaskDAO(this);
        _tasksList = _tasksDAO.getAllTasks();
        
        ListView listview = (ListView) findViewById(R.id.tasksListView);
        _taskAdaper = new TaskAdapter(this, R.layout.todolist_item, _tasksList, _tasksDAO);
        listview.setAdapter(_taskAdaper);
        
        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        NewTaskListener newTaskListener = new NewTaskListener(_tasksList, (EditText) findViewById(R.id.editTitle), _taskAdaper, _tasksDAO);
        btnNewTask.setOnClickListener(newTaskListener);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
            	deleteTasks();
            	_taskAdaper.notifyDataSetChanged();
                return true;
            case R.id.deleteDone:
            	deleteTasksDone();
            	_taskAdaper.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    private void deleteTasks() {
    	for(Task task : _tasksList) {
    		_tasksDAO.deleteTask(task.getId());
    	}
    	_tasksList.clear();
    }
    
    private void deleteTasksDone() {
    	List<Task> toRemove = new ArrayList<Task>();
    	for(Task task : _tasksList) {
    		if(task.isDone()) {
    			_tasksDAO.deleteTask(task.getId());
    			toRemove.add(task);
    		}
    	}
    	_tasksList.removeAll(toRemove);
    }
}
