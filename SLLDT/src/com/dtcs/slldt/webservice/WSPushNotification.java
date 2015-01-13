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

public class WSPushNotification extends BaseSoapService {

	public ResultModel TestPush(String registrationId, long studentId, int osType){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_PUSH_NOTIFICATION);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD,
													SessionStore.getInstance().getPassword(), 
													String.class);
		
		PropertyInfo registrationIdInfo = createPropertyInfo(WSDefine.PARAM_REGISTRATION_ID, 
													registrationId, 
													String.class);
		
		PropertyInfo phoneNumberInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													SessionStore.getInstance().getUserId(), 
													String.class);
		
		PropertyInfo osTypeInfo = createPropertyInfo(WSDefine.PARAM_PHONENUMBER, 
													osType, 
													Integer.class);
		
		PropertyInfo studentIdInfo = createPropertyInfo(WSDefine.PARAM_STUDENT_ID, 
													studentId, 
													Long.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_PUSH_NOTIFICATION, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_PUSH_NOTIFICATION+WSDefine.AKEY)),
																String.class);
		PropertyInfo responseInfo = createPropertyInfo(WSDefine.PARAM_RESPONSE, 
																refResponse,
																String.class);
		
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(osTypeInfo);
		soapObject.addProperty(phoneNumberInfo);
		soapObject.addProperty(registrationIdInfo);
		soapObject.addProperty(studentIdInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
//		soapObject.addProperty(responseInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ResultModel ret = new ResultModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_PUSH_NOTIFICATION), envelope);
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
}
