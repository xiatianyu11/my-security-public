package com.my.web.async;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		new Thread(() -> {
			while(true) {
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					String orderNumber = mockQueue.getCompleteOrder();
					System.out.println("order execution result: " + orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("success");
					mockQueue.setCompleteOrder(null);
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}
