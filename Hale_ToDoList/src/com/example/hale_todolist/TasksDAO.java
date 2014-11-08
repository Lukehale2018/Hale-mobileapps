package com.example.hale_todolist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class TasksDAO {
	// Fields
	private SQLiteDatabase database;
	private TaskSQLiteHelper dbHelper;
	private String [] allColumns = {
			TaskSQLiteHelper.COLUMN_ID,
			TaskSQLiteHelper.COLUMN_PRIORITY,
			TaskSQLiteHelper.COLUMN_DATE,
			TaskSQLiteHelper.COLUMN_TASK,
			TaskSQLiteHelper.COLUMN_COMPLETED};
	
	
	
	// Constructor
	public TasksDAO(Context context) {
		dbHelper = new TaskSQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	public void close() {
		dbHelper.close();
	}
	
	public Task createTask(Task t) {
		ContentValues values = new ContentValues();
		// place t date into values objects
		// handle boolean values
		int priority = 0;
		if (t.getPriority()) {
			priority = 1;
		}
		int done = 0;
		if (t.getCompleted()) {
			done = 1;
		}
		
		values.put(TaskSQLiteHelper.COLUMN_PRIORITY, priority);
		values.put(TaskSQLiteHelper.COLUMN_DATE, t.getDate());
		values.put(TaskSQLiteHelper.COLUMN_TASK, t.getTaskDetails());
		values.put(TaskSQLiteHelper.COLUMN_COMPLETED, done);
		
		long insertId = database.insert(TaskSQLiteHelper.TABLE_TASKS, null, values);
		
		Cursor cursor = database.query(TaskSQLiteHelper.TABLE_TASKS, allColumns,
				TaskSQLiteHelper.COLUMN_ID + " = " + insertId , null , null, null, null);
		
		cursor.moveToLast();
		Task newTask = cursorToTask(cursor);
		cursor.close();
		
		return newTask;
		
		
		
		
		
	}
	
	public void deleteTask(Task t) {
		int id = t.getId();
		database.delete(TaskSQLiteHelper.TABLE_TASKS,
				TaskSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
	
	public void editTask(Task t) {
		ContentValues values = new ContentValues();
		int id = t.getId();
		
		int priority = 0;
		if (t.getPriority()) {
			priority = 1;
		}
		
		int done = 0;
		if (t.getCompleted()) {
			done = 1;
		}
	
		values.put(TaskSQLiteHelper.COLUMN_PRIORITY, priority);
		values.put(TaskSQLiteHelper.COLUMN_DATE, t.getDate());
		values.put(TaskSQLiteHelper.COLUMN_TASK, t.getTaskDetails());
		values.put(TaskSQLiteHelper.COLUMN_COMPLETED, done);
	
		database.update(TaskSQLiteHelper.TABLE_TASKS, values, TaskSQLiteHelper.COLUMN_ID + " = " + id, null);
	}
		public List<Task> getAllTasks() {
			List <Task> taskList = new ArrayList<Task>(0);
			
			Cursor cursor = database.query(TaskSQLiteHelper.TABLE_TASKS, allColumns, null, null, null, null, null);
			
			while(!cursor.isAfterLast()) {
				Task task = cursorToTask(cursor);
				taskList.add(task);
				cursor.moveToNext();
			}
			
			return taskList;
				
		}
		
		
		
		private Task cursorToTask(Cursor cursor) {
			
			int id = cursor.getInt(0);
			int priority = cursor.getInt(1);
			String date = cursor.getString(2);
			String taskString = cursor.getString(3);
			int complete = cursor.getInt(4);
			
			Task t = new Task();
			
			t.setTaskId(id);
			t.setPriority(priority == 1);
			t.setDate(date);
			t.setTask(taskString);
			t.setCompleted(complete == 1);
			
			return t; 
			
								
		}
		
	public Task getTaskById(int id) {
		// create a cursor
		Cursor cursor = database.query(TaskSQLiteHelper.TABLE_TASKS, allColumns, 
				TaskSQLiteHelper.COLUMN_ID + " = " + id, null, null, null, null);
		return (cursor.moveToFirst()) ? cursorToTask(cursor) : null;
	}
		
	
	
	
	
}
