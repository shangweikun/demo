package com.example.demo;

import com.seed.world.HelloWorldSeed.Speaker;

public class App1 {

	public static void main(String[] args) {
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println("Hello world");
					try {
						Thread.sleep(10000l);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		Speaker person = new Speaker();
		while(true) {
			person.say();
		}
		
	}
	
}
