package fr.univpau.listener;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.util.Task;

public class DoneTaskListener implements View.OnClickListener {

    Task _task;
    CheckBox _doneCheckBox;
    TaskAdapter _taskAdaper;
    TaskDAO _taskDAO;

    public DoneTaskListener() {

    }

    public DoneTaskListener(Task task, CheckBox doneCheckBox, TaskAdapter taskAdaper, TaskDAO taskDAO) {
        _task = task;
        _doneCheckBox = doneCheckBox;
        _taskAdaper = taskAdaper;
        _taskDAO = taskDAO;
    }

    @Override
    public void onClick(View v) {
        _task.setDone(!_task.isDone());
        _taskDAO.updateTask(_task);
        _taskAdaper.notifyDataSetChanged();
    }

}
