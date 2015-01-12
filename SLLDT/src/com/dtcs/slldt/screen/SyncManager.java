package com.dtcs.slldt.screen;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import com.dtcs.slldt.common.UserInfoStoreManager;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.model.TotalSMSModel;
import com.dtcs.slldt.webservice.SMSGatewayWebservice;
import com.dtcs.slldt.webservice.SMSGatewayWebservice.WebserviceTaskListener;

// TODO: Auto-generated Javadoc
/**
 * The Class SyncManager.
 */
public class SyncManager {

	/** The instance. */
	private static SyncManager INSTANCE = null;

	/** The m listener. */
	private ISyncListener mListener;

	/** The m count request. */
	private CountDownLatch mCountRequest;

	/** The result request. */
	private ResultModel resultRequest;

	/** The m student id. */
	private long mStudentId;

	/** The Constant NUMBER_SYNC. */
	private static final int NUMBER_SYNC = 3;

	/**
	 * Gets the single instance of SyncManager.
	 * 
	 * @return single instance of SyncManager
	 */
	public static SyncManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new SyncManager();
		}
		return INSTANCE;
	}

	/**
	 * Sync data.
	 * 
	 * @param studentId
	 *            the student id
	 * @param listener
	 *            the listener
	 */
	public void syncData(long studentId, ISyncListener listener) {
		mStudentId = studentId;
		mListener = listener;
		resultRequest = new ResultModel();
		resultRequest.setResultSuccess();
		initCount(NUMBER_SYNC);
		syncInbox();
		syncNewSMS();
		syncOutbox();
	}

	/**
	 * Inits the count.
	 * 
	 * @param number
	 *            the number
	 */
	private void initCount(int number) {
		mCountRequest = new CountDownLatch(number);
		if (mListener != null) {
			mListener.syncStart();
		}
	}

	/**
	 * Count down.
	 */
	private void countDown() {
		mCountRequest.countDown();
		if (mCountRequest.getCount() <= 0 && mListener != null) {
			mListener.syncComplete(resultRequest);
		}
	}

	/**
	 * Sync inbox.
	 */
	private void syncInbox() {
		StudentModel studentModel = UserInfoStoreManager.getInstance().getCurrentStudent();
		if (studentModel == null) {
			resultRequest.strResult = null;
			countDown();
			return;
		}
		SMSGatewayWebservice.getListSmsBySudentId(mStudentId, new WebserviceTaskListener<ArrayList<SMSModel>>() {

			@Override
			public void onTaskComplete(ArrayList<SMSModel> ob, ResultModel result) {
				if (ob != null && result != null) {
					UserInfoStoreManager.getInstance().setListSMS(ob);
				} else {
					resultRequest.strResult = null;
				}
				countDown();
			}
		});
	}

	/**
	 * Sync new sms.
	 */
	private void syncNewSMS() {
		StudentModel studentModel = UserInfoStoreManager.getInstance().getCurrentStudent();
		if (studentModel == null) {
			resultRequest.strResult = null;
			countDown();
			return;
		}
		SMSGatewayWebservice.getTotalNewSMSByStudentId(mStudentId, new WebserviceTaskListener<TotalSMSModel>() {

			@Override
			public void onTaskComplete(TotalSMSModel ob, ResultModel result) {
				if (ob != null && result != null) {
					UserInfoStoreManager.getInstance().setTotalSMSModel(ob);
				} else {
					resultRequest.strResult = null;
				}
				countDown();
			}
		});
	}

	/**
	 * Sync outbox.
	 */
	private void syncOutbox() {
		countDown();
	}

}
