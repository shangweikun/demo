package com.example.demo.leetcode.week.www;

import java.util.Comparator;
import java.util.PriorityQueue;

public class App00MaxTwoNumber {

	class Solution {
		public int maxProduct(int[] nums) {

			PriorityQueue<Integer> que = new PriorityQueue<Integer>(new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});

			if (nums.length == 2)
				return (nums[0] - 1) * (nums[1] - 1);
			for (int one : nums) {
				que.add(one);
			}
			return (que.poll() - 1) * (que.poll() - 1);

		}
	}

	public static void main(String[] args) {
		int i = 1;
		int j = 2;
		PriorityQueue<Integer> que = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		que.add(j);
		que.add(i);
		System.out.println(que.toString());
	}
}
