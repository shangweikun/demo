package com.again.www;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NK1 {

	static Map<Character, String> MAP = new HashMap<Character, String>();
	static {
//		壹、贰、叁、肆、伍、陆、柒、捌、玖
		MAP.put('0', "零");
		MAP.put('1', "壹");
		MAP.put('2', "贰");
		MAP.put('3', "叁");
		MAP.put('4', "肆");
		MAP.put('5', "伍");
		MAP.put('6', "陆");
		MAP.put('7', "柒");
		MAP.put('8', "捌");
		MAP.put('9', "玖");

	}

	public static void main(String[] args) {

		Map<Character, String> MAP = new HashMap<Character, String>();
		MAP.put('0', "零");
		MAP.put('1', "壹");
		MAP.put('2', "贰");
		MAP.put('3', "叁");
		MAP.put('4', "肆");
		MAP.put('5', "伍");
		MAP.put('6', "陆");
		MAP.put('7', "柒");
		MAP.put('8', "捌");
		MAP.put('9', "玖");

		Scanner in = new Scanner(System.in);
        while (in.hasNextDouble()) {
        	
        	Double input = in.nextDouble();
        	String s = new DecimalFormat("#.00").format(input);
        	String[] si = s.split("\\.");
        	StringBuffer so = new StringBuffer("人民币");
        	char[] ci = si[0].toCharArray();
        	int length = ci.length;
        	boolean flag = false;
        	for (int i = 0; i < ci.length; i++) {
        		flag = false;
        		char onec = ci[i];
        		if (onec == '0') {
        			while (i < ci.length - 1 && ci[i] == ci[++i]) {
        				length--;
        				if (!flag && length == 9) {
        					so.append("亿");
        					flag = true;
        				}
        				if (!flag && length == 5) {
        					so.append("万");
        					flag = true;
        				}
        			}
        			if (i == ci.length - 1)
        				continue;
        			else
        				so.append(MAP.get(onec));
        		} else {
        			if (!(length % 4 == 2 && ci[i] == '1'))
        				so.append(MAP.get(onec));
        			
        		}
        		if (length == 9) {
        			so.append("亿");
        		}
        		if (length == 5) {
        			so.append("万");
        		}
        		
        		if (length % 4 == 0)
        			so.append("仟");
        		if (length % 4 == 3)
        			so.append("佰");
        		if (length % 4 == 2)
        			so.append("拾");
        		
        		length--;
        		
        	}
        	if (ci.length > 0)
        		so.append("元");
        	
        	ci = si[1].toCharArray();
        	if (ci[0] != '0') {
        		so.append(MAP.get(ci[0]));
        		so.append("角");
        	}
        	
        	if (ci[1] != '0') {
        		so.append(MAP.get(ci[1]));
        		so.append("分");
        	}
        	
        	System.out.println(so.toString());
        }

	}
}
