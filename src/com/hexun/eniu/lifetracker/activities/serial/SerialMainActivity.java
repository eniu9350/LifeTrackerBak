package com.hexun.eniu.lifetracker.activities.serial;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.activities.serial.tabs.SerialTabsAllActivity;
import com.hexun.eniu.lifetracker.activities.serial.tabs.SerialTabsRunningActivity;

public class SerialMainActivity extends TabActivity {

	private TabHost mTabHost;

	private final SerialMainActivity _this = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serial_main);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);

		TabSpec spec1 = createRunningTabSpec();
		TabSpec spec2 = createAllTabSpec();

		mTabHost.addTab(spec1);
		mTabHost.addTab(spec2);

		// ViewGroup singleChild = (ViewGroup) mTabHost.getChildAt(0);
		// singleChild = (ViewGroup) singleChild.getChildAt(0);
		// Log.i("===tabhost==c count", "" + singleChild.getChildCount());
		// for (int i = 0; i < singleChild.getChildCount(); i++) {
		// Log
		// .i("===tabhost==c" + i + " type", ""
		// + singleChild.getChildAt(i));
		// Log.i("===tabhost==c" + i + " id", ""
		// + singleChild.getChildAt(i).getId());
		// Log.i("===tabhost==c" + i + " tag", ""
		// + singleChild.getChildAt(i).getTag());
		//
		// }

	}

	private TabSpec createRunningTabSpec() {
		LinearLayout tabview = new LinearLayout(this);
		tabview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		// tabview.setTag("tv1tag");
		// tabview1.setId(0x213131 + 11);
		// tabview.setPadding(10, 10, 10, 10);
		tabview.setGravity(Gravity.CENTER);
		tabview.setOrientation(LinearLayout.VERTICAL);

		TextView tv = new TextView(tabview.getContext());
		// tv1.setId(0x213132 + 12);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setText("===running===   ");
		tabview.addView(tv);

		Intent intent = new Intent().setClass(this,
				SerialTabsRunningActivity.class);

		TabSpec spec = mTabHost.newTabSpec("tab1").setIndicator(tabview)
				.setContent(intent);

		return spec;
	}

	private TabSpec createAllTabSpec() {
		LinearLayout tabview = new LinearLayout(this);
		tabview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tabview.setTag("tv2tag");
		// tabview.setId(0x213131 + 21);
		// tabview.setPadding(10, 10, 10, 10);
		tabview.setGravity(Gravity.CENTER);
		tabview.setOrientation(LinearLayout.VERTICAL);

		TextView tv = new TextView(tabview.getContext());
		// tv2.setId(0x213132 + 22);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setText("   ===all===");
		tabview.addView(tv);
		Intent intent = new Intent()
				.setClass(this, SerialTabsAllActivity.class);
		TabSpec spec = mTabHost.newTabSpec("tab2").setIndicator(tabview)
				.setContent(intent);
		return spec;
	}

	private void setupTab(final TextView c, final String tag, int idoff) {
		// View tabview = createTabView(mTabHost.getContext(), tag);

		LinearLayout tabview = new LinearLayout(this);
		tabview.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
		tabview.setId(0x213131 + idoff);
		// tabview.setPadding(10, 10, 10, 10);
		tabview.setGravity(Gravity.CENTER);
		tabview.setOrientation(LinearLayout.VERTICAL);

		TextView tv = new TextView(tabview.getContext());
		tv.setId(0x213132 + idoff);
		tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setText("tt" + tag);
		tabview.addView(tv);
		// tv.setTextSize((float) 15.0);

		// LayoutInflater.from(tabview.getContext()).inflate(
		// R.layout.serial_main_old, null);

		TabSpec setContent = mTabHost.newTabSpec(tag).setIndicator(tabview)
				.setContent(new TabContentFactory() {
					public View createTabContent(String tag) {
						c.setText(tag);
						return c;
					}
				});
		mTabHost.addTab(setContent);
	}

	// private static View createTabView(final Context context, final String
	// text) {
	// View view = LayoutInflater.from(context).inflate(
	// R.layout.serial_main_tab, null);
	// TextView tv = (TextView) view.findViewById(R.id.tabsText);
	// tv.setText(text);
	// return view;
	// }
}
