package com.dtcs.slldt.screen.header;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dtcs.slldt.screen.main.MainScreen;
import com.dtcs.slldt.screen.outbox.OutboxScreen;
import com.edu.ebookcontact.R;

public class SimpleHeaderBar {

	private Fragment mFrag;
	public SimpleHeaderBar(Fragment frag){
		mFrag = frag;
	}
	
	public View onCreateHeaderView(LayoutInflater inflater,
			ViewGroup container) {
		View root = inflater.inflate(R.layout.header_bar_simple, container);
		root.findViewById(R.id.btnBack).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mFrag instanceof IHeaderBar) {
					((IHeaderBar)mFrag).onBackPress();
				}
			}
		});
		root.findViewById(R.id.main_btn_switch).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mFrag instanceof IHeaderBar) {
					((IHeaderBar)mFrag).sync();
				}
			}
		});
		if (!(mFrag instanceof MainScreen)
				&& !(mFrag instanceof OutboxScreen)) {
			root.findViewById(R.id.main_btn_switch).setVisibility(View.GONE);
		}
		if (mFrag instanceof IHeaderBar) {
			TextView tvTitle = (TextView)root.findViewById(R.id.title);
			tvTitle.setText(((IHeaderBar)mFrag).getTitle());
		}
		return null;
	}
}
