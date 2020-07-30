package com.example.demo;

public class App8 {

	static boolean flag;

	public static void main(String[] args) {
		flag = true;
		if (flag)
			System.out.println("Hello, Java!");
		if (flag == true)
			System.out.println("Hello, JVM!");
//		ConcurrentHashMap<String, String> map;
		System.out.println(
				String.valueOf(
						Float.intBitsToFloat(0x80000000) ==
						Float.intBitsToFloat(0x00000000)));
	}
}
