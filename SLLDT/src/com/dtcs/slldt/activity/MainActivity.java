package com.dtcs.slldt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.dtcs.slldt.common.ICommonDefine;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.common.database.DatabaseQueryController;
import com.dtcs.slldt.common.database.MyDatabaseManager;
import com.dtcs.slldt.model.ContactModel;
import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.login.LoginScreen;
import com.dtcs.slldt.screen.outbox.OutboxScreen;

public class MainActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fragment cur = getCurrentFragment();
		boolean isStartFromNotification = getIntent().getBooleanExtra(ICommonDefine.KEY_START_FROM_NOTIFICATION, false);
		int sID = getIntent().getIntExtra(ICommonDefine.KEY_NOTIFICATION_FOR_STUDENT_ID, ICommonDefine.DEFAULT_ID);
		
		if (isStartFromNotification || (cur != null && !(cur instanceof InboxScreen))
				|| (cur != null && !(cur instanceof OutboxScreen))) {
			if (sID != ICommonDefine.DEFAULT_ID) {
				switchContent(new InboxScreen(), false);
			} else {
				switchContent(new OutboxScreen(), false);
			}
		} else {
			switchContent(new LoginScreen(), false);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		this.showScreenWithCheck(intent);
	}

	private void showScreenWithCheck(Intent intent) {
		int stID = intent.getIntExtra(ICommonDefine.KEY_NOTIFICATION_FOR_STUDENT_ID, 0);
		if (stID > 0) {
			UserInfoStoreManager.getInstance().setCurrentStudentId(stID);
			Log.i("current ID", "id:  " + stID);
		}
		Fragment cur = getCurrentFragment();
		if (cur != null && !(cur instanceof InboxScreen) && stID != ICommonDefine.DEFAULT_ID) {
			switchContent(new InboxScreen(), false);
		} else if (cur instanceof InboxScreen && stID != ICommonDefine.DEFAULT_ID) {
			Toast.makeText(this, "goi request get inbox", Toast.LENGTH_SHORT).show();
			((InboxScreen) cur).showInbox();
		} else if (cur != null && !(cur instanceof OutboxScreen) && stID == ICommonDefine.DEFAULT_ID) {
			switchContent(new OutboxScreen(), false);
		}
	}

}
