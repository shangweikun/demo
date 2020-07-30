package com.example.demo.leetcode;

public class App6 {

	public static void main(String[] args) throws InterruptedException {

//		Thread thread = Thread.startVirtualThread(() -> System.out.println("Hello"));
//		thread.join();

		Thread thread0 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (!Thread.currentThread().isInterrupted()) {
					try {
						Thread.sleep(10000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
				System.err.println("END");
			}
		});
		thread0.start();
		Thread.sleep(2000l);
		thread0.interrupt();

	}

}
