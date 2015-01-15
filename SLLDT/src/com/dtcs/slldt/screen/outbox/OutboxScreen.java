package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.DialogCommons.OnDialogClickOkListener;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.screen.SMSAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class OutboxScreen extends EContactFragment {

	private ListView mListView;
	private Dialog dCreateSMS;
	private OutBoxAdapter mAdapter;
	private ArrayList<SMSModel> mDatas;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_outbox, container, false);
		v.findViewById(R.id.btnOutSMS).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (dCreateSMS == null) {
					dCreateSMS = DialogCommons.getDialogSent(getActivity(), 
							mDialogClickOkListener);
				}
				dCreateSMS.show();
				
//				DialogCommons.getDialogShowSMS(getActivity(), "test content", mDialogClickOkListener).show();
			}
		});
		mListView = (ListView) v.findViewById(R.id.lv_outbox);
		init();
		return v;
	}

	private OnDialogClickOkListener mDialogClickOkListener = new OnDialogClickOkListener() {

		@Override
		public void onOkClick(Object obj) {
			Toast.makeText(getActivity(), "abc:  "+ String.valueOf(obj), 1).show();
		}
	};

	private void init(){
//		mListView.setAdapter(new SMSAdapter(getActivity(), UserInfoStoreManager.getInstance().getListSMS()));
		SMSGatewayWebservice.getListSmsSended(new WebserviceTaskListener<ArrayList<SMSModel>>() {
			
			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob!=null) {
					mDatas = ob;
					mAdapter = new OutBoxAdapter(getActivity(), mDatas);
					mListView.setAdapter(mAdapter);
				}
			}
		});
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_outbox);
	}
	
	class OutBoxAdapter extends SMSAdapter{

		public OutBoxAdapter(Activity activity, ArrayList<SMSModel> datas) {
			super(activity, datas);
		}
		
		@Override
		protected String getPhoneAtPosition(int pos) {
			SMSModel model = getItem(pos);
			if (model.SDT_Nhan != null && !model.SDT_Nhan.trim().equals("")) {
				return "To: "+model.SDT_Nhan;
			}
			return "";
		}
	}
}
