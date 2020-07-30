package com.example.demo;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object sup, Method method, Object[] args, MethodProxy proxy) throws Throwable {

		System.out.println("--------------intercept beagin--------------");
		Object object =  proxy.invokeSuper(sup, args);
		System.out.println("--------------intercept end--------------");
		return object;

	}

}
