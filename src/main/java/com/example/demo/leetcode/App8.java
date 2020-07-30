package com.example.demo.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class App8 {

	class ZeroEvenOdd {
		private int n;

		private int num = 1;
		private boolean even = false;
		private boolean odd = false;

		ReentrantLock Lock = new ReentrantLock();
		Condition condZero = Lock.newCondition();
		Condition condEven = Lock.newCondition();
		Condition condOdd = Lock.newCondition();

		public ZeroEvenOdd(int n) {
			this.n = n;
		}

		// printNumber.accept(x) outputs "x", where x is an integer.
		public void zero(IntConsumer printNumber) throws InterruptedException {
			Lock.lock();
			try {
				while (true) {
					if (!even && !odd && num <= n) {
						printNumber.accept(0);
						if (num % 2 == 1) {
							odd = true;
							condOdd.signal();
						} else {
							condEven.signal();
							even = true;
						}
					} else if (num <= n) {
						condZero.await();
					} else {
						break;
					}
				}
				if ((num+1) % 2 == 1) {
					condEven.signal();
				} else {
					condOdd.signal();
				}
				
				
			} finally {
				Lock.unlock();
			}
		}

		public void even(IntConsumer printNumber) throws InterruptedException {
			Lock.lock();
			try {
				while (true) {
					if (even && num <= n) {
						printNumber.accept(num);
						num++;
						even = false;
						condZero.signal();
					} else if (num <= n) {
						condEven.await();
					} else {
						break;
					}
				}
			} finally {
				Lock.unlock();
			}
		}

		public void odd(IntConsumer printNumber) throws InterruptedException {
			Lock.lock();
			try {
				while (true) {
					if (odd && num <= n) {
						printNumber.accept(num);
						num++;
						odd = false;
						condZero.signal();
					} else if (num <= n) {
						condOdd.await();
					} else {
						break;
					}
				}
			} finally {
				Lock.unlock();
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
			App8 app = new App8();
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
