package com.example.hale_finalexam_fall14;

import com.example.hale_finalexam_fall14.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends ActionBarActivity {
	
	//Fields
	private Button buttonDollars;
	private Button buttonYens;
	private Button buttonPounds;
	
	private EditText editTextD;
	private EditText editTextY;
	private EditText editTextP;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Bind
		buttonDollars = (Button)findViewById(R.id.buttonDollars);
		buttonYens = (Button)findViewById(R.id.buttonYens);
		buttonPounds = (Button)findViewById(R.id.buttonPounds);
		editTextD = (EditText)findViewById(R.id.editTextD);
		editTextY = (EditText)findViewById(R.id.editTextY);
		editTextP = (EditText)findViewById(R.id.editTextP);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	
	//Calculations
	
	private String dollarsToPounds(double dollars) {
		double pounds = dollars *0.64;
		return String.valueOf(dollars);
	}

	private String poundsToDollars(double pounds) {
		double miles = pounds /0.64;
		return String.valueOf(pounds);
	}
	
	private String dollarsToYens(double dollars) {
		double yens = dollars *116.25;
		return String.valueOf(dollars);
	}
	private String yensToDollars(double yens) {
		double dollars = yens /116.25;
		return String.valueOf(yens);
	}
	
	private String yensToPounds(double yens) {
		double pounds = yens *181.93;
		return String.valueOf(yens);
	}
	private String poundsToYens(double pounds) {
		double yens = pounds /181.93;
		return String.valueOf(pounds);
	}
	
	
	
	
	
	
	
	
	
	
}//end class
