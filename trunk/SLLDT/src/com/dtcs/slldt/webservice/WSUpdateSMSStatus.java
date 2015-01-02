package com.dtcs.slldt.webservice;

import java.io.IOException;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.dtcs.slldt.common.SessionStore;
import com.dtcs.slldt.model.ResultModel;

// TODO: Auto-generated Javadoc
/**
 * The Class WSUpdateSMSStatus.
 */
public class WSUpdateSMSStatus extends BaseSoapService{

	/**
	 * Update sms status by phone number.
	 *
	 * @return the result model
	 */
	public ResultModel updateSmsStatusByPhoneNumber(){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_UPDATE_SMS_STATUS_BY_PHONENUMBER);
		
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
																WSDefine.METHOD_UPDATE_SMS_STATUS_BY_PHONENUMBER, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_UPDATE_SMS_STATUS_BY_PHONENUMBER+WSDefine.AKEY)),
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
		ResultModel ret = new ResultModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_UPDATE_SMS_STATUS_BY_PHONENUMBER), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			ret.strResult = respondsObject.getPropertyAsString(0);
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
	 * Update sms status by student id.
	 *
	 * @param studentId the student id
	 * @return the result model
	 */
	public ResultModel updateSmsStatusByStudentId(long studentId){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_UPDATE_SMS_STATUS_BY_STUDENT_ID);
		
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
																WSDefine.METHOD_UPDATE_SMS_STATUS_BY_STUDENT_ID, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_UPDATE_SMS_STATUS_BY_STUDENT_ID+WSDefine.AKEY)),
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
		ResultModel ret = new ResultModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_UPDATE_SMS_STATUS_BY_STUDENT_ID), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			ret.strResult = respondsObject.getPropertyAsString(0);
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
