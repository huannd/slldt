package com.dtcs.slldt.common.database;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	/** DATABASE_NAME */
	private static final String DATABASE_NAME = "e_slldt.db";
	/** VERSION */
	private static final int VERSION = 1;
	private static MyDatabaseHelper mSingleton = null;

	public MyDatabaseHelper(Context context) {
		super(context, Environment.getExternalStorageDirectory() + File.separator + DATABASE_NAME, null, VERSION);
	}

	/**
	 * create an instance of SeDatabaseHelper for application.
	 * 
	 * @param context
	 *            Context
	 * @return SeDatabaseHelper
	 */
	public static synchronized MyDatabaseHelper getInstance(final Context context) {
		if (mSingleton == null) {
			mSingleton = new MyDatabaseHelper(context);
		}
		return mSingleton;
	}

	/**
	 * Create simple table todos _id - key todo - todo text
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Execute create table and index SQL
		createTableAndIndex(db);
	}

	/**
	 * Recreates table
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		// drop table
		dropTable(db);
		// Recreate table
		onCreate(db);
	}

	/**
	 * create tables
	 * 
	 * @param db
	 *            SQLiteDatabase
	 */
	private void createTableAndIndex(SQLiteDatabase db) {
		SllContactTable contact = new SllContactTable(db);
		contact.create();

	}

	/**
	 * drop tables
	 * 
	 * @param db
	 *            SQLiteDatabase
	 */
	private void dropTable(final SQLiteDatabase db) {
		SllContactTable contact = new SllContactTable(db);
		contact.drop();
	}
}