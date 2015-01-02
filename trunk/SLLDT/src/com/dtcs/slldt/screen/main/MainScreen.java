package com.dtcs.slldt.screen.main;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.StudentAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.searching.SearchingScreen;
import com.dtcs.slldt.template.TemplateDefault;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class MainScreen extends EContactFragment implements OnClickListener{

	private View btnInbox;
	private View btnSearching;
	private View btnAccount;
	private View btnAbout;
	private View btnSettings;
	private View btnOutbox;
	private TextView tvCurrentId;
	private StudentModel mCurrentStudent;
	private ProgressDialog mProgressDialog;
	private Dialog idPickerDialog;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_main, container,false);
		btnInbox = v.findViewById(R.id.btn_inbox);
		btnSearching = v.findViewById(R.id.btn_searching);
		btnAccount = v.findViewById(R.id.btn_account);
		btnAbout = v.findViewById(R.id.btn_about);
		btnSettings = v.findViewById(R.id.btn_settings);
		btnOutbox = v.findViewById(R.id.btn_outbox);
		tvCurrentId = (TextView) v.findViewById(R.id.tv_current_id);
		mProgressDialog = new ProgressDialog(getActivity());
		init();
		switchData();
		return v;
	}
	
	private void init(){
		initPickerDialog();
		btnInbox.setOnClickListener(this);
		btnSearching.setOnClickListener(this);
		btnAccount.setOnClickListener(this);
		btnAbout.setOnClickListener(this);
		btnSettings.setOnClickListener(this);
		btnOutbox.setOnClickListener(this);
	}
	
	private void switchData(){
		mProgressDialog.show();
		mCurrentStudent = UserInfoStoreManager.getInstance().getCurrentStudent();
		SMSGatewayWebservice.getListSmsBySudentId(mCurrentStudent.Ma_Hs, new WebserviceTaskListener<ArrayList<SMSModel>>() {
			
			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				mProgressDialog.dismiss();
				if (ob != null && result !=null) {
					UserInfoStoreManager.getInstance().setListSMS(ob);
				}
				String info = "Tên : " + mCurrentStudent.Hoten_HocSinh + " | Lớp : " + mCurrentStudent.Ten_Lop + " | Trường : " + mCurrentStudent.Ten_Truong;
				tvCurrentId.setText(info);
				tvCurrentId.setSelected(true);
				tvCurrentId.setEllipsize(TextUtils.TruncateAt.MARQUEE);
				tvCurrentId.setTypeface(TemplateDefault.typeface01);
			}
		});
	}
	
	@Override
	public String getTitle() {
		return getResources().getString(R.string.app_nameH);
	}
	
	private void initPickerDialog(){
		idPickerDialog = new Dialog(getActivity());
		idPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		idPickerDialog.setContentView(R.layout.screen_account);
		idPickerDialog.setCanceledOnTouchOutside(false);
		TextView tvPhoneNumber = (TextView)idPickerDialog.findViewById(R.id.tv_phone_number);
		TextView tvChangePW = (TextView) idPickerDialog.findViewById(R.id.tv_change_pw);
		ListView listStudent = (ListView) idPickerDialog.findViewById(R.id.lv_switch);
		Button btnExit = (Button) idPickerDialog.findViewById(R.id.btn_exit);
		tvPhoneNumber.setText("Số điện thoại : "+UserInfoStoreManager.getInstance().getPhoneNumber());
		final ArrayList<StudentModel> listStudentModel = UserInfoStoreManager.getInstance().getListStudent();
		listStudent.setAdapter(new StudentAdapter(getActivity(), listStudentModel));
		listStudent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				idPickerDialog.dismiss();
				UserInfoStoreManager.getInstance().setCurrentStudent(listStudentModel.get(position));
				switchData();
			}
		});
		
		tvChangePW.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				idPickerDialog.dismiss();
			}
		});
		
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				idPickerDialog.dismiss();
			}
		});
	}
	
	private void openInbox(){
		switchContent(new InboxScreen(), true);
	}
	
	private void openSearching(){
		switchContent(new SearchingScreen(), true);
	}
	
	private void openAccount(){
		if (!idPickerDialog.isShowing()) {
			idPickerDialog.show();
		}
	}
	
	private void openAbout(){
		
	}
	
	private void openSettings(){
		
	}
	
	private void openOutbox(){
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_inbox:
			openInbox();
			break;
		case R.id.btn_searching:
			openSearching();
			break;
		case R.id.btn_account:
			openAccount();
			break;
		case R.id.btn_about:
			openAbout();
			break;
		case R.id.btn_settings:
			openSettings();
			break;
		case R.id.btn_outbox:
			openOutbox();
			break;
		default:
			break;
		}
	}
}
