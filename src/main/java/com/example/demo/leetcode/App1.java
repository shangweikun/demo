package com.example.demo.leetcode;

import java.util.concurrent.atomic.AtomicReference;

public class App1 {

	class WareHouse {


		final int upper;
		final int lower;
		public String toString() {
			return "WareHouse [upper=" + upper + ", lower=" + lower + "]";
		}

		public WareHouse(int upper, int lower) {
			super();
			this.upper = upper;
			this.lower = lower;
		}

	}

	class SafeWH {

		final AtomicReference<WareHouse> ref = new AtomicReference<WareHouse>(new WareHouse(0, 0));

		public void setWareHouse(int i, int j) throws Exception {
			while (true) {
				WareHouse ow = ref.get();
				if (i > j)
					throw new Exception();
				WareHouse nw = new WareHouse(i, j);
				System.out.println("loop");
				if (ref.compareAndSet(ow, nw)) {
					System.out.println(Thread.currentThread().getName() + ref.get().toString());
					return;
				}
			}
		}

	}

	static class Test {
		public static void main(String[] args) {
			App1 app = new App1();
			SafeWH test1 = app.new SafeWH();
			Thread thread1 = new Thread(new Runnable() {

				public void run() {
					while(true) {
						try {
							test1.setWareHouse(1, 1);
							Thread.sleep(1000l);
							break;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			Thread thread2 = new Thread(new Runnable() {

				public void run() {
					while(true) {
						try {
							test1.setWareHouse(2, 2);
							Thread.sleep(1000l);
							break;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread1.start();
			thread2.start();
		}
	}

}
