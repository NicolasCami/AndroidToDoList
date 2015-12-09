package fr.univpau.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import fr.univpau.util.TaskSQLiteHelper;

public class TaskProvider extends ContentProvider {
	
	public static final String PROVIDER_NAME = "fr.univpau.provider";
	public static final String URL = "content://" + PROVIDER_NAME + "/tasks";
	public static final Uri CONTENT_URI = Uri.parse(URL);

    private TaskSQLiteHelper _taskHelper;

    public boolean onCreate() {
    	_taskHelper = new TaskSQLiteHelper(this.getContext());
        return true;
    }

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if(uri.equals(CONTENT_URI)) {
			return _taskHelper.selectAllTasks();
		}
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return "vnd.android.cursor.dir/vnd.univpau.todolist";
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
}