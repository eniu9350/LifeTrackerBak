package com.hexun.eniu.lifetracker.activities;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class NewTargetActivity extends Activity {

	private LinearLayout mainContainer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainContainer = new LinearLayout(this);

		// 1. name
		EditText name = new EditText(this);
		name.setText("enter target name here");

		// 2. ok button
		ImageButton btOk = new ImageButton(this);
		btOk.setImageResource(R.drawable.btn_default);

		mainContainer.addView(name);
		mainContainer.addView(btOk);

		setContentView(mainContainer);

		// 2. approximate time (optional)
	}
}
