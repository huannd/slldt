package com.dtcs.slldt.webservice;

import java.net.Proxy;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseSoapService.
 */
public class BaseSoapService {

	
	/**
	 * Creates the soap object.
	 *
	 * @param name the name
	 * @return the soap object
	 */
	public SoapObject createSoapObject(String name){
		SoapObject soapObject = new SoapObject(WSDefine.NAMESPACE, name);
		return soapObject;
	}
	
	/**
	 * Creates the property info.
	 *
	 * @param name the name
	 * @param value the value
	 * @param type the type
	 * @return the property info
	 */
	public PropertyInfo createPropertyInfo(String name,String value,Class<?> type){
		PropertyInfo info = new PropertyInfo();
		info.setName(name);
		info.setValue(value);
		info.setType(type);
		return info;
	}
	
	/**
	 * Creates the property info.
	 *
	 * @param name the name
	 * @param value the value
	 * @param type the type
	 * @return the property info
	 */
	public PropertyInfo createPropertyInfo(String name,long value,Class<?> type){
		PropertyInfo info = new PropertyInfo();
		info.setName(name);
		info.setValue(value);
		info.setType(type);
		return info;
	}
	
	/**
	 * Gets the soap serialization envelope.
	 *
	 * @param request the request
	 * @return the soap serialization envelope
	 */
	public SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
	    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
	    envelope.dotNet = true;
	    envelope.implicitTypes = true;
	    envelope.setAddAdornments(false);
	    envelope.setOutputSoapObject(request);
	 
	    return envelope;
	}
	
	/**
	 * Gets the http transport se.
	 *
	 * @return the http transport se
	 */
	public HttpTransportSE getHttpTransportSE() {
	    HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,WSDefine.URL,WSDefine.TIMEOUT);
//	    ht.debug = true;
//	    ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
	    return ht;
	}
	
	/**
	 * Gets the soap action.
	 *
	 * @param methodName the method name
	 * @return the soap action
	 */
	public String getSoapAction(String methodName){
		return WSDefine.NAMESPACE+methodName;
	}
}
