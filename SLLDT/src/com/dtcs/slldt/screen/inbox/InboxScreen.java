package com.dtcs.slldt.screen.inbox;

import java.util.ArrayList;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.gcmservice.GCMManagerMessage;
import com.dtcs.slldt.gcmservice.OnGCMNewMessageListener;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.SMSAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.main.MainScreen;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class InboxScreen extends EContactFragment {

	private ListView mListView;
	private ArrayList<SMSModel> mDatas;
	private SMSAdapter mAdapter;
	private long currentStudentId = -1;

	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_inbox, container, false);
		mListView = (ListView) v.findViewById(R.id.lv_inbox);
		init();
		// currentStudentId =
		// UserInfoStoreManager.getInstance().getCurrentStudentId();
		// getInboxDatas();
		GCMManagerMessage.getInstance().addDelegateListener(mGcmNewMessageListener);
		return v;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		GCMManagerMessage.getInstance().removeDelegateListener(mGcmNewMessageListener);
	}

	@Override
	public void onResume() {
		super.onResume();
		long studentId = UserInfoStoreManager.getInstance().getCurrentStudentId();
		if (currentStudentId != studentId) {
			currentStudentId = studentId;
			getInboxDatas();
		}
	}
	
	public void showInbox(){
		long studentId = UserInfoStoreManager.getInstance().getCurrentStudentId();
		if (currentStudentId != studentId) {
			currentStudentId = studentId;
			getInboxDatas();
		}
	}
	
	private void init() {
		mDatas = new ArrayList<SMSModel>();
		mAdapter = new SMSAdapter(getActivity(), mDatas);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Dialog dialogSMSContent = DialogCommons.getDialogShowSMS(getActivity(), mDatas.get(position).Noi_Dung, null);
				dialogSMSContent.show();
			}
		});
	}

	private void getInboxDatas() {
		StudentModel model = UserInfoStoreManager.getInstance().getCurrentStudent();
		if (model == null)
			return;
		showLoading();
		SMSGatewayWebservice.getListSmsBySudentId(model.Ma_Hs, new WebserviceTaskListener<ArrayList<SMSModel>>() {

			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob != null && result != null) {
					mDatas.clear();
					mDatas.addAll(ob);
					mAdapter.notifyDataSetChanged();
//					Toast.makeText(getActivity(), "get data completed", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getActivity(), "Lấy dữ liệu thất bại. Vui lòng thử lại sau", Toast.LENGTH_SHORT)
							.show();
				}
				hideLoading();
			}
		});
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_inbox);
	}

	@Override
	public void onBackPress() {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		if (fm.getBackStackEntryCount() > 1) {
			super.onBackPress();
		} else {
			MainScreen main = new MainScreen();
			main.setShowListStudent(false);
			switchContent(main, false);
		}
	}

	private OnGCMNewMessageListener mGcmNewMessageListener = new OnGCMNewMessageListener() {

		@Override
		public void onNewMessage(long id) {
			//TODO; get update
			Log.i("INBOX", "has new message");
		}
	};
}
