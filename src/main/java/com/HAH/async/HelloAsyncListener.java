package com.HAH.async;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class HelloAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("Async Complete Process.");
		
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		System.out.println("Async Timeout Process.");
		event.getAsyncContext().complete();
		
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		System.out.println("Async Error Process.");
		event.getAsyncContext().dispatch("error.jsp");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		System.out.println("Async Start Process.");
	}

}
