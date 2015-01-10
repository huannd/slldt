package com.dtcs.slldt.activity;

import android.content.Intent;
import android.os.Bundle;

import com.dtcs.slldt.common.ICommonDefine;
import com.dtcs.slldt.screen.inbox.InboxScreen;
import com.dtcs.slldt.screen.login.LoginScreen;

public class MainActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switchContent(new LoginScreen(), false);
		String screenId = getIntent().getStringExtra(ICommonDefine.SCREEN_KEY);
		if (screenId != null && screenId.equalsIgnoreCase(ICommonDefine.SCREEN_MAIN)) {
			switchContent(new InboxScreen(), false);
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		String screenId = intent.getStringExtra(ICommonDefine.SCREEN_KEY);
		if (screenId != null && screenId.equalsIgnoreCase(ICommonDefine.SCREEN_MAIN)) {
			switchContent(new InboxScreen(), false);
		}
	}
}
