package com.example.demo.leetcodeii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppED_ThreeNumber {

	class Solution {
		public List<List<Integer>> threeSum(int[] nums) {

			List<List<Integer>> result = new ArrayList<>();
			if (nums.length < 3)
				return result;

			Arrays.parallelSort(nums);
			if (nums[0] > 0 || nums[nums.length - 1] < 0)
				return result;

			for (int i = 0; i < nums.length;) {
				if (nums[i] > 0 || i == nums.length - 1)
					break;
				int n = i + 1, m = nums.length - 1;
				while (n < m) {
					if (nums[i] + nums[n] + nums[m] < 0)
						while (n < nums.length - 1 && nums[n] == nums[++n])
							;
					else if (nums[i] + nums[n] + nums[m] > 0)
						while (m > 0 && nums[m] == nums[--m])
							;
					else {
						List<Integer> lst = new ArrayList<Integer>(3);
						lst.add(nums[i]);
						lst.add(nums[n]);
						lst.add(nums[m]);
						result.add(lst);
						while (n < nums.length - 1 && nums[n] == nums[++n])
							;
						while (m > 0 && nums[m] == nums[--m])
							;

					}
				}
				while (i < nums.length - 1 && nums[i] == nums[++i])
					;
			}

			return result;

		}
	}
}
