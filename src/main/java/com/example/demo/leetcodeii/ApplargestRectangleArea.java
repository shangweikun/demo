package com.example.demo.leetcodeii;

import java.util.Deque;
import java.util.LinkedList;

public class ApplargestRectangleArea {
	class Solution {
		Deque<Integer> deque = new LinkedList<>();

		public int largestRectangleArea(int[] heights) {

			if (heights.length == 0)
				return 0;

			int result = 0;
			int tmp;
			for (int i = 0; i < heights.length; i++) {
				if (deque.isEmpty() || heights[deque.peekLast()] <= heights[i])
					deque.add(i);
				else {
					tmp = calculateTmpMax(heights, i);
					result = result > tmp ? result : tmp;
					deque.add(i);
				}
			}
			tmp = calculateTmpMax(heights, heights.length);
			result = result > tmp ? result : tmp;
			return result;

		}

		private int calculateTmpMax(int[] heights, int n) {
			int result = 0;
			int tmp;
			int i;
			while (!deque.isEmpty() && (n == heights.length || heights[deque.peekLast()] > heights[n])) {
				// 难点确定‘左右扩展’的高度差
				// Example:[5,4,1,2] [2,1,5,6,2,3] [4,2,0,3,2,5]
				i = deque.pollLast();
				tmp = heights[i] * (n - (deque.isEmpty() ? 0 : deque.peekLast() + 1));
				result = result > tmp ? result : tmp;
			}
			return result;
		}
	}
}
