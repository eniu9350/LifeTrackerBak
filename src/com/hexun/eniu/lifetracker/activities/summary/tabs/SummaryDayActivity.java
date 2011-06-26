package com.hexun.eniu.lifetracker.activities.summary.tabs;

import java.util.ArrayList;
import java.util.List;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.entity.Target;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class SummaryDayActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TextView textview = new TextView(this);
		// textview.setText("This is the Day tab");
		setContentView(R.layout.summary_main);

		ListView list = (ListView) findViewById(R.id.SummaryDay_lv);

		//get t list from db
		List<Target> targetList = new ArrayList<Target>();

		SummaryDayAdapter adapter = new SummaryDayAdapter(this, targetList);

		list.setAdapter(adapter);
	}
}
