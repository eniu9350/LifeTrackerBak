package com.hexun.eniu.lifetracker.activities;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.TableLayout.LayoutParams;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.entity.Target;

public class TargetsActivity extends Activity {

	public static final int MAX_TARGET_COUNT = 20;
	private int count = 0; // mmm:temp
	private final Target targets[];

	private LinearLayout mainContainer;
	private LinearLayout targetContainers[];

	{
		targets = new Target[MAX_TARGET_COUNT];
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
		targetContainers = new LinearLayout[MAX_TARGET_COUNT];
		mainContainer = new LinearLayout(this);
		mainContainer.setLayoutParams(new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		mainContainer.setOrientation(LinearLayout.VERTICAL);

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
		Log.e("TargetsActivity", "onActivityResult 0");

		if (resultCode == RESULT_CANCELED) {

		}

		else if (resultCode == RESULT_OK) {
			String targetName = data.getStringExtra("nameOfNewTarget");
			targets[count] = new Target(targetName);

			Log.e("TargetsActivity", "onActivityResult 1, targetName="
					+ targetName);
			LinearLayout ll = new LinearLayout(this);
			ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
					LayoutParams.WRAP_CONTENT));

			TextView tv = new TextView(this);
			tv.setText(targetName);
			tv.setTextSize(15.0f);
			tv.setTextColor(Color.WHITE);
			tv.setWidth(100);

			Log.e("TargetsActivity", "onActivityResult 2");

			// button1: start,resume/pause
			ToggleButton btToggle = new ToggleButton(this);
			btToggle.setText("Ready");
			btToggle.setTextOn("Running");
			btToggle.setTextOff("Paused");
			btToggle.setMinimumWidth(120);
			btToggle.setWidth(120);
			// btToggle.setImageResource(R.drawable.btn_default);
			btToggle.setTag(count);
			btToggle.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					int id = (Integer) v.getTag();
					Log.e("TargetsActivity", "toggle on click,id=" + id);
					if (targets[id].getState() == Target.STATE_CREATED
							|| targets[id].getState() == Target.STATE_PAUSED) {
						targets[id].getStartTime().add(new Date().getTime());
						Log.e("TargetsActivity", "new start time added: "
								+ new Date().getTime());
						targets[id].setState(Target.STATE_RUNNING);
					} else if (targets[id].getState() == Target.STATE_RUNNING) {
						targets[id].getEndTime().add(new Date().getTime());
						targets[id].setState(Target.STATE_PAUSED);
						Log.e("TargetsActivity", "new pause time added: "
								+ new Date().getTime());
					}
					Log.e("TargetsActivity", "toggle on click end");
				}
			});

			Log.e("TargetsActivity", "onActivityResult 3");

			// button2: stop
			ImageButton btStop = new ImageButton(this);
			btStop.setTag(count);
			// btStop.setImageResource(R.drawable.btn_default);
			btStop.setImageResource(R.drawable.targets_entry_stop);
			btStop.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					/*
					 * if (targets[count].getState() == Target.STATE_CREATED) {
					 * 
					 * }
					 */

					// int id = (Integer) v.getTag();
					// targets[id].getEndTime().add(new Date().getTime());
					// targets[id].setState(Target.STATE_ENDED);
					//
					// Log.e("TargetsActivity", "new end time added: "
					// + new Date().toString());
				}
			});

			Log.e("TargetsActivity", "onActivityResult 4");

			// button3: delete
			ImageButton btDelete = new ImageButton(this);
			btDelete.setTag(count);
			// btStop.setImageResource(R.drawable.btn_default);
			btDelete.setImageResource(R.drawable.targets_entry_delete);
			btDelete.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

				}
			});

			// button4: show detail (debug)
			ImageButton btDebug = new ImageButton(this);
			btDebug.setTag(count);
			btDebug.setImageResource(R.drawable.targets_entry_info);
			btDebug.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					String s;
					int id = (Integer) v.getTag();
					int i;
					long sum = 0;
					if (targets[id].getStartTime().size() > targets[id]
							.getEndTime().size()) {
						for (i = 0; i < targets[id].getEndTime().size(); i++) {
							sum += targets[id].getEndTime().get(i)
									- targets[id].getStartTime().get(i);
						}
						sum += new Date().getTime()
								- targets[id].getStartTime().get(i);

					} else {
						for (i = 0; i < targets[id].getEndTime().size(); i++) {
							sum += targets[id].getEndTime().get(i)
									- targets[id].getStartTime().get(i);
						}
					}

					String time = String.valueOf(sum / 3600000) + "h"
							+ String.valueOf((sum % 3600000) / 60000) + "m"
							+ String.valueOf((sum % 60000) / 1000) + "s";
					new AlertDialog.Builder(TargetsActivity.this).setMessage(
							time).setTitle("total time").setCancelable(true)
							.setNeutralButton(android.R.string.ok,
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int whichButton) {
										}
									}).show();

				}
			});

			ll.addView(tv);
			ll.addView(btToggle);
			ll.addView(btStop);
			ll.addView(btDelete);
			ll.addView(btDebug);

			targetContainers[count] = ll;
			mainContainer.addView(ll);
			count++;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		super.onOptionsItemSelected(item);

		Log.e("TEMP", "www");

		if (item.getItemId() == 1) {
			Log.e("TEMP", "getItemId 1");
			Intent intent = new Intent(this, NewTargetActivity.class);
			startActivityForResult(intent, 1);

		} else if (item.getItemId() == 2) {
			String s = "";
			for (int i = 0; i < targets[0].getStartTime().size(); i++) {
				s += targets[0].getStartTime().get(i).toString() + ", ";
			}

			new AlertDialog.Builder(this).setMessage(s).setTitle("temp title")
					.setCancelable(true).setNeutralButton(
							android.R.string.cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).show();
		}

		return true;
	}
}