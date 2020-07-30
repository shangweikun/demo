package com.example.demo;

public class App7 {

	static class Node{
		
		String info;
		Node next;
		
		public String getInfo() {
			return info;
		}
		public Node getNext() {
			return next;
		}
		public void setInfo(String info) {
			this.info = info;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [info=" + info + ", next=" + next + "]";
		}
		
		
	}
	
	public static void testAdd(Node head) {
		
		Node result = new Node();
		
//		result.setInfo("a");
		result.info = "a";
		
		head = result;
		
	}
	public static void main(String[] args) {
		Node head = new Node();
		head.setInfo("b");
		System.out.println(head.toString());
		testAdd(head);
		System.out.println(head.toString());

	}
	
}
