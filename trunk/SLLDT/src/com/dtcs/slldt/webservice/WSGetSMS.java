package com.dtcs.slldt.webservice;

import java.io.IOException;
import java.util.ArrayList;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.dtcs.slldt.common.SessionStore;
import com.dtcs.slldt.model.ResultModel;
import com.dtcs.slldt.model.SMSModel;
import com.dtcs.slldt.model.TotalSMSModel;

// TODO: Auto-generated Javadoc
/**
 * The Class WSGetSMS.
 */
public class WSGetSMS extends BaseSoapService {

	/**
	 * Gets the total new sms by phone number.
	 *
	 * @param result the result
	 * @return the total new sms by phone number
	 */
	public TotalSMSModel getTotalNewSMSByPhoneNumber(ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_PHONENUMBER);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_PHONENUMBER, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_PHONENUMBER+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		TotalSMSModel ret = new TotalSMSModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_PHONENUMBER), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret.count = Integer.valueOf(responds);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the total new sms by student id.
	 *
	 * @param studentId the student id
	 * @param result the result
	 * @return the total new sms by student id
	 */
	public TotalSMSModel getTotalNewSMSByStudentId(long studentId,ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_STUDENT_ID);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo studentIdInfo = createPropertyInfo(WSDefine.PARAM_STUDENT_ID, 
													studentId, 
													Long.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_STUDENT_ID, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_STUDENT_ID+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(studentIdInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		TotalSMSModel ret = new TotalSMSModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_TOTAL_NEW_SMS_BY_STUDENT_ID), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret.count = Integer.valueOf(responds);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the list new sms by phone number.
	 *
	 * @param result the result
	 * @return the list new sms by phone number
	 */
	public ArrayList<SMSModel> getListNewSmsByPhoneNumber(ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_NEW_SMS_BY_PHONENUMBER);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_NEW_SMS_BY_PHONENUMBER, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_NEW_SMS_BY_PHONENUMBER+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_NEW_SMS_BY_PHONENUMBER), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the list new sms by student id.
	 *
	 * @param studentId the student id
	 * @param result the result
	 * @return the list new sms by student id
	 */
	public ArrayList<SMSModel> getListNewSmsByStudentId(long studentId,ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_NEW_SMS_BY_STUDENT_ID);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo studentIdInfo = createPropertyInfo(WSDefine.PARAM_STUDENT_ID, 
													studentId, 
													Long.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_NEW_SMS_BY_STUDENT_ID, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_NEW_SMS_BY_STUDENT_ID+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(studentIdInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_NEW_SMS_BY_STUDENT_ID), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the list sms by phone number.
	 *
	 * @param result the result
	 * @return the list sms by phone number
	 */
	public ArrayList<SMSModel> getListSmsByPhoneNumber(ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_SMS_BY_PHONENUMBER);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_SMS_BY_PHONENUMBER, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_SMS_BY_PHONENUMBER+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_SMS_BY_PHONENUMBER), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the list sms by student id.
	 *
	 * @param studentId the student id
	 * @param result the result
	 * @return the list sms by student id
	 */
	public ArrayList<SMSModel> getListSmsByStudentId(long studentId,ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_SMS_BY_STUDENT_ID);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo studentIdInfo = createPropertyInfo(WSDefine.PARAM_STUDENT_ID, 
													studentId, 
													Long.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_SMS_BY_STUDENT_ID, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_SMS_BY_STUDENT_ID+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(studentIdInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_SMS_BY_STUDENT_ID), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the list sms by phone number and date.
	 *
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param result the result
	 * @return the list sms by phone number and date
	 */
	public ArrayList<SMSModel> getListSmsByPhoneNumberAndDate(String fromDate,String toDate,ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_SMS_BY_PHONENUMBER_AND_DATE);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo fromDateInfo = createPropertyInfo(WSDefine.PARAM_FROM_DATE,
													fromDate, 
													String.class);
		
		PropertyInfo toDateInfo = createPropertyInfo(WSDefine.PARAM_TO_DATE,
													toDate, 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_SMS_BY_PHONENUMBER_AND_DATE, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_SMS_BY_PHONENUMBER_AND_DATE+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(fromDateInfo);
		soapObject.addProperty(toDateInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_SMS_BY_PHONENUMBER_AND_DATE), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets the list sms by student id and date.
	 *
	 * @param studentId the student id
	 * @param fromDate the from date
	 * @param toDate the to date
	 * @param result the result
	 * @return the list sms by student id and date
	 */
	public ArrayList<SMSModel> getListSmsByStudentIdAndDate(long studentId,String fromDate,String toDate,ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_SMS_BY_STUDENT_ID_AND_DATE);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo studentIdInfo = createPropertyInfo(WSDefine.PARAM_STUDENT_ID, 
													studentId, 
													Long.class);
		
		PropertyInfo fromDateInfo = createPropertyInfo(WSDefine.PARAM_FROM_DATE,
													fromDate, 
													String.class);
		
		PropertyInfo toDateInfo = createPropertyInfo(WSDefine.PARAM_TO_DATE,
													toDate, 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_SMS_BY_STUDENT_ID_AND_DATE, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_SMS_BY_STUDENT_ID_AND_DATE+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(studentIdInfo);
		soapObject.addProperty(fromDateInfo);
		soapObject.addProperty(toDateInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_SMS_BY_STUDENT_ID_AND_DATE), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultModel sendSMS(String targetUserId,String msgContent){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_SEND_SMS);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo targetUserIdInfo = createPropertyInfo(WSDefine.PARAM_TARGET_USER_ID,
													targetUserId, 
													String.class);
		
		PropertyInfo isChatInfo = createPropertyInfo(WSDefine.PARAM_IS_CHAT, 
													true, 
													boolean.class);
		
		PropertyInfo msgContentInfo = createPropertyInfo(WSDefine.PARAM_MESSAGE_CONTENT,
													msgContent, 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_SEND_SMS, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_SEND_SMS+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(msgContentInfo);
		soapObject.addProperty(targetUserIdInfo);
		soapObject.addProperty(isChatInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
//		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ResultModel ret = new ResultModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_SEND_SMS), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			if (responds!=null) {
				ret.strResult = responds;
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SMSModel> getListSmsSended(ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_LIST_SMS_SENDED);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo isChatInfo = createPropertyInfo(WSDefine.PARAM_IS_CHAT, 
													2, 
													Integer.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_LIST_SMS_SENDED, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_LIST_SMS_SENDED+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(isChatInfo);
//		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
//		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_LIST_SMS_SENDED), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<SMSModel> getListSmsChat(ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_LIST_SMS_CHAT);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo isChatInfo = createPropertyInfo(WSDefine.PARAM_IS_CHAT, 
													2, 
													Integer.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_GET_LIST_SMS_CHAT, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_LIST_SMS_CHAT+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
//		soapObject.addProperty(isChatInfo);
//		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ArrayList<SMSModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_LIST_SMS_CHAT), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, SMSModel.class);
			}
			return ret;
		} catch (HttpResponseException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
