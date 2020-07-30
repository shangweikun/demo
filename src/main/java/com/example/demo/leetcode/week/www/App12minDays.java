package com.example.demo.leetcode.week.www;

public class App12minDays {
	
	class Solution {
	    public int minDays(int[] bloomDay, int m, int k) {
	    	/**
	    	 * 二分查找bloomDay中的数字 1～1,000,000,000
	    	 */
	    	return 0;
	    }
	}
	class Solution0 {
		int result = Integer.MAX_VALUE;

		public int minDays(int[] bloomDay, int m, int k) {
			if ((long) bloomDay.length < (long) m * k)
				return -1;
			minDayProcess(bloomDay, m, k, 0, Integer.MIN_VALUE);
			return this.result;
		}

		private void minDayProcess(int[] bloomDay, int m, int k, int i, int result) {
			if ((long) (bloomDay.length - i) < (long) m * k || i > bloomDay.length)
				return;
			if (m == 0) {
				this.result = this.result > result ? result : this.result;
				return;
			}

			int tmp;
			for (int j = i; j < bloomDay.length; j++) {
				minDayProcess(bloomDay, m, k, j + 1, result);
				tmp = calResult(bloomDay, j, k - 1);
				minDayProcess(bloomDay, m - 1, k, j + k, result < tmp ? tmp : result);
			}
		}

		private int calResult(int[] bloomDay, int j, int k) {
			if (k < 0 || j + k >= bloomDay.length)
				return Integer.MIN_VALUE;
			int tmp;
			tmp = calResult(bloomDay, j, k - 1);
			return bloomDay[j + k] < tmp ? tmp : bloomDay[j + k];
		}
	}
}
