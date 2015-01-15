package com.dtcs.slldt.gcmservice;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.util.Log;

import com.dtcs.slldt.EBookContactApp;
import com.dtcs.slldt.common.MyNotificationManager;

/**
 * @author TND
 * 
 */
public class GCMManagerMessage {
	// FIXME: key get message is client guess:
	public static final String GCM_MSG_KEY = "alert";
	public static final String KEY_STUDENT_ID = "studentId";
	public static final String KEY_COMMAND = "command";
	public static final String KEY_PHONE = "So_Dien_Thoai";
	
	
	
	private static GCMManagerMessage instance = null;

	/**
	 * Data Delegates.
	 */
	private List<OnGCMNewMessageListener> listDelegateOnNewMessage;

	public void addDelegateListener(OnGCMNewMessageListener pGcmNewMessageListener) {
		if (listDelegateOnNewMessage == null) {
			listDelegateOnNewMessage = new ArrayList<OnGCMNewMessageListener>();
		}

		if (!listDelegateOnNewMessage.contains(pGcmNewMessageListener)) {
			listDelegateOnNewMessage.add(pGcmNewMessageListener);
		}
	}

	/**
	 * must call when destroy object addDelegateListener(OnGCMNewMessageListener
	 * pGcmNewMessageListener).
	 * 
	 * @param pGcmNewMessageListener
	 */
	public void removeDelegateListener(OnGCMNewMessageListener pGcmNewMessageListener) {
		if (listDelegateOnNewMessage != null && listDelegateOnNewMessage.contains(pGcmNewMessageListener)) {
			listDelegateOnNewMessage.remove(pGcmNewMessageListener);
		}
	}

	private GCMManagerMessage() {
	}

	public static synchronized GCMManagerMessage getInstance() {
		if (instance == null) {
			instance = new GCMManagerMessage();
		}
		return instance;
	}

	public void onDestroy() {
		instance = null;
	}

	/**
	 * called when server deleted messages is pending up time saved on GCM
	 * server (messages over 100 or delay time over 4 weeks)
	 * 
	 * @param pIntent
	 */
	public void onMessageDeleteByServer(Intent pIntent) {
	}

	/**
	 * It's an error.
	 * 
	 * @param pIntent
	 */
	public void onMessageSendError(Intent pIntent) {
	}

	String GCM = "gcm";

	public void onMessageRegular(Intent pIntent) {
		Log.e("GCMManagerMessage", "GCMManagerMessage received");
		String content = pIntent.getStringExtra(GCM_MSG_KEY);
		System.err.println("Alert:  " + content);
		Log.i(GCM, "command: " + pIntent.getStringExtra("command"));
		Log.i(GCM, "So_Dien_Thoai: " + pIntent.getStringExtra("So_Dien_Thoai"));
		Log.i(GCM, "studentId: " + pIntent.getStringExtra("studentId"));

		System.err.println("Msg:  " + pIntent.getStringExtra("msg"));
		if (listDelegateOnNewMessage != null) {
			for (OnGCMNewMessageListener receiver : listDelegateOnNewMessage) {
				receiver.onNewMessage(content);
			}
		}
		// make notification when received message
		MyNotificationManager notify = MyNotificationManager.getInstance();
		notify.setMessageAndKey(pIntent.getStringExtra(GCM_MSG_KEY), pIntent.getStringExtra(KEY_STUDENT_ID));
		notify.showNotify(EBookContactApp.getAppContext());
	}

}
