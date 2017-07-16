package fr.univpau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.univpau.dao.TaskDAO;
import fr.univpau.listener.ViewCategoryListener;
import fr.univpau.todolist.R;
import fr.univpau.todolist.TaskListActivity;
import fr.univpau.util.TaskCategory;

public class TaskListAdapter extends ArrayAdapter<TaskCategory> {

    public TaskListAdapter(Context context, int textViewResourceId, List<TaskCategory> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.category_item, parent, false);
        } else {
            rowView = convertView;
        }

        TaskCategory category = getItem(position);

        TextView itemCategory = (TextView) rowView.findViewById(R.id.itemCategory);
        itemCategory.setText(category.getTitle() + " #" + category.getId());

        ViewCategoryListener clickListener = new ViewCategoryListener(getContext(), TaskListActivity.class, category);
        rowView.setOnClickListener(clickListener);

        return rowView;
    }
}