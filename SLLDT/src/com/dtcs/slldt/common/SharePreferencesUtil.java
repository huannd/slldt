package com.dtcs.slldt.common;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.dtcs.slldt.EBookContactApp;

/**
 * @author TND Oct 24, 2013
 */
public abstract class SharePreferencesUtil {

	private static final String LOG_TAG = SharePreferencesUtil.class.getSimpleName();
	private static SharedPreferences mPreferences;
	private static String PREFERENCES_NAME = "sll_share_preferen";

	private static SharedPreferences getPreferences() {
		if (mPreferences == null)
			mPreferences = EBookContactApp.getAppContext()
					.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
		return mPreferences;
	}

	private SharePreferencesUtil() {
	}

	private static Editor saveValueToSharedPreferences() {
		return getPreferences().edit();
	}

	public static void putString(final String pKeyName, final String pValue) {
		saveValueToSharedPreferences().putString(pKeyName, pValue).commit();
	}

	public static void putInt(final String pKeyName, final int pValue) {
		saveValueToSharedPreferences().putInt(pKeyName, pValue).commit();
	}

	public void putLong(final String pKeyName, final long pValue) {
		saveValueToSharedPreferences().putLong(pKeyName, pValue).commit();
	}

	public static void putBoolean(final String pKeyName, final boolean pValue) {
		saveValueToSharedPreferences().putBoolean(pKeyName, pValue).commit();
	}

	public static String getString(final String pKeyName, final String pDefaultValue) {
		return getPreferences().getString(pKeyName, pDefaultValue);
	}

	public static int getInt(final String pKeyName, final int pDefaultValue) {
		return getPreferences().getInt(pKeyName, pDefaultValue);
	}

	public static long getLong(final String pKeyName, final long pDefaultValue) {
		return getPreferences().getLong(pKeyName, pDefaultValue);
	}

	public static boolean getBoolean(final String pKeyName, final boolean pDefaultValue) {
		return getPreferences().getBoolean(pKeyName, pDefaultValue);
	}
}
