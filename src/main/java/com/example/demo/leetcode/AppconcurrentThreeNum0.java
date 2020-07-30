package com.example.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AppconcurrentThreeNum0 {

	class Solution {

		LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		final ThreadPoolExecutor pool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, queue);
		final Object lOCK = new Object();
		AtomicInteger countLock = new AtomicInteger(0);

		public List<List<Integer>> threeSum(int[] nums) {
			List<List<Integer>> result = new ArrayList<List<Integer>>();
			Arrays.sort(nums);
			int i = 0;
			while (i < nums.length) {
				if (nums[i] > 0)
					break;
				if (i != 0 && nums[i - 1] == nums[i]) {
					i++;
					continue;
				}
				final int n = i + 1;
				final int m = nums.length - 1;
				final int tmpi = i;
				countLock.addAndGet(1);
				pool.submit(new Runnable() {
					int subN = n, subM = m;
					int subi = tmpi;

					@Override
					public void run() {
						while (subN < subM) {
							int tmp = nums[subi] + nums[subN] + nums[subM];
							// 放在最后面会出现影响，跳过重复n,m,但等于零的值
							// 单纯的n++;m--;在这里也会影响正确答案[-2,0,0,2,2]

							if (tmp > 0) {
								// 学一招
								while (m > 0 && nums[subM] == nums[--subM])
									;
							} else if (tmp < 0) {
								while (n < nums.length - 1 && nums[subN] == nums[++subN])
									;
							} else {
								synchronized (lOCK) {

									// 学一招
									result.add(new ArrayList<>(Arrays.asList(nums[subi], nums[subN], nums[subM])));
								}
								while (subM > 0 && nums[subM] == nums[--subM])
									;
								while (subN < nums.length - 1 && nums[subN] == nums[++subN])
									;
							}
						}
						countLock.getAndDecrement();
					}
				});

				i++;
			}

			while (countLock.get() != 0)
				;
			pool.shutdown();
			return result;
		}
	}

	static class test {
		static public void main(String args[]) {
			AppconcurrentThreeNum0 app = new AppconcurrentThreeNum0();
			Solution sol = app.new Solution();
			for (List<Integer> one : sol.threeSum(new int[] { -1, 0, 1, 2, -1, -4 })) {
				for (Integer tmp : one) {
					System.out.print(tmp + ",");
				}
				System.out.println();
			}
			System.out.println("11111");
		}
	}

}
