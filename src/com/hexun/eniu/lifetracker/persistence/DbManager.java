package com.hexun.eniu.lifetracker.persistence;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbManager {
	// the Activity or Application that is creating an object from this class.
	Context context;

	// a reference to the database used by this application/object
	private SQLiteDatabase db;

	// These constants are specific to the database. They should be
	// changed to suit your needs.
	private final String DB_NAME = "test";
	private final int DB_VERSION = 1;

	// These constants are specific to the database table. They should be
	// changed to suit your needs.
	private final String TABLE_NAME = "TABLE_LIFETRACKER_TARGET";
	private final String TABLE_COLUMNNAME_ID = "id";
	private final String TABLE_COLUMNNAME_NAME = "name";
	private final String TABLE_COLUMNNAME_COMMENTS = "comments";

	public DbManager(Context context) {
		this.context = context;

		// create or open the database
		CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
		this.db = helper.getWritableDatabase();
	}

	/**********************************************************************
	 * ADDING A ROW TO THE DATABASE TABLE
	 * 
	 * This is an example of how to add a row to a database table using this
	 * class. You should edit this method to suit your needs.
	 * 
	 * the key is automatically assigned by the database
	 * 
	 * @param rowStringOne
	 *            the value for the row's first column
	 * @param rowStringTwo
	 *            the value for the row's second column
	 */
	public void addRow(String name, String comments) {
		// this is a key value pair holder used by android's SQLite functions
		ContentValues values = new ContentValues();
		values.put(TABLE_COLUMNNAME_NAME, name);
		values.put(TABLE_COLUMNNAME_COMMENTS, comments);

		// ask the database object to insert the new data
		try {
			db.insert(TABLE_NAME, null, values);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
	}

	/**********************************************************************
	 * DELETING A ROW FROM THE DATABASE TABLE
	 * 
	 * This is an example of how to delete a row from a database table using
	 * this class. In most cases, this method probably does not need to be
	 * rewritten.
	 * 
	 * @param rowID
	 *            the SQLite database identifier for the row to delete.
	 */
	public void deleteRow(long rowID) {
		// ask the database manager to delete the row of given id
		try {
			db.delete(TABLE_NAME, TABLE_COLUMNNAME_ID + "=" + rowID, null);
		} catch (Exception e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}
	}

	/**********************************************************************
	 * UPDATING A ROW IN THE DATABASE TABLE
	 * 
	 * This is an example of how to update a row in the database table using
	 * this class. You should edit this method to suit your needs.
	 * 
	 * @param rowID
	 *            the SQLite database identifier for the row to update.
	 * @param rowStringOne
	 *            the new value for the row's first column
	 * @param rowStringTwo
	 *            the new value for the row's second column
	 */
	public void updateRow(long rowID, String rowStringOne, String rowStringTwo) {
		// this is a key value pair holder used by android's SQLite functions
		ContentValues values = new ContentValues();
		values.put(TABLE_COLUMNNAME_NAME, rowStringOne);
		values.put(TABLE_COLUMNNAME_COMMENTS, rowStringTwo);

		// ask the database object to update the database row of given rowID
		try {
			db.update(TABLE_NAME, values, TABLE_COLUMNNAME_ID + "=" + rowID, null);
		} catch (Exception e) {
			Log.e("DB Error", e.toString());
			e.printStackTrace();
		}
	}

	/**********************************************************************
	 * RETRIEVING A ROW FROM THE DATABASE TABLE
	 * 
	 * This is an example of how to retrieve a row from a database table using
	 * this class. You should edit this method to suit your needs.
	 * 
	 * @param rowID
	 *            the id of the row to retrieve
	 * @return an array containing the data from the row
	 */
	public ArrayList<Object> getRowAsArray(long rowID) {
		// create an array list to store data from the database row.
		// I would recommend creating a JavaBean compliant object
		// to store this data instead. That way you can ensure
		// data types are correct.
		ArrayList<Object> rowArray = new ArrayList<Object>();
		Cursor cursor;

		try {
			// this is a database call that creates a "cursor" object.
			// the cursor object store the information collected from the
			// database and is used to iterate through the data.
			cursor = db.query(TABLE_NAME, new String[] { TABLE_COLUMNNAME_ID,
					TABLE_COLUMNNAME_NAME, TABLE_COLUMNNAME_COMMENTS }, TABLE_COLUMNNAME_ID + "=" + rowID,
					null, null, null, null, null);

			// move the pointer to position zero in the cursor.
			cursor.moveToFirst();

			// if there is data available after the cursor's pointer, add
			// it to the ArrayList that will be returned by the method.
			if (!cursor.isAfterLast()) {
				do {
					rowArray.add(cursor.getLong(0));
					rowArray.add(cursor.getString(1));
					rowArray.add(cursor.getString(2));
				} while (cursor.moveToNext());
			}

			// let java know that you are through with the cursor.
			cursor.close();
		} catch (SQLException e) {
			Log.e("DB ERROR", e.toString());
			e.printStackTrace();
		}

		// return the ArrayList containing the given row from the database.
		return rowArray;
	}

	/**********************************************************************
	 * RETRIEVING ALL ROWS FROM THE DATABASE TABLE
	 * 
	 * This is an example of how to retrieve all data from a database table
	 * using this class. You should edit this method to suit your needs.
	 * 
	 * the key is automatically assigned by the database
	 */

	public ArrayList<ArrayList<Object>> getAllRowsAsArrays() {
		// create an ArrayList that will hold all of the data collected from
		// the database.
		ArrayList<ArrayList<Object>> dataArrays = new ArrayList<ArrayList<Object>>();

		// this is a database call that creates a "cursor" object.
		// the cursor object store the information collected from the
		// database and is used to iterate through the data.
		Cursor cursor;

		try {
			// ask the database object to create the cursor.
			cursor = db.query(TABLE_NAME, new String[] { TABLE_COLUMNNAME_ID,
					TABLE_COLUMNNAME_NAME, TABLE_COLUMNNAME_COMMENTS }, null, null, null, null,
					null);

			// move the cursor's pointer to position zero.
			cursor.moveToFirst();

			// if there is data after the current cursor position, add it
			// to the ArrayList.
			if (!cursor.isAfterLast()) {
				do {
					ArrayList<Object> dataList = new ArrayList<Object>();

					dataList.add(cursor.getLong(0));
					dataList.add(cursor.getString(1));
					dataList.add(cursor.getString(2));

					dataArrays.add(dataList);
				}
				// move the cursor's pointer up one position.
				while (cursor.moveToNext());
			}
		} catch (SQLException e) {
			Log.e("DB Error", e.toString());
			e.printStackTrace();
		}

		// return the ArrayList that holds the data collected from
		// the database.
		return dataArrays;
	}

	/**********************************************************************
	 * THIS IS THE BEGINNING OF THE INTERNAL SQLiteOpenHelper SUBCLASS.
	 * 
	 * I MADE THIS CLASS INTERNAL SO I CAN COPY A SINGLE FILE TO NEW APPS AND
	 * MODIFYING IT - ACHIEVING DATABASE FUNCTIONALITY. ALSO, THIS WAY I DO NOT
	 * HAVE TO SHARE CONSTANTS BETWEEN TWO FILES AND CAN INSTEAD MAKE THEM
	 * PRIVATE AND/OR NON-STATIC. HOWEVER, I THINK THE INDUSTRY STANDARD IS TO
	 * KEEP THIS CLASS IN A SEPARATE FILE.
	 *********************************************************************/

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
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i("DbManager","OnCreate!!!");
			// This string is used to create the database. It should
			// be changed to suit your needs.
			String newTableQueryString = "create table " + TABLE_NAME + " ("
					+ TABLE_COLUMNNAME_ID
					+ " integer primary key autoincrement not null,"
					+ TABLE_COLUMNNAME_NAME + " text," + TABLE_COLUMNNAME_COMMENTS + " text" + ");";
			// execute the query string to the database.
			db.execSQL(newTableQueryString);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// NOTHING TO DO HERE. THIS IS THE ORIGINAL DATABASE VERSION.
			// OTHERWISE, YOU WOULD SPECIFIY HOW TO UPGRADE THE DATABASE.
		}
	}
}
