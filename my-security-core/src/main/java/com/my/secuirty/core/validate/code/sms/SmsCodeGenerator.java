package com.my.secuirty.core.validate.code.sms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import com.my.secuirty.core.properties.SecurityProperties;
import com.my.secuirty.core.validate.code.ValidateCode;
import com.my.secuirty.core.validate.code.ValidateCodeGenerator;
@Component
public class SmsCodeGenerator implements ValidateCodeGenerator{
	
	private SecurityProperties securityProperties = new SecurityProperties();
	
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
	
	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
