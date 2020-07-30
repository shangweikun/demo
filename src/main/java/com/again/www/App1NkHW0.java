package com.again.www;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class App1NkHW0 {

	private static Scanner sc;

	public static void main(String[] args) {

		int num = 1;
		List<String> lst = new LinkedList<String>();
		sc = new Scanner(System.in);
		String str = null;
		int count = 0;
		while (count <= num && sc.hasNext()) {
			count++;
			str = sc.next();
			if (count == 1)
				num = Integer.valueOf(str.trim());
			else
				lst.add(str.trim());
		}

		TreeSet<String> set = new TreeSet<String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
			}
		});
		Iterator<String> it = null;
		set.addAll(lst);
		
		it = set.iterator();
		System.out.println("========output==========");
		while (it.hasNext()) {
			System.out.println(it.next());
		}

	}
}
