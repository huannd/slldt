package com.dtcs.slldt.screen.header;

import com.dtcs.slldt.screen.header.IHeaderBar.HeaderType;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HeaderBarFactory {
	protected Fragment mFrag;
	private SimpleHeaderBar mSimpleHeaderBar;
	
	public HeaderBarFactory(Fragment frag){
		mFrag = frag;
	}
	
	public View onCreateHeaderView(LayoutInflater inflater,
			ViewGroup container,HeaderType headerType) {
		switch (headerType) {
		case NONE:{
			return null;
		}
		case DEFAULT:{
			mSimpleHeaderBar = new SimpleHeaderBar(mFrag);
			return mSimpleHeaderBar.onCreateHeaderView(inflater, container);
		}
		default:
			return null;
		}
	}
}
