package com.example.demo.leetcode.week.www;

public class App0CheckWord {

	class Solution {
		public int isPrefixOfWord(String sentence, String searchWord) {

			String[] input = sentence.split(" ");

			int result = -1;
			for (int i = 0; i < input.length; i++) {
				if (input[i].length() >= searchWord.length())
					if (input[i].substring(0, searchWord.length()).equals(searchWord))
						result = i + 1;
			}
			return result;
		}
	}
}
