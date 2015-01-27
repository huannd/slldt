package com.dtcs.slldt.common.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;

public abstract class AbstractDatabaseManager implements SQLiteTransactionListener {
	protected SQLiteDatabase mDb;
	private SQLiteOpenHelper mOpenHelper;
	private final ThreadLocal<Boolean> mApplyingBatch = new ThreadLocal<Boolean>();

	public AbstractDatabaseManager() {
		mOpenHelper = null;
	}

	public AbstractDatabaseManager(final Context context) {
		mOpenHelper = getDatabaseHelper(context);
	}

	public boolean onCreate(final Context context) {
		mOpenHelper = getDatabaseHelper(context);
		return true;
	}

	protected abstract SQLiteOpenHelper getDatabaseHelper(Context context);

	protected abstract long insertInTransaction(String table, ContentValues values);

	protected abstract int updateInTransaction(String table, ContentValues values, String selection,
			String[] selectionArgs);

	protected abstract int deleteInTransaction(String table, String selection, String[] selectionArgs);

	protected final SQLiteOpenHelper getDatabaseHelper() {
		return mOpenHelper;
	}

	private boolean applyingBatch() {
		return mApplyingBatch != null && mApplyingBatch.get() != null && mApplyingBatch.get();
	}

	public final long insert(final String table, final ContentValues values) {
		long result = 0L;
		final boolean applyingBatch = applyingBatch();
		if (!applyingBatch) {
			mDb = mOpenHelper.getWritableDatabase();
			mDb.beginTransactionWithListener(this);
			try {
				result = insertInTransaction(table, values);
				mDb.setTransactionSuccessful();
			} finally {
				mDb.endTransaction();
			}
		} else {
			result = insertInTransaction(table, values);
		}
		return result;
	}

	public final int bulkInsert(final String table, final ContentValues[] values) {
		int result = 0;
		final int numValues = values.length;
		mDb = mOpenHelper.getWritableDatabase();
		mDb.beginTransactionWithListener(this);
		try {
			for (int i = 0; i < numValues; i++) {
				insertInTransaction(table, values[i]);
				mDb.yieldIfContendedSafely();
				result++;
			}
			mDb.setTransactionSuccessful();
		} finally {
			mDb.endTransaction();
		}
		return result;
	}

	public final int bulkInsert(final String table, final List<ContentValues> values) {
		int result = 0;
		final int numValues = values.size();
		mDb = mOpenHelper.getWritableDatabase();
		mDb.beginTransactionWithListener(this);
		try {
			for (int i = 0; i < numValues; i++) {
				insertInTransaction(table, values.get(i));
				mDb.yieldIfContendedSafely();
				result++;
			}
			mDb.setTransactionSuccessful();
		} finally {
			mDb.endTransaction();
		}
		return result;
	}

	public final int update(final String table, final ContentValues values, final String selection,
			final String[] selectionArgs) {
		int count = 0;
		final boolean applyingBatch = applyingBatch();
		if (!applyingBatch) {
			mDb = mOpenHelper.getWritableDatabase();
			mDb.beginTransactionWithListener(this);
			try {
				count = updateInTransaction(table, values, selection, selectionArgs);
				mDb.setTransactionSuccessful();
			} finally {
				mDb.endTransaction();
			}
		} else {
			count = updateInTransaction(table, values, selection, selectionArgs);
		}
		return count;
	}

	public final int updateNotCommit(final String table, final ContentValues values, final String selection,
			final String[] selectionArgs) throws SQLException {
		int result = 0;
		result = mDb.update(table, values, selection, selectionArgs);
		return result;
	}

	public final int bulkUpdate(final String table, final ContentValues[] values, final String selection,
			final ArrayList<String[]> selectionArgs) {
		int count = 0;
		int result = 0;
		final int valuesCount = values.length;
		final int selectionArgsCount = selectionArgs.size();
		if (valuesCount != selectionArgsCount) {
			return count;
		}
		final boolean applyingBatch = applyingBatch();
		if (!applyingBatch) {
			mDb = mOpenHelper.getWritableDatabase();
			mDb.beginTransactionWithListener(this);
			try {
				for (int i = 0; i < selectionArgsCount; i++) {
					count = updateInTransaction(table, values[i], selection, selectionArgs.get(i));
					result += count;
					if (count < 1) {
					}
				}
				mDb.setTransactionSuccessful();
			} finally {
				mDb.endTransaction();
			}
		} else {
			for (int i = 0; i < selectionArgsCount; i++) {
				count = updateInTransaction(table, values[i], selection, selectionArgs.get(i));
				result += count;
				if (count < 1) {
				}
			}
		}
		return result;
	}

	public final int delete(final String table, final String selection, final String[] selectionArgs) {
		int count = 0;
		final boolean applyingBatch = applyingBatch();
		if (!applyingBatch) {
			mDb = mOpenHelper.getWritableDatabase();
			mDb.beginTransactionWithListener(this);
			try {
				count = deleteInTransaction(table, selection, selectionArgs);
				mDb.setTransactionSuccessful();
			} finally {
				mDb.endTransaction();
			}
		} else {
			count = deleteInTransaction(table, selection, selectionArgs);
		}
		return count;
	}

	@Override
	public final void onBegin() {
		onBeginTransaction();
	}

	@Override
	public final void onCommit() {
		beforeTransactionCommit();
	}

	@Override
	public final void onRollback() {
	}

	protected final void onBeginTransaction() {
	}

	protected final void beforeTransactionCommit() {
	}

	public final void upgrade() {
		final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		mOpenHelper.onUpgrade(db, db.getVersion(), db.getVersion());
		mDb = db;
		return;
	}

	public final void beginTransaction() {
		mDb = mOpenHelper.getWritableDatabase();
		mDb.beginTransactionWithListener(this);
		mApplyingBatch.set(true);
		return;
	}

	public final void setTransactionSuccessful() {
		mApplyingBatch.set(false);
		mDb = mOpenHelper.getWritableDatabase();
		mDb.setTransactionSuccessful();
		return;
	}

	public final void endTransaction() {
		mApplyingBatch.set(false);
		mDb = mOpenHelper.getWritableDatabase();
		mDb.endTransaction();
		return;
	}
}