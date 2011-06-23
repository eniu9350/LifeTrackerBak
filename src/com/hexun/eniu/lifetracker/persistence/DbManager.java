package com.hexun.eniu.lifetracker.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbManager {

	// a reference to the database used by this application/object
	private SQLiteDatabase db;
	// These constants are specific to the database. They should be
	// changed to suit your needs.
	private final int DB_VERSION = 1;

	private String dbname;
	// the Activity or Application that is creating an object from this class.
	private Context context;
	private static DbManager instance;

	private DbManager(Context context, String dbname) {
		this.context = context;
		this.dbname = dbname;
		// create or open the database
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(this.context);
		this.db = helper.getWritableDatabase();
	}

	public static DbManager getInstance(Context context, String dbname) {
		if (instance == null) {
			instance = new DbManager(context, dbname);
		}
		return instance;
	}

	public SQLiteDatabase getDB() {
		return db;
	}

	/**
	 * This class is designed to check if there is a database that currently
	 * exists for the given program. If the database does not exist, it creates
	 * one. After the class ensures that the database exists, this class will
	 * open the database for use. Most of this functionality will be handled by
	 * the SQLiteOpenHelper parent class. The purpose of extending this class is
	 * to tell the class how to create (or update) the database.
	 * 
	 * @author Randall Mitchell
	 * 
	 */
	private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
		public CustomSQLiteOpenHelper(Context context) {
			super(context, dbname, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// Log.i("DbManager","OnCreate!!!");
			// This string is used to create the database. It should
			// be changed to suit your needs.
			// String newTableQueryString = "create table " + TABLE_NAME + " ("
			// + TABLE_COLUMNNAME_ID
			// + " integer primary key autoincrement not null,"
			// + TABLE_COLUMNNAME_NAME + " text," + TABLE_COLUMNNAME_COMMENTS +
			// " text" + ");";
			// // execute the query string to the database.
			// db.execSQL(newTableQueryString);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// NOTHING TO DO HERE. THIS IS THE ORIGINAL DATABASE VERSION.
			// OTHERWISE, YOU WOULD SPECIFIY HOW TO UPGRADE THE DATABASE.
		}
	}
}
