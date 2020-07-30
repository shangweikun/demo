package com.example.demo;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class App5 {
	public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/swk/git/code");
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(HelloWorld.class);
		enhancer.setCallback(new MyMethodInterceptor());
		
		HelloWorld helloWorld =(HelloWorld) enhancer.create();
		helloWorld.say();
		
	}
}
