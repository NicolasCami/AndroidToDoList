package fr.univpau.todolist;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import fr.univpau.adapter.TaskListAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.dao.TaskCategoryDAO;
import fr.univpau.dialog.NewCategoryDialog;
import fr.univpau.util.TaskCategory;

public class MainActivity extends AppCompatActivity implements NewCategoryDialog.NewCategoryDialogListener {

    List<TaskCategory>  m_categoryList;
    TaskListAdapter     m_taskAdaper;
    TaskDAO             m_tasksDAO;
    TaskCategoryDAO     m_taskCategoryDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_tasksDAO = new TaskDAO(this);
        m_taskCategoryDAO = new TaskCategoryDAO(this);

        refreshList();
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newCategory:
                DialogFragment deleteAllDialog = new NewCategoryDialog();
                deleteAllDialog.show(getSupportFragmentManager(), "newCategory");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onConfirmNewCategory(DialogFragment dialog, String title) {
        TaskCategory newTaskCategory = new TaskCategory(title);
        m_taskCategoryDAO.insertTaskCategory(newTaskCategory);

        refreshList();
    }

    @Override
    public void onCancelNewCategory(DialogFragment dialog) {
        // nothing to do...
    }

    public void refreshList() {
        m_categoryList = m_taskCategoryDAO.getAllTaskCategory();
        ListView listview = (ListView) findViewById(R.id.categoryListView);
        m_taskAdaper = new TaskListAdapter(this, R.layout.todolist_item, m_categoryList);
        listview.setAdapter(m_taskAdaper);
    }
}
