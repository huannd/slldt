package com.dtcs.slldt.common;

import com.dtcs.slldt.EBookContactApp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class SessionStore {
	
	private static final String FILE_NAME 			= "userinfostore";
	public static final String KEY_USER_ID = "userId";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_REGISTRATION_ID = "registrationId";
	
	private static SessionStore INSTANCE = null;
	
	public static SessionStore getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new SessionStore();
		}
		return INSTANCE;
	}

	public SharedPreferences getSharedPreferences()
	{
		return EBookContactApp.getAppContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	}
	
	public String getUserId() {
		return getSharedPreferences().getString(KEY_USER_ID, null);
	}

	public void setUserId(String userId) {
		Editor editor = getSharedPreferences().edit();
		editor.putString(KEY_USER_ID, userId);
		editor.commit();
	}

	public String getPassword() {
		return getSharedPreferences().getString(KEY_PASSWORD, null);
	}

	public void setPassword(String password) {
		Editor editor = getSharedPreferences().edit();
		editor.putString(KEY_PASSWORD, password);
		editor.commit();
	}
	
	public void setRegistrationId(String registrationId){
		Editor editor = getSharedPreferences().edit();
		editor.putString(KEY_REGISTRATION_ID, registrationId);
		editor.commit();
	}
	
	public String getRegistrationId(){
		return getSharedPreferences().getString(KEY_REGISTRATION_ID, null);
	}
}
