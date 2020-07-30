package com.example.demo.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class App7 {
	class ZeroEvenOdd {
		private int n;

		private volatile int num = (1);

		public ZeroEvenOdd(int n) {
			this.n = n;
		}

		public Semaphore semZero = new Semaphore(1);
		public Semaphore semEven = new Semaphore(0);
		public Semaphore semOdd = new Semaphore(0);

		// printNumber.accept(x) outputs "x", where x is an integer.
		public void zero(IntConsumer printNumber) throws InterruptedException {

			while (true) {
				semZero.acquire();
				if (num == n + 1)
					break;
				printNumber.accept(0);
				if (num % 2 == 1) {
					semOdd.release();
				} else {
					semEven.release();
				}
			}
			if ((n+1)% 2 == 1) {
				semOdd.release();
			} else {
				semEven.release();
			}

		}

		public void even(IntConsumer printNumber) throws InterruptedException {
			while (num <= n) {
				semEven.acquire();
				if (num == n + 1)
					break;
				printNumber.accept(num);
				num++;
				semZero.release();
			}
		}

		public void odd(IntConsumer printNumber) throws InterruptedException {
			while (num <= n) {
				semOdd.acquire();
				if (num == n + 1)
					break;
				printNumber.accept(num);
				num++;
				semZero.release();
			}
		}
	}

	class IntConsumerTmp implements IntConsumer {

		@Override
		public void accept(int value) {
			System.out.println(value);
		}

	}

	static class Test {
		public static void main(String[] args) {
			App7 app = new App7();
			ZeroEvenOdd test = app.new ZeroEvenOdd(2);
			Thread thread0 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						test.zero(app.new IntConsumerTmp());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

			Thread thread1 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						test.odd(app.new IntConsumerTmp());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

			Thread thread2 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						test.even(app.new IntConsumerTmp());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

			thread0.start();
			thread1.start();
			thread2.start();

		}
	}
}
