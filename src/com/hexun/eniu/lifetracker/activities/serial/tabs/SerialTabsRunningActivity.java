package com.hexun.eniu.lifetracker.activities.serial.tabs;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.activities.mem.Cache;
import com.hexun.eniu.lifetracker.activities.serial.SerialTimerActivity;
import com.hexun.eniu.lifetracker.entity.Target;

public class SerialTabsRunningActivity extends Activity {

	public static final int REQUEST_CODE_SerialTimer = 1;
	public static final int REQUEST_CODE_SummaryMain = 2;

	private ViewFlipper flipper;
	private String targetName;

	private Chronometer crono;
	private long lastPause;

	private ToggleButton tbResume;

	private Cache cache;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.serial_tabs_running);

		// get cache
		cache = new Cache(this);

		flipper = new ViewFlipper(this);
		addInputViewToFlipper(flipper);
		addCountDownViewToFlipper(flipper);

		// flipper.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// flipper.showNext();
		// }
		// });

		setContentView(flipper);

		configInputView();
		// configInputView();
		// configCountDownView();

	}

	private void addInputViewToFlipper(ViewFlipper f) {
		LayoutInflater li = LayoutInflater.from(f.getContext());
		li.inflate(R.layout.serial_tabs_running_input, f);
	}

	private void addCountDownViewToFlipper(ViewFlipper f) {
		LayoutInflater li = LayoutInflater.from(f.getContext());
		li.inflate(R.layout.serial_tabs_running_countdown, f);

		// new
		// AlertDialog.Builder(this).setMessage("mymessage").setTitle("title")
		// .setCancelable(true).setNeutralButton(android.R.string.cancel,
		// new DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog,
		// int whichButton) {
		// }
		// }).show();
	}

	private void configInputView() {
		Button btStart = (Button) flipper.findViewById(R.id.SerialMain_btStart);
		// btStart.setText("msg");
		btStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText et = (EditText) ((View) v.getParent())
						.findViewById(R.id.SerialMain_etTargetName);
				targetName = et.getText().toString();

				// clear edittext
				et.setText("");

				// cache&db op
				Target t = cache.getSerialTarget();
				t.setName(et.getText().toString());
				t.setCreated(new Date().getTime());

				// Intent i = new Intent(SerialTabsRunningActivity.this,
				// SerialTimerActivity.class);
				// Log.i("SerialMain====", "1");

				if (et == null) { // mmm:not reachable?
					Log.i("SerialMain====", "et null");
					return;
				}

				// i.putExtra("targetName", et.getText().toString());
				// SerialMainActivity.this.startActivity(i);
				// startActivityForResult(i, REQUEST_CODE_SerialTimer); //
				// mmm:important,
				// must
				// be
				// zero?

				fromInputToCountDown();
				flipper.showNext();

			}
		});

	}

	private void fromCountDownToInput() {
		// ArrayList<ArrayList<Object>> l = dbm.getAllRowsAsArrays();
		// Log.i("====before insert===", "count=" + l.size());

		// dbm.addRow(targetName, "nothing");

		long elapsedMillis;

		// calc lasting time
		tbResume = (ToggleButton) this.findViewById(R.id.SerialTimer_tbResume);
		if (tbResume.getText().toString().equalsIgnoreCase("start")) {
			// paused
			elapsedMillis = lastPause - crono.getBase();
		} else { // running
			elapsedMillis = new Date().getTime() - crono.getBase();
			crono.stop();
		}

		// long elapsedMillis = SystemClock.elapsedRealtime() - crono.getBase();

		// show lasting time
		TextView tvLastTargetStat = (TextView) findViewById(R.id.SerialMain_tvLastTargetStat);
		tvLastTargetStat.setText("last target (" + targetName + "): "
				+ elapsedMillis + "ms");

		// save lasting time
		// db op
		// ContentValues cv = new ContentValues();
		// cv.put("name", targetName);
		// cv.put("lasting", 50);
		// cv.put("created", 12313);
		// db.insert(TABLENAME, null, cv);

		// Intent intent = new Intent();
		// intent.putExtra("msg", elapsedMillis);
		// setResult(Activity.RESULT_OK, intent);
		// finish();
	}

	private void fromInputToCountDown() {
		// cache op
		long now = new Date().getTime();
		cache.addSerialTargetEntry(now);
		Log.i("====TabRunningActivity====fromInputToCountDown,start=", "" + now);

		// setup ui component
		tbResume = (ToggleButton) this.findViewById(R.id.SerialTimer_tbResume);
		tbResume.setChecked(true);
		tbResume.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton cmpbt, boolean bChecked) {
				// TODO Auto-generated method stub
				if (bChecked) { // to start
					Log.i("===tbResume===changed !", "bChecked true");
					Log.i("===bChecked=== text", tbResume.getText().toString());
					long now = new Date().getTime();
					crono.setBase(crono.getBase() + now - lastPause);
					crono.start();

					// cache&db op
					cache.addSerialTargetEntry(new Date().getTime());
				} else { // to pause
					Log.i("===bChecked=== text", tbResume.getText().toString());
					lastPause = new Date().getTime();
					crono.stop();

					// cache op
					Log.i("====TabRunningActivity====stop=", "" + lastPause);
					cache.getSerialTarget().getLastTargetEntry()
							.setStopTime(lastPause);
				}
			}

		});

		Intent intent = getIntent();
		String targetName = intent.getStringExtra("targetName");

		TextView tvTargetName = (TextView) this
				.findViewById(R.id.SerialTimer_tvTargetName);
		tvTargetName.setText(targetName);

		crono = (Chronometer) findViewById(R.id.SerialTimer_crMain);
		// crono.setBase(SystemClock.elapsedRealtime());
		startCrono(crono);

		Button stop = (Button) findViewById(R.id.SerialTimer_btStop);
		stop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// tbResume = (ToggleButton) view.getRootView()
				// .findViewById(R.id.SerialTimer_tbResume);

				// cache&db op
				if (tbResume.getText().toString().equalsIgnoreCase("start")) {// paused
					cache.getSerialTarget().getLastTargetEntry().setbLast(true);
				} else {// running
					cache.setLastTargetEntryStopTime(new Date().getTime(), true);
				}
				long lasting = 0;
				for (int i = 0; i < cache.getSerialTarget().getTes().size(); i++) {
					lasting += cache.getSerialTarget().getTes().get(i)
							.getStopTime()
							- cache.getSerialTarget().getTes().get(i)
									.getStartTime();
				}
				cache.getSerialTarget().setLasting(lasting);
				cache.saveSerialTargetToDB();

				fromCountDownToInput();
				flipper.showPrevious();
			}

		});
	}

	private void startCrono(Chronometer crono) {
		// crono.setBase(SystemClock.elapsedRealtime());
		crono.setBase(new Date().getTime());
		crono.start();
	}

	// @Override
	public void onCreate1(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serial_tabs_running);

		Log.i("SerialMain====", "0");

		Button btStart = (Button) this.findViewById(R.id.SerialMain_btStart);
		// btStart.setText("msg");
		btStart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText et = (EditText) ((View) v.getParent())
						.findViewById(R.id.SerialMain_etTargetName);

				// db op
				// Cache.setSerialTarget(et.getText().toString(), new Date(),
				// 0);

				Intent i = new Intent(SerialTabsRunningActivity.this,
						SerialTimerActivity.class);
				Log.i("SerialMain====", "1");

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
				Intent i = new Intent(SerialTabsRunningActivity.this,
						SerialTabsAllActivity.class);

				// i.putExtra("targetName", et.getText().toString());
				// SerialMainActivity.this.startActivity(i);
				startActivityForResult(i, REQUEST_CODE_SummaryMain); // mmm:important
			}
		});
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	//
	// switch (requestCode) {
	// case REQUEST_CODE_SerialTimer:
	//
	// long elapsedMillis = data.getExtras().getLong("msg");
	// String s = String.valueOf((int) (elapsedMillis / 1000));
	//
	// Button btStart = (Button) this
	// .findViewById(R.id.SerialMain_btStart);
	// btStart.setText(s);
	// break;
	// case REQUEST_CODE_SummaryMain:
	// Log.i("SerialMain<--", "return from SummaryMain");
	// break;
	// }
	// }
	@Override
	public void onDestroy() {
		cache.clear();
	}
}
