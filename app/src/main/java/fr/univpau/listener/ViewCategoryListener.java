package fr.univpau.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import fr.univpau.todolist.TaskListActivity;
import fr.univpau.util.TaskCategory;

public class ViewCategoryListener implements View.OnClickListener {

    TaskCategory    m_category;
    Context         m_context;
    Intent          m_intent;

    public ViewCategoryListener() {

    }

    public ViewCategoryListener(Context context, Class<?> cls, TaskCategory category) {
        m_category = category;
        m_context = context;
        m_intent = new Intent();
        m_intent.setClass(context, cls);
    }

    @Override
    public void onClick(View v) {
        m_intent.putExtra(TaskListActivity.EXTRA_CATEGORY, m_category);
        m_context.startActivity(m_intent);
    }

}
