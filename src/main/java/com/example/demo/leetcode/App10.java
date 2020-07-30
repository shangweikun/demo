package com.example.demo.leetcode;

import java.util.Arrays;
import java.util.stream.Stream;

public class App10 {

	class Method {
		void say() {
			System.out.println("hello");
		}
	}

	public static void main(String[] args) {

		App10 app = new App10();

		new Thread(app.new Method()::say).start();

		Stream.of(1, 2, 3, 4).forEach(System.out::println);

		Arrays.asList("What do you want to do".split(" "))
			.stream().map(s -> s + " ").forEach(System.out::print);

	}
}
