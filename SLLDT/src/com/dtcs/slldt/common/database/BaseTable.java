package com.dtcs.slldt.common.database;

import android.database.sqlite.SQLiteDatabase;

public abstract class BaseTable {
	private SQLiteDatabase db;

	public BaseTable(SQLiteDatabase database) {
		db = database;
	}

	final public void create() {
		createTable();
		createIndex();
	}

	final public void drop() {
		dropTable();
	}

	/**
	 * @param db
	 */
	final public void createTable() {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE " + getTableName() + " (");
		String[][] tableDef = getTableDef();
		for (int i = 0; i < tableDef.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(tableDef[i][0] + " " + tableDef[i][1] + " " + tableDef[i][2]);
		}
		StringBuffer sbPkey = new StringBuffer();
		String[] pk = getPrimaryKeyCol();
		if (pk != null) {
			for (int i = 0; i < pk.length; i++) {
				if (i > 0) {
					sbPkey.append(",");
				}
				sbPkey.append(pk[i]);
			}
			sb.append(", PRIMARY KEY (" + sbPkey.toString() + ")");
		}
		sb.append(");");
		db.execSQL(sb.toString());
	}

	/**
	 * @param db
	 */
	final public void createIndex() {
		String[][] indexCol = getIndexCol();
		if (null != indexCol && indexCol.length > 0) {
			for (int i = 0; i < indexCol.length; i++) {
				createIndex(i, indexCol[i]);
			}
		}
	}

	/**
	 * @param db
	 * @param n
	 * @param indexCol
	 */
	final private void createIndex(int n, String[] indexCol) {
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE INDEX idx" + n + "_" + getTableName() + " ON " + getTableName() + " (");
		for (int i = 0; i < indexCol.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(indexCol[i]);
		}
		sb.append(");");
		db.execSQL(sb.toString());
	}

	/**
	 * @param db
	 */
	final public void dropTable() {
		db.execSQL("DROP TABLE IF EXISTS " + getTableName());
	}

	/**
	 * @return
	 */
	abstract public String[][] getTableDef();

	/**
	 * @return
	 */
	abstract public String[][] getIndexCol();

	/**
	 * @return
	 */
	abstract public String[] getPrimaryKeyCol();

	/**
	 * @return
	 */
	abstract public String getTableName();
}