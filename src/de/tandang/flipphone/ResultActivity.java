package de.tandang.flipphone;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finishApp();
			}
		});
		
		// uebergebene werte auslesen
		Intent intent = getIntent();
		String roll = intent.getStringExtra(ReadActivity.EXTRA_ROLL);
		String pitch = intent.getStringExtra(ReadActivity.EXTRA_PITCH);
		String yaw = intent.getStringExtra(ReadActivity.EXTRA_YAW);

		// TextView
		TextView rollView = (TextView) findViewById(R.id.resView2);
		TextView pitchView = (TextView) findViewById(R.id.resView3);
		TextView yawView = (TextView) findViewById(R.id.resView4);
		rollView.setText(roll);
		pitchView.setText(pitch);
		yawView.setText(yaw);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}
	
	public void finishApp() {
		finish();
	}

}
