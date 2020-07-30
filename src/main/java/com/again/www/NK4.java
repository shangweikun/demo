package com.again.www;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NK4 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Long tmp = 0l;
		List<Long> lst = new ArrayList<Long>();
		while (sc.hasNext()) {
			tmp = sc.nextLong();
			long result = 2;
			while (result <= tmp) {
				if (tmp % result == 0) {
					lst.add(result);
					tmp /= result;
					continue;
				} else {
					++result;
				}
			}
			System.out.print(lst.get(0));
			for (int i = 1; i < lst.size(); i++) {
				System.out.print(" ");
				System.out.print(lst.get(i));
			}
		}
		sc.close();
	}

	/*private static boolean isPrime(long l) {
		for (int i = 2; i < l - 1; i++) {
			if (l % i == 0)
				return false;
		}
		return true;
	}*/
}
