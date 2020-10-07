package com.my.secuirty.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.my.secuirty.core.properties.SecurityProperties;
import com.my.secuirty.core.validate.code.image.ImageCodeGenerator;
import com.my.secuirty.core.validate.code.sms.DefaultSmsCodeSender;
import com.my.secuirty.core.validate.code.sms.SmsCodeSender;

@Configuration
public class VlidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties = new SecurityProperties();
	
	@Bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator")
	public ValidateCodeGenerator imageValidateCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
	
	@Bean
	@ConditionalOnMissingBean(SmsCodeSender.class)
	public SmsCodeSender smsCodeSender() {
		SmsCodeSender smsCodeSender = new DefaultSmsCodeSender();
		return smsCodeSender;
	}


}
