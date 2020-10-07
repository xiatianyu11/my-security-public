package com.my.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@RequestMapping("/feferorder")
	public DeferredResult<String> deferorder() throws Exception {
		System.out.println("main thread start");
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		System.out.println("main thread end");
		return result;
	}
	
	@RequestMapping("/order")
	public Callable<String> order() {
		System.out.println("main thread start");
		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("sub thread start");
				Thread.sleep(1000);
				System.out.println("sub thread end");
				return "success";
			}
			
		};
		System.out.println("main thread end");
		return result;
	}
}
