package com.dtcs.slldt.webservice;

import java.io.IOException;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.dtcs.slldt.model.ResultModel;

import android.util.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class WSLogin.
 */
public class WSLogin extends BaseSoapService{

	/**
	 * Login.
	 *
	 * @param phone the phone
	 * @param pass the pass
	 * @return the result model
	 */
	public ResultModel login(String phone,String pass){
		SoapObject soapObject = createSoapObject(WSDefine.METHOD_LOGIN);
		
		PropertyInfo userIdInfo = createPropertyInfo(WSDefine.PARAM_USER_ID, 
													phone, 
													String.class);
		
		PropertyInfo passInfo = createPropertyInfo(WSDefine.PARAM_PASSWORD, 
													pass, 
													String.class);
		
		PropertyInfo methodIdentifierInfo = createPropertyInfo(WSDefine.PARAM_METHOD_IDENTIFIER, 
																WSDefine.METHOD_LOGIN, 
																String.class);
		
		PropertyInfo authenticationInfo = createPropertyInfo(WSDefine.PARAM_AUTHENTICATION_KEY, 
																WSDefine.AKEY, 
																String.class);
		PropertyInfo checksumInfo = createPropertyInfo(WSDefine.PARAM_CHECKSUM, 
																MD5.encrypt((WSDefine.METHOD_LOGIN+WSDefine.AKEY)),
																String.class);
		soapObject.addProperty(userIdInfo);
		soapObject.addProperty(passInfo);
		soapObject.addProperty(methodIdentifierInfo);
		soapObject.addProperty(authenticationInfo);
		soapObject.addProperty(checksumInfo);
		
		SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(soapObject);
		HttpTransportSE ht = getHttpTransportSE();
		ResultModel ret = new ResultModel();
		try {
			ht.call(getSoapAction(WSDefine.METHOD_LOGIN), envelope);
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
