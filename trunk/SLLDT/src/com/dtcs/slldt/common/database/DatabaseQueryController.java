package com.dtcs.slldt.common.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

import com.dtcs.slldt.model.ContactModel;

public class DatabaseQueryController {
	private DatabaseQueryController() {
	}

	private static DatabaseQueryController controller;

	public static DatabaseQueryController getInstance() {
		if (controller == null) {
			controller = new DatabaseQueryController();
		}
		return controller;
	}

	public long insertNewContact(MyDatabaseManager db, ContactModel model) {
		ContentValues ctv = new ContentValues();
		ctv.put(SllContactTable.CONTACT_NAME, model.getName());
		ctv.put(SllContactTable.PHONE_NUM, model.getPhoneNum());
		ctv.put(SllContactTable.DESCRIPTION, model.getDescriptions());
		return db.insert(SllContactTable.TABLE_NAME, ctv);
	}

	public List<ContactModel> getContactList(MyDatabaseManager db) {
		List<ContactModel> lst = new ArrayList<ContactModel>();
		String query = "SELECT * FROM " + SllContactTable.TABLE_NAME + " ORDER BY " + SllContactTable.CONTACT_NAME
				+ " DESC";
		Cursor cursor = db.rawQuery(query, null);
		try {
			if (cursor.moveToFirst()) {
				do {
					ContactModel ct = new ContactModel();
					ct.setName(cursor.getString(cursor.getColumnIndexOrThrow(SllContactTable.CONTACT_NAME)));
					ct.setPhoneNum(cursor.getString(cursor.getColumnIndexOrThrow(SllContactTable.PHONE_NUM)));
					ct.setDescriptions(cursor.getString(cursor.getColumnIndexOrThrow(SllContactTable.DESCRIPTION)));
					lst.add(ct);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
		} finally {
			cursor.close();
		}
		return lst;
	}
}
