package fr.univpau.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import fr.univpau.todolist.TaskListActivity;
import fr.univpau.util.TaskCategory;

public class ViewCategoryListener implements View.OnClickListener {

    TaskCategory _category;
    Context _context;
    Intent _intent;

    public ViewCategoryListener() {

    }

    public ViewCategoryListener(Context context, Class<?> cls, TaskCategory category) {
        _category = category;
        _context = context;
        _intent = new Intent();
        _intent.setClass(context, cls);
    }

    @Override
    public void onClick(View v) {
        _intent.putExtra(TaskListActivity.EXTRA_CATEGORY, _category);
        _context.startActivity(_intent);
    }

}
