package com.example.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App_ThreeNumber {

	class Solution {

		class ThreadGate {

			private boolean isOpen;
			private int generation;

			public synchronized void close() {
				isOpen = false;
			}

			public synchronized void open() {
				++generation;
				isOpen = true;
				notifyAll();
			}

			public synchronized void await() throws InterruptedException {
				int arrivalGeneration = generation;
				while (!isOpen && arrivalGeneration == this.generation) {
					wait();
				}
			}

		}

		class EspecialPool {

			ThreadPoolExecutor pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, queue);

			public synchronized void submit(Runnable task) {
				pool.submit(task);
			}

			public synchronized Future<?> submit(Callable<?> task) {
				return pool.submit(task);
			}

			/**
			 * 该位置使用wait()无法唤醒,存在交叉,无法保证一致,产生死等待
			 * 
			 * @throws InterruptedException
			 */
			public void await() {
				while (!queue.isEmpty() || pool.getActiveCount() != 0)
					;
			}

		}

		// 通过queue来判断并非线程安全的
		AtomicInteger countTask = new AtomicInteger(0);
		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		Object Lock = new Object();
		EspecialPool pool = new EspecialPool();
		ThreadGate gate = new ThreadGate();

		class Node {
			public Node(int num) {
				super();
				this.num = num;
				this.count = 1;
			}

			int num;
			int count;
		}

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int tempResult;
		Map<String, Boolean> note = new HashMap<>();

		public List<List<Integer>> threeSum(int[] nums) {
			ArrayList<Node> nodenums = new ArrayList<Node>();
			boolean flag;
			Arrays.sort(nums);
			if (nums.length == 0)
				return result;
			if (nums[0] > 0 || nums.length < 3)
				return result;
			for (int one : nums) {
				flag = false;
				for (Node Node : nodenums) {
					if (Node.num == one) {
						Node.count++;
						flag = true;
						continue;
					}
				}
				if (!flag) {
					nodenums.add(new Node(one));
				}

			}

			pool.submit(new Callable<Void>() {

				@Override
				public Void call() {
					countTask.addAndGet(1);
					threeSumMain(nodenums, 0, 0, 0, nodenums.size());
					countTask.addAndGet(-1);
					return null;
				}
			});
			pool.await();

			System.out.println("end");
			return result;
		}

		private void threeSumMain(ArrayList<Node> nodenums, int i, int p_n, int p_m, int size) {
			String key;
//			boolean flag = false;
			if (i == size || nodenums.get(i).num > 0) {
				return;

//				while (!queue.isEmpty() || countTask.get() !=0) // 如果将i的循环任务也提交的话,将是一个无限的死锁
			}
			if (i <= size - 1 && p_n == 0 && p_m == 0) {

				pool.submit(new Callable<Void>() {

					@Override
					public Void call() {
						countTask.addAndGet(1);
						threeSumMain(nodenums, i + 1, p_n, p_m, size);
						countTask.addAndGet(-1);
						return null;
					}
				});

			}

			if (p_n == 0 && nodenums.get(i).count == 1) {

				threeSumMain(nodenums, i, p_n + 1, p_m, size);

			} else {
				int n = i + p_n, m = size - 1 + p_m;

				if (n > m || (n == m && nodenums.get(n).count == 1))
					return;
				if (i == n && i == m && nodenums.get(n).count < 3)
					return;

				int tmp = nodenums.get(i).num + nodenums.get(n).num + nodenums.get(m).num;
				if (tmp > 0) {

					threeSumMain(nodenums, i, p_n, p_m - 1, size);

				} else if (tmp < 0) {

					threeSumMain(nodenums, i, p_n + 1, p_m, size);

//				if(!flag)
				} else {
					key = (new StringBuffer().append(nodenums.get(i).num).append(nodenums.get(n).num)
							.append(nodenums.get(m).num)).toString();
					if (note.get(key) != null && note.get(key)) {
					} else {
						synchronized (Lock) {
							note.put(key, true);
							result.add(new ArrayList<>(
									Arrays.asList(nodenums.get(i).num, nodenums.get(n).num, nodenums.get(m).num)));
						}

						threeSumMain(nodenums, i, p_n + 1, p_m - 1, size);

					}
				}
			}

		}
	}

	static class test {
		static public void main(String args[]) {
			App_ThreeNumber app = new App_ThreeNumber();
			Solution sol = app.new Solution();
			for (List<Integer> one : sol.threeSum(new int[] { 2, -2, 9, -9, 7, -7, 2, -7, 0, 3, 8, -9, -3, -9, -3, -10,
					-5, -4, -3, -9, -9, -4, 0, 3, -10, -7 })) {
				for (Integer tmp : one) {
					System.out.print(tmp + ",");
				}
				System.out.println();
			}
			System.out.println("11111");
		}
	}

}
//