package com.dtcs.slldt.screen.inbox;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.SMSAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class InboxScreen extends EContactFragment {

	private ListView mListView;
	private ArrayList<SMSModel> mDatas;
	private SMSAdapter mAdapter;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_inbox, container,false);
		mListView = (ListView) v.findViewById(R.id.lv_inbox);
		init();
		getInboxDatas();
		return v;
	}
	
	private void init(){
		mDatas = new ArrayList<SMSModel>();
		mAdapter = new SMSAdapter(getActivity(), mDatas);
		mListView.setAdapter(mAdapter);
	}

	private void getInboxDatas(){
		StudentModel model = UserInfoStoreManager.getInstance().getCurrentStudent();
		if (model == null) return;
		showLoading();
		SMSGatewayWebservice.getListSmsBySudentId(model.Ma_Hs, new WebserviceTaskListener<ArrayList<SMSModel>>() {
			
			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob != null && result != null) {
					mDatas.clear();
					mDatas.addAll(ob);
					mAdapter.notifyDataSetChanged();
				} else {
					Toast.makeText(getActivity(), "Lấy dữ liệu thất bại. Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
				}
				hideLoading();
			}
		});
	}
	
	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_inbox);
	}
}
