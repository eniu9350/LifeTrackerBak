package com.hexun.eniu.lifetracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.activities.serial.SerialMainActivity;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.i("mytag", "My simple message");

		setContentView(R.layout.main);

		Button btModeS = (Button) findViewById(R.id.main_btModeS);
		btModeS.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this,
						SerialMainActivity.class);
				MainActivity.this.startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0 // Group
				, 1 // item id
				, 0 // order
				, "target list"); // title
		menu.add(0, 2, 1, "...");
		menu.add(0, 3, 2, "...");
		// It is important to return true to see the menu

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		if (item.getItemId() == 1) {
			// Intent it = new
			// Intent("com.hexun.eniu.LifeTracker.intent.action.TargetsPageActivity");
			// item.setIntent(it);

			Intent intent = new Intent(this, TargetsActivity.class);
			startActivity(intent);
		}

		return true;
	}

}