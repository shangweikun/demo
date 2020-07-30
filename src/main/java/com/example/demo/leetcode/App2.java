package com.example.demo.leetcode;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public class App2 {

	public static void main(String[] args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 50000, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(),new CallerRunsPolicy());

		int i = 0;
		while (true) {
			if (i > 100)
				break;
			i++;
			System.out.println("loop" + i);
			pool.submit(() -> System.out.println(" I'm sub Thread"));
		}

	}

}
