package com.dtcs.slldt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.dtcs.slldt.common.ICommonDefine;
import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.login.LoginScreen;

public class MainActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fragment cur = getCurrentFragment();
		boolean isStartFromNotification = getIntent().getBooleanExtra(ICommonDefine.KEY_START_FROM_NOTIFICATION, false);
		if (isStartFromNotification || (cur != null && !(cur instanceof InboxScreen))) {
			switchContent(new InboxScreen(), false);
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
		if (cur != null && !(cur instanceof InboxScreen)) {
			switchContent(new InboxScreen(), false);
		} else if (cur instanceof InboxScreen) {
			Toast.makeText(this, "goi request get inbox", Toast.LENGTH_SHORT).show();
			((InboxScreen)cur).showInbox();
		}
	}
}
