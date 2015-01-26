package com.dtcs.slldt.model;

import java.util.ArrayList;

import com.dtcs.slldt.EBookContactApp;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.edu.ebookcontact.R;

public class MainDashboardItem {

	public enum DashboardAction{
		ActionNone,
		ActionInbox,
		ActionOutbox,
		ActionContactBook,
		ActionSearchSms,
		ActionSettings,
		ActionAccount,
		ActionAbout
	}
	
	public MainDashboardItem(int dashboardIcon,String dashboardName,DashboardAction dashboardAction){
		this.dashboardIcon = dashboardIcon;
		this.dashboardName = dashboardName;
		this.dashboardAction = dashboardAction;
	}
	
	public int dashboardIcon;
	public String dashboardName;
	public DashboardAction dashboardAction;
	
	public int getBadgeNumber(){
		return UserInfoStoreManager.getInstance().getBadgeNumber(dashboardAction);
	}
	
	public static ArrayList<MainDashboardItem> getMainDashboardList(){
		
		ArrayList<MainDashboardItem> listDashboard = new ArrayList<MainDashboardItem>();
		MainDashboardItem inboxItem = new MainDashboardItem(R.drawable.inbox,
				EBookContactApp.getAppContext().getResources().getString(R.string.lbl_sms_inbox) , 
				DashboardAction.ActionInbox);
		
		MainDashboardItem searchSmsItem = new MainDashboardItem(R.drawable.tracuu,
				EBookContactApp.getAppContext().getResources().getString(R.string.lbl_sms_searching) , 
				DashboardAction.ActionSearchSms);
		
		MainDashboardItem outboxItem = new MainDashboardItem(R.drawable.sms,
				EBookContactApp.getAppContext().getResources().getString(R.string.lbl_sms_outbox) , 
				DashboardAction.ActionOutbox);
		
//		MainDashboardItem contactBookItem = new MainDashboardItem(R.drawable.tracuu,
//				EBookContactApp.getAppContext().getResources().getString(R.string.lbl_sms_contact) , 
//				DashboardAction.ActionContactBook);
		
		MainDashboardItem accountItem = new MainDashboardItem(R.drawable.taikhoan,
				EBookContactApp.getAppContext().getResources().getString(R.string.Taikhoan) , 
				DashboardAction.ActionAccount);
		
		MainDashboardItem aboutItem = new MainDashboardItem(R.drawable.about,
				EBookContactApp.getAppContext().getResources().getString(R.string.About) , 
				DashboardAction.ActionAbout);
		
		listDashboard.add(inboxItem);
		listDashboard.add(searchSmsItem);
		listDashboard.add(outboxItem);
//		listDashboard.add(contactBookItem);
		listDashboard.add(accountItem);
		listDashboard.add(aboutItem);
		return listDashboard;
		
		
	}
}
