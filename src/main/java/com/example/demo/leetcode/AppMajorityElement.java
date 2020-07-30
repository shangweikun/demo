package com.example.demo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class AppMajorityElement {

	class Solution {

		public int majorityElement(int[] nums) {
			if (nums.length == 1)
				return nums[0];
			int left = 0, right = nums.length - 1;
			int tmpNumber = 0;
			int tmp = Integer.MIN_VALUE;
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			/**
			 * 奇偶指针类似
			 */
			while (left <= right) {

				if (map.get(nums[left]) == null) {
					map.put(nums[left], 1);
				} else {
					map.put(nums[left], map.get(nums[left]) + 1);
				}

				if (left != right) {

					if (map.get(nums[right]) == null) {
						map.put(nums[right], 1);
					} else {
						map.put(nums[right], map.get(nums[right]) + 1);
					}
				}
				if (map.get(nums[right]) < map.get(nums[left])) {

					if (tmp < map.get(nums[left])) {
						tmp = map.get(nums[left]);
						tmpNumber = nums[left];
					}
				} else {
					if (tmp < map.get(nums[right])) {
						tmp = map.get(nums[right]);
						tmpNumber = nums[right];
					}
				}

				left++;
				right--;
			}
			for (Integer key : map.keySet()) {
				if (tmp < map.get(key)) {
					tmp = map.get(key);
					tmpNumber = key;
				}
			}

			return tmpNumber;
		}

	}

	class Solution0 {

		public int majorityElement(int[] nums) {
			if (nums.length == 1)
				return nums[0];
			final int odd = 0, even = 1;
			int tmpNumber = 0;
			int tmp = Integer.MIN_VALUE;
			CountDownLatch latch = new CountDownLatch(2);
			ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>();

			Thread thread0 = new Thread(new Runnable() {

				@Override
				public void run() {

					int tmp = odd;
					while (tmp < nums.length) {
						if (map.putIfAbsent(nums[tmp], 0) != null) {
							map.put(nums[tmp], map.get(nums[tmp]) + 1);
						}
						tmp += 2;
					}
					latch.countDown();

				}
			});
			Thread thread1 = new Thread(new Runnable() {

				@Override
				public void run() {

					int tmp = even;
					while (tmp < nums.length) {
						if (map.putIfAbsent(nums[tmp], 0) != null) {
							map.put(nums[tmp], map.get(nums[tmp]) + 1);
						}
						tmp += 2;
					}
					latch.countDown();

				}
			});
			thread0.start();
			thread1.start();

			try {
				latch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (Integer key : map.keySet()) {
				if (tmp < map.get(key)) {
					tmp = map.get(key);
					tmpNumber = key;
				}
			}

			return tmpNumber;
		}

	}

	class Solution1 {
		public int majorityElement(int[] nums) {
			Arrays.parallelSort(nums);
			return nums[nums.length >> 1];
		}
	}

	class Solution2 {
		public int majorityElement(int[] nums) {
			int result = nums[0];
			int count = 1;
			for (int i = 1; i < nums.length; i++) {
				if (count == 0) {
					result = nums[i];
					count = 1;
					continue;
				}
				if (result == nums[i])
					count++;
				else
					count--;
			}
			return result;

		}
	}

	static class test {
		public static void main(String[] args) {
			AppMajorityElement app = new AppMajorityElement();
			Solution2 sol = app.new Solution2();
			System.err.println(sol.majorityElement(new int[] { 2, 2, 1, 1, 1, 2, 2 }));
		}
	}

}
