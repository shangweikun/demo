package com.example.demo;

public class App6 {

	static class Node {
		int num;
		Node next;

		public Node() {
		}

		public Node(int n) {
			this.num = n;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		/*
		@Override
		public String toString() {
			return "Node{" + "num=" + num + ", next=" + next + '}';
		}
		*/
	}

	public static Node GeneratedList(int n) {
		Node head = new Node();
		head.setNum(0);
		Node tmp = head;
		for (int i = 0; i < n; i++) {
			tmp.next = new Node();
			tmp = tmp.next;
			tmp.setNum((int) (Math.random() * 10));
		}

		return head;
	}

	/**
	 * 空间复杂度O(1),时间复杂度O(n) 编写过程中遇到问题:
	 * <p>
	 * ·原链中单节点取出断链操作
	 * </p>
	 * <p>
	 * ·注意单节点增加完毕后,result链和flag标志为重置
	 * </p>
	 * <p>
	 * ·flag只有在为false时,才会在result链尾部链接
	 * </p>
	 * 
	 * @param head
	 * @return
	 */
	public static void insertSort(Node head) {

		System.out.println("head:" + head.hashCode());

		Node tmp = head;
		Node result = new Node();
		Node orderNode;
		Node preNode = result;
		result.setNum(0);
		boolean flag = false;
		while (head.next != null) {
			tmp = head.next;
			head.next = tmp.next;
			tmp.next = null;

			orderNode = result;
			flag = false;
			while (orderNode.next != null && !flag) {
				preNode = orderNode;
				orderNode = orderNode.next;
				if (orderNode.getNum() > tmp.getNum()) {
					tmp.next = orderNode;
					preNode.next = tmp;
					flag = true;
				}
			}
			if (!flag) {

				orderNode.next = tmp;
			}
		}
		result.setNum(10);

		/* 这两个均能在main中产生变化 */
//		head.num++;
//		head.next = result.next;

		/* java变量逃逸现象发生 */
//		head.next = result;

		head = result;

		System.out.println(head.toString());
		System.out.println(result.toString());

		/*
		 * head.next = result.next;
		 */
//		return head;
	}

	/**
	 * 对于链表来说,冒泡只要做到比较,确定指针位置即可,CompareAndNoSwap
	 * 
	 * @param Head
	 * @return
	 */
	public static Node upSort(Node head) {
		Node result = new Node(0);
		Node resultend = result;

		Node preNode;
		Node tagNode;
		Node tmpNode;
		Node tmpPreNode;
		while (head.getNext() != null) {
			preNode = head;
			tagNode = preNode.next;
			tmpNode = preNode.next;
			/**
			 * 查询单次冒泡的目标节点
			 */
			while (tmpNode.next != null) {
				tmpPreNode = tmpNode;
				tmpNode = tmpNode.next;
				if (tagNode.getNum() > tmpNode.getNum()) {
					preNode = tmpPreNode;
					tagNode = tmpNode;
				}
			}

			/**
			 * 断链
			 */
			preNode.next = tagNode.next;
			tagNode.next = null;

			resultend.next = tagNode;
			resultend = resultend.next;
		}

		return result;
	}

	public static void add(int a) {
		a++;
	}

	public static void testAdd(Node head) {
		System.out.println("add0:" + head.toString());
		Node result = new Node(2);
		result.num++;

//		head.next = result;
		head =result;
		System.out.println("add:" + head.toString());
		System.out.println("add:" + result.toString());
	}

	public static void main(String[] args) {

		int a = 1;
		add(a);
		System.out.println(a);

		Node first = GeneratedList(10);

		/*
		 * Node first = new Node(0); Node tmp1 = new Node(2); Node tmp2 = new Node(5);
		 * Node tmp3 = new Node(0); Node tmp4 = new Node(4); Node tmp5 = new Node(2);
		 * first.setNext(tmp1); tmp1.setNext(tmp2); tmp2.setNext(tmp3);
		 * tmp3.setNext(tmp4); tmp4.setNext(tmp5);
		 */

		System.out.println(first);
		System.out.println("first:" + first.hashCode());
		insertSort(first);
//		Node insertlst = insertSort(first);
//		System.out.println("insertlst:"+insertlst.hashCode());

//		Node uplst = upSort(first);
		System.out.println(first);
//		System.out.println(uplst);

		Node test = new Node(1);
		System.out.println(test.toString());

		testAdd(test);
		System.out.println(test.toString());

	}
}
