package com.dtcs.slldt.activity;

import android.os.Bundle;

import com.dtcs.slldt.screen.login.LoginScreen;

public class MainActivity extends BaseFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		switchContent(new LoginScreen(), false);
	}

}
