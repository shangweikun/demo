package com.example.demo.leetcode;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class App13ValidParentheses {

	class Solution {

		public Deque<String> sta = new LinkedList<String>();
		Set<String> set1 = new HashSet<>();
		Set<String> set2 = new HashSet<>();
		Set<String> set3 = new HashSet<>();

		public boolean isValid(String s) {

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
					sta.add(one);
				} else if (set2.contains(one)) {
					if (!set3.contains(sta.pollLast() + one))
						return false;
				}
			}

			return sta.isEmpty();

		}
	}

	static class test {
		public static void main(String[] args) {
			App13ValidParentheses app = new App13ValidParentheses();
			System.out.println(app.new Solution().isValid("([)]"));
		}
	}

}
