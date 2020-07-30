package com.example.demo.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class App12H202 {

	class H2O {
		
		Semaphore semaH = new Semaphore(2);
		Semaphore semaO = new Semaphore(1);
		CyclicBarrier barrier = new CyclicBarrier(3,()->{
			semaH.release(2);
			semaO.release(1);
		});

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

		}

		public void oxygen(Runnable releaseOxygen) throws InterruptedException {
			// releaseOxygen.run() outputs "O". Do not change or remove this line.
			semaO.acquire();
			releaseOxygen.run();
			try {
				barrier.await();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
