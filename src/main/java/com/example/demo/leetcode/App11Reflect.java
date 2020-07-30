package com.example.demo.leetcode;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class App11Reflect {

	public final int i = 1;

	public static void printConstructors(Class<?> cl) {

		Constructor<?>[] con = cl.getDeclaredConstructors();
		System.out.println("constructor:");
		for (Constructor<?> one : con) {
			System.out.println(one.getName());
			System.out.println("param:");
			for (Class<?> param : one.getParameterTypes()) {
				System.out.println(param.getName());
			}
		}

	}

	public static void printMethod(Class<?> cl) {

		Method[] meth = cl.getDeclaredMethods();
		System.out.println("method:");
		for (Method one : meth) {
			System.out.println(one.getName());
			System.out.println("param:");
			for (Class<?> param : one.getParameterTypes()) {
				System.out.println(param.getName());
			}
		}

	}

	public static void printFiled(Class<?> cl) {

		Field[] field = cl.getDeclaredFields();
		System.out.println("method:");
		for (Field one : field) {
			System.out.print(Modifier.toString(one.getModifiers()) + " ");
			System.out.println(one.getName());
		}

	}

	class Employee {
		public void getsalary() {
			System.out.println("100");
		}
	}

	static class test {
		public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			Double a = 11.0;
			printConstructors(a.getClass());
			System.out.println("-------------------");
			printMethod(a.getClass());
			System.out.println("-------------------");
			printFiled(a.getClass());

			App11Reflect app =new App11Reflect();
			Employee ee = app.new Employee();
			Method m1 = ee.getClass().getMethod("getsalary");
			m1.invoke(ee);
			
			Array.newInstance(a.getClass(), 2);
			System.out.println(Array.getLength(a));
		}
	}

}
