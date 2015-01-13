package com.dtcs.slldt.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SMSModel {

	public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public String Sms_Id;
	public String Noi_Dung;
	public String Ngay_Duyet;
	public String SDT_Gui;
	public String SDT_Nhan;
	
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
}
