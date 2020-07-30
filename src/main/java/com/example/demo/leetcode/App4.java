package com.example.demo.leetcode;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class App4 {

	class FooBar {
		private int n;

		public FooBar(int n) {
			this.n = n;
		}

		private BlockingDeque deque = new LinkedBlockingDeque(1);

		public void foo(Runnable printFoo) throws InterruptedException {

			for (int i = 0; i < n; i++) {
				deque.put(i);
				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				deque.put(i);
				deque.put(i);
			}
		}

		public void bar(Runnable printBar) throws InterruptedException {

			for (int i = 0; i < n; i++) {
				deque.take();
				deque.take();
				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();
				deque.take();
			}
		}
	}

	static class Test {
		public static void main(String[] args) throws InterruptedException {
			App4 app = new App4();
			final FooBar foo = app.new FooBar(10000);
			Thread thread0 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						foo.foo(new Runnable() {

							@Override
							public void run() {
								System.out.print("foo");
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			Thread thread1 = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						foo.bar(new Runnable() {

							@Override
							public void run() {
								System.out.println("bar");
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			thread0.start();
			thread1.start();

		}
	}
}
