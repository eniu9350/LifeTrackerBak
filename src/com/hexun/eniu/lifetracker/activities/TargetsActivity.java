package com.hexun.eniu.lifetracker.activities;

import java.util.Arrays;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

import com.hexun.eniu.lifetracker.entity.Target;

public class TargetsActivity extends Activity {

	public static final int MAX_TARGET_COUNT = 20;
	private int count = 2; // mmm:temp
	private Target targets[];

	private LinearLayout mainContainer;
	private LinearLayout targetContainers[];

	{
		targets = new Target[MAX_TARGET_COUNT];
		Target target0 = new Target("t1");
		Target target1 = new Target("t2");
		targets[0] = target0;
		targets[1] = target1;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0 // Group
				, 1 // item id
				, 0 // order
				, "add target"); // title
		menu.add(0, 2, 1, "...");
		menu.add(0, 3, 2, "...");
		// It is important to return true to see the menu

		return true;
	}

	public void initTargetsLayout() {
		targetContainers = new LinearLayout[count];
		mainContainer = new LinearLayout(this);
		mainContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mainContainer.setOrientation(LinearLayout.VERTICAL);

		int i;
		for (i = 0; i < count; i++) {

			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));

			TextView tv = new TextView(this);
			tv.setText(targets[i].getName());

			// button1: start,resume/pause
			ImageButton btToggle = new ImageButton(this);
			btToggle.setImageResource(R.drawable.btn_default);

			// button2: stop
			ImageButton btStop = new ImageButton(this);
			btStop.setImageResource(R.drawable.btn_default);

			ll.addView(tv);
			ll.addView(btToggle);
			ll.addView(btStop);

			targetContainers[i] = ll;
			mainContainer.addView(ll);

		}
	}

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		initTargetsLayout();
		setContentView(mainContainer);
		// TextView tv = new TextView(this);
		// tv.setText("www");
		// setContentView(tv);
		// setContentView(R.layout.main);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		String targetName = data.getStringExtra("nameOfNewTarget");
		
		{
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));

			TextView tv = new TextView(this);
			tv.setText(targetName);

			// button1: start,resume/pause
			ImageButton btToggle = new ImageButton(this);
			btToggle.setImageResource(R.drawable.btn_default);

			// button2: stop
			ImageButton btStop = new ImageButton(this);
			btStop.setImageResource(R.drawable.btn_default);

			ll.addView(tv);
			ll.addView(btToggle);
			ll.addView(btStop);

			targetContainers[count-1] = ll;
			count++;
			mainContainer.addView(ll);
		}
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		if (item.getItemId() == 1) {
			Intent intent = new Intent(this, NewTargetActivity.class);
			startActivity(intent);

		}

		return true;
	}
}