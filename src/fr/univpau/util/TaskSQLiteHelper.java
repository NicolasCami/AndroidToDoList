package fr.univpau.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskSQLiteHelper extends SQLiteOpenHelper {

	public static final String TABLE_TASK = "task";
	public static final String COLUMN_TASK_ID = "id";
	public static final String COLUMN_TASK_TITLE = "title";
	public static final String COLUMN_TASK_DATE = "date";
	public static final String COLUMN_TASK_DONE = "done";

	// You may use your own database name
	private static final String DATABASE_NAME = "fr.univpau.todolist.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_CREATE = "CREATE TABLE "
		+ TABLE_TASK + "("  + COLUMN_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  " 
		+ COLUMN_TASK_TITLE + " TEXT NOT NULL,  " 
		+ COLUMN_TASK_DATE + " TEXT NOT NULL,  " 
		+ COLUMN_TASK_DONE + " INTEGER NOT NULL ); ";
	
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
				+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK);
		onCreate(db);
	}
	
	public long insertTask(String title, String date, boolean done) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_TASK_TITLE, title);
		contentValues.put(COLUMN_TASK_DATE, date);
		contentValues.put(COLUMN_TASK_DONE, (done) ? 1 : 0);
		return db.insert(TABLE_TASK, null, contentValues);
	}
	
	public boolean updateTask(int id, String title, String date, boolean done) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(COLUMN_TASK_TITLE, title);
		contentValues.put(COLUMN_TASK_DATE, date);
		contentValues.put(COLUMN_TASK_DONE, (done) ? 1 : 0);
		db.update(TABLE_TASK, contentValues, COLUMN_TASK_ID + " = ? ", new String[] { Integer.toString(id) } );
		return true;
	}
	
	/*public Cursor getTask(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.rawQuery( "select * from " + TABLE_TASK + " where " + COLUMN_TASK_ID + "=" + id + "", null );
		return res;
	}*/
	
	public Cursor selectAllTasks() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res =  db.query(TABLE_TASK, null, null, null, null, null, null, null);
		res.moveToFirst();

		return res;
	}
	
	public Integer deleteTask(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TABLE_TASK, COLUMN_TASK_ID + " = ? ", new String[] { Integer.toString(id) });
	}
	
}
