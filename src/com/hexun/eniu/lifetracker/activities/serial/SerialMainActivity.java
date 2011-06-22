package com.hexun.eniu.lifetracker.activities.serial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.activities.summary.SummaryMainActivity;

public class SerialMainActivity extends Activity {

	public static final int REQUEST_CODE_SerialTimer = 1;
	public static final int REQUEST_CODE_SummaryMain = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.serial_main);

		Log.i("SerialMain====", "0");

		Button btStart = (Button) this.findViewById(R.id.SerialMain_btStart);
		// btStart.setText("msg");
		btStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SerialMainActivity.this,
						SerialTimerActivity.class);
				Log.i("SerialMain====", "1");
				EditText et = (EditText) ((View) v.getParent())
						.findViewById(R.id.SerialMain_etTargetName);

				if (et == null) { // mmm:not reachable?
					Log.i("SerialMain====", "et null");
					return;
				}

				i.putExtra("targetName", et.getText().toString());
				// SerialMainActivity.this.startActivity(i);
				startActivityForResult(i, REQUEST_CODE_SerialTimer); // mmm:important,
				// must
				// be
				// zero?
			}
		});

		Button btSummary = (Button) this
				.findViewById(R.id.SerialMain_btSummary);
		btSummary.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(SerialMainActivity.this,
						SummaryMainActivity.class);

				// i.putExtra("targetName", et.getText().toString());
				// SerialMainActivity.this.startActivity(i);
				startActivityForResult(i, REQUEST_CODE_SummaryMain); // mmm:important
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case REQUEST_CODE_SerialTimer:

			long elapsedMillis = data.getExtras().getLong("msg");
			String s = String.valueOf((int) (elapsedMillis / 1000));

			Button btStart = (Button) this
					.findViewById(R.id.SerialMain_btStart);
			btStart.setText(s);
			break;
		case REQUEST_CODE_SummaryMain:
			Log.i("SerialMain<--", "return from SummaryMain");
			break;
		}
	}
}
