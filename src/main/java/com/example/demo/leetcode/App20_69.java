package com.example.demo.leetcode;

public class App20_69 {

	class Solution {
		public int mySqrt(int x) {

			return sqrt(0, x, x);
		}

		public int sqrt(int left, int right, int x) {

			if (left == right) {
				if (left * right > x)
					return left - 1;
				else
					return left;
			}
			int mid = left + ((right - left) / 2);
			long result = mid * mid;

			if (result > (long)x)
				return sqrt(left, mid, x);
			else if (result < (long)x)
				return sqrt(mid + 1, right, x);
			else
				return mid;

		}
	}
}
