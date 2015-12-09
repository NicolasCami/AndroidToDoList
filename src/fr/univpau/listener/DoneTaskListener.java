package fr.univpau.listener;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.util.Task;

public class DoneTaskListener implements OnClickListener {
	
	Task task;
	CheckBox doneCheckBox;
	TaskAdapter taskAdaper;
	TaskDAO _taskDAO;
	
	public DoneTaskListener() {
		
	}
	
	public DoneTaskListener(Task task, CheckBox doneCheckBox, TaskAdapter taskAdaper, TaskDAO taskDAO) {
		this.task = task;
		this.doneCheckBox = doneCheckBox;
		this.taskAdaper = taskAdaper;
		_taskDAO = taskDAO;
	}

	@Override
	public void onClick(View v) {
		task.setDone(doneCheckBox.isChecked());
		_taskDAO.updateTask(task);
		taskAdaper.notifyDataSetChanged();
	}

}
