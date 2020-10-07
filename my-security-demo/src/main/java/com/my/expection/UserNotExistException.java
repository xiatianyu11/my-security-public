package com.my.expection;

public class UserNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8950272378850092119L;
	
	public UserNotExistException(String id) {
		super("user not exist");
		this.id=id;
	}
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
