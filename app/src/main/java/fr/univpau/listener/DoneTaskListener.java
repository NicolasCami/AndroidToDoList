package fr.univpau.listener;

import android.view.View;
import android.widget.CheckBox;

import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.util.Task;

public class DoneTaskListener implements View.OnClickListener {

    Task            m_task;
    CheckBox        m_doneCheckBox;
    TaskAdapter     m_taskAdaper;
    TaskDAO         m_taskDAO;

    public DoneTaskListener() {

    }

    public DoneTaskListener(Task task, CheckBox doneCheckBox, TaskAdapter taskAdaper, TaskDAO taskDAO) {
        m_task = task;
        m_doneCheckBox = doneCheckBox;
        m_taskAdaper = taskAdaper;
        m_taskDAO = taskDAO;
    }

    @Override
    public void onClick(View v) {
        m_task.setDone(!m_task.isDone());
        m_taskDAO.updateTask(m_task);
        m_taskAdaper.notifyDataSetChanged();
    }

}
