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
import fr.univpau.dao.TaskCategoryDAO;
import fr.univpau.dao.TaskDAO;
import fr.univpau.dialog.ConfirmDeleteAllDialog;
import fr.univpau.dialog.ConfirmDeleteCategoryDialog;
import fr.univpau.dialog.ConfirmDeleteDialog;
import fr.univpau.listener.NewTaskListener;
import fr.univpau.util.Task;
import fr.univpau.util.TaskCategory;

/**
 * Activity that list all tasks related to a category.
 */
public class TaskListActivity extends AppCompatActivity implements ConfirmDeleteDialog.ConfirmDeleteDialogListener, ConfirmDeleteAllDialog.ConfirmDeleteAllDialogListener, ConfirmDeleteCategoryDialog.ConfirmDeleteCategoryDialogListener {

    public final static String EXTRA_CATEGORY = "fr.univpau.todolist.CATEGORY";

    List<Task>          m_tasksList;
    TaskAdapter         m_taskAdaper;
    TaskDAO             m_tasksDAO;
    TaskCategoryDAO     m_taskCateogryDAO;
    TaskCategory        m_taskCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        m_tasksDAO = new TaskDAO(this);
        m_taskCateogryDAO = new TaskCategoryDAO(this);
        m_taskCategory = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            m_taskCategory = (TaskCategory)getIntent().getSerializableExtra(EXTRA_CATEGORY);
            m_tasksList = m_tasksDAO.getTasks(m_taskCategory);
        }
        else {
            m_tasksList = m_tasksDAO.getAllTasks();
        }

        ListView listview = (ListView) findViewById(R.id.tasksListView);
        m_taskAdaper = new TaskAdapter(this, R.layout.todolist_item, m_tasksList, m_tasksDAO);
        listview.setAdapter(m_taskAdaper);

        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        NewTaskListener newTaskListener = new NewTaskListener(m_tasksList, m_taskCategory, (EditText) findViewById(R.id.editTitle), m_taskAdaper, m_tasksDAO);
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
            case R.id.deleteCategory:
                DialogFragment deleteListDialog = new ConfirmDeleteCategoryDialog();
                deleteListDialog.show(getSupportFragmentManager(), "confirmDeleteCategory");
                return true;
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
        for(Task task : m_tasksList) {
            m_tasksDAO.deleteTask(task.getId());
        }
        m_tasksList.clear();
        m_taskAdaper.notifyDataSetChanged();
    }

    private void deleteTasksDone() {
        List<Task> toRemove = new ArrayList<Task>();
        for(Task task : m_tasksList) {
            if(task.isDone()) {
                m_tasksDAO.deleteTask(task.getId());
                toRemove.add(task);
            }
        }
        m_tasksList.removeAll(toRemove);
        m_taskAdaper.notifyDataSetChanged();
    }

    private void deleteCategory() {
        // remove all tasks under this category
        deleteTasks();

        // delete the category
        if(m_taskCategory != null)
        {
            m_taskCateogryDAO.deleteTaskCategory(m_taskCategory.getId());
        }

        // leave this activity
        finish();
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

    @Override
    public void onConfirmDeleteCategory(DialogFragment dialog) {
        deleteCategory();
    }

    @Override
    public void onCancelDeleteCategory(DialogFragment dialog) {
        // Do nothing
    }
}
