package com.dtcs.slldt.gcmservice;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.TimeoutException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.AsyncTask;
import android.util.Log;

import com.dtcs.slldt.common.ICommonDefine;
import com.dtcs.slldt.common.SharePreferencesUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * @author TND
 * 
 */
public class GCMRequester {

	public interface IHandleRegisterGCMKey {
		public void onRegisterSuccess(String pRegisId);

		public void onRegisterFail();
	}

	private static GCMRequester instance = null;
	private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

	/**
	 * key stored registration ID in share preference
	 */
	public static final String PROPERTY_REG_ID = "registration_id";

	// FIXME: sender id of project
	public static final String SENDER_ID = "309725080089";

	private final String TAG = getClass().getSimpleName().toString();

	private GCMRequester() {
	}

	public static synchronized GCMRequester getInstance() {
		if (instance == null) {
			instance = new GCMRequester();
		}
		return instance;
	}

	public void onDestroy() {
		instance = null;
	}

	public void register(final Context pContext, final IHandleRegisterGCMKey listener) {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				if (pContext != null) {
					String mRegistrationId = SharePreferencesUtil.getString(ICommonDefine.KEY_SAVED_REGIS_ID, "");
					if (mRegistrationId.equals("")) {
						try {
							mRegistrationId = GoogleCloudMessaging.getInstance(pContext).register(SENDER_ID);
							if (mRegistrationId.equals("")) {
								listener.onRegisterFail();
							} else {
								listener.onRegisterSuccess(mRegistrationId);
							}
						} catch (IOException e) {
							listener.onRegisterFail();
							e.printStackTrace();
						}
					}else{
						listener.onRegisterSuccess(mRegistrationId);
					}
				}
				return null;
			}
		}.execute();

	}


	/**
	 * 
	 * Sends the registration ID to your server over HTTP, so it can use
	 * GCM/HTTP or CCS to send messages to your app. Not needed for this demo
	 * since the device sends upstream messages to a server that echoes back the
	 * message using the 'from' address in the message.
	 * 
	 * 
	 * @param pContext
	 * @param pRegisterID
	 * @return jsonObj
	 * @throws TimeoutException
	 * @throws SocketException
	 * @throws IOException
	 */
	public void registerWithOwnServer(Context pContext, String pRegisterID, String userID) throws SocketException,
			TimeoutException, IOException {
	}

	private void updateTokenId(Context pContext, String pRegisterID) {
	}

	/**
	 * 
	 * @param pContext
	 * @param pRegisterID
	 * @return
	 * @throws IOException
	 */
	private void unregisterWithOwnServer() {
	}


	/**
	 * 
	 * /** Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
	boolean isShow = false;
	Dialog googlePlayServiceDialog;

	public boolean checkPlayServices(Context pContext) {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(pContext);
		if (resultCode != ConnectionResult.SUCCESS) {

			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {

				if (googlePlayServiceDialog == null) {
					googlePlayServiceDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) pContext,
							PLAY_SERVICES_RESOLUTION_REQUEST);

					googlePlayServiceDialog.setOnDismissListener(new OnDismissListener() {

						@Override
						public void onDismiss(DialogInterface dialog) {
							isShow = false;
						}
					});
				}

				if (!isShow) {
					googlePlayServiceDialog.show();
				}

			} else {
				Log.i(TAG, "This device is not supported.");
			}
			return false;
		}
		return true;
	}
}
