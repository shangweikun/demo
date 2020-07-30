package com.again.www;

public class NKMain {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer(Integer.toBinaryString(Integer.valueOf("7", 16)));
		if (sb.length() < 4) {
			for (int i = 0; i < 4 - sb.length(); i++)
				sb.insert(0, "0");
		}

		System.out.println(
				Integer.toHexString(
						Integer.valueOf(sb.reverse().toString(), 2)
						).toUpperCase()
				);
	}
}
