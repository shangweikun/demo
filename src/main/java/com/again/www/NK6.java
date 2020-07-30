package com.again.www;

import java.util.Scanner;
import java.util.regex.Pattern;

public class NK6 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str = sc.next();
			String[] s = str.split("[a-zA-Z]+");
			int max1 = Integer.MIN_VALUE;
			int max2 = Integer.MIN_VALUE;
			for (String one : s) {
				if (max1 <= one.length() && one.length() != str.length() && one.length() != 0) {
					max2 = max1;
					max1 = one.length();
					continue;
				}
				if (max2 <= one.length() && one.length() != str.length() && one.length() != 0) {
					max2 = one.length();
				}
			}
			int result = 0;
			if (max2 > Integer.MIN_VALUE) {
				if ((result = check(str, max1 + max2 + 1)) == -1) {
					System.out.println(max1 + 1);
				} else {
					if (result > max1 + 1) {

						System.out.println(result);
					} else {
						System.out.println(max1 + 1);
					}

				}
			} else if (max1 > Integer.MIN_VALUE) {
				System.out.println(max1 + 1);
			} else {
				System.out.println(-1);
			}
		}
		sc.close();
	}

	private static int check(String str, int length) {
		String tmp;
		while (length >= 0) {

			for (int i = 0; i <= str.length() - length; i++) {
				tmp = str.substring(i, i + length);
				if (Pattern.matches("[0-9]+[a-zA-Z][0-9]+", tmp)) {
					return length;
				}
			}
			length--;
		}
		return -1;
	}
}
