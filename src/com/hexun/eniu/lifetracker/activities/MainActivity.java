package com.hexun.eniu.lifetracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hexun.eniu.lifetracker.R;
import com.hexun.eniu.lifetracker.activities.serial.SerialMainActivity;
import com.hexun.eniu.lifetracker.persistence.DbManager;

public class MainActivity extends Activity {
	private SQLiteDatabase db;

	private static String DBNAME = "test";
	private static String TABLENAME = "LIFETRACKER_TARGET";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// prepare database
		db = DbManager.getInstance(this, DBNAME).getDB();
		// db.execSQL("CREATE TABLE mytable (_id INTEGER PRIMARY KEY  AUTOINCREMENT, title TEXT, value REAL);");
		db
				.execSQL("create table if not exists "
						+ TABLENAME
						+ " (_id INTEGER PRIMARY KEY  AUTOINCREMENT, name TEXT, lasting INTEGER, created INTEGER);");

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
		} else if (item.getItemId() == 2) {
			try {

				Cursor cur;

				cur = db.rawQuery("select * from sqlite_master where type='table' AND name='" + TABLENAME
						+ "';", null);

				
				
				if (cur == null)

				{
					
					Log.i("====DB===", "TABLE" + TABLENAME + "NOT EXIST!");
					// our table doesn't exist, so we'll create one or take an
					// action.

				} else {
					cur = db.rawQuery("SELECT * from "+TABLENAME+";",null);
					Log.i("====DB===", "TABLE" + TABLENAME + "EXIST! count="+cur.getCount());
				}

			} catch (SQLiteException e) {
				// our table doesn't exist, so we'll create one or take an
				// action.
			}
		}

		return true;
	}
	
//	@Override
//	protected void onDestroy() {
//	    if (db!=null){
//	        db.close();
//	    }
//	    super.onDestroy();
//	}
}