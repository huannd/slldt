package com.dtcs.slldt.screen.account;

import java.util.ArrayList;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.DialogCommons.OnDialogClickOkListener;
import com.dtcs.slldt.common.SessionStore;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.StudentAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class AccountScreen extends EContactFragment{

	private ListView mListView;
	private TextView tvPhoneNumber;
	private TextView tvChangePW;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_account, container,false);
		tvPhoneNumber = (TextView)v.findViewById(R.id.tv_phone_number);
		tvChangePW = (TextView) v.findViewById(R.id.tv_change_pw);
		mListView = (ListView) v.findViewById(R.id.lv_switch);
		init();
		return v;
	}
	
	
	private void init(){
		tvPhoneNumber.setText("Số điện thoại : "+UserInfoStoreManager.getInstance().getPhoneNumber());
		final ArrayList<StudentModel> listStudentModel = UserInfoStoreManager.getInstance().getListStudent();
		mListView.setAdapter(new StudentAdapter(getActivity(), listStudentModel));
//		mListView.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				UserInfoStoreManager.getInstance().setCurrentStudent(listStudentModel.get(position));
//				onBackPress();
//			}
//		});
		tvChangePW.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Dialog dialogChangePass = DialogCommons.getDialogChangePassword(getActivity(), new OnDialogClickOkListener() {
					
					@Override
					public void onOkClick(Object obj) {
						final String newPass = ((String)obj);
						showLoading("Đang đổi mật khẩu...");
						SMSGatewayWebservice.changePassword(newPass, new WebserviceTaskListener<ResultModel>() {
							
							@Override
							public void onTaskComplete(ResultModel ob, ResultModel result) {
								if (result != null && result.getErrorCode() == 0) {
									Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
									SessionStore.getInstance().setPassword(newPass);
								}else{
									Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại. Vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
								}
								hideLoading();
							}
						});
					}
				});
				dialogChangePass.show();
			}
		});
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_account_info);
	}
}
