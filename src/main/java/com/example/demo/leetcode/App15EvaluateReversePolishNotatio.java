package com.example.demo.leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class App15EvaluateReversePolishNotatio {

	class Solution {

		final Set<String> key = new HashSet<String>();

		Solution() {
			key.add("+");
			key.add("*");
			key.add("-");
			key.add("/");
		}

		public int evalRPN(String[] tokens) {

			Deque<String> stack = new LinkedList<String>();

			for (String one : tokens) {
				if (key.contains(one)) {
					stack.add(String.valueOf(caculate(stack.pollLast(), stack.pollLast(), one)));
				} else {
					stack.add(one);
				}
			}
			return Integer.valueOf(stack.remove());

		}

		private int caculate(String pollLast, String string, String one) {
			if (one.equals("+"))
				return Integer.valueOf(pollLast) + Integer.valueOf(string);
			if (one.equals("*"))
				return Math.multiplyExact(Integer.valueOf(pollLast), Integer.valueOf(string));
			if (one.equals("/"))
				return Integer.valueOf(string) / Integer.valueOf(pollLast);
			if (one.equals("-"))
				return Integer.valueOf(string) - Integer.valueOf(pollLast);
			return 0;
		}
	}

	static class test {
		public static void main(String[] args) {
			App15EvaluateReversePolishNotatio app = new App15EvaluateReversePolishNotatio();
			Solution sol = app.new Solution();
			String[] a = { "2", "1", "+", "3", "*" };
			System.out.println(sol.evalRPN(a));
		}
	}

}
