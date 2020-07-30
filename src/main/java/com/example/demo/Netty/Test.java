package com.example.demo.Netty;

public class Test {

	public static Object o = new Object();

	public static void main(String[] args) {
		Thread t = new Thread(() -> {

			try {
				synchronized (o) {
					o.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		if (!t.isAlive()) {
			t.setDaemon(false);
			t.start();
		}
	}
}
