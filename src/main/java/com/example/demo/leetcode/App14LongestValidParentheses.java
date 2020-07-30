package com.example.demo.leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class App14LongestValidParentheses {

	class Solution {

		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();

		public boolean isValid(String s) {
			Deque<String> stack = new LinkedList<String>();

			String[] input = s.split("");
			set1.add("{");
			set1.add("[");
			set1.add("(");
			set2.add("}");
			set2.add("]");
			set2.add(")");
			set3.add("{}");
			set3.add("[]");
			set3.add("()");

			for (String one : input) {
				if (set1.contains(one)) {
					stack.add(one);
				} else if (set2.contains(one)) {
					if (!set3.contains(stack.pollLast() + one))
						return false;
				}
			}

			return stack.isEmpty();

		}

		public int longestValidParentheses(String s) {
			int num = 0;
			//用map会超时
//			Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

			for (int i = 0; i < s.length() - 1; i++) {
				//改为+2超时
				for (int j = i + 1; j < s.length(); j+=2) {
//					if (map.get(j - i + 1) == null || !map.get(j - i + 1))
//						map.put(j - i + 1, isValid(s.substring(i, j + 1)));
					if(isValid(s.substring(i, j + 1)))
						num = Math.max(num, j - i + 1);
				}
			}
//			for (Integer one : map.keySet()) {
//				if (map.get(one) && one > num)
//					num = one;
//			}
			return num;

		}
	}

	static class test {
		public static void main(String[] args) {
			App14LongestValidParentheses app = new App14LongestValidParentheses();
			Solution sol = app.new Solution();
			System.out.println(sol.longestValidParentheses("(()"));
		}
	}

}
