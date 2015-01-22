package com.dtcs.slldt.gcmservice;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.util.Log;

import com.dtcs.slldt.EBookContactApp;
import com.dtcs.slldt.common.ICommonDefine;
import com.dtcs.slldt.common.MyNotificationManager;
import com.dtcs.slldt.common.UserInfoStoreManager;

/**
 * @author TND
 * 
 */
public class GCMManagerMessage {
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

	public void onMessageRegular(Intent pIntent) {
		if (listDelegateOnNewMessage != null) {
			for (OnGCMNewMessageListener receiver : listDelegateOnNewMessage) {
				int cur = (int) UserInfoStoreManager.getInstance().getCurrentStudentId();
				String sId = pIntent.getStringExtra(KEY_STUDENT_ID);
				int sIdNum = Integer.valueOf(sId);
				if (sId != null && !sId.equalsIgnoreCase("") && !sId.equalsIgnoreCase(" ")) {
					int idPush = Integer.valueOf(sId);
					if (cur == idPush || sIdNum == ICommonDefine.DEFAULT_ID) {
						receiver.onNewMessage(Integer.valueOf(idPush));
					}
				}
			}
		}
		// make notification when received message
		MyNotificationManager notify = MyNotificationManager.getInstance();
		notify.setMessageAndKey(pIntent.getStringExtra(GCM_MSG_KEY), pIntent.getStringExtra(KEY_STUDENT_ID));
		notify.showNotify(EBookContactApp.getAppContext());
	}

}
