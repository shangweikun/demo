package com.example.demo.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class App19_1188 {

	/**
	 * Failure 后续尝试实现
	 * 
	 * @author swk
	 *
	 */
	class BoundedBlockingQueue {

		AtomicInteger capacity;
		final int maxsize;
		Object Lock = new Object();
		int[] queue;

		public BoundedBlockingQueue(int capacity) {
			this.capacity = new AtomicInteger(0);
			this.maxsize = capacity;
			this.queue = new int[capacity];
		}

		public void enqueue(int element) throws InterruptedException {
			while (true) {
				synchronized (Lock) {
					if (capacity.get() == maxsize)
						wait();
					else {
						capacity.getAndAdd(1);
						this.queue[this.capacity.get() - 1] = element;
						break;
					}
				}
			}
		}

		public int dequeue() throws InterruptedException {
			int tmp;
			while (true) {
				synchronized (Lock) {
					if (capacity.get() == 0)
						wait();
					else {
						tmp = this.queue[this.capacity.get() - 1];
						this.queue[this.capacity.get() - 1] = 0;
						capacity.getAndDecrement();
						break;
					}
				}
			}
			return tmp;
		}

		public int size() {
			return this.capacity.get();
		}
	}

	class App19_1188_example {
		class BoundedBlockingQueue {

			ReentrantLock Lock = new ReentrantLock();
			Condition FullCondition = Lock.newCondition();
			Condition EmptyCondition = Lock.newCondition();
			volatile int capacity;
			final int maxsize;
			Queue<Integer> queue = new LinkedList<Integer>();

			public BoundedBlockingQueue(int capacity) {
				this.capacity = 0;
				this.maxsize = capacity;
			}

			public void enqueue(int element) throws InterruptedException {
				try {
					Lock.lock();
					while (capacity == maxsize)
						FullCondition.await();
					capacity++;
					queue.add(element);
					EmptyCondition.signalAll();
				} catch (InterruptedException e) {
					throw e;
				} finally {
					Lock.unlock();
				}
			}

			public int dequeue() throws InterruptedException {
				try {
					Lock.lock();
					while (capacity == 0)
						EmptyCondition.await();
					capacity--;
					int tmp = queue.poll();
					FullCondition.signalAll();
					return tmp;
				} catch (InterruptedException e) {
					throw e;
				} finally {
					Lock.unlock();
				}
			}

			public int size() {
				return capacity;
			}
		}
	}

}
