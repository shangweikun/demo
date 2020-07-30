package com.example.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class App2 {

	public static void main(String[] args) {
		final ReentrantLock lock = new ReentrantLock();

		Thread t = new Thread(new Runnable() {

			public void run() {
				boolean flag = true;//标志很重要,否则会 ’在finally中因无法获得锁,而unlock‘ 报错
				try {
					if (lock.tryLock(10l, TimeUnit.SECONDS)) {
						System.out.println("I have come out the lock.");
					}
				} catch (Exception e) {
					e.printStackTrace();
					flag = false;
				} finally {
					if(!flag) {
						System.out.println("I'm success of getting lock.");
						lock.unlock();
					}else {
						System.out.println("I'm failure of getting lock.");	
					}
				}
			}
		});


		try {

			if (lock.tryLock()) {
				t.start();
				System.out.println("Main has got the lock.");
				while (true) {
					
				}
			}
		} finally {
			lock.unlock();
		}
	}
}
