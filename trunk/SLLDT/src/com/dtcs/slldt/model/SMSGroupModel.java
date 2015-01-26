package com.dtcs.slldt.model;

import java.util.ArrayList;
import java.util.Date;

public class SMSGroupModel {

	public ArrayList<SMSModel> datas;
	
	public String getPhoneChat(){
		int size = datas.size();
		if (datas == null || size == 0) {
			return "";
		}
		SMSModel smsModel = datas.get(size - 1);
		return smsModel.getPhoneChat();
	}
	
	public String getMessage(){
		int size = datas.size();
		if (datas == null || size == 0) {
			return "";
		}
		SMSModel smsModel = datas.get(0);
		return smsModel.Noi_Dung;
	}
	
	public Date getTime(){
		int size = datas.size();
		if (datas == null || size == 0) {
			return new Date();
		}
		SMSModel smsModel = datas.get(0);
		return smsModel.getTime();
	}
}
