package com.my.secuirty.core.properties;

public interface SecurityConstants {
	
	public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

	public static final String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";
	
	public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
	
	public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
	
	public static final String DEFAULT_LOGIN_PAGE_URL = "/my-signIn.html";
	
	public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
	
	public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
	
	public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
	
	public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

}
