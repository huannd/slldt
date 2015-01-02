package com.dtcs.slldt.common;

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
	
	
}
