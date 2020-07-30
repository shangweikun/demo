package com.example.demo.leetcode.week.www;

public class App1ConfirmTheBigNumber {
	class Solution {
		public int maxVowels(String s, int k) {

			char[] input = s.toCharArray();
			int result = 0;
			int tmp = result;

			if (input.length == k)
				return calculate(input, k);

			for (int i = k, j = 0; i < input.length; i++, j++) {
				if (j == 0) {
					result = calculate(input, i);
					tmp = result;
				}
				if (input[j] == 'a' || input[j] == 'e' || input[j] == 'i' || input[j] == 'o' || input[j] == 'u') {
					tmp--;
				}
				if (input[i] == 'a' || input[i] == 'e' || input[i] == 'i' || input[i] == 'o' || input[i] == 'u') {
					tmp++;
				}
				result = tmp > result ? tmp : result;
				if (result == k)
					return result;
			}
			return result;
		}

		private int calculate(char[] input, int k) {
			int result = 0;
			for (int j = 0; j < k; j++) {
				if (input[j] == 'a' || input[j] == 'e' || input[j] == 'i' || input[j] == 'o' || input[j] == 'u') {
					result++;
				}
			}
			return result;
		}
	}
	static class test{
		public static void main(String[] args) {
			App1ConfirmTheBigNumber app = new App1ConfirmTheBigNumber();
			Solution sol = app.new Solution();
			sol.maxVowels("tryhard", 4);
		}
	}
}

//			List<String> lst = new ArrayList<String>();
//
//			for (int i = 0; i <= s.length() - k; i++) {
//				lst.add(s.substring(i, k + i));
//			}
//			int result = 0;
//			int tmp = result;
//			char[] tmpList;
//			for (String one : lst) {
//				tmp = 0;
//				tmpList = one.toCharArray();
//				for (int i = 0; i < tmpList.length; i++) {
//					if (tmpList[i] == 'a' || tmpList[i] == 'e' || tmpList[i] == 'i' || tmpList[i] == 'o'
//							|| tmpList[i] == 'u') {
//						tmp++;
//					}
//					result = tmp > result ? tmp : result;
//					if (result == k)
//						return result;
//				}
//			}
//			return result;