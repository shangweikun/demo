package com.again.www;

import java.util.Scanner;

public class NK5 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str = sc.next();
			String[] s = str.split("[1-9]+[a-Z][1-9]+");
			
			if (s.length == 0) {
				System.out.println(-1);
				continue;
			}
			if (s.length == 1) {
				if (s[0].length() == str.length()) {
					System.out.println(-1);
					continue;
				} else {
					System.out.println(str.length() - 1 - (str.indexOf(s[0]) + s[0].length() - 1) + 1);
					continue;
				}
			}

			int result;
			if (s[0].isEmpty()) {
				result = str.indexOf(s[1]) - (0 + 0 - 1);
			} else {

				result = str.indexOf(s[1]) - (str.indexOf(s[0]) + s[0].length() - 1);

			}
			int tmp;
			int precursor = 0;
			int cursor = str.indexOf(s[0]) + s[0].length();
			for (int i = 1; i < s.length - 1; i++) {
				tmp = 0;
				if (s[i].length() == 1) {
					tmp = str.indexOf(s[i], cursor) - (str.indexOf(s[i - 1], precursor) + s[i - 1].length() - 1);
					precursor = cursor;
					cursor = str.indexOf(s[i], cursor) + s[i].length();
					tmp += str.indexOf(s[i + 1], cursor) - str.indexOf(s[i], precursor) - 1;
				} else {
					precursor = cursor;
					cursor = str.indexOf(s[i], cursor) + s[i].length();
					tmp = str.indexOf(s[i + 1], cursor) - (str.indexOf(s[i], precursor) + s[i].length() - 1);
				}
				result = result > tmp ? result : tmp;
			}

			/**
			 * 末尾为数字考虑
			 */
			tmp = 0;
			if (str.lastIndexOf(s[s.length - 1]) + s[s.length - 1].length() != str.length()) {
				cursor = str.lastIndexOf(s[s.length - 1]) - s[s.length - 1].length();
				if (s[s.length - 1].length() == 1) {
					
					tmp = str.lastIndexOf(s[s.length - 1])
							- (str.lastIndexOf(s[s.length - 2],cursor)
									+ s[s.length - 2].length() - 1);
					tmp += str.length() - str.lastIndexOf(s[s.length - 1]) - 1;
				} else {
					tmp = str.length() - (str.lastIndexOf(s[s.length - 1]) + s[s.length - 1].length() - 1);
				}
			}
			result = result > tmp ? result : tmp;

			System.out.println(result);
		}
		sc.close();
	}
}
