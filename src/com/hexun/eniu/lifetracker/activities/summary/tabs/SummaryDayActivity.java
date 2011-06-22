package com.hexun.eniu.lifetracker.activities.summary.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryDayActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TextView textview = new TextView(this);
		textview.setText("This is the Day tab");
		setContentView(textview);
	}
}
