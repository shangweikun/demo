package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App0 {

	public static void main(String[] args) throws InterruptedException {

		ThreadPoolExecutor service = new MyThreadPoolExecutor(2, 2, 60, TimeUnit.MINUTES,
				new LinkedBlockingQueue<Runnable>());
		

		ThreadLocal<AtomicInteger> iCount = new ThreadLocal<AtomicInteger>();
		iCount.set(new AtomicInteger(0));
		List<Callable<String>> lstTask = new ArrayList<Callable<String>>();
		
		for(int i =0 ; i<10;i++) {
			iCount.get().incrementAndGet();
			lstTask.add(new Callable<String>() {
				
				@Override
				public String call() throws Exception {
					while(true) {
						System.out.println(iCount.hashCode());
						System.out.println("name is "+ Thread.currentThread().getName());
						Thread.currentThread().wait(10000l);
						
					}
				}
			});
		}
		
		service.invokeAll(lstTask);
		System.out.println("Task are finashed");
		Thread.sleep(10000l);
		System.out.println(Thread.currentThread().getName());
		while(true) {
			
			service.execute(new Runnable() {
				
				@Override
				public void run() {
					int i =1/0;
				}
			});
			
			Thread.sleep(10000l);
		}
	}
 
}
