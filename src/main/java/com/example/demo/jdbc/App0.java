package com.example.demo.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理的class的 类 1、简单extend Proxy类 2 实现对应的参数接口
 * 
 * @author swk
 *
 */
public class App0 {

	interface IHello {
		void say();
	}

	class IHelloImp implements IHello {

		@Override
		public void say() {
			System.out.println("Hello World");
		}

	}

	interface IHello0 {
		void say();
	}

	class MyInvocationHandler implements InvocationHandler {

		private Object targer;

		public MyInvocationHandler(Object obj) {
			this.targer = obj;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

			return method.invoke(targer, args);

		}

	}

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class proxyClazz = Proxy.getProxyClass(IHello.class.getClassLoader(), IHello.class, IHello0.class);
		/*
		 * Constructor constructor = proxyClazz.getConstructor();
		 * System.out.println(constructor.newInstance(new Object()).getClass());
		 */
		System.out.println(proxyClazz.getSuperclass());
		for (Class one : proxyClazz.getInterfaces()) {
			System.out.println(one.getName());
		}
		/**
		 * jdk动态代理的实例 IHelloImp 实现的是IHello
		 * 			最终成功的从IHello0拦截到对应实现
		 */
		App0 app = new App0();
		IHello0 proxyDemo = (IHello0) Proxy.newProxyInstance(IHello.class.getClassLoader(),
				new Class[] { IHello.class, IHello0.class }, app.new MyInvocationHandler(app.new IHelloImp()));
		proxyDemo.say();
		System.out.println(proxyDemo.getClass().getName());
	}
}
