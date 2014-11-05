package com.example.hale_todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EditTaskActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_task);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// handle button CLicks
	public void handleClick(View v) {
		switch (v.getId()) {
		case R.id.buttonAddTask:
			break;
		case R.id.buttonEditTask:
		break;
		case R.id.buttonDeleteTask:
			break;
		case R.id.buttonAddToDo:
			break;
		case R.id.buttonMainMenu:		
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		break;
			
		}
	}
	
	
	
	
	
	
	
	
}
