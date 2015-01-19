package com.dtcs.slldt.webservice;

// TODO: Auto-generated Javadoc
/**
 * The Interface WSDefine.
 */
public interface WSDefine {
	//Webservice
	/** The Constant AKEY. */
	public static final String AKEY 		= "%^()zW2axR1pUXLfdCPskMD3w27xndc=!@#";
	
	/** The Constant NAMESPACE. */
	public static final String NAMESPACE 	= "http://tempuri.org/";
	
	/** The Constant URL. */
//	public static final String URL 			= "http://27.118.28.162/SLLMobileService1/SLLService.asmx?wsdl";
	public static final String URL 			= "http://27.118.28.162:8586/smsws/SLLMobileService/SLLService.asmx?wsdl";
	
	/** The Constant TIMEOUT. */
	public static final int TIMEOUT 		= 5000;
	
	//Method
	/** The Constant METHOD_LOGIN. */
	public static final String METHOD_LOGIN 							= "Login";
	
	/** The Constant METHOD_CHANGE_PASSWORD. */
	public static final String METHOD_CHANGE_PASSWORD 					= "ChangePassword";
	
	/** The Constant METHOD_RESET_PASSWORD. */
	public static final String METHOD_RESET_PASSWORD 					= "ResetPassword";
	
	/** The Constant METHOD_GET_STUDENT_BY_PHONENUMBER. */
	public static final String METHOD_GET_STUDENT_BY_PHONENUMBER 		= "GetStudentByPhoneNumber";
	
	/** The Constant METHOD_GET_TOTAL_NEW_SMS_BY_PHONENUMBER. */
	public static final String METHOD_GET_TOTAL_NEW_SMS_BY_PHONENUMBER 	= "GetTotalNewSmsByPhoneNumber";
	
	/** The Constant METHOD_GET_TOTAL_NEW_SMS_BY_STUDENT_ID. */
	public static final String METHOD_GET_TOTAL_NEW_SMS_BY_STUDENT_ID 	= "GetTotalNewSmsByStudentId";
	
	/** The Constant METHOD_GET_NEW_SMS_BY_PHONENUMBER. */
	public static final String METHOD_GET_NEW_SMS_BY_PHONENUMBER 		= "GetNewSmsByPhoneNumber";
	
	/** The Constant METHOD_GET_NEW_SMS_BY_STUDENT_ID. */
	public static final String METHOD_GET_NEW_SMS_BY_STUDENT_ID 		= "GetNewSmsByStudentId";
	
	/** The Constant METHOD_GET_SMS_BY_PHONENUMBER. */
	public static final String METHOD_GET_SMS_BY_PHONENUMBER 			= "GetSmsByPhoneNumber";
	
	/** The Constant METHOD_GET_SMS_BY_STUDENT_ID. */
	public static final String METHOD_GET_SMS_BY_STUDENT_ID 			= "GetSmsByStudentId";
	
	/** The Constant METHOD_GET_SMS_BY_PHONENUMBER_AND_DATE. */
	public static final String METHOD_GET_SMS_BY_PHONENUMBER_AND_DATE 	= "GetSmsByPhoneNumberAndDate";
	
	/** The Constant METHOD_GET_SMS_BY_STUDENT_ID_AND_DATE. */
	public static final String METHOD_GET_SMS_BY_STUDENT_ID_AND_DATE 	= "GetSmsByStudentIdAndDate";
	
	/** The Constant METHOD_UPDATE_SMS_STATUS_BY_PHONENUMBER. */
	public static final String METHOD_UPDATE_SMS_STATUS_BY_PHONENUMBER 	= "UpdateSmsStatusByPhoneNumber";
	
	/** The Constant METHOD_UPDATE_SMS_STATUS_BY_STUDENT_ID. */
	public static final String METHOD_UPDATE_SMS_STATUS_BY_STUDENT_ID 	= "UpdateSmsStatusByStudentId";
	
	public static final String METHOD_PUSH_NOTIFICATION					= "TestPush";
	
	public static final String METHOD_SEND_SMS							= "SendSMS";
	
	public static final String METHOD_REGISTER_PUSH_NOTIFICATION		= "InsertRegistration";
	
	public static final String METHOD_GET_LIST_SMS_SENDED				= "GetListSmsSended";
	
	public static final String METHOD_GET_LIST_SMS_CHAT					= "GetListSmsChat";
	
	//Param
	/** The Constant PARAM_METHOD_IDENTIFIER. */
	public static final String PARAM_METHOD_IDENTIFIER 	= "methodIdentifier";
	
	/** The Constant PARAM_AUTHENTICATION_KEY. */
	public static final String PARAM_AUTHENTICATION_KEY = "authenticationKey";
	
	/** The Constant PARAM_USER_ID. */
	public static final String PARAM_USER_ID 			= "userId";
	
	/** The Constant PARAM_PASSWORD. */
	public static final String PARAM_PASSWORD 			= "password";
	
	/** The Constant PARAM_CHECKSUM. */
	public static final String PARAM_CHECKSUM 			= "checkSum";
	
	/** The Constant PARAM_PHONENUMBER. */
	public static final String PARAM_PHONENUMBER 		= "phoneNumber";
	
	/** The Constant PARAM_RESPONSE. */
	public static final String PARAM_RESPONSE 			= "response";
	
	/** The Constant PARAM_FROM_DATE. */
	public static final String PARAM_FROM_DATE 			= "fromDate";
	
	/** The Constant PARAM_TO_DATE. */
	public static final String PARAM_TO_DATE 			= "toDate";
	
	/** The Constant PARAM_STUDENT_ID. */
	public static final String PARAM_STUDENT_ID 		= "studentId";
	
	public static final String PARAM_REGISTRATION_ID	= "registrationId";
	
	public static final String PARAM_OS_TYPE			= "osType";
	
	public static final String PARAM_MESSAGE_CONTENT	= "msgContent";
	
	public static final String PARAM_TARGET_USER_ID		= "targetUserId";
	
	public static final String PARAM_DEVICE_INFO		= "deviceInfo";
	
	public static final String PARAM_IS_CHAT			= "isChat";
}
