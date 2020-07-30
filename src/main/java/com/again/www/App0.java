package com.again.www;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App0 {
	
	public static void test(int i) {
		new Exception("#"+i).printStackTrace();
	}
	
	

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class<?> a = Class.forName("com.again.www.App0");
		
		Method method = a.getMethod("test", int.class);
		
		method.invoke(null, 1);
	}
}
