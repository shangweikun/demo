package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class App18 {

	class Solution {
		class bref<T> {
			T value;

			public bref(T test) {
				this.value = test;
			}

			public T getValue() {
				return value;
			}

			public void setValue(T value) {
				this.value = value;
			}

		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		bref<Integer> brf = new bref<Integer>(1);

		public int climbStairs(int n) {

			if (map.containsKey(n))
				return map.get(n);
			if (n <= 0)
				return 0;
			if (n == 1)
				return 1;
			if (n == 2)
				return 2;

			int left = climbStairs(n - 1);
			int right = climbStairs(n - 2);
			map.put(n, left + right);

			return left + right;

		}

		public Executor exe = Executors.newCachedThreadPool();
		Map<Integer, Future<Integer>> map0 = new ConcurrentHashMap<Integer, Future<Integer>>();

		/**
		 * Failure
		 * 
		 * @param n
		 * @return
		 * @throws InterruptedException
		 * @throws ExecutionException
		 */
		public int climbStairsForParallel(final int n) throws InterruptedException, ExecutionException {

			if (n <= 0)
				return 0;
			if (n == 1)
				return 1;
			if (n == 2)
				return 2;
			if (map0.containsKey(n))
				return map0.get(n).get();

			map0.putIfAbsent(n - 1, ((ThreadPoolExecutor) exe).submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return climbStairsForParallel(n - 1);
				}

			}));
			map0.putIfAbsent(n - 2, ((ThreadPoolExecutor) exe).submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					return climbStairsForParallel(n - 2);
				}

			}));

			return map0.get(n - 1).get() + map0.get(n - 2).get();

		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		App18 app = new App18();
		Solution sol = app.new Solution();
		System.out.println(sol.climbStairsForParallel(5));
		((ThreadPoolExecutor) sol.exe).shutdown();
	}

}
