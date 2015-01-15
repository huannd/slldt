package com.dtcs.slldt.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.model.TotalSMSModel;
import com.dtcs.slldt.model.MainDashboardItem.DashboardAction;

public class UserInfoStoreManager {

	private static UserInfoStoreManager INSTANCE = null;
	private SimpleDateFormat dateFormat;
	
	private ArrayList<StudentModel> listStudent;
//	private ArrayList<SMSModel> listSMS;
//	private StudentModel currentStudent;
	private long currentStudentId;
	private String phoneNumber;
	private TotalSMSModel totalSMSModel;
	  
	public static UserInfoStoreManager getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new UserInfoStoreManager();
		}
		return INSTANCE;
	}

	public ArrayList<StudentModel> getListStudent() {
		return listStudent;
	}

	public void setListStudent(ArrayList<StudentModel> listStudent) {
		this.listStudent = listStudent;
	}

	public StudentModel getCurrentStudent() {
//		return currentStudent;
		return getStudentById(currentStudentId);
	}
//
//	public void setCurrentStudent(StudentModel currentStudent) {
//		this.currentStudent = currentStudent;
//	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

//	public ArrayList<SMSModel> getListSMS() {
//		return listSMS;
//	}
//
//	public void setListSMS(ArrayList<SMSModel> listSMS) {
//		this.listSMS = listSMS;
//	}
	
	public SimpleDateFormat getDateFormat(){
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(ICommonDefine.DATE_FORMAT, Locale.US);
		}
		return dateFormat;
	}

	public TotalSMSModel getTotalSMSModel() {
		return totalSMSModel;
	}
	
	public void setTotalSMSModel(TotalSMSModel totalSMSModel) {
		this.totalSMSModel = totalSMSModel;
	}
	
	public int getBadgeNumber(DashboardAction action){
		if (action == DashboardAction.ActionInbox && totalSMSModel != null) {
			return totalSMSModel.count;
		}
		return 0;
	}
	
	public ArrayList<SMSModel> filterSMSByStudentId(long studentId){
		ArrayList<SMSModel> ret = new ArrayList<SMSModel>();
//		if (listSMS!=null && listSMS.size()>0) {
//			for (SMSModel smsModel : ret) {
//				
//			}
//		}
		return ret;
	}
	
	public long getCurrentStudentId() {
		return currentStudentId;
	}

	public void setCurrentStudentId(long currentStudentId) {
		this.currentStudentId = currentStudentId;
	}

	public StudentModel getStudentById(long id){
		if (listStudent == null) return null;
		for (StudentModel model : listStudent) {
			if (model.Ma_Hs == id) {
				return model;
			}
		}
		return null;
	}
}
