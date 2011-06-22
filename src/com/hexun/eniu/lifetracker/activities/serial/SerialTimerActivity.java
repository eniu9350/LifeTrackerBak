package com.hexun.eniu.lifetracker.activities.serial;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.persistence.DbManager;

public class SerialTimerActivity extends Activity {
	private DbManager dbm;
	private Chronometer crono;

	private String targetName ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		if(dbm == null)	{ 
			dbm = new DbManager(this);
//		}

		setContentView(R.layout.serial_timer);

//		new AlertDialog.Builder(this).setMessage("mymessage").setTitle("title")
//				.setCancelable(true).setNeutralButton(android.R.string.cancel,
//						new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog,
//									int whichButton) {
//							}
//						}).show();

		Intent intent = getIntent();
		targetName = intent.getStringExtra("targetName");

		TextView tvTargetName = (TextView) this
				.findViewById(R.id.SerialTimer_tvTargetName);
		tvTargetName.setText(targetName);

		this.crono = (Chronometer) findViewById(R.id.SerialTimer_crMain);
		crono.setBase(SystemClock.elapsedRealtime());
		startCrono();

		Button stop = (Button) findViewById(R.id.SerialTimer_btStop);
		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				
				ArrayList<ArrayList<Object>> l = dbm.getAllRowsAsArrays();
				Log.i("====before insert===", "count="+l.size());
				
				dbm.addRow(targetName, "nothing");
				
				
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
