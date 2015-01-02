package com.dtcs.slldt.screen.login;

import java.util.ArrayList;

import android.app.Dialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dtcs.slldt.common.SessionStore;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.StudentAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.main.MainScreen;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class LoginScreen extends EContactFragment implements OnClickListener{

	private View btnLogin;
	private EditText edtPhone;
	private EditText edtPassword;
	private TextView tvRegister;
	private TextView tvForgotPassword;
	private TextView tvError;
	private ProgressBar prgLogin;
	private ArrayList<StudentModel> mListStudent;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_login, container,false);
		btnLogin = v.findViewById(R.id.btn_login);
		edtPhone = (EditText) v.findViewById(R.id.edt_phone);
		edtPassword = (EditText) v.findViewById(R.id.edt_pass);
		tvRegister = (TextView) v.findViewById(R.id.tvRegister);
		tvForgotPassword = (TextView) v.findViewById(R.id.tvForgotPassword);
		tvError = (TextView) v.findViewById(R.id.tvError);
		prgLogin = (ProgressBar) v.findViewById(R.id.prg_login);
		init();
		return v;
	}
	
	private void init(){
		tvRegister.setText(Html.fromHtml("<u>Đăng ký</u>"));
		tvForgotPassword.setText(Html.fromHtml("<u>Quên mật khẩu</u>"));
		btnLogin.setOnClickListener(this);
		tvRegister.setOnClickListener(this);
		tvForgotPassword.setOnClickListener(this);
		String userIdStore = SessionStore.getInstance().getUserId();
		String password = SessionStore.getInstance().getPassword();
		if (userIdStore!=null && password!=null) {
			edtPhone.setText(userIdStore);
			edtPassword.setText(password);
			startLogin();
		}
	}
	
	public void setEditable(boolean isEdit){
		edtPhone.setEnabled(isEdit);
		edtPassword.setEnabled(isEdit);
		tvRegister.setEnabled(isEdit);
		tvForgotPassword.setEnabled(isEdit);
	}
	
	private void setLoginState(boolean isLogin){
		if (isLogin) {
			setEditable(false);
			tvError.setText("");
			prgLogin.setVisibility(View.VISIBLE);
			btnLogin.setVisibility(View.INVISIBLE);
		}else {
			setEditable(true);
			prgLogin.setVisibility(View.INVISIBLE);
			btnLogin.setVisibility(View.VISIBLE);
		}
	}
	
	private boolean checkInvalidate(String str){
		if (str == null || str.equals("") || str.length() < 2) {
			return false;
		}
		return true;
	}
	
	private void startLogin(){
		final String phone = edtPhone.getText().toString();
		final String pass = edtPassword.getText().toString();
		if (!checkInvalidate(phone) || !checkInvalidate(pass)) {
			tvError.setText(getResources().getString(R.string.err_wrong_phone_or_pass));
			return;
		}
		setLoginState(true);
		SMSGatewayWebservice.login(phone, pass, new WebserviceTaskListener<ResultModel>() {
			
			@Override
			public void onTaskComplete(ResultModel ob, ResultModel result) {
				if (result == null) {
					tvError.setText(getResources().getString(R.string.err_network_connection));
					setLoginState(false);
				}else {
					if (result.getErrorCode() == 0) {
						SessionStore.getInstance().setUserId(phone);
						SessionStore.getInstance().setPassword(pass);
						UserInfoStoreManager.getInstance().setPhoneNumber(phone);
						getListStudent();
//						switchContent(new MainScreen(), false);
					}else {
						tvError.setText(getResources().getString(R.string.err_not_exist_account));
						setLoginState(false);
					}
				}
			}
		});
	}
	
	private void getListStudent(){
		SMSGatewayWebservice.getListStudentByPhoneNumber(new WebserviceTaskListener<ArrayList<StudentModel>>() {
			
			@Override
			public void onTaskComplete(ArrayList<StudentModel> ob, ResultModel result) {
				if (ob!=null && result!=null) {
					mListStudent = ob;
					showListStudentDialog();
				}else {
					tvError.setText(getResources().getString(R.string.err_network_connection));
					setLoginState(false);
				}
			}
		});
	}
	
	private void showListStudentDialog(){
		final Dialog idPickerDialog = new Dialog(getActivity());
		idPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		idPickerDialog.setContentView(R.layout.dialog_idpicker);
		idPickerDialog.setCanceledOnTouchOutside(false);
		TextView tvTitle = (TextView)idPickerDialog.findViewById(R.id.tv_title);
		ListView listStudent = (ListView) idPickerDialog.findViewById(R.id.lv_switch);
		Button btnExit = (Button) idPickerDialog.findViewById(R.id.btn_exit);
		String title = getResources().getString(R.string.lbl_select_slldt);
		tvTitle.setText(title);
		listStudent.setAdapter(new StudentAdapter(getActivity(), mListStudent));
		listStudent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				idPickerDialog.dismiss();
				UserInfoStoreManager.getInstance().setListStudent(mListStudent);
				UserInfoStoreManager.getInstance().setCurrentStudent(mListStudent.get(position));
				switchContent(new MainScreen(), false);
			}
		});
		
		btnExit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setLoginState(false);
				tvError.setText("");
				idPickerDialog.dismiss();
			}
		});
		idPickerDialog.show();
	}
	
	private void register(){
		
	}
	
	private void forgotPassword(){
		
	}
	
	
	@Override
	protected HeaderType getHeaderBarType() {
		return HeaderType.NONE;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			startLogin();
			break;
		case R.id.tvRegister:
			register();
			break;
		case R.id.tvForgotPassword:
			forgotPassword();
			break;
		default:
			break;
		}
	}

}
