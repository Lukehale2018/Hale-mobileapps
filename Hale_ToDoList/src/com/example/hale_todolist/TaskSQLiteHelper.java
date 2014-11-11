package com.example.hale_todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TaskSQLiteHelper extends SQLiteOpenHelper {


	// Define the table and columns 
	public static final String TABLE_TASKS = "tasks";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PRIORITY = "priority";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TASK = "task";
	public static final String COLUMN_COMPLETED = "completed";
	
	public static final String DATABASE_NAME = "tasks.db";
	public static final int DATABASE_VERSION = 1;
	
	// Database creation SQL statement 
	private static final String DATABASE_CREATE = "create table " 
	+TABLE_TASKS + "("
	+ COLUMN_ID + " integer primary key autoincrement, " 
	+ COLUMN_DATE + " text not null, "
	+ COLUMN_TASK + " text not null, "
	+ COLUMN_PRIORITY + " integer not null, " 
	+ COLUMN_COMPLETED + " integer not null);";

	public TaskSQLiteHelper(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION); 
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {		
		db.execSQL("DROP TABLE IF EXISTS" + "TABLE_TASKS");
		onCreate(db);
		
	}
	
	

}
