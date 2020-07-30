package com.example.demo;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import net.sf.cglib.proxy.Enhancer;

public class App4 {

	static class Node {

		int a = 1;
		Node next = null;

		public int getA() {
			return a;
		}

		public Node getNext() {
			return next;
		}

		public void setA(int a) {
			this.a = a;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public boolean checkLoop(Node head) {
			int headHashcode = head.hashCode();
			Node loopNode = head.next;
			while (loopNode != null) {
				if (loopNode.hashCode() == headHashcode)
					return true;
				loopNode = loopNode.next;
			}
			return false;

		}

		public Node mergeList(Node Firstlst, Node SecondLst) {

			Node head = new Node();
			head.setA(0);

			Node tmp = head;

			while (true) {

				tmp = tmp.next;

				if (Firstlst.getA() > SecondLst.getA()) {
					tmp = SecondLst;
					SecondLst = SecondLst.next;
					if (SecondLst == null) {
						break;
					}
				} else {
					tmp = Firstlst;
					Firstlst = Firstlst.next;
					if (Firstlst == null) {
						break;
					}
				}
			}

			tmp = tmp.next;

			if (Firstlst == null) {
				tmp = SecondLst;
			} else {

				tmp = Firstlst;
			}

			return head;

		}

		public void removeNode(int a, Node head) {
			
			Node priorNode = head;
			Node removeNode = head;
			for(int i =0 ; i < 2 ; i++ ) {
				priorNode = priorNode.next;
			}
			while(priorNode.next != null){
				removeNode = removeNode.next;
				priorNode=priorNode.next;
			}
			removeNode.next = removeNode.next.next;
		}
		
		public Node createNewList() {

			Node head = new Node();
			head.setA(0);
			Node tmp = head;
			int i = 0;
			while (i > 5) {

				tmp.next = new Node();
				tmp = tmp.next;
				i++;
			}
			return head;
		}

	}

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map;
		HashMap<String, String> map1;
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(null);
		BeanUtil beanUtil;

	}
}
