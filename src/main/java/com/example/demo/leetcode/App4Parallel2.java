package com.example.demo.leetcode;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class App4Parallel2 {

	class FooBar {

		class Gate {
			private AtomicInteger changed = new AtomicInteger(0);
			private Semaphore sem = new Semaphore(1);

			public void requireEntrance() throws InterruptedException {
				while (changed.get() != 0)
					;
				sem.acquire();
				changed.addAndGet(1);
			}

			public void requireExit() throws InterruptedException {
				while (changed.get() != 1)
					;
				sem.acquire();
				changed.decrementAndGet();

			}

			public void release() {
				sem.release();
			}

		}

		private int n;

		public Gate gate = new Gate();

		public FooBar(int n) {
			this.n = n;
		}

		public void foo(Runnable printFoo) throws InterruptedException {

			for (int i = 0; i < n; i++) {

				gate.requireEntrance();
				// printFoo.run() outputs "foo". Do not change or remove this line.
				printFoo.run();
				gate.release();

			}
		}

		public void bar(Runnable printBar) throws InterruptedException {

			for (int i = 0; i < n; i++) {
				gate.requireExit();
				// printBar.run() outputs "bar". Do not change or remove this line.
				printBar.run();
				gate.release();
			}
		}

	}
	static class Test {
		public static void main(String[] args) throws InterruptedException {
			App4Parallel2 app = new App4Parallel2();
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
