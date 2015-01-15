package com.dtcs.slldt.webservice;

import java.util.ArrayList;

import android.os.AsyncTask;

import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.StudentModel;
import com.dtcs.slldt.model.TotalSMSModel;
import com.google.android.gms.internal.nu;

// TODO: Auto-generated Javadoc
/**
 * The Class SMSGatewayWebservice.
 */
public class SMSGatewayWebservice {
	
	/**
	 * Login.
	 *
	 * @param user the user
	 * @param pass the pass
	 * @param webserviceTaskListener the webservice task listener
	 */
	public static void login(final String user,final String pass,final WebserviceTaskListener<ResultModel> webserviceTaskListener){
		new AsyncTask<Void, Void, ResultModel>() {

			@Override
			protected ResultModel doInBackground(Void... params) {
				ResultModel result =  new WSLogin().login(user, pass);
				return result;
			}
			
			protected void onPostExecute(ResultModel result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(null,result);
				}
			};
			
		}.execute();
	}

	/**
	 * Gets the list student by phone number.
	 *
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list student by phone number
	 */
	public static void getListStudentByPhoneNumber(final WebserviceTaskListener<ArrayList<StudentModel>> webserviceTaskListener){
		new AsyncTask<Void, Void, ArrayList<StudentModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<StudentModel> doInBackground(Void... params) {
				ArrayList<StudentModel> ret = new WSGetStudent().getListStudentByPhoneNumber(refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<StudentModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the total new sms by phone number.
	 *
	 * @param webserviceTaskListener the webservice task listener
	 * @return the total new sms by phone number
	 */
	public static void getTotalNewSMSByPhoneNumber(final WebserviceTaskListener<TotalSMSModel> webserviceTaskListener){
		new AsyncTask<Void, Void, TotalSMSModel>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected TotalSMSModel doInBackground(Void... params) {
				TotalSMSModel ret = new WSGetSMS().getTotalNewSMSByPhoneNumber(refResult);
				return ret;
			}
			
			protected void onPostExecute(TotalSMSModel result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the total new sms by student id.
	 *
	 * @param studentId the student id
	 * @param webserviceTaskListener the webservice task listener
	 * @return the total new sms by student id
	 */
	public static void getTotalNewSMSByStudentId(final long studentId,final WebserviceTaskListener<TotalSMSModel> webserviceTaskListener){
		new AsyncTask<Void, Void, TotalSMSModel>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected TotalSMSModel doInBackground(Void... params) {
				TotalSMSModel ret = new WSGetSMS().getTotalNewSMSByStudentId(studentId, refResult);
				return ret;
			}
			
			protected void onPostExecute(TotalSMSModel result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the list new sms by phone number.
	 *
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list new sms by phone number
	 */
	public static void getListNewSmsByPhoneNumber(final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){		
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListNewSmsByPhoneNumber(refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the list new sms by student id.
	 *
	 * @param studentId the student id
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list new sms by student id
	 */
	public static void getListNewSmsByStudentId(final long studentId,final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListNewSmsByStudentId(studentId, refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the list sms by phone number.
	 *
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list sms by phone number
	 */
	public static void getListSmsByPhoneNumber(final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){		
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListSmsByPhoneNumber(refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	public static void getListSmsSended(final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){		
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListSmsSended(refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the list sms by sudent id.
	 *
	 * @param studentId the student id
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list sms by sudent id
	 */
	public static void getListSmsBySudentId(final long studentId,final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListSmsByStudentId(studentId, refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the list sms by phone number and date.
	 *
	 * @param studentId the student id
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list sms by phone number and date
	 */
	public static void getListSmsByPhoneNumberAndDate(final long studentId,final String fromDate,final String toDate,final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListSmsByPhoneNumberAndDate(fromDate, toDate, refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	/**
	 * Gets the list sms by student id and date.
	 *
	 * @param studentId the student id
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param webserviceTaskListener the webservice task listener
	 * @return the list sms by student id and date
	 */
	public static void getListSmsByStudentIdAndDate(final long studentId,final String fromDate,final String toDate,final WebserviceTaskListener<ArrayList<SMSModel>> webserviceTaskListener){
		new AsyncTask<Void, Void, ArrayList<SMSModel>>() {
			ResultModel refResult = new ResultModel(); 
			@Override
			protected ArrayList<SMSModel> doInBackground(Void... params) {
				ArrayList<SMSModel> ret = new WSGetSMS().getListSmsByStudentIdAndDate(studentId, fromDate, toDate, refResult);
				return ret;
			}
			
			protected void onPostExecute(ArrayList<SMSModel> result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(result,refResult);
				}
			};
			
		}.execute();
	}
	
	public static void sendSMS(final String targetUserId,final String msgContent,final WebserviceTaskListener<ResultModel> webserviceTaskListener){
		new AsyncTask<Void, Void, ResultModel>() {
			@Override
			protected ResultModel doInBackground(Void... params) {
				ResultModel ret = new WSGetSMS().sendSMS(targetUserId, msgContent);
				return ret;
			}
			
			protected void onPostExecute(ResultModel result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(null,result);
				}
			};
			
		}.execute();
	}
	
	public static void registerPushNotification(final String registrationId,final String deviceInfo,final int osType,final WebserviceTaskListener<ResultModel> webserviceTaskListener){
		new AsyncTask<Void, Void, ResultModel>() {
			@Override
			protected ResultModel doInBackground(Void... params) {
				ResultModel ret = new WSPushNotification().registerPushNotification(registrationId, deviceInfo, osType);
				return ret;
			}
			
			protected void onPostExecute(ResultModel result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(null,result);
				}
			};
			
		}.execute();
	}
	
	public static void testPush(final String registrationId, final long studentId, final int osType ,final WebserviceTaskListener<ResultModel> webserviceTaskListener){
		new AsyncTask<Void, Void, ResultModel>() {

			@Override
			protected ResultModel doInBackground(Void... params) {
				ResultModel result =  new WSPushNotification().TestPush(registrationId, studentId, osType);
				return result;
			}
			
			protected void onPostExecute(ResultModel result) {
				if (webserviceTaskListener != null) {
					webserviceTaskListener.onTaskComplete(null,result);
				}
			};
			
		}.execute();
	}
	
	/**
	 * The listener interface for receiving webserviceTask events.
	 * The class that is interested in processing a webserviceTask
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addWebserviceTaskListener<code> method. When
	 * the webserviceTask event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @param <T> the generic type
	 * @see WebserviceTaskEvent
	 */
	public interface WebserviceTaskListener<T>{
		
		/**
		 * On task complete.
		 *
		 * @param ob the ob
		 * @param result the result
		 */
		public void onTaskComplete(T ob,ResultModel result);
	}
}
