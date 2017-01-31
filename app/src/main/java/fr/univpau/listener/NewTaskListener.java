package fr.univpau.listener;

import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.util.Task;

public class NewTaskListener implements View.OnClickListener {

    List<Task> tasks;
    EditText editTitle;
    TaskAdapter taskAdaper;
    TaskDAO _taskDAO;

    public NewTaskListener() {

    }

    public NewTaskListener(List<Task> tasks, EditText editTitle, TaskAdapter taskAdaper, TaskDAO taskDAO) {
        this.tasks = tasks;
        this.editTitle = editTitle;
        this.taskAdaper = taskAdaper;
        _taskDAO = taskDAO;
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = sdf.format(calendar.getTime());
        Task newTask = new Task(editTitle.getText().toString(), dateString, false);
        tasks.add(newTask);
        _taskDAO.insertTask(newTask);
        //Log.i("DEBUG", tasks.toString());
        taskAdaper.notifyDataSetChanged();
        editTitle.setText("");
    }

}
