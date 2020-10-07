package com.my.secuirty.core.properties;

public class SmsCodeProperties {
	
	private int length = 6;
	
	private int expireIn = 60;
	
	private String urls;


	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}
	
	

}
