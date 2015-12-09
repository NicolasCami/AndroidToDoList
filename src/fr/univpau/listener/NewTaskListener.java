package fr.univpau.listener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.univpau.adapter.TaskAdapter;
import fr.univpau.dao.TaskDAO;
import fr.univpau.todolist.R;
import fr.univpau.util.Task;
import fr.univpau.util.TaskSQLiteHelper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewTaskListener implements OnClickListener {
	
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
