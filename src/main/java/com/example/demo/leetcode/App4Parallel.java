package com.example.demo.leetcode;

import java.util.concurrent.atomic.AtomicInteger;

public class App4Parallel {

	class FooBar {

		class Gate1 {

			private volatile boolean flag0 = true;// 改为boolean也可以

			/**
			 * 不使用volatile 即会导致死锁问题
			 */

			public void requireEntrance() {
				while (!flag0)
					;
			}

			public void requireExit() {
				while (flag0)
					;
			}

			public void release() {
				flag0 = !flag0;
			}
		}

		class Gate0 {

			private volatile boolean flag0 = true;// 无锁保证,必须使用可见性保证一致
			private boolean flag = true; // 用于保证wait()和notifyALl() 匹配一致 synchronized保证

			public AtomicInteger i = new AtomicInteger(0);

			/**
			 * 加入wait,出现死锁 解决方案:见注释
			 * 
			 * @throws InterruptedException
			 */
			public void requireEntrance() throws InterruptedException {
				while (!flag0) {
					synchronized (this) {
//						if (flag0) {
//							i.addAndGet(1);
//							break;
//						}
						flag = false;

						wait();
					}
				}
			}

			public void requireExit() throws InterruptedException {
				while (flag0) {
					synchronized (this) {
//						if (!flag0) {
//							i.addAndGet(1);
//							break;
//						}
						flag = false;
						wait();
					}
				}
			}

			public void release() {
				flag0 = !flag0;
				synchronized (this) {
					while (!flag) {
						notifyAll();
						flag = true;
					}
				}
			}
		}

		private int n;

		public final Gate0 gate = new Gate0();

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
			App4Parallel app = new App4Parallel();
			final FooBar foo = app.new FooBar(20000);
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

			while (thread1.isDaemon() || thread0.isAlive()) {

			}

//			System.err.println(foo.gate.i.get());

		}
	}

}
