package fr.univpau.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskCategorySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_TASK_CATEGORY = "taskcategory";
    public static final String COLUMN_TASK_CATEGORY_ID = "id";
    public static final String COLUMN_TASK_CATEGORY_TITLE = "title";

    private static final String DATABASE_NAME = "fr.univpau.todolist.db";
    private static final int DATABASE_VERSION = 3;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_TASK_CATEGORY + "("  + COLUMN_TASK_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  "
            + COLUMN_TASK_CATEGORY_TITLE + " TEXT NOT NULL);";

    public TaskCategorySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // create table
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TaskSQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK_CATEGORY);
        onCreate(db);
    }

    public long insertTaskCategory(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TASK_CATEGORY_TITLE, title);
        return db.insert(TABLE_TASK_CATEGORY, null, contentValues);
    }

    public boolean updateTaskCategory(int id, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TASK_CATEGORY_TITLE, title);
        db.update(TABLE_TASK_CATEGORY, contentValues, COLUMN_TASK_CATEGORY_ID + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Cursor selectAllTaskCategory() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.query(TABLE_TASK_CATEGORY, null, null, null, null, null, null, null);
        res.moveToFirst();

        return res;
    }

    public Cursor selectTaskCategory(int id) {
        SQLiteDatabase db = getReadableDatabase();

        String where = COLUMN_TASK_CATEGORY_ID + "=" + id + "";

        Cursor res =  db.query(TABLE_TASK_CATEGORY, null, where, null, null, null, null, null);
        res.moveToFirst();

        return res;
    }

    public Integer deleteTaskCategory(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_TASK_CATEGORY, COLUMN_TASK_CATEGORY_ID + " = ? ", new String[] { Integer.toString(id) });
    }

}
