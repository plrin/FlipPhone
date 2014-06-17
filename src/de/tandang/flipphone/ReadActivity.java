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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ReadActivity extends Activity {
	
	private static SensorManager sensorManager;
	private Sensor sensor;
	final static String EXTRA_ROLL = "roll";
	final static String EXTRA_PITCH = "pitch";
	final static String EXTRA_YAW = "yaw";
	private EditText rollText;
	private EditText pitchText;
	private EditText yawText;
	private TextView x;
	private TextView y;
	private TextView z;
	private TextView rotations;
	private int numberOfRotations = 0;
//	private int numberOfRotationsY = 0;
//	private int numberOfRotationsZ = 0;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopFirstAcitivity();
				switchActivity();
			}
		});
		
		
		// sensor definieren
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		if (sensor != null) {
			sensorManager.registerListener(mySensorEventListener, sensor,
		          SensorManager.SENSOR_DELAY_FASTEST);
		      Log.i("Compass MainActivity", "Registerered for ORIENTATION Sensor");
		    } else {
		      Log.e("Compass MainActivity", "Registerered for ORIENTATION Sensor");
		      Toast.makeText(this, "ORIENTATION Sensor not found",
		          Toast.LENGTH_LONG).show();
		      finish();
		    }	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read, menu);
		return true;
	}
	
	boolean rotationCondition = false;
	// sensor listener definieren
	private SensorEventListener mySensorEventListener = new SensorEventListener() {
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			
		    //Definiere TextFelder
		   	x = (TextView) findViewById(R.id.resView2);
		   	y = (TextView) findViewById(R.id.resView3);
		    z = (TextView) findViewById(R.id.resView4);
		    
		    rotations = (TextView) findViewById(R.id.textView5);

		    //Definiere variablen f�r Gyroskop Ausgabe
		    float axisX = event.values[0];
		    float axisY = event.values[1];
		    float axisZ = event.values[2];
		    //aendere TextFelder
		    
		    
		    
		    if ((axisY > 0) && rotationCondition == false){
		    	rotationCondition = true;
		    	
		    }
		    if ((axisY < 0) && rotationCondition == true){
		    	numberOfRotations += 1;
		    	rotationCondition = false;
		    
		    }
		    
		    x.setText("" + axisX);
		    y.setText("" + axisY );
		    z.setText("" + axisZ );
		    rotations.setText("" + numberOfRotations);
		    
		    
		}
	};
	
	
	// eigene methoden

	public void stopFirstAcitivity() {
		finish();
	}

	//wechselt und übergibt werte zur result activity 
	public void switchActivity() {
		
		String rollValue = x.getText().toString();
		String pitchValue = y.getText().toString();
		String yawValue = z.getText().toString();
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra(EXTRA_PITCH, rollValue);
		intent.putExtra(EXTRA_ROLL, pitchValue);
		intent.putExtra(EXTRA_YAW, yawValue);
		startActivity(intent);
	}
	
	public void count() {
		
	}

}
