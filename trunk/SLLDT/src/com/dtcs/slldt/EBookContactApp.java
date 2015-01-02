package com.dtcs.slldt;

import android.app.Application;
import android.content.Context;

public class EBookContactApp extends Application{

	/** The App Context. */
	private static Context mContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
	}
	
	public static Context getAppContext(){
		return mContext;
	}
	
}
