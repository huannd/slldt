package com.dtcs.slldt.screen.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtcs.slldt.screen.base.EContactFragment;
import com.edu.ebookcontact.R;

public class AboutSceen extends EContactFragment {

	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_about, container, false);
		return v;
	}
	
	@Override
	public String getTitle() {
		return "Giới Thiệu";
	}

}
