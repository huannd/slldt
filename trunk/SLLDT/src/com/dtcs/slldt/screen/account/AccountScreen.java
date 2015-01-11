package com.dtcs.slldt.screen.account;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.StudentAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
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
	}

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_account_info);
	}
}
