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
import com.dtcs.slldt.model.StudentModel;

import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class WSGetStudent.
 */
public class WSGetStudent extends BaseSoapService {

	/**
	 * Gets the list student by phone number.
	 *
	 * @param result the result
	 * @return the list student by phone number
	 */
	public ArrayList<StudentModel> getListStudentByPhoneNumber(ResultModel result){
		String refResponse = "";
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_GET_STUDENT_BY_PHONENUMBER);
		
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
																WSDefine.METHOD_GET_STUDENT_BY_PHONENUMBER, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_GET_STUDENT_BY_PHONENUMBER+WSDefine.AKEY)),
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
		ArrayList<StudentModel> ret = null;
		try {
			ht.call(getSoapAction(WSDefine.METHOD_GET_STUDENT_BY_PHONENUMBER), envelope);
			SoapObject respondsObject = (SoapObject)envelope.bodyIn;
			String responds = respondsObject.getPropertyAsString(0);
			result.strResult = respondsObject.getPropertyAsString(1);
			if (responds!=null) {
				ret = JsonUtil.convertArrayListFromJsonString(responds, StudentModel.class);
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
		}
	}
}
