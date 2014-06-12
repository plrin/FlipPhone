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
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
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
	
	
	// sensor listener definieren
	private SensorEventListener mySensorEventListener = new SensorEventListener() {

	    @Override
	    public void onAccuracyChanged(Sensor sensor, int accuracy) {
	    }

	    @Override
	    public void onSensorChanged(SensorEvent event) {
	    	
	    	//Definiere TextFelder
	    	TextView x = (TextView) findViewById(R.id.resView2);
	    	TextView y = (TextView) findViewById(R.id.resView3);
	    	TextView z = (TextView) findViewById(R.id.resView4);
	    	//Definiere variablen f�r Gyroskop Ausgabe
	    	float axisX = event.values[0];
	        float axisY = event.values[1];
	        float axisZ = event.values[2];
	        //�ndere TextFelder
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

	public void stopFirstAcitivity() {
		finish();
	}

	//wechselt und übergibt werte zur result activity 
	public void switchActivity() {
		
		// hole werte aus view und speichere sie im edittext um in result zu uebergeben
				rollText = (EditText) findViewById(R.id.resView2);
				pitchText = (EditText) findViewById(R.id.resView3);
				yawText = (EditText) findViewById(R.id.resView4);
		
		String rollValue = rollText.getText().toString();
		String pitchValue = pitchText.getText().toString();
		String yawValue = yawText.getText().toString();
		Intent intent = new Intent(this, ResultActivity.class);
		intent.putExtra(EXTRA_PITCH, rollValue);
		intent.putExtra(EXTRA_ROLL, pitchValue);
		intent.putExtra(EXTRA_YAW, yawValue);
		startActivity(intent);
	}
	
	public void count() {
		
	}

}
