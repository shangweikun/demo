package com.example.demo.leetcode;

import java.util.ArrayList;
import java.util.List;

public class AppMajorityElementII {

	class Solution {
		/* *
		 * 错误的摩尔选举即 example
		 * */
		public List<Integer> majorityElement(int[] nums) {

			List<Integer> result = new ArrayList<Integer>();
			int tmp0 = 0, tmp1 = 0;
			int count0 = 0, count1 = 0;

			for (int i = 0; i < nums.length; i++) {

				if (tmp0 == nums[i]) {
					count0++;
					continue;
				}

				if (tmp1 == nums[i]) {
					count1++;
					continue;
				}

				if (count0 > 0 && count1 > 0) {
					count1--;
					count0--;
				}
				if (count0 == 0) {
					tmp0 = nums[i];
					count0++;
				} else if (count1 == 0) {
					tmp1 = nums[i];
					count1++;
				}

			}
			count0 = 0;
			count1 = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == tmp0) {
					count0++;
				} else if (nums[i] == tmp1) {
					count1++;
				}
			}
			if (count0 > nums.length / 3)
				result.add(tmp0);
			if (count1 > nums.length / 3)
				result.add(tmp1);
			return result;
		}
	}

	static class test {
		public static void main(String[] args) {
			AppMajorityElementII app = new AppMajorityElementII();
			Solution sol = app.new Solution();
			System.err.println(sol.majorityElement(new int[] { 1, 1, 1, 2, 3, 4, 5, 6 }));
		}
	}
}
