package fr.univpau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import fr.univpau.dao.TaskDAO;
import fr.univpau.listener.DoneTaskListener;
import fr.univpau.todolist.R;
import fr.univpau.util.Task;

public class TaskAdapter extends ArrayAdapter<Task> {

    TaskDAO     m_taskDAO;

    public TaskAdapter(Context context, int textViewResourceId, List<Task> objects, TaskDAO taskDAO) {
        super(context, textViewResourceId, objects);
        m_taskDAO = taskDAO;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = null;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.todolist_item, parent, false);
        }
        else {
            rowView = convertView;
        }

        Task task = getItem(position);

        TextView itemTitle = (TextView) rowView.findViewById(R.id.itemTitle);
        itemTitle.setText(task.getId() + " : " + task.getTitle());

        TextView itemDate = (TextView) rowView.findViewById(R.id.itemDate);
        itemDate.setText(task.getDate());

        TextView itemCategory = (TextView) rowView.findViewById(R.id.itemCategory);
        if(task.getCategory() != null)
        {
            itemCategory.setText(task.getCategory().getTitle());
        }
        else
        {
            itemCategory.setText("Undefined");
        }

        CheckBox doneCheckBox = (CheckBox) rowView.findViewById(R.id.doneCheckBox);
        doneCheckBox.setChecked(task.isDone());
        DoneTaskListener doneTaskListener = new DoneTaskListener(task, doneCheckBox, this, m_taskDAO);
        rowView.setOnClickListener(doneTaskListener);
        doneCheckBox.setOnClickListener(doneTaskListener);

        return rowView;
    }

}
