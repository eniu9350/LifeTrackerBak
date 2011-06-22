package com.hexun.eniu.lifetracker.activities.serial;

import com.hexun.eniu.lifetracker.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class SerialTimerActivity extends Activity {
	private Chronometer crono;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.serial_timer);

		// new
		// AlertDialog.Builder(this).setMessage("mymessage").setTitle("title")
		// .setCancelable(true).setNeutralButton(android.R.string.cancel,
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int whichButton) {
		// }
		// }).show();

		Intent intent = getIntent();
		String targetName = intent.getStringExtra("targetName");

		TextView tvTargetName = (TextView) this
				.findViewById(R.id.SerialTimer_tvTargetName);
		tvTargetName.setText(targetName);

		this.crono = (Chronometer) findViewById(R.id.SerialTimer_crMain);
		crono.setBase(SystemClock.elapsedRealtime());
		startCrono();

		Button next = (Button) findViewById(R.id.SerialTimer_btStop);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				crono.stop();

				long elapsedMillis = SystemClock.elapsedRealtime()
						- crono.getBase();

				Intent intent = new Intent();
				intent.putExtra("msg", elapsedMillis);
				setResult(Activity.RESULT_OK, intent);
				finish();
			}

		});

	}

	private void startCrono() {
		crono.setBase(SystemClock.elapsedRealtime());
		crono.start();
	}
}
