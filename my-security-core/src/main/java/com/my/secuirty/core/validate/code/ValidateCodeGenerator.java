package com.my.secuirty.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
	
	public ValidateCode generate(ServletWebRequest request);

}
