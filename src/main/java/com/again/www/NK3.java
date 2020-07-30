package com.again.www;

import java.util.Scanner;
import java.util.regex.Pattern;

public class NK3 {

	static enum LegalIP {
		TRUE, FALSE, NULL;
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Integer a = 0, b = 0, c = 0, d = 0, e = 0, errorIP = 0, errorCode = 0, privateIP = 0;
		while (sc.hasNext()) {
			String str = sc.nextLine();
			if (str.trim().equals("0"))
				break;
			String[] input = str.split("~");
			LegalIP IPStatus;
			/*
			 * 掩码检查
			 */
			if (!ErrorCodeCheck(input[1])) {
				errorCode++;
			}

			/*
			 * IP检查
			 */
			if ((IPStatus = checkIPlegal(input[0])) != LegalIP.TRUE) {
				if (IPStatus == LegalIP.FALSE)
					errorIP++;
				continue;
			}

			if (privateCheck(input[0]))
				privateIP++;
			switch (typeCheck(input[0])) {
			case "a":
				a++;
				break;
			case "b":
				b++;
				break;
			case "c":
				c++;
				break;
			case "d":
				d++;
				break;
			case "e":
				e++;
				break;
			default:
				break;
			}

		}
		System.out.println(a + " " + b + " " + c + " " + d + " " + e + "" + 
				(errorCode + errorIP) + " " + privateIP);

	}

	private static boolean ErrorCodeCheck(String string) {
		String[] s = string.split("\\.");
		String pattern = "1+0+";
		int tmp = 0;
		StringBuffer si = new StringBuffer();
		for (int i = 0; i < s.length; i++) {
			if (s[i].trim().isEmpty())
				return false;
			tmp = Integer.valueOf(s[i]);
			si.append(fillLength(Integer.toBinaryString(tmp)));
		}

		return Pattern.matches(pattern, si.toString());

	}

	/**
	 * 掩码二进制位补全
	 * 
	 * @param binaryString
	 * @return
	 */
	private static String fillLength(String binaryString) {
		StringBuffer sb = new StringBuffer();
		if (binaryString.length() < 8) {
			for (int i = 8 - binaryString.length(); i > 0; i--)
				sb.append("0");
			sb.append(binaryString);
			return sb.toString();
		}
		return binaryString;
	}

	private static String typeCheck(String string) {

		String[] s = string.split("\\.");
		int tmp = Integer.valueOf(s[0]);
		if (tmp >= 1 && tmp <= 126)
			return "a";
		else if (tmp >= 128 && tmp <= 191)
			return "b";
		else if (tmp >= 192 && tmp <= 223)
			return "c";
		else if (tmp >= 224 && tmp <= 239)
			return "d";
		else if (tmp >= 240 && tmp <= 255)
			return "e";

		return null;

	}

	private static boolean privateCheck(String string) {
		String[] s = string.split("\\.");
		int tmp;
		for (int i = 0; i < 2; i++) {
			tmp = Integer.valueOf(s[i]);
			if (i == 0) {
				if (tmp != 10 && tmp != 172 && tmp != 192)
					return false;
			} else if (i == 1 && Integer.valueOf(s[0]) == 172 && (tmp < 16 || tmp > 31))
				return false;
			else if (i == 1 && Integer.valueOf(s[0]) == 192 && tmp != 168)
				return false;
			/*
			 * else break;
			 */
		}
		return true;
	}

	private static LegalIP checkIPlegal(String string) {
		String[] s = string.split("\\.");
		int tmp;
		for (int i = 0; i < s.length; i++) {
			if (s[i].trim().isEmpty())
				return LegalIP.FALSE;
			tmp = Integer.valueOf(s[i]);
			if (i == 0) {
				if (tmp < 0 || tmp > 255)
					return LegalIP.FALSE;
				else if (tmp == 0 || tmp == 127)
					return LegalIP.NULL;
			} else if (tmp < 0 || tmp > 255)
				return LegalIP.FALSE;
		}
		return LegalIP.TRUE;
	}
}
