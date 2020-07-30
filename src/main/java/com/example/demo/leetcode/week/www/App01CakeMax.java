package com.example.demo.leetcode.week.www;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class App01CakeMax {
	static class Solution {
		public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {

			/*
			 * int[] hor2 = Arrays.copyOf(horizontalCuts, horizontalCuts.length + 2); int[]
			 * ver2 = Arrays.copyOf(verticalCuts, verticalCuts.length + 2); hor2[hor2.length
			 * - 2] = 0; hor2[hor2.length - 1] = h; ver2[ver2.length - 2] = 0;
			 * ver2[ver2.length - 1] = w;
			 */
			Arrays.parallelSort(horizontalCuts);
			Arrays.parallelSort(verticalCuts);

			int result1 = horizontalCuts[0] - 0;
			for (int i = 1; i < horizontalCuts.length; i++) {
				result1 = Math.max(horizontalCuts[i] - horizontalCuts[i - 1], result1);
			}
			result1 = Math.max(h - horizontalCuts[horizontalCuts.length - 1], result1);

			int result2 = verticalCuts[0] - 0;
			for (int i = 1; i < verticalCuts.length; i++) {
				result2 = Math.max(verticalCuts[i] - verticalCuts[i - 1], result2);
			}
			result2 = Math.max(w - verticalCuts[verticalCuts.length - 1], result2);

			return (int) (((long) result1 * result2) % (1000000007));
		}
		
		public static void main(String[] args) {
			int i = 1000000000;
			int j = 1000000000;
			System.out.println(String.valueOf((long)i*j));
		}
	}

	@SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
//		App01CakeMax app = new App01CakeMax();
		Solution sol = new Solution();
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		File file1 = new File("/Users/swk/test/tmp1");
//		File file2 = new File("/Users/swk/test/tmp2");

		InputStream input = new FileInputStream(file1);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
		int len = 0;
		byte[] nums = new byte[1024];
		String str = "";
		while ((str = bufferedReader.readLine()) != null) {
			System.out.println(str);
		}

		for (String one : str.trim().split(",")) {
			System.out.println(one);
		}
		while ((len = input.read(nums)) != -1) {
			outSteam.write(nums, 0, len);
		}

		System.out.println(outSteam.toString());

		/*
		 * for (int one : nums) { System.out.println(one); }
		 */
		input.close();
		outSteam.close();
		bufferedReader.close();

		System.out.println(sol.maxArea(50, 15, new int[] { 37, 40, 41, 30, 27, 10, 31 },
				new int[] { 2, 1, 9, 5, 4, 12, 6, 13, 11 }));
		int[] a = new int[] { 0, 1, 2, 3 };
		int[] hor2 = Arrays.copyOf(a, a.length + 2);
		for (int one : hor2) {
			System.out.println(one);
		}


		
	}
}
