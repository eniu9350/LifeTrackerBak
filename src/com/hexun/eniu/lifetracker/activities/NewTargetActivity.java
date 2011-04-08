package com.hexun.eniu.lifetracker.activities;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class NewTargetActivity extends Activity {

	private LinearLayout mainContainer;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainContainer = new LinearLayout(this);

		// 1. name
		final EditText name = new EditText(this);
		name.setText("enter target name here");

		// 2. ok button
		ImageButton btOk = new ImageButton(this);
		btOk.setImageResource(R.drawable.btn_default);
		btOk.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Intent intent = getIntent();
				// intent.setAction("XXXX");
				// setResult(RESULT_OK, intent);
				// finish();

				// http://mobileorchard.com/android-app-development-using-intents-to-pass-data-and-return-results-between-activities/
				Intent intent = new Intent();
				Log.e("NewTargetActivity, name = ", name.getText().toString());
				String s = name.getText().toString();
				// intent.putExtra("nameOfNewTarget", name.getText()); //why
				// wrong???
				intent.putExtra("nameOfNewTarget", s);
				setResult(RESULT_OK, intent); // this will not return!
				finish();

			}
		});

		mainContainer.addView(name);
		mainContainer.addView(btOk);

		setContentView(mainContainer);

		// 3. approximate time (optional)
	}
}
