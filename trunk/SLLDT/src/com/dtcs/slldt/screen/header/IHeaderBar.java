package com.dtcs.slldt.screen.header;

public interface IHeaderBar {
	
	public enum HeaderType{
		NONE,
		DEFAULT
	}
	
	public void onBackPress();
	public String getTitle();
}
