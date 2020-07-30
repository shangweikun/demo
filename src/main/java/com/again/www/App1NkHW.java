package com.again.www;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class App1NkHW {

	static class test {

		public static int calculate(String n) {
			int input = Integer.valueOf(n);
			Deque<Integer> deque = new LinkedList<Integer>();
			int result = 0;
			while (input > 0) {
				deque.add(1);
				input--;
				if (deque.size() == 3) {
					input++;
					deque.clear();
					result++;
				}
			}

			if (deque.size() == 2)
				return result + 1;

			return result;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str = null;
		int count = 0;
		while (sc.hasNext() && !(str = sc.next()).equals("0") && count < 10) {
			count++;
			System.out.println(test.calculate(str.trim()));
		}

	}
}
