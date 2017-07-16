package fr.univpau.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TaskSQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TASK = "task";
    public static final String COLUMN_TASK_ID = "id";
    public static final String COLUMN_TASK_TITLE = "title";
    public static final String COLUMN_TASK_DATE = "date";
    public static final String COLUMN_TASK_DONE = "done";
    public static final String COLUMN_TASK_CATEGORY = "category";

    // You may use your own database name
    private static final String DATABASE_NAME = "fr.univpau.todolist.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_TASK + "("  + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  "
            + COLUMN_TASK_TITLE + " TEXT NOT NULL,  "
            + COLUMN_TASK_DATE + " TEXT NOT NULL,  "
            + COLUMN_TASK_DONE + " INTEGER NOT NULL, "
            + COLUMN_TASK_CATEGORY + " INTEGER ); ";

    public TaskSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TaskSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which should not destroy old data, but be careful.");

        // Get existing tasks
        List<Task> objectList = new ArrayList<Task>();
        Cursor res =  db.query(TABLE_TASK, null, null, null, null, null, null, null);
        res.moveToFirst();
        while(res.isAfterLast() == false) {
            Task task = new Task(res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_ID)),
                    res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_TITLE)),
                    res.getString(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DATE)),
                    (res.getInt(res.getColumnIndex(TaskSQLiteHelper.COLUMN_TASK_DONE)) > 0) ? true : false,
                    null);
            objectList.add(task);
            res.moveToNext();
        }

        // delete old table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);

        // create new table
        onCreate(db);

        // insert old tasks
        for(Task task : objectList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_TASK_TITLE, task.getTitle());
            contentValues.put(COLUMN_TASK_DATE, task.getDate());
            contentValues.put(COLUMN_TASK_DONE, (task.isDone()) ? 1 : 0);
            db.insert(TABLE_TASK, null, contentValues);
        }
    }

    public long insertTask(String title, String date, boolean done, TaskCategory category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TASK_TITLE, title);
        contentValues.put(COLUMN_TASK_DATE, date);
        contentValues.put(COLUMN_TASK_DONE, (done) ? 1 : 0);
        if(category != null)
        {
            contentValues.put(COLUMN_TASK_CATEGORY, category.getId());
        }
        return db.insert(TABLE_TASK, null, contentValues);
    }

    public boolean updateTask(int id, String title, String date, boolean done, TaskCategory category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TASK_TITLE, title);
        contentValues.put(COLUMN_TASK_DATE, date);
        contentValues.put(COLUMN_TASK_DONE, (done) ? 1 : 0);
        if(category != null)
        {
            contentValues.put(COLUMN_TASK_CATEGORY, category.getId());
        }
        db.update(TABLE_TASK, contentValues, COLUMN_TASK_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Cursor selectAllTasks() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.query(TABLE_TASK, null, null, null, null, null, null, null);
        res.moveToFirst();

        return res;
    }

    public Cursor selectTasks(TaskCategory category) {
        SQLiteDatabase db = getReadableDatabase();

        String where = "";
        if(category != null)
        {
            where = COLUMN_TASK_CATEGORY + "=" + category.getId() + "";
        }
        else
        {
            where = COLUMN_TASK_CATEGORY + " IS NULL";
        }

        Cursor res =  db.query(TABLE_TASK, null, where, null, null, null, null, null);
        res.moveToFirst();

        return res;
    }

    public Integer deleteTask(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_TASK, COLUMN_TASK_ID + " = ? ", new String[] { Integer.toString(id) });
    }

}
