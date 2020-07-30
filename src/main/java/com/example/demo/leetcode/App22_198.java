package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;

public class App22_198 {
	class Solution {
		/**
		 * f(n) = Max(f(n-1),f(n-2)+nums[n]);
		 * 
		 * @param nums
		 * @return
		 */
		public int rob(int[] nums) {

			if (nums.length == 0)
				return 0;
			if (nums.length == 1)
				return nums[0];
			int result = Math.max(nums[0], nums[1]);
			nums[1] = result;
			if (nums.length == 2)
				return result;
			for (int i = 2; i < nums.length; i++) {
				nums[i] = Math.max(nums[i - 1], nums[i] + nums[i - 2]);
				result = result > nums[i] ? result : nums[i];
			}

			return result;
		}

		/**
		 * parallel available
		 */

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		public int rob2(int[] nums) {
			int result = 0;
			int wealth = 0;
			result = robMain(nums, 0, wealth);

			return result;
		}

		private int robMain(int[] nums, int i, int wealth) {

			if (i >= nums.length)
				return wealth;

			int hasI = 0;
			int noHasI = 0;
			if (map.containsKey(i)) {
				// map 用于记录到当前房间时,的最大值
				if (map.get(i) >= wealth) // 全0的坑
					return 0;
			}
			map.put(i, wealth);

			hasI = robMain(nums, i + 2, wealth + nums[i]);
			noHasI = robMain(nums, i + 1, wealth);

			return hasI > noHasI ? hasI : noHasI;
		}

	}

	public static void main(String[] args) {
		App22_198 app = new App22_198();
		Solution sol = app.new Solution();
		System.out.println(sol.rob2(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0 }));
	}
}
