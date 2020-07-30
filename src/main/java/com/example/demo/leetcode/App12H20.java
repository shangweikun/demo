package com.example.demo.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class App12H20 {

	class H2O {
		CyclicBarrier barrier = new CyclicBarrier(2);
		Semaphore semaH = new Semaphore(2);
		Semaphore semaO = new Semaphore(0);
		volatile boolean flag = false;
		final Object LOCK = new Object();

		public H2O() {
		}

		public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
			// releaseHydrogen.run() outputs "H". Do not change or remove this line.
			semaH.acquire();
			releaseHydrogen.run();
			try {
				barrier.await();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			while (true) {
				synchronized (LOCK) {
					if (flag) {
						flag = false;
						break;
					}else {			
						flag = true;
						semaO.release();
						break;
					}
				}
			}

		}

		public void oxygen(Runnable releaseOxygen) throws InterruptedException {
			// releaseOxygen.run() outputs "O". Do not change or remove this line.
			semaO.acquire();
			releaseOxygen.run();
			semaH.release();
			semaH.release();

		}
	}
}
