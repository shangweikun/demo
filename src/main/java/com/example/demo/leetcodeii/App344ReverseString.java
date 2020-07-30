package com.example.demo.leetcodeii;

public class App344ReverseString {

	class Solution {
		public void reverseString(char[] s) {

			"123".toCharArray();
			if (s.length == 0 || s.length == 1)
				return;
			char tmp;

			for (int i = 0; i < s.length >>1; i++) {
				tmp = s[i];
				s[i] = s[s.length - 1 - i];
				s[s.length - 1 - i] = tmp;
			}
		}
	}
}
