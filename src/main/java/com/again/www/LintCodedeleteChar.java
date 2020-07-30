package com.again.www;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;


public class LintCodedeleteChar {
	class Solution {
		/**
		 * @param str: the string
		 * @param k:   the length
		 * @return: the substring with the smallest lexicographic order
		 */
		public String deleteChar(String str, int k) {
			/**
			 * 解题思路错误
			 */
			if (str.length() == 0 || str.trim() == null)
				return null;

			Deque<Character> deque = new LinkedList<Character>();
			char[] s = str.toCharArray();
			deque.add(s[0]);
			StringBuffer result = null;
			int resetNum = str.length() - k;

			for (int i = 1; i < s.length && resetNum > 0; i++) {
				while (!deque.isEmpty() && deque.peekLast() > s[i]) {
					deque.pollLast();
					resetNum--;
					if (resetNum <= 0) {

						int loop = k;
						Deque<Character> tmpDeque = new LinkedList<Character>(deque);
						result = new StringBuffer();

						while (!tmpDeque.isEmpty() && loop > 0) {
							result.append(tmpDeque.pollFirst());
							loop--;
						}
						if (tmpDeque.isEmpty()) {

							while (loop > 0) {
								result.append(s[i]);
								loop--;
								i++;
							}
						}
						break;
					}
				}
				if (i < s.length && resetNum > 0)
					deque.add(s[i]);
			}
			if (deque.size() >= k) {
				int loop = k;
				Deque<Character> tmpDeque = new LinkedList<Character>(deque);
				result = new StringBuffer();
				while (!tmpDeque.isEmpty() && loop > 0) {
					result.append(tmpDeque.pollFirst());
					loop--;
				}
			}

			return result == null ? null : result.toString();
		}

		public String deleteChar1(String str, int k) {
			char[] c = str.toCharArray();
			Arrays.parallelSort(c);
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < k; i++) {
				result.append(c[i]);
			}
			return result.toString();
		}

		StringBuffer result;
		int resultLength = Integer.MAX_VALUE;

		public String deleteChar0(String str, int k) {
			char[] c = str.toCharArray();
			mainProcess(null, 0, k, c, str.length());
			return result.toString();
		}

		private void mainProcess(Deque<Character> deque, int i, int k, char[] c, int length) {

			Deque<Character> stack = deque == null ? new LinkedList<Character>() : new LinkedList<Character>(deque);

			if (stack.size() >= k) {
				int loop = k;
				Deque<Character> tmpDeque = new LinkedList<Character>(stack);
				StringBuffer tmpResult = new StringBuffer();
				int tmpResultLength = 0;

				while (!tmpDeque.isEmpty() && loop > 0) {
					tmpResultLength += (int) tmpDeque.peekFirst();
					tmpResult.append(tmpDeque.pollFirst());
					loop--;
				}
				if (tmpResultLength < resultLength) {

					result = tmpResult;
					resultLength = tmpResultLength;
				}

			}

			if (i == length)
				return;

			mainProcess(stack, i + 1, k, c, length);
			if (stack.isEmpty() || stack.peekLast() <= c[i]) {
				stack.add(c[i]);
				mainProcess(stack, i + 1, k, c, length);
			}

		}

	}

	public static void main(String[] args) {
		LintCodedeleteChar app = new LintCodedeleteChar();
		Solution sol = app.new Solution();
		System.out.println(sol.deleteChar(
//				"fsakbacsi",
				"tjvzieuxvwuomxipjajasjoknjzkljuzgsjtghjcfvqlxtsbbtbvifajznyvldhgqaumqikafzpwqclanrzpqnijusmarxqfntll",
				65));

		int a = (int) 'a' + (int) 'c' + (int) 'i';
		System.out.println(a);
	}
}
