package com.dtcs.slldt.screen.outbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.screen.SMSAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.edu.ebookcontact.R;

public class OutboxScreen extends EContactFragment{

private ListView mListView;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_outbox, container,false);
		mListView = (ListView) v.findViewById(R.id.lv_outbox);
		init();
		return v;
	}
	
	private void init(){
//		mListView.setAdapter(new SMSAdapter(getActivity(), UserInfoStoreManager.getInstance().getListSMS()));
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_outbox);
	}
}
