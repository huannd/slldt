package com.dtcs.slldt.screen.searching;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtcs.slldt.screen.base.EContactFragment;
import com.edu.ebookcontact.R;

public class SearchingScreen extends EContactFragment{

	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_searching, container,false);
		return v;
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_searching);
	}
}
