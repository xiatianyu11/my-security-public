package com.my.secuirty.core.validate.code;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {
	
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
	
	void create(ServletWebRequest request) throws Exception;
	
	void validate(ServletWebRequest servletWebRequest) throws ServletRequestBindingException;

}
