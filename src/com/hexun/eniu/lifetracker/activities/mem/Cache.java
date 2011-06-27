package com.hexun.eniu.lifetracker.activities.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hexun.eniu.lifetracker.entity.Target;
import com.hexun.eniu.lifetracker.entity.TargetEntry;
import com.hexun.eniu.lifetracker.persistence.DbManager;

public class Cache {

	// private static int STATE_FULL = 1;
	// private static int STATE_EMPTY = 2;

	// DB constants
	private static String DBNAME = "test";

	private static String TABLENAME_TARGET = "LIFETRACKER_TARGET";
	private static String TABLECOLUMNNAME_TARGET_ID = "_id";
	private static String TABLECOLUMNNAME_TARGET_TID = "tid";
	private static String TABLECOLUMNNAME_TARGET_STARTTIME = "starttime";
	private static String TABLECOLUMNNAME_TARGET_STOPTIME = "stoptime";
	private static String TABLECOLUMNNAME_TARGET_BFIRST = "bfirst";
	private static String TABLECOLUMNNAME_TARGET_BLAST = "blast";

	private static String TABLENAME_TARGETENTRY = "LIFETRACKER_TARGETENTRY";

	// DB var
	private SQLiteDatabase db;

	private Target serialTarget;
	// private static int stSerialTarget = STATE_EMPTY;

	private List<Target> concurrentTargetList;
	private Context ctx;

	// private static int stconcurrentTargetList = STATE_EMPTY;

	public Cache(Context ctx) {
		this.ctx = ctx;
		concurrentTargetList = new ArrayList<Target>();

		db = DbManager.getInstance(ctx, DBNAME).getDB();

		// create table if not exist
		String newTableQueryString = "create table if not exists "
				+ TABLENAME_TARGETENTRY + " (" + TABLECOLUMNNAME_TARGET_ID
				+ " integer primary key autoincrement not null,"
				+ TABLECOLUMNNAME_TARGET_TID + " integer,"
				+ TABLECOLUMNNAME_TARGET_STARTTIME + " integer,"
				+ TABLECOLUMNNAME_TARGET_STOPTIME + " integer,"
				+ TABLECOLUMNNAME_TARGET_BFIRST + " integer,"
				+ TABLECOLUMNNAME_TARGET_BLAST + " integer" + ");";
		db.execSQL(newTableQueryString);
	}

	// public void init() {
	// }

	public void clear() {
		db.close();
	}

	// ---------------------------- condition serial -------
	public Target getSerialTarget() {
		if (serialTarget == null) {
			serialTarget = new Target("_NoName");
		}
		return serialTarget;
	}

	public void setSerialTarget(String name, long created, long lasting) {
		if (serialTarget == null) {
			serialTarget = new Target("_NoName");
		} else {
			resetSerialTarget();
			serialTarget.setName(name);
			serialTarget.setCreated(created);
			serialTarget.setLasting(lasting);
			// stSerialTarget = STATE_FULL;

		}
	}

	public void addSerialTargetEntry(long startTime) {
		TargetEntry te = new TargetEntry();
		te.setStartTime(startTime);
		if (serialTarget == null) {
			serialTarget = new Target("_NoName");
			te.setbFirst(true);
		} else {
			if (serialTarget.getTes().size() == 0) {
				te.setbFirst(true);
			}
		}
		serialTarget.addTargetEntry(te);
	}

	public void setLastTargetEntryStopTime(long stopTime) {
		setLastTargetEntryStopTime(stopTime, false);
	}

	public void setLastTargetEntryStopTime(long stopTime, boolean bLast) {
		Log.i("====Cache====stop=", "" + stopTime);

		serialTarget.getLastTargetEntry().setStopTime(stopTime);
		serialTarget.getLastTargetEntry().setbLast(bLast);
	}

	// public static void clearSerialTarget() {
	// stSerialTarget = STATE_EMPTY;
	// }
	public void resetSerialTarget() {
		serialTarget.setName("_NoName");
		serialTarget.getTes().clear();
	}

	/**
	 * save target and its entries
	 */
	public void saveSerialTargetToDB() {
		// save target (mmm:id ok?)
		ContentValues cv;
		cv = new ContentValues();
		cv.put("name", serialTarget.getName());
		cv.put("lasting", serialTarget.getLasting());
		cv.put("created", serialTarget.getCreated());
		long rowid = db.insert(TABLENAME_TARGET, null, cv);
		Cursor c = db.query(true, TABLENAME_TARGET, new String[] { "rowid",
				"_id" }, "rowid=" + rowid, null, null, null, null, null);
		c.moveToNext();
		int colid = c.getColumnIndex("_id");
		int tid = c.getInt(colid);

		Log.i("======Cache===== ", "colid of _id=" + colid);
		Log.i("======Cache===== ", "tid=" + tid);
		Log.i("======Cache===== ", "rowid=" + rowid);

		// save tes
		List<TargetEntry> tes = serialTarget.getTes();
		for (int i = 0; i < tes.size(); i++) {
			cv = new ContentValues();
			cv.put("tid", tid);// mmm
			cv.put("starttime", tes.get(i).getStartTime());
			cv.put("stoptime", tes.get(i).getStopTime());
			cv.put("bfirst", tes.get(i).isbFirst());
			cv.put("blast", tes.get(i).isbLast());
			db.insert(TABLENAME_TARGETENTRY, null, cv);

		}
	}

	// ---------------------------- condition concurrent -------
	public void addConcurrentTarget(Target t) {
		concurrentTargetList.add(t);
	}

	public void addConcurrentTargetEntry(int tuiid, TargetEntry t) {
		concurrentTargetList.get(tuiid);
	}
}
