package com.my.web.async;

import org.springframework.stereotype.Component;

@Component
public class MockQueue {
	private String placeOrder;
	
	private String completeOrder;

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) throws Exception {
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
		}).start();
		
	}

	public String getCompleteOrder() {
		return completeOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
	
	

}
