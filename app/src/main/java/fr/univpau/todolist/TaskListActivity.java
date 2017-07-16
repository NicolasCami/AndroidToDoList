package fr.univpau.todolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.dialog.ConfirmDeleteAllDialog;
import fr.univpau.dialog.ConfirmDeleteDialog;
import fr.univpau.listener.NewTaskListener;
import fr.univpau.util.Task;
import fr.univpau.util.TaskCategory;

/**
 * Activity that list all tasks related to a category.
 */
public class TaskListActivity extends AppCompatActivity implements ConfirmDeleteDialog.ConfirmDeleteDialogListener, ConfirmDeleteAllDialog.ConfirmDeleteAllDialogListener {

    public final static String EXTRA_CATEGORY = "fr.univpau.todolist.CATEGORY";

    List<Task> _tasksList;
    TaskAdapter _taskAdaper;
    TaskDAO _tasksDAO;
    TaskCategory taskCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        _tasksDAO = new TaskDAO(this);
        taskCategory = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            taskCategory = (TaskCategory)getIntent().getSerializableExtra(EXTRA_CATEGORY);
            _tasksList = _tasksDAO.getTasks(taskCategory);
        }
        else {
            _tasksList = _tasksDAO.getAllTasks();
        }

        ListView listview = (ListView) findViewById(R.id.tasksListView);
        _taskAdaper = new TaskAdapter(this, R.layout.todolist_item, _tasksList, _tasksDAO);
        listview.setAdapter(_taskAdaper);

        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        NewTaskListener newTaskListener = new NewTaskListener(_tasksList, taskCategory, (EditText) findViewById(R.id.editTitle), _taskAdaper, _tasksDAO);
        btnNewTask.setOnClickListener(newTaskListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_task_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAll:
                DialogFragment deleteAllDialog = new ConfirmDeleteAllDialog();
                deleteAllDialog.show(getSupportFragmentManager(), "confirmDeleteAll");
                return true;
            case R.id.deleteDone:
                DialogFragment deleteDialog = new ConfirmDeleteDialog();
                deleteDialog.show(getSupportFragmentManager(), "confirmDelete");
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
        _taskAdaper.notifyDataSetChanged();
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
        _taskAdaper.notifyDataSetChanged();
    }

    @Override
    public void onConfirmDelete(DialogFragment dialog) {
        deleteTasksDone();
    }

    @Override
    public void onCancelDelete(DialogFragment dialog) {
        // Do nothing
    }

    @Override
    public void onConfirmDeleteAll(DialogFragment dialog) {
        deleteTasks();
    }

    @Override
    public void onCancelDeleteAll(DialogFragment dialog) {
        // Do nothing
    }
}