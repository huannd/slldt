package com.dtcs.slldt.common;

import com.google.android.gms.internal.os;

public class DeviceInfoStore {

	private static DeviceInfoStore INSTANCE = null;
	private int screenWidth;
	private int screenHeight;
	
	public static DeviceInfoStore getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new DeviceInfoStore();
		}
		return INSTANCE;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	public String getDeviceInfo(){
//		String version = System.getProperty("os.version"); // OS version
//		String name = System.getProperty("os.name"); // OS version
//		String ret = "OS Name : "+ name +"\nOS Version : "+ version;
//		return ret;
		String ret="Device-infos:";
		ret += "\n OS Version: " + System.getProperty("os.version") + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
		ret += "\n OS API Level: " + android.os.Build.VERSION.SDK_INT;
		ret += "\n Device: " + android.os.Build.DEVICE;
		ret += "\n Model (and Product): " + android.os.Build.MODEL + " ("+ android.os.Build.PRODUCT + ")";
		return ret;
	}
	
	public int getOsType(){
		return 5;
	}
	
}
