package com.dtcs.slldt.screen.main;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.SessionStore;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.common.DialogCommons.OnDialogClickOkListener;
import com.dtcs.slldt.model.MainDashboardItem;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.StudentAdapter;
import com.dtcs.slldt.screen.account.AccountScreen;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.login.LoginScreen;
import com.dtcs.slldt.screen.outbox.ContactScreen;
import com.dtcs.slldt.screen.outbox.OutboxScreen;
import com.dtcs.slldt.screen.searching.SearchingScreen;
import com.dtcs.slldt.template.TemplateDefault;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;
import com.google.android.gms.internal.ms;

/**
 * The Class MainScreen.
 */
public class MainScreen extends EContactFragment{

	/** The grid view. */
	private GridView gridView;
	
	/** The m adapter. */
	private MainDashboardAdapter mAdapter;
	
	/** The m list dash boards. */
	private ArrayList<MainDashboardItem> mListDashBoards;
	
	/** The tv current id. */
	private TextView tvCurrentId;
	
	/** The m current student. */
	private StudentModel mCurrentStudent;
	
	/** The m progress dialog. */
	private ProgressDialog mProgressDialog;
	
	private boolean isShowListStudent = false;
	
	private long currentStudentId = -1;
	/**
	 * @param isShowListStudent the isShowListStudent to set
	 */
	public void setShowListStudent(boolean isShowListStudent) {
		this.isShowListStudent = isShowListStudent;
	}

	/* (non-Javadoc)
	 * @see com.dtcs.slldt.screen.base.BaseFragment#onCreateContentView(android.view.LayoutInflater, android.view.ViewGroup)
	 */
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_main, container,false);
		tvCurrentId = (TextView) v.findViewById(R.id.tv_current_id);
		gridView = (GridView) v.findViewById(R.id.grid);
		mProgressDialog = new ProgressDialog(getActivity());
		mProgressDialog.setMessage("tải dữ liệu...");
		init();
//		currentStudentId = UserInfoStoreManager.getInstance().getCurrentStudentId();
		if (isShowListStudent){
			showListStudentDialog(false);
		}
//		getAllData();
		return v;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		long studentId = UserInfoStoreManager.getInstance().getCurrentStudentId();
		if (currentStudentId != studentId) {
			currentStudentId = studentId;
			switchData();
		}
	}
	
	/**
	 * Inits the.
	 */
	private void init(){
		mListDashBoards = MainDashboardItem.getMainDashboardList();
		mAdapter = new MainDashboardAdapter(getActivity(), mListDashBoards);
		gridView.setAdapter(mAdapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MainDashboardItem item = mListDashBoards.get(position);
				switch (item.dashboardAction) {
				case ActionInbox:
					switchContent(new InboxScreen(), true);
					break;
				case ActionSearchSms:
					switchContent(new SearchingScreen(), true);
					break;
				case ActionOutbox:
					switchContent(new ContactScreen(), true);
//					switchContent(new OutboxScreen(), true);
					break;
				case ActionContactBook:
					showListStudentDialog(true);
					break;
				case ActionAccount:
//					switchContent(new AccountScreen(), true);
					showAccountDialog();
					break;
				case ActionAbout:
					break;
				default:
					break;
				}
			}
		});
	}
		
	@Override
	public void sync() {
//		switchData();
		String regId = SessionStore.getInstance().getRegistrationId();
		long studentID = UserInfoStoreManager.getInstance().getCurrentStudent().Ma_Hs;
		int osType = 5;
		SMSGatewayWebservice.testPush(regId, studentID, osType, new WebserviceTaskListener<ResultModel>() {
			
			@Override
			public void onTaskComplete(ResultModel ob, ResultModel result) {
				if (result!=null && result.getErrorCode() == 0) {
					Log.d("Send Push ","Success");
				}
			}
		});
		
//		SMSGatewayWebservice.sendSMS("01272768149", "Test Send SMS", new WebserviceTaskListener<ResultModel>() {
//			
//			@Override
//			public void onTaskComplete(ResultModel ob, ResultModel result) {
//				if (result!=null && result.getErrorCode() == 0){
//					Log.d("Send SMS : ", "Success");
//				}
//			}
//		});
		
	}
	
	/**
	 * Switch data.
	 */
	private void switchData(){
		mCurrentStudent = UserInfoStoreManager.getInstance().getCurrentStudent();
		String info = "Tên : " + mCurrentStudent.Hoten_HocSinh + " | Lớp : " + mCurrentStudent.Ten_Lop + " | Trường : " + mCurrentStudent.Ten_Truong;
		tvCurrentId.setText(info);
		tvCurrentId.setSelected(true);
		tvCurrentId.setEllipsize(TextUtils.TruncateAt.MARQUEE);
		tvCurrentId.setTypeface(TemplateDefault.typeface01);
		mAdapter.notifyDataSetChanged();
//		hideLoading();
//		SyncManager.getInstance().syncData(mCurrentStudent.Ma_Hs,new ISyncListener() {
//			
//			@Override
//			public void syncStart() {
//				showLoading();
//			}
//			
//			@Override
//			public void syncComplete(ResultModel result) {
//				String info = "Tên : " + mCurrentStudent.Hoten_HocSinh + " | Lớp : " + mCurrentStudent.Ten_Lop + " | Trường : " + mCurrentStudent.Ten_Truong;
//				tvCurrentId.setText(info);
//				tvCurrentId.setSelected(true);
//				tvCurrentId.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//				tvCurrentId.setTypeface(TemplateDefault.typeface01);
//				mAdapter.notifyDataSetChanged();
//				hideLoading();
//			}
//		});
	}
	
	/**
	 * Show list student dialog.
	 *
	 * @param isCancelAble the is cancel able
	 */
	private void showListStudentDialog(boolean isCancelAble) {
		showAccountDialog();
//		final ArrayList<StudentModel> students = UserInfoStoreManager.getInstance().getListStudent();
//		if (students == null) {
//			getListStudent();
//			return;
//		}
//		final Dialog idPickerDialog = new Dialog(getActivity());
//		idPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		idPickerDialog.setContentView(R.layout.dialog_idpicker);
//		idPickerDialog.setCanceledOnTouchOutside(false);
//		TextView tvTitle = (TextView) idPickerDialog.findViewById(R.id.tv_title);
//		final ListView listStudent = (ListView) idPickerDialog.findViewById(R.id.lv_switch);
//		Button btnExit = (Button) idPickerDialog.findViewById(R.id.btn_exit);
//		if (isCancelAble) {
//			btnExit.setVisibility(View.VISIBLE);
//		}else{
//			btnExit.setVisibility(View.GONE);
//		}
//		String title = getResources().getString(R.string.lbl_select_slldt);
//		tvTitle.setText(title);
//		idPickerDialog.setOnShowListener(new OnShowListener() {
//			
//			@Override
//			public void onShow(DialogInterface dialog) {
//				listStudent.setAdapter(new StudentAdapter(getActivity(), students));
//				listStudent.setOnItemClickListener(new OnItemClickListener() {
//
//					@Override
//					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//						idPickerDialog.dismiss();
////						UserInfoStoreManager.getInstance().setCurrentStudent(students.get(position));
//						currentStudentId = students.get(position).Ma_Hs;
//						UserInfoStoreManager.getInstance().setCurrentStudentId(currentStudentId);
//						switchData();
//					}
//				});
//			}
//		});
//
//		btnExit.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				idPickerDialog.dismiss();
//			}
//		});
//		idPickerDialog.show();
	}
	
	/**
	 * Show list student dialog.
	 *
	 * @param isCancelAble the is cancel able
	 */
	private void showAccountDialog() {
		final ArrayList<StudentModel> students = UserInfoStoreManager.getInstance().getListStudent();
		if (students == null) {
			getListStudent();
			return;
		}
		final Dialog idPickerDialog = new Dialog(getActivity());
		idPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		idPickerDialog.setContentView(R.layout.screen_account);
		idPickerDialog.setCanceledOnTouchOutside(false);
		final TextView tvPhoneNumber = (TextView)idPickerDialog.findViewById(R.id.tv_phone_number);
		final TextView tvChangePW = (TextView) idPickerDialog.findViewById(R.id.tv_change_pw);
		final ListView mListView = (ListView) idPickerDialog.findViewById(R.id.lv_switch);
		Button btnExit = (Button) idPickerDialog.findViewById(R.id.btn_exit);
		tvPhoneNumber.setText("Số điện thoại : "+UserInfoStoreManager.getInstance().getPhoneNumber());
		tvChangePW.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				idPickerDialog.dismiss();
				Dialog dialogChangePass = DialogCommons.getDialogChangePassword(getActivity(), new OnDialogClickOkListener() {
					@Override
					public void onOkClick(Object obj) {
						if (currentStudentId == -1) {
							currentStudentId = students.get(0).Ma_Hs;
							UserInfoStoreManager.getInstance().setCurrentStudentId(currentStudentId);
							switchData();
						}
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
		idPickerDialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				mListView.setAdapter(new StudentAdapter(getActivity(), students));
				mListView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						idPickerDialog.dismiss();
//						UserInfoStoreManager.getInstance().setCurrentStudent(students.get(position));
						currentStudentId = students.get(position).Ma_Hs;
						UserInfoStoreManager.getInstance().setCurrentStudentId(currentStudentId);
						switchData();
					}
				});
			}
		});

		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentStudentId == -1) {
					currentStudentId = students.get(0).Ma_Hs;
					UserInfoStoreManager.getInstance().setCurrentStudentId(currentStudentId);
					switchData();
				}
				idPickerDialog.dismiss();
			}
		});
		idPickerDialog.show();
	}
	
	private void getListStudent() {
		showLoading();
		SMSGatewayWebservice.getListStudentByPhoneNumber(new WebserviceTaskListener<ArrayList<StudentModel>>() {

			@Override
			public void onTaskComplete(ArrayList<StudentModel> ob, ResultModel result) {
				if (ob != null && result != null) {
					UserInfoStoreManager.getInstance().setListStudent(ob);
					showListStudentDialog(false);
				}
				hideLoading();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see com.dtcs.slldt.screen.base.EContactFragment#getTitle()
	 */
	@Override
	public String getTitle() {
		return getResources().getString(R.string.app_nameH);
	}
	
	@Override
	public void onBackPress() {
		FragmentManager fm = getActivity().getSupportFragmentManager();
		if (fm.getBackStackEntryCount() > 1) {
			super.onBackPress();
		} else {
			LoginScreen login = new LoginScreen();
			login.setAutoLogin(false);
			switchContent(login, false);
		}
	}
}
