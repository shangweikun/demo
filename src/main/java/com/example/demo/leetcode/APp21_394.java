package com.example.demo.leetcode;

import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APp21_394 {

	class Solution {

		public Pattern pattern = Pattern.compile("[0-9]+");

		public String decodeString(String s) {

			if (s.length() == 0)
				return "";

			String result = "";
			String IntegerTemp = "";
			Deque<String> deque = new LinkedList<String>();
			String[] input = s.split("");

			/**
			 * O(n) = n
			 */
			for (String one : input) {
				if (!one.equals("]"))
					deque.add(one);
				else {
					result = "";
					while (!deque.peekLast().equals("[")) {
						StringBuffer lst = new StringBuffer(deque.pollLast());
						lst.append(result);
						result = lst.toString();
					}
					deque.pollLast(); // “[”出栈

					/* 重新推倒公式 */
					IntegerTemp = "";
					while (!deque.isEmpty() && !deque.peekLast().equals("[") && checkNum(deque.peekLast())) {
						StringBuffer lst = new StringBuffer(deque.pollLast());
						lst.append(IntegerTemp);
						IntegerTemp = lst.toString();
					}
					for (int i = 0; i < Integer.valueOf(IntegerTemp); i++) {
						deque.add(result);
					}
				}
			}
			result = "";
			Iterator<String> ite = deque.iterator();
			while (ite.hasNext())
				result = result + ite.next();

			return result;
		}

		public boolean checkNum(String peekLast) {
			try {
				Integer.valueOf(peekLast);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		public boolean checkInteger(String peekLast) {
			Matcher isNum = pattern.matcher(peekLast);
			return isNum.matches();
		}

	}

	class Solution_example {

		int ptr;

		public String decodeString(String s) {
			LinkedList<String> stk = new LinkedList<String>();
			ptr = 0;

			while (ptr < s.length()) {
				char cur = s.charAt(ptr);
				/*
				 * ps:char型的计算相对来说方便些 isDigit() isLetter()
				 */
				if (Character.isDigit(cur)) {
					// 获取一个数字并进栈
					String digits = getDigits(s);
					stk.addLast(digits);
				} else if (Character.isLetter(cur) || cur == '[') {
					// 获取一个字母并进栈
					stk.addLast(String.valueOf(s.charAt(ptr++)));
				} else {
					++ptr;
					LinkedList<String> sub = new LinkedList<String>();
					while (!"[".equals(stk.peekLast())) {
						sub.addLast(stk.removeLast());
					}
					/**
					 * ps:翻转处理
					 */
					Collections.reverse(sub);
					// 左括号出栈
					stk.removeLast();
					// 此时栈顶为当前 sub 对应的字符串应该出现的次数
					int repTime = Integer.parseInt(stk.removeLast());
					StringBuffer t = new StringBuffer();
					String o = getString(sub);
					// 构造字符串
					while (repTime-- > 0) {
						t.append(o);
					}
					// 将构造好的字符串入栈
					stk.addLast(t.toString());
				}
			}

			return getString(stk);
		}

		/**
		 * 直接从原输入“s”中截取数字
		 * 	转换成String入栈
		 * 
		 * @param s
		 * @return
		 */
		public String getDigits(String s) {
			StringBuffer ret = new StringBuffer();
			while (Character.isDigit(s.charAt(ptr))) {
				ret.append(s.charAt(ptr++));
			}
			return ret.toString();
		}

		public String getString(LinkedList<String> v) {
			StringBuffer ret = new StringBuffer();
			for (String s : v) {
				ret.append(s);
			}
			return ret.toString();
		}

	}

	public static void main(String[] args) {

		APp21_394 app = new APp21_394();
		Solution_example sol_e = app.new Solution_example();
		Solution sol = app.new Solution();
		System.out.println(sol.decodeString("31[a]"));
		System.out.println(sol_e.decodeString("31[a]"));

		Pattern pattern = Pattern.compile("[0-9]+");
		Matcher isNum = pattern.matcher(null); // ERROR
		System.out.println(isNum.matches());

	}
}
