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
import fr.univpau.util.TaskCategory;

public class NewTaskListener implements View.OnClickListener {

    List<Task>      m_tasks;
    TaskCategory    m_taskCategory;
    EditText        m_editTitle;
    TaskAdapter     m_taskAdaper;
    TaskDAO         m_taskDAO;

    public NewTaskListener() {

    }

    public NewTaskListener(List<Task> tasks, TaskCategory category, EditText editTitle, TaskAdapter taskAdaper, TaskDAO taskDAO) {
        m_tasks = tasks;
        m_taskCategory = category;
        m_editTitle = editTitle;
        m_taskAdaper = taskAdaper;
        m_taskDAO = taskDAO;
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = new GregorianCalendar();
        Date trialTime = new Date();
        calendar.setTime(trialTime);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String dateString = sdf.format(calendar.getTime());
        Task newTask = new Task(m_editTitle.getText().toString(), dateString, false, m_taskCategory);
        m_tasks.add(newTask);
        m_taskDAO.insertTask(newTask);
        m_taskAdaper.notifyDataSetChanged();
        m_editTitle.setText("");
    }

}
