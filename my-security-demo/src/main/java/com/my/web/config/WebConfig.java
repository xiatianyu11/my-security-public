
package com.my.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.my.web.filter.TimeFilter;
import com.my.web.intercepter.TimeIntercepter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeIntercepter timeIntecepter;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeIntecepter);
	}

	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registerationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registerationBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registerationBean.setUrlPatterns(urls);
		return registerationBean;
	}
	
	

}
