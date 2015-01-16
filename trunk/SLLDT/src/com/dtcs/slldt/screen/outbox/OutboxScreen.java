package com.dtcs.slldt.screen.outbox;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
			if (obj instanceof SMSModel) {
				SMSModel model = ((SMSModel)obj);
				showLoading("Đang gửi tin...");
				SMSGatewayWebservice.sendSMS(model.SDT_Nhan, model.Noi_Dung, new WebserviceTaskListener<ResultModel>() {
					
					@Override
					public void onTaskComplete(ResultModel ob, ResultModel result) {
						hideLoading();
						if (result!=null && result.getErrorCode() == 0) {
							Toast.makeText(getActivity(), "Gửi tin nhắn thành công.", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(getActivity(), "Gửi tin thất bại.Vui lòng gửi lại sau", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		}
	};

	private void init(){
		mDatas = new ArrayList<SMSModel>();
		mAdapter = new OutBoxAdapter(getActivity(), mDatas);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				Dialog dialogSMSContent = DialogCommons.getDialogShowSMS(getActivity(), mDatas.get(position).Noi_Dung, null);
				dialogSMSContent.show();
			}
		});
		sync();
	}

	@Override
	public void sync() {
		showLoading();
		SMSGatewayWebservice.getListSmsSended(new WebserviceTaskListener<ArrayList<SMSModel>>() {
			
			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob!=null) {
					mDatas.clear();
					mDatas.addAll(ob);
					mAdapter.notifyDataSetChanged();
				}
				hideLoading();
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