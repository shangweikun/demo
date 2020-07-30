package com.again.www;

import java.util.Arrays;
import java.util.Scanner;

public class App2NkHW {

	private static Scanner sc;

	public static void main(String[] args) {

		System.out.println(Arrays.copyOfRange(new int[] { 1, 2, 3, 4 }, 0, 4));

		sc = new Scanner(System.in);
		int n = 0, m = 0;
		if (sc.hasNext())
			n = sc.nextInt();
		if (sc.hasNext())
			m = sc.nextInt();
		int[] lst = new int[n];
		for (int i = 0; i < n; i++) {
			if (sc.hasNext())
				lst[i] = sc.nextInt();
		}
		String handle = null;
		int a = 0, b = 0;
		for (int i = 0; i < m; i++) {
			if (sc.hasNext()) {
				handle = sc.next();
			}
			if (sc.hasNext()) {
				a = sc.nextInt();
			}
			if (sc.hasNext()) {
				b = sc.nextInt();
			}

			if (handle.equals("Q")) {
				int[] tmp = Arrays.copyOfRange(lst, a - 1, b);
				Arrays.parallelSort(tmp);
				System.out.println(tmp[tmp.length - 1]);
			} else if (handle.equals("U")) {
				lst[a - 1] = b;
			}

		}
	}
}
