package com.example.demo.leetcode;

public class App3MissingPositive {

	class Solution {
		public int firstMissingPositive(int[] nums) {

			if (nums.length == 0)
				return 1;

			int[] result = new int[nums.length + 1];
			for (int i = 0; i < result.length; i++) {
				result[i] = 0;
			}
			for (int one : nums) {
				if (one <= 0 || one >= result.length)
					continue;

				result[one]++;
			}
			for (int i = 1; i < result.length; i++) {
				if (result[i] == 0)
					return i;
			}

			return result.length;
		}

		public int firstMissingPositive0(int[] nums) {

			if (nums.length == 0)
				return 1;

			int tmp;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == i)
					continue;
				/*
				 * if (nums[i] <= 0 || nums[i] >= nums.length) { nums[i] = 0; if (nums[i] ==
				 * nums.length) { if (nums[0] != nums.length)// 减少交换 nums[0] = nums.length; } }
				 */
				if (nums[i] <= 0 || nums[i] > nums.length)
					nums[i] = 0;
				else if (nums[i] == nums.length) {
					nums[i] = 0;
					if (nums[0] != nums.length)// 减少交换
						nums[0] = nums.length;
				} else {
					tmp = nums[i];// temporary待交换下标
					if (tmp < i) {
						nums[tmp] = tmp;
						nums[i] = 0;
					} else {
						nums[i] = nums[tmp] != tmp ? nums[tmp] : 0;// 目标处于正确状态
						nums[tmp] = tmp;
						i--;
					}
				}

			}

			for (int i = 1; i < nums.length; i++) {
				if (nums[i] == 0)
					return i;
			}
			if (nums[0] != nums.length)
				return nums.length;
			else
				return nums.length + 1;
		}
	}

	static class Test {
		public static void main(String[] args) {
			App3MissingPositive app = new App3MissingPositive();
			Solution sol = app.new Solution();
			System.out.println(sol.firstMissingPositive0(new int[] { 3, 3 }));
		}
	}
}
