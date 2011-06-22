package com.hexun.eniu.lifetracker.activities.summary;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.activities.summary.tabs.SummaryCustomActivity;
import com.hexun.eniu.lifetracker.activities.summary.tabs.SummaryDayActivity;
import com.hexun.eniu.lifetracker.activities.summary.tabs.SummaryMonthActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class SummaryMainActivity extends TabActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary_main);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, SummaryDayActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("artists").setIndicator("Day",
				res.getDrawable(R.drawable.ic_tab_summary_main)).setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, SummaryMonthActivity.class);
		spec = tabHost.newTabSpec("albums").setIndicator("Month",
				res.getDrawable(R.drawable.ic_tab_summary_main)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SummaryCustomActivity.class);
		spec = tabHost.newTabSpec("songs").setIndicator("Custom",
				res.getDrawable(R.drawable.ic_tab_summary_main)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}
