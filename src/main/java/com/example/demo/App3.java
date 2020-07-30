package com.example.demo;

import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class App3 {


	public static void main(String[] args) {
		class Node<E> {
			E A;
			E next;

			public Node(E value) {
				this.A = value;
			}

			public E getNext() {
				return next;
			}

			public void setNext(E next) {
				this.next = next;
			}

			@Override
			public String toString() {
				return "Node [A=" + A + ", next=" + next + "]";
			}

		}
		AbstractQueuedSynchronizer a;
		SynchronousQueue<String> b;
		FutureTask<String> c;
		AtomicReferenceFieldUpdater<String, String> StringUpdater = AtomicReferenceFieldUpdater.newUpdater(String.class,
				String.class, "next");
		AtomicReference<String> d;
//		AtomicReferenceArray<E>;
		Node<String> node0 = new Node<String>("A");
		Node<String> node1 = new Node<String>("A");
		Node<String> node2 = new Node<String>("B");


		node0.setNext("B");
		node1.setNext("B");
		StringUpdater.compareAndSet(node0.next, node1.next, node2.next);
		System.out.println(node0.toString());
	}

}
