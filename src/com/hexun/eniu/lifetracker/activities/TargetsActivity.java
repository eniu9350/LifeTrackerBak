package com.hexun.eniu.lifetracker.activities;

import java.util.Arrays;

import android.R;
import android.app.Activity;
import android.os.Bundle;
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
			
			ImageButton btn = new ImageButton(this);
			btn.setImageResource(R.drawable.btn_default);
			
			
			ll.addView(tv);
			ll.addView(btn);

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

}