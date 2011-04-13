package com.hexun.eniu.lifetracker.activities;

//import android.R;
import com.hexun.eniu.lifetracker.R;

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
		setContentView(R.layout.newtarget);

		LinearLayout m = (LinearLayout) NewTargetActivity.this
				.findViewById(R.id.newtarget);

//		if (m == null) {
//			Log.e("null", "null");
//		} else {
//			Log.e("onCreate", m.toString());
//		}
		// System.out.println(m.getOrientation());

		// 1. name
		final EditText name = new EditText(this);
		// name.setText("new");
		name.setText(getResources().getText(R.string.target_name_default));

		// 2. ok button
		ImageButton btOk = new ImageButton(this);
		btOk.setImageResource(R.drawable.newtarget_add);
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

		m.addView(name);
		m.addView(btOk);

		// other methods:
		// LinearLayout ll = (LinearLayout) a.findViewById(R.id.content2);
		// ViewInflate vi = (ViewInflate)
		// a.getSystemService(Context.INFLATE_SERVICE);
		// View vv = vi.inflate(R.layout.mynewxmllayout, null, null);
		// ll.addView(vv, new
		// LinearLayout.LayoutParams(ll.getLayoutParams().width,
		// ll.getLayoutParams().height));

	}

	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	//	  
	//	  
	// // switch(requestCode) {
	// // case (STATIC_INTEGER_VALUE) : {
	// // if (resultCode == Activity.RESULT_OK) {
	// // int tabIndex = data.getIntExtra(PUBLIC_STATIC_STRING_IDENTIFIER);
	// // // TODO Switch tabs using the index.
	// // }
	// // break;
	// // }
	// // }
	// }

	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		setResult(RESULT_CANCELED, intent); // this will not return!
		finish();
	}

}
