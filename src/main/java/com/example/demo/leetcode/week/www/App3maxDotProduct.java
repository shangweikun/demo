package com.example.demo.leetcode.week.www;

public class App3maxDotProduct {

	class Solution {
		public int maxDotProduct(int[] nums1, int[] nums2) {

			int[][] map = new int[nums1.length][nums2.length];

			int result = Integer.MIN_VALUE;

			map[0][0] = nums1[0] * nums2[0];

			for (int i = 1; i < nums2.length; i++) {
				map[0][i] = nums1[0] * nums2[i];
				result = result < map[0][i] ? map[0][i] : result;

			}
			for (int i = 1; i < nums1.length; i++) {
				map[i][0] = nums1[i] * nums2[0];
				result = result < map[i][0] ? map[i][0] : result;
			}

			for (int i = 1; i < nums1.length; i++) {
				for (int j = 1; j < nums2.length; j++) {
					map[i][j] = nums1[i] * nums2[j];
					map[i][j] += Math.max(
							Math.max(map[i - 1][j] - nums1[i - 1] * nums2[j], map[i][j - 1] - nums1[i] * nums2[j - 1]),
							Math.max(map[i - 1][j - 1] - nums1[i - 1] * nums2[j - 1], map[i-1][j-1]));
					result = result < map[i][j] ? map[i][j] : result;
				}
			}
			return result;
		}
	}

	int f[][];
	int a1[];
	int a2[];
	int maxPoint;

	public int maxDotProduct(int[] nums1, int[] nums2) {
		f = new int[nums1.length][nums2.length];
		for (int i = 0; i < nums1.length; i++) {
			f[i] = new int[nums2.length];
		}
		a1 = nums1;
		a2 = nums2;
		maxPoint = nums1[0] * nums2[0];
		f[0][0] = nums1[0] * nums2[0];

		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				int curNum = a1[i] * a2[j];
				f[i][j] = curNum;
				if (((i - 1) >= 0) && ((j - 1) >= 0)) {
					curNum = f[i - 1][j - 1] + a1[i] * a2[j];
					if (f[i][j] < curNum) {
						f[i][j] = curNum;
					}
				}

				if (i >= 1) {
					if (f[i][j] < f[i - 1][j])
						f[i][j] = f[i - 1][j];
				}

				if (j >= 1) {
					if (f[i][j] < f[i][j - 1])
						f[i][j] = f[i][j - 1];
				}

				if (maxPoint < f[i][j])
					maxPoint = f[i][j];
			}
		}

		return maxPoint;
	}

	public int maxDotProduct(int[] nums1, int[] nums2, int num) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int dp[][] = new int[len1 + 1][len2 + 1];
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				max = Math.max(max, dp[i - 1][j - 1] + nums1[i - 1] * nums2[j - 1]);
				dp[i][j] = Math.max(Math.max(dp[i][j - 1], dp[i - 1][j]),
						dp[i - 1][j - 1] + nums1[i - 1] * nums2[j - 1]);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		App3maxDotProduct app = new App3maxDotProduct();
		Solution sol = app.new Solution();
		System.out.println(app.maxDotProduct(new int[] { 1, 1, 1 }, new int[] { 1, 1, 1 }, 0));
		System.out.println(sol.maxDotProduct(new int[] { 1, 1, 1 }, new int[] { 1, 1, 1 }));
	}

}
