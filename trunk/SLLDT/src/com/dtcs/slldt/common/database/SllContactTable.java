package com.dtcs.slldt.common.database;

import android.database.sqlite.SQLiteDatabase;

public class SllContactTable extends BaseTable {
	public static final String TABLE_NAME = "sll_contact";

	public static final String PHONE_NUM = "phone_num";
	public static final String CONTACT_NAME = "contact_name";
	public static final String DESCRIPTION = "descriptions";

	/** structure table field */
	private static final String[][] TABLE_DEF = { { PHONE_NUM, "TEXT", "NOT NULL" },
			{ CONTACT_NAME, "TEXT", "NOT NULL" }, { DESCRIPTION, "TEXT", "" } };

	/** TABLE_COLUMNS */
	public static final String[] TABLE_COLUMNS = new String[] { PHONE_NUM, CONTACT_NAME, DESCRIPTION };

	/**
	 * @param database
	 */
	public SllContactTable(SQLiteDatabase database) {
		super(database);
	}

	@Override
	public String[][] getTableDef() {
		return TABLE_DEF;
	}

	@Override
	public String[][] getIndexCol() {
		return null;
	}

	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	@Override
	public String[] getPrimaryKeyCol() {
		return new String[] { PHONE_NUM };
	}
}