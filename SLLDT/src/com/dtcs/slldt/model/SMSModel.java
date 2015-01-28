package com.dtcs.slldt.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dtcs.slldt.common.UserInfoStoreManager;

public class SMSModel {

	public enum ChatType {
		CHAT_ALL, CHAT_SEND, CHAT_RECEIVE, CONTACT
	}
	
	public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public String Sms_Id;
	public String Noi_Dung;
	public String Ngay_Duyet;
	public String SDT_Gui;
	public String SDT_Nhan;
	public boolean Is_Chat;
	
	public Date getTime(){
		try {
			String result = Ngay_Duyet.replaceAll("\\D+","");
			Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(Long.parseLong(result));
		    return calendar.getTime();
//			Date ret = format.parse(result);
//			return ret;
		} catch (Exception e) {
			return null;
		}
	}
	
	public ChatType getChatType(){
		if (SDT_Gui == null || SDT_Gui.trim().equals("") 
				|| SDT_Nhan == null || SDT_Nhan.trim().equals("")
				|| SDT_Gui.trim().equals(SDT_Nhan.trim())) {
			return ChatType.CHAT_ALL;
		}
		String phoneNumber = UserInfoStoreManager.getInstance().getPhoneNumber();
		if (SDT_Gui.equals(phoneNumber)) {
			return ChatType.CHAT_SEND;
		}else if (SDT_Nhan.equals(phoneNumber)) {
			return ChatType.CHAT_RECEIVE;
		}else{
			return ChatType.CHAT_ALL;
		}
	}
	
	public String getPhoneChat(){
		if (SDT_Gui == null || SDT_Gui.trim().equals("") 
				|| SDT_Nhan == null || SDT_Nhan.trim().equals("")) {
			return "";
		}
		String phoneNumber = UserInfoStoreManager.getInstance().getPhoneNumber();
		if (!SDT_Gui.equals(phoneNumber)) {
			return SDT_Gui;
		}else if (!SDT_Nhan.equals(phoneNumber)) {
			return SDT_Nhan;
		}else if( SDT_Gui.equals(SDT_Nhan)){
			return SDT_Gui;
		}else{
			return "";
		}
	}
}
