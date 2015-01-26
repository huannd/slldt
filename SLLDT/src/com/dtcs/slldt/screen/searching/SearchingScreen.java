package com.dtcs.slldt.screen.searching;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.dtcs.slldt.common.DialogCommons;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.screen.SMSAdapter;
import com.dtcs.slldt.screen.base.EContactFragment;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;
import com.edu.ebookcontact.R;

public class SearchingScreen extends EContactFragment implements OnClickListener{

	private ListView mListView;
	private Button btnDateFrom;
	private Button btnDateTo;
	private Button btnSearch;
	private ArrayList<SMSModel> mDatas;
	private SMSAdapter mAdapter;
	
	@Override
	protected View onCreateContentView(LayoutInflater inflater,
			ViewGroup container) {
		View v = LayoutInflater.from(getActivity()).inflate(R.layout.screen_searching, container,false);
		btnDateFrom = (Button)v.findViewById(R.id.btn_date_from);
		btnDateTo = (Button)v.findViewById(R.id.btn_date_to);
		btnSearch = (Button)v.findViewById(R.id.btn_search);
		mListView = (ListView) v.findViewById(R.id.lv_searching);
		init();
		return v;
	}
	
	private void init(){
		btnDateFrom.setOnClickListener(this);
		btnDateTo.setOnClickListener(this);
		btnSearch.setOnClickListener(this);
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

	@Override
	public String getTitle() {
		return getResources().getString(R.string.lbl_sms_searching);
	}

	@SuppressWarnings("deprecation")
	private void showDatePickerFrom(){
		// Use the current date as the default date in the picker
		Calendar c = Calendar.getInstance();
		String dateFromText = btnDateFrom.getText().toString();
		if (dateFromText != null && !dateFromText.equals("")) {
			SimpleDateFormat dateFormat = UserInfoStoreManager.getInstance().getDateFormat();
			try {
				Date date = dateFormat.parse(dateFromText);
				c.setTime(date);
			} catch (Exception e) {
				c = Calendar.getInstance();
				e.printStackTrace();
			}
		}else{
			c = Calendar.getInstance();
		}
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);;
		int day = c.get(Calendar.DAY_OF_MONTH);
	    DatePickerDialog fromDatePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				monthOfYear += 1;
				String daySet = dayOfMonth >= 10 ? (""+dayOfMonth):("0"+dayOfMonth);
				String monthSet = monthOfYear >= 10 ? (""+monthOfYear):("0"+monthOfYear);
				String timeSet = monthSet+"/"+daySet+"/"+year;
				btnDateFrom.setText(timeSet);
			}
		}, year, month, day);
	    fromDatePickerDialog.show();
	}
	
	private void showDatePickerTo(){
		Calendar c = Calendar.getInstance();
		String dateToText = btnDateTo.getText().toString();
		if (dateToText != null && !dateToText.equals("")) {
			SimpleDateFormat dateFormat = UserInfoStoreManager.getInstance().getDateFormat();
			try {
				Date date = dateFormat.parse(dateToText);
				c.setTime(date);
			} catch (Exception e) {
				c = Calendar.getInstance();
				e.printStackTrace();
			}
		}else{
			c = Calendar.getInstance();
		}
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);;
		int day = c.get(Calendar.DAY_OF_MONTH);
	    DatePickerDialog toDatePickerDialog = new DatePickerDialog(getActivity(), new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				monthOfYear += 1;
				String daySet = dayOfMonth >= 10 ? (""+dayOfMonth):("0"+dayOfMonth);
				String monthSet = monthOfYear >= 10 ? (""+monthOfYear):("0"+monthOfYear);
				String timeSet = monthSet+"/"+daySet+"/"+year;
				btnDateTo.setText(timeSet);
			}
		}, year, month, day);
	    toDatePickerDialog.show();
	}
	
	private void search(){
		String dateFrom = btnDateFrom.getText().toString();
		String dateTo = btnDateTo.getText().toString();
		if (dateFrom != null && dateTo != null && !dateFrom.equals("") && !dateTo.equals("")) {
			showLoading();
			StudentModel currentStudent = UserInfoStoreManager.getInstance().getCurrentStudent();
			SMSGatewayWebservice.getListSmsByStudentIdAndDate(currentStudent.Ma_Hs, dateFrom, dateTo, new WebserviceTaskListener<ArrayList<SMSModel>>() {
				
				@Override
				public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
					if (result != null && ob != null && result.getErrorCode() == 0) {
						mDatas.clear();
						mDatas.addAll(ob);
						mAdapter.notifyDataSetChanged();
					}
					hideLoading();
				}
			});
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_date_from:
			showDatePickerFrom();
			break;
		case R.id.btn_date_to:
			showDatePickerTo();
			break;
		case R.id.btn_search:
			search();
			break;
		default:
			break;
		}
	}
}
