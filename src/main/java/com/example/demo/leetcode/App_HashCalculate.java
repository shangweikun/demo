package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

public class App_HashCalculate {
	/* 扰动函数test */

	static class ByRef<T> {
		public T value;

		ByRef(T value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {

		final AtomicReference<Boolean> subFlag = new AtomicReference<Boolean>(false);

		Thread thread = new Thread(() -> {
			int count = 0;
			Map<Integer, ByRef<Integer>> map = new HashMap<>();
			ByRef<Integer> tmp;
			Object o;
			while (true) {

				count = 0;
				map.clear();
				while (true) {
					o = new Object();
					count++;
//				System.out.printf("count=[%d]", count);
//				System.out.println();
//				System.out.println(o.hashCode());
//				System.out.println(o.hashCode() ^ (o.hashCode() >>> 16));
					/* 平均仍无法较为平均 */
					if ((tmp = map.putIfAbsent((o.hashCode() ^ (o.hashCode() >>> 16)) % 20,
							new ByRef<Integer>(1))) != null)
						tmp.value++;
					/*
					 * 1000000 - 999768
					 */
					if (count == 1000) {
						try {
							Thread.sleep(1000l);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
				}

				System.out.println(map.keySet().size());
				int max = Integer.MIN_VALUE;
				int min = Integer.MAX_VALUE;

				for (Integer one : map.keySet()) {
					max = Math.max(max, map.get(one).value);
					min = Math.min(min, map.get(one).value);
				}
				System.out.println("Max:" + max + ",Min:" + min + "---minus" + (max - min));
				if (subFlag.get())
					break;
			}
			System.out.println("---------end-------------");
		});
		thread.start();
		try (Scanner scan = new Scanner(System.in)) {

			while (!scan.hasNext())
				;
			System.out.println("------test-----");
			subFlag.set(true);
			System.out.println(scan.nextInt());
		}

	}
}
