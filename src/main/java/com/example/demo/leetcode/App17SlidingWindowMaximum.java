package com.example.demo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App17SlidingWindowMaximum {

	class Solution {
		public int[] maxSlidingWindow(int[] nums, int k) {

			if (nums.length == 0)
				return new int[] { 0 };

			int max = calculate(nums, 0, k);

			if (nums.length == k)
				return new int[] { max };

			List<Integer> result = new ArrayList<Integer>();
			result.add(max);

			for (int i = k, j = 0; i < nums.length; i++, j++) {
				if (nums[j] == max)
					max = calculate(nums, j + 1, i);
				if (max < nums[i])
					max = nums[i];
				result.add(max);
			}

			int[] r = new int[nums.length - k + 1];

			int i = 0;
			for (Integer one : result) {
				r[i] = one;
				i++;
			}

			return r;

		}

		private int calculate(int[] nums, int i, int k) {
			int[] tmp = Arrays.copyOfRange(nums, i, k);
			Arrays.parallelSort(tmp);
			return tmp.length != 0 ? tmp[tmp.length - 1] : Integer.MIN_VALUE;
		}
	}

	public static void main(String[] args) {
		int[] lst = new int[] { 1, -1 };
		Arrays.copyOfRange(lst, 1, 1);

		App17SlidingWindowMaximum app = new App17SlidingWindowMaximum();
		Solution sol = app.new Solution();
		for (int one : sol.maxSlidingWindow(lst, 1)) {
			System.out.println(one);
		}
	}
}
