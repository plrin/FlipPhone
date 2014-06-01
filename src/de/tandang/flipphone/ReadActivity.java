package de.tandang.flipphone;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class ReadActivity extends Activity {
	private static SensorManager sensorManager;
	private Sensor sensor;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				stopRead();
				switchActivity();
				
			}
			
			
		});
		
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
		if (sensor != null) {
		      sensorManager.registerListener(mySensorEventListener, sensor,
		          SensorManager.SENSOR_DELAY_NORMAL);
		      Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");
		    } else {
		      Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
		      Toast.makeText(this, "ORIENTATION Sensor not found",
		          Toast.LENGTH_LONG).show();
		      finish();
		    }

	}
	
	private SensorEventListener mySensorEventListener = new SensorEventListener() {

	    @Override
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	    @Override
	    public void onSensorChanged(SensorEvent event) {
	    	
	    	//Definiere TextFelder
	    	TextView x = (TextView) findViewById(R.id.textView5);
	    	TextView y = (TextView) findViewById(R.id.textView6);
	    	TextView z = (TextView) findViewById(R.id.textView7);
	    	//Definiere variablen für Gyroskop Ausgabe
	    	float axisX = event.values[0];
	        float axisY = event.values[1];
	        float axisZ = event.values[2];
	        //Ändere TextFelder
	        x.setText("" + axisX );
	        y.setText("" + axisY );
	        z.setText("" + axisZ ); 
	    }
	  };
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read, menu);
		return true;
	}

	
	public void stopRead() {
		// stop the reading and switch to next screen with number of rotations
		
	}
	
	public void switchActivity() {
		finish();
		Intent intent = new Intent(this, ResultActivity.class);
		startActivity(intent);
	}

}
