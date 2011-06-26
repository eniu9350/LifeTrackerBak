package com.hexun.eniu.lifetracker.activities.serial.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SerialTabsAllActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("===oncreate===", "SerialTabsAllActivity");
		TextView tv = new TextView(this);
		tv.setText("summary content");
		setContentView(tv);
		// setupViews();
	}

}
