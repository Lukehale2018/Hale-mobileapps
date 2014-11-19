package com.example.pedometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView; 
import android.media.MediaPlayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Pedometer extends Activity {
	
	// Media player Array
		private MediaPlayer soundPlayer;
		
		private int soundResources[] = {R.raw.rooster};
		
	
	//Display fields for accelerometer
	private TextView textViewX;
	private TextView textViewY;
private TextView textViewZ;

// display field for sensitivity 
private TextView textSensitive;

//display for steps
private TextView textViewSteps;

// reset button
private Button buttonReset;

// sensor manager
private SensorManager sensorManager;
private float acceleration;

//values to calculate number of steps
private float previousY;
private float currentY;
private int numSteps;

//seekbar fields
private SeekBar seekBar;
private int threshold; //point at which we want to trigger a 'step'
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
        
        // attach objects to XML View
        textViewX = (TextView)findViewById(R.id.textViewX);
        textViewY = (TextView)findViewById(R.id.textViewY);
        textViewZ = (TextView)findViewById(R.id.textViewZ);
        
        // attach step and sensitive view objects to XML
        textViewSteps = (TextView)findViewById(R.id.textSteps);
        textSensitive = (TextView)findViewById(R.id.textSensitive);
        
        // attach reset btn
        buttonReset = (Button)findViewById(R.id.buttonReset);
        
        // attach the seekbar
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        
        //set the values on seekbar, threshold, and threshold display
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(seekBarListener); 
        threshold = 10;
        textSensitive.setText(String.valueOf(threshold));
        
        // initialize values
        previousY = 0;
        currentY = 0;
        numSteps = 0;
        
        // initialize accel values
        acceleration = 0.00f;
        
        // enable listener
        enableAccelerometerListening();
        
        soundPlayer = MediaPlayer.create(this, soundResources[0]);
    }
    private void enableAccelerometerListening() {
    	// initialize the sensor manager
    	sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    	sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
    			SensorManager.SENSOR_DELAY_NORMAL);
    }

    // event handler for accelerometer events
    private SensorEventListener sensorEventListener =
    		new SensorEventListener()
    {
    	// listens for change in acceleration, displays, and computes the steps
    	public void onSensorChanged(SensorEvent event)
    	{
    		//gather the values from accelerometer
    		float x = event.values[0];
    		float y = event.values[1];
    		float z = event.values[2];
    		
    		//fetch the current y
    		currentY = y;
    		
    		// measure if a step is taken
    		if ( Math.abs(currentY - previousY) > threshold ) {
    			numSteps++;
    			textViewSteps.setText(String.valueOf(numSteps));
    		} // end if
    		
    		// display the values
    		textViewX.setText(String.valueOf(x));
    		textViewY.setText(String.valueOf(y));
    		textViewZ.setText(String.valueOf(z));
    		
    		// store the previous y
    		previousY = y;
    		
    		}
    	public void onAccuracyChanged(Sensor sensor, int accuracy)
    	{
    		//empty - required by class
    	} // end onAccuracy changed
    	
    };
    	
    	
    
    
    // called by the resetbutton to the steps count to 0 and reset the display
    public void resetSteps(View v) {
    	numSteps = 0;
    	textViewSteps.setText(String.valueOf(numSteps));
    	
    	 // Functions to play sounds onClick
     		soundPlayer.start();
    	
    }//end method resetSteps
    
    // the inner class for the seekbarlistener
    private OnSeekBarChangeListener seekBarListener =
    		new OnSeekBarChangeListener()
    {
    	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    		//change the threshold
    		threshold = seekBar.getProgress();
    		//writes to the TextView
    		textSensitive.setText(String.valueOf(threshold));
    	} // end method onProgressCHanged()
    	
    	public void onStartTrackingTouch(SeekBar seekBar) {
    		
    	}// end method onstarttrackingtouch()
    	
    	public void onStopTrackingTouch(SeekBar seekBar) {
    		
    	}

    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pedometer, menu);
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
    

 	
 	
}
