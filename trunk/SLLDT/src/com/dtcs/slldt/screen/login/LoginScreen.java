package com.dtcs.slldt.screen.login;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dtcs.slldt.common.DeviceInfoStore;
import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.DialogCommons.OnDialogClickOkListener;
import com.dtcs.slldt.common.SessionStore;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.gcmservice.GCMRequester;
import com.dtcs.slldt.gcmservice.GCMRequester.IHandleRegisterGCMKey;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.main.MainScreen;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class LoginScreen extends EContactFragment implements OnClickListener {

	private View btnLogin;
	private EditText edtPhone;
	private EditText edtPassword;
	private TextView tvRegister;
	private TextView tvForgotPassword;
	private TextView tvError;
//	private ProgressBar prgLogin;
	private ProgressDialog prgLogin;
	private ArrayList<StudentModel> mListStudent;
	private boolean isAutoLogin = true;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_lg, container, false);
		btnLogin = v.findViewById(R.id.btn_login);
		edtPhone = (EditText) v.findViewById(R.id.edt_phone);
		edtPassword = (EditText) v.findViewById(R.id.edt_pass);
		tvRegister = (TextView) v.findViewById(R.id.tv_register);
		tvForgotPassword = (TextView) v.findViewById(R.id.tv_forgot_password);
		tvError = (TextView) v.findViewById(R.id.tv_error);
//		prgLogin = (ProgressBar) v.findViewById(R.id.prg_login);
		prgLogin = new ProgressDialog(getActivity());
		prgLogin.setMessage("đăng nhập...");
		init();
		return v;
	}

	private void init() {
		tvRegister.setText(Html.fromHtml("<u>Đăng Ký</u>"));
		tvForgotPassword.setText(Html.fromHtml("<u>Quên Mật Khẩu</u>"));
		btnLogin.setOnClickListener(this);
		tvRegister.setOnClickListener(this);
		tvForgotPassword.setOnClickListener(this);
		String userIdStore = SessionStore.getInstance().getUserId();
		String password = SessionStore.getInstance().getPassword();
		if (userIdStore != null && password != null) {
			edtPhone.setText(userIdStore);
			edtPassword.setText(password);
			if (isAutoLogin) {
				startLogin();
			}
		}
//		if (isAutoLogin) {
//			actionRegisterGCM();
//		}
	}

	public void setAutoLogin(boolean isAuto){
		isAutoLogin = isAuto;
	}
	
	/**
	 * <p>
	 * Check Google play Service application install on Device, if not install
	 * will show dialog confirm install GPService application
	 * </p>
	 */
	private void actionRegisterGCM() {
		if (GCMRequester.getInstance().checkPlayServices(getActivity())) {
			GCMRequester.getInstance().register(getActivity(), new IHandleRegisterGCMKey() {

				@Override
				public void onRegisterSuccess(final String pRegisId) {
					Log.i("GCM: ", pRegisId);
					String prefRegisId = SessionStore.getInstance().getRegistrationId();
//					if (prefRegisId!=null && pRegisId!=null && prefRegisId.equals(pRegisId)) return;
					if (pRegisId != null) {
						String deviceInfo = DeviceInfoStore.getInstance().getDeviceInfo();
						int osType = DeviceInfoStore.getInstance().getOsType();
						SMSGatewayWebservice.registerPushNotification(pRegisId, deviceInfo, osType, new WebserviceTaskListener<ResultModel>() {
							
							@Override
							public void onTaskComplete(ResultModel ob, ResultModel result) {
								if (result!=null && result.getErrorCode() == 0) {
									SessionStore.getInstance().setRegistrationId(pRegisId);
								}
							}
						});
					}
				}

				@Override
				public void onRegisterFail() {

				}
			});
		}
	}

	public void setEditable(boolean isEdit) {
		edtPhone.setEnabled(isEdit);
		edtPassword.setEnabled(isEdit);
		tvRegister.setEnabled(isEdit);
		tvForgotPassword.setEnabled(isEdit);
	}

	private void setLoginState(boolean isLogin) {
//		if (isLogin) {
//			setEditable(false);
//			tvError.setText("");
//			prgLogin.setVisibility(View.VISIBLE);
//			btnLogin.setVisibility(View.INVISIBLE);
//		} else {
//			setEditable(true);
//			prgLogin.setVisibility(View.INVISIBLE);
//			btnLogin.setVisibility(View.VISIBLE);
//		}
		if (isLogin) {
			tvError.setText("");
			prgLogin.show();
		} else {
			prgLogin.dismiss();
		}
	}

	private boolean checkInvalidate(String str) {
		if (str == null || str.equals("") || str.length() < 2) {
			return false;
		}
		return true;
	}

	private void startLogin() {
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
				} else {
					if (result.getErrorCode() == 0) {
						SessionStore.getInstance().setUserId(phone);
						SessionStore.getInstance().setPassword(pass);
						UserInfoStoreManager.getInstance().setPhoneNumber(phone);
						getListStudent();
						actionRegisterGCM();
						// switchContent(new MainScreen(), false);
					} else {
						tvError.setText(getResources().getString(R.string.err_not_exist_account));
						setLoginState(false);
					}
				}
			}
		});
	}

	private void getListStudent() {
		SMSGatewayWebservice.getListStudentByPhoneNumber(new WebserviceTaskListener<ArrayList<StudentModel>>() {

			@Override
			public void onTaskComplete(ArrayList<StudentModel> ob, ResultModel result) {
				if (ob != null && result != null) {
					mListStudent = ob;
//					showListStudentDialog();
					UserInfoStoreManager.getInstance().setListStudent(mListStudent);
					gotoMainScreen();
				} else {
					tvError.setText(getResources().getString(R.string.err_network_connection));
				}
				setLoginState(false);
			}
		});
	}
	
	private void gotoMainScreen(){
		MainScreen mainFrag = new MainScreen();
		mainFrag.setShowListStudent(true);
		switchContent(mainFrag, true);
	}

	private void register() {
		DialogCommons.getInputDialog(getActivity(), "Đăng Ký", new OnDialogClickOkListener() {
			
			@Override
			public void onOkClick(Object obj) {
				String phone = (String)obj;
				showLoading();
				SMSGatewayWebservice.register(phone, new WebserviceTaskListener<ResultModel>() {
					
					@Override
					public void onTaskComplete(ResultModel ob, ResultModel result) {
						hideLoading();
						if(result != null){
							int code = result.getErrorCode();
							switch (code) {
							case 60:
								Toast.makeText(getActivity(), "Số điện thoại này đã tồn tại", Toast.LENGTH_SHORT).show();
								break;
							case 61:
								Toast.makeText(getActivity(), "Vui lòng đăng ký số điện thoại tại trường.", Toast.LENGTH_SHORT).show();
								break;
							case 62:
								Toast.makeText(getActivity(), "Số điện thoại này chưa được kích hoạt.", Toast.LENGTH_SHORT).show();
								break;
							default:
								Toast.makeText(getActivity(), "Số điện thoại này chưa được kích hoạt.", Toast.LENGTH_SHORT).show();
								break;
							}
						}
					}
				});
			}
		}).show();
	}

	private void forgotPassword() {
		DialogCommons.getInputDialog(getActivity(), "Quên Mật Khẩu", new OnDialogClickOkListener() {
			
			@Override
			public void onOkClick(Object obj) {
				String phone = (String)obj;
				showLoading();
				SMSGatewayWebservice.forgotPassword(phone, new WebserviceTaskListener<ResultModel>() {
					
					@Override
					public void onTaskComplete(ResultModel ob, ResultModel result) {
						hideLoading();
						if (result != null) {
							int code = result.getErrorCode();
							switch (code) {
							case 60:
								DialogCommons.showConfirmDialog(getActivity(), "Thông Báo", "Mật khẩu của bạn là: "+result.getErrorDescription());
								break;
							case 61:
								Toast.makeText(getActivity(), "Tài khoản này không tồn tại.", Toast.LENGTH_SHORT).show();
								break;
							case 62:
								Toast.makeText(getActivity(), "Tài khoản này chưa được kích hoạt.", Toast.LENGTH_SHORT).show();
								break;
							default:
								Toast.makeText(getActivity(), "Tài khoản này không tồn tại.", Toast.LENGTH_SHORT).show();
								break;
							}
						}
					}
				});
			}
		}).show();
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
		case R.id.tv_register:
			register();
			break;
		case R.id.tv_forgot_password:
			forgotPassword();
			break;
		default:
			break;
		}
	}

}
