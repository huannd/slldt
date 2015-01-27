package com.dtcs.slldt.common.database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class MyDatabaseManager extends AbstractDatabaseManager {
	/** デバッグ用データ. */
	private Context mContext;
	/** SQL output file optional. */
	private static final boolean SQL_OUTPUT_FILE = false;

	public MyDatabaseManager() {
		super();
	}

	private static MyDatabaseManager myDatabaseManager;

	public static synchronized MyDatabaseManager getInstance(Context context) {
		if (myDatabaseManager == null) {
			myDatabaseManager = new MyDatabaseManager(context);
		}
		return myDatabaseManager;
	}

	private MyDatabaseManager(final Context context) {
		super(context);
		final MyDatabaseHelper dbHelper = (MyDatabaseHelper) getDatabaseHelper();
		mDb = dbHelper.getWritableDatabase();
		if (SQL_OUTPUT_FILE) {
			mContext = context;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MyDatabaseHelper getDatabaseHelper(final Context context) {
		return MyDatabaseHelper.getInstance(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreate(final Context context) {
		super.onCreate(context);
		final MyDatabaseHelper dbHelper = (MyDatabaseHelper) getDatabaseHelper();
		mDb = dbHelper.getWritableDatabase();
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int deleteInTransaction(final String table, final String whereClause, final String[] whereArgs) {
		final SQLiteDatabase db = mDb;
		if (db == null) { // fail safe
			throw new IllegalArgumentException("There is no database.");
		}
		final int result = db.delete(table, whereClause, whereArgs);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected long insertInTransaction(final String table, final ContentValues values) {
		final SQLiteDatabase db = mDb;
		if (db == null) { // fail safe
			throw new IllegalArgumentException("There is no database.");
		}
		if ((values == null) || (values.valueSet().size() == 0)) {
			throw new IllegalArgumentException("There is no data to be set.");
		}
		final long result = db.insert(table, "", values);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int updateInTransaction(final String table, final ContentValues values, final String whereClause,
			final String[] whereArgs) {
		final SQLiteDatabase db = mDb;
		if (db == null) { // fail safe
			throw new IllegalArgumentException("There is no database.");
		}
		if (values == null) { // fail safe
			throw new IllegalArgumentException("There is no data to be updated.");
		}
		if (values.containsKey("_id")) { // fail safe
			throw new IllegalArgumentException("Updated item is invalid.");
		}
		final int result = db.update(table, values, whereClause, whereArgs);
		if (result < 0) { // テーブルへの更新に失敗した場合
		}
		return result;
	}

	public Cursor query(final String table, final String[] projection, final String selection,
			final String[] selectionArgs, final String groupBy, final String having, final String sortOrder,
			final String limit) {
		final SQLiteDatabase db = mDb;
		if (db == null) { // fail safe
			throw new IllegalArgumentException("There is no database.");
		}
		final Cursor result = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder, limit);
		if (result == null) {
		}
		return result;
	}

	public Cursor query(final String table, final String[] projection, final String selection,
			final String[] selectionArgs, final String groupBy, final String having, final String sortOrder) {
		return query(table, projection, selection, selectionArgs, groupBy, having, sortOrder, null);
	}

	public Cursor query(final String table, final String[] projection, final String selection,
			final String[] selectionArgs, final String sortOrder) {
		return query(table, projection, selection, selectionArgs, null, null, sortOrder, null);
	}

	public Cursor rawQuery(final String sql, final String[] selectionArgs) {
		if (SQL_OUTPUT_FILE) {
			final Context context = mContext;
			outputSql(context, sql, selectionArgs);
		}
		final SQLiteDatabase db = mDb;
		if (db == null) { // fail safe
			throw new IllegalArgumentException("There is no database.");
		}
		final Cursor result = db.rawQuery(sql, selectionArgs);
		if (SQL_OUTPUT_FILE) {
			final Context context = mContext;
			outputResultCursor(context, result);
		}
		return result;
	}

	private static final void outputSql(final Context context, final String sql, final String[] selectionArgs) {
		if (SQL_OUTPUT_FILE) {
			try {
				final Pattern pt = Pattern.compile("[?]");
				Matcher mc = pt.matcher(sql);
				int j = 0;
				String result = sql;
				while (mc.find()) {
					final StringBuilder sb = new StringBuilder();
					sb.append("'");
					sb.append(selectionArgs[j++]);
					sb.append("'");
					result = mc.replaceFirst(sb.toString());
					mc = pt.matcher(result);
				}
				output(context, ".sql", result + "\n■\n");
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 取得結果のCursorをファイル出力する(デバッグ用).
	 * 
	 * @param context
	 *            アプリケーションコンテキスト
	 * @param cursor
	 *            カーソル
	 */
	private static final void outputResultCursor(final Context context, final Cursor cursor) {
		if (SQL_OUTPUT_FILE) {
			try {
				if (cursor == null) {
					return;
				}
				if (cursor.isClosed()) {
					return;
				}
				if (!cursor.moveToFirst()) {
					return;
				}
				final String[] names = cursor.getColumnNames();
				if (names == null) {
					return;
				}
				final StringBuilder sb = new StringBuilder();
				do {
					for (final String name : names) {
						if (TextUtils.isEmpty(name)) {
							continue;
						}
						final int index = cursor.getColumnIndex(name);
						if (index < 0) {
							continue;
						}
						final String value = cursor.getString(index);
						sb.append(value);
						sb.append(",");
					}
					sb.append("\n");
				} while (cursor.moveToNext());
				output(context, ".csv", sb.toString());
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static final void output(final Context context, final String extension, final String value)
			throws IOException {
		if (SQL_OUTPUT_FILE) {
			if (context == null) {
				return;
			}
			final long currentTimeMillis = System.currentTimeMillis();
			final java.util.Date date = new java.util.Date(currentTimeMillis);
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			final String now = simpleDateFormat.format(date);
			final String filepath = context.getExternalFilesDir(null).getPath() + "/log/" + now + extension;
			final File directory = new File(filepath).getParentFile();
			if (!directory.exists()) {
				directory.mkdirs();
			}
			BufferedWriter bw = null;
			try {
				// BufferedWriterを利用してテキスト書き込み
				// ※FileOutputStreamの第二引数をTrueにすると
				// 追加書き込みし、Falseにすると上書き作成します。
				bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filepath, true), "UTF-8"));
				bw.write(value);
				// BufferedWriterをクローズするのを忘れないようにする。
				bw.close();
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (final FileNotFoundException e) {
				e.printStackTrace();
			} catch (final IOException e) {
				e.printStackTrace();
			} finally {
				if (bw != null) {
					bw.close();
				}
			}
		}
	}

	/**
	 * @return the mContext
	 */
	public Context getContext() {
		return mContext;
	}

	/**
	 * @param mContext
	 *            the mContext to set
	 */
	public void setContext(Context mContext) {
		this.mContext = mContext;
	}
}