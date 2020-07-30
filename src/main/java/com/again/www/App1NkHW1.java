package com.again.www;

import java.util.Scanner;

public class App1NkHW1 {

	private static Scanner sc;

	public static void main(String[] args) {
		sc = new Scanner(System.in);
		String str = null;
		while (sc.hasNext()) {
			str = sc.nextLine();
			System.out.println(Integer.valueOf(str.substring(2), 16));
		}
	}
}
