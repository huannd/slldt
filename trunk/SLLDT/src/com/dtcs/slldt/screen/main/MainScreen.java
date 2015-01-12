package com.dtcs.slldt.screen.main;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.text.TextUtils;
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

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.MainDashboardItem;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.ISyncListener;
import com.dtcs.slldt.screen.StudentAdapter;
import com.dtcs.slldt.screen.SyncManager;
import com.dtcs.slldt.screen.account.AccountScreen;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.outbox.OutboxScreen;
import com.dtcs.slldt.screen.searching.SearchingScreen;
import com.dtcs.slldt.template.TemplateDefault;
import com.edu.ebookcontact.R;

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
		showListStudentDialog(false);
		return v;
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
					switchContent(new OutboxScreen(), true);
					break;
				case ActionContactBook:
					showListStudentDialog(true);
					break;
				case ActionAccount:
					switchContent(new AccountScreen(), true);
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
		switchData();
	}
	
	/**
	 * Switch data.
	 */
	private void switchData(){
		mCurrentStudent = UserInfoStoreManager.getInstance().getCurrentStudent();
		SyncManager.getInstance().syncData(mCurrentStudent.Ma_Hs,new ISyncListener() {
			
			@Override
			public void syncStart() {
				showLoading();
			}
			
			@Override
			public void syncComplete(ResultModel result) {
				String info = "Tên : " + mCurrentStudent.Hoten_HocSinh + " | Lớp : " + mCurrentStudent.Ten_Lop + " | Trường : " + mCurrentStudent.Ten_Truong;
				tvCurrentId.setText(info);
				tvCurrentId.setSelected(true);
				tvCurrentId.setEllipsize(TextUtils.TruncateAt.MARQUEE);
				tvCurrentId.setTypeface(TemplateDefault.typeface01);
				hideLoading();
			}
		});
	}
	
	/**
	 * Show list student dialog.
	 *
	 * @param isCancelAble the is cancel able
	 */
	private void showListStudentDialog(boolean isCancelAble) {
		final ArrayList<StudentModel> students = UserInfoStoreManager.getInstance().getListStudent();
		final Dialog idPickerDialog = new Dialog(getActivity());
		idPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		idPickerDialog.setContentView(R.layout.dialog_idpicker);
		idPickerDialog.setCanceledOnTouchOutside(false);
		TextView tvTitle = (TextView) idPickerDialog.findViewById(R.id.tv_title);
		final ListView listStudent = (ListView) idPickerDialog.findViewById(R.id.lv_switch);
		Button btnExit = (Button) idPickerDialog.findViewById(R.id.btn_exit);
		if (isCancelAble) {
			btnExit.setVisibility(View.VISIBLE);
		}else{
			btnExit.setVisibility(View.GONE);
		}
		String title = getResources().getString(R.string.lbl_select_slldt);
		tvTitle.setText(title);
		idPickerDialog.setOnShowListener(new OnShowListener() {
			
			@Override
			public void onShow(DialogInterface dialog) {
				listStudent.setAdapter(new StudentAdapter(getActivity(), students));
				listStudent.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						idPickerDialog.dismiss();
						UserInfoStoreManager.getInstance().setCurrentStudent(students.get(position));
						switchData();
					}
				});
			}
		});

		btnExit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				idPickerDialog.dismiss();
			}
		});
		idPickerDialog.show();
	}
	
	/* (non-Javadoc)
	 * @see com.dtcs.slldt.screen.base.EContactFragment#getTitle()
	 */
	@Override
	public String getTitle() {
		return getResources().getString(R.string.app_nameH);
	}
	
}