package com.dtcs.slldt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.login.LoginScreen;

public class MainActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fragment cur = getCurrentFragment();
		if (cur != null && !(cur instanceof InboxScreen)) {
			switchContent(new InboxScreen(), false);
		} else {
			switchContent(new LoginScreen(), false);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Fragment cur = getCurrentFragment();
		if (cur != null && !(cur instanceof InboxScreen)) {
			switchContent(new InboxScreen(), false);
		} else if (cur instanceof InboxScreen) {
			Toast.makeText(this, "goi request get inbox", Toast.LENGTH_SHORT).show();
		}
	}
}
