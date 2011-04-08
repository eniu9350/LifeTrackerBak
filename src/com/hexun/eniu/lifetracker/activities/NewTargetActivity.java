package com.hexun.eniu.lifetracker.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

public class NewTargetActivity extends Activity {

	private LinearLayout mainContainer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 1. name
		mainContainer = new LinearLayout(this);

		EditText name = new EditText(this);
		name.setText("enter target name here");

		mainContainer.addView(name);

		// 2. approximate time (optional)
	}
}
