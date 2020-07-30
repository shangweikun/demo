package com.example.demo.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class App23_445 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

			double a = 0;
			double b = 0;

			while (l1 != null && l2 != null) {
				a *= 10.00;
				a += l1.val;
				l1 = l1.next;
				b *= 10.00;
				b += l2.val;
				l2 = l2.next;

			}
			while (l1 != null) {
				a *= 10;
				a += l1.val;
				l1 = l1.next;
			}
			while (l2 != null) {
				b *= 10;
				b += l2.val;
				l2 = l2.next;
			}
			ListNode head = new ListNode(0);
			ListNode tmp = head;
			double result = a + b;
			int count = 0;
			/* double型处理太多复杂 */
			for (char one : String.valueOf(result).toCharArray()) {
				if (Character.isDigit(one)) {
					tmp.next = new ListNode(Integer.parseInt(String.valueOf(one)));
					tmp = tmp.next;
				}
				count++;
				if (one == 'E') {

					break;
				}

				if (one == '.' && result < 10.00)
					break;

				if (one == '.' && count != 2)
					break;

			}
			return head.next;
		}
	}

	class Solution2 {

		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			Deque<ListNode> dStack1 = new LinkedList<>();
			Deque<ListNode> dStack2 = new LinkedList<>();
			Deque<ListNode> dStack = new LinkedList<>();

			while (l1 != null && l2 != null) {
				dStack1.add(new ListNode(l1.val));
				l1 = l1.next;
				dStack2.add(new ListNode(l2.val));
				l2 = l2.next;

			}
			while (l1 != null) {
				dStack1.add(new ListNode(l1.val));
				l1 = l1.next;
			}
			while (l2 != null) {
				dStack2.add(new ListNode(l2.val));
				l2 = l2.next;
			}
			
			
			int addNum = 0;
			while (!dStack1.isEmpty() && !dStack2.isEmpty()) {
				dStack.add(new ListNode((dStack1.peekLast().val + dStack2.peekLast().val + addNum) % 10));
				addNum = (dStack1.removeLast().val + dStack2.removeLast().val + addNum) / 10;
			}
			while (!dStack1.isEmpty()) {
				dStack.add(new ListNode((dStack1.peekLast().val + addNum) % 10));
				addNum = (dStack1.removeLast().val + addNum) / 10;
			}
			while (!dStack2.isEmpty()) {
				dStack.add(new ListNode((dStack2.peekLast().val + addNum) % 10));
				addNum = (dStack2.removeLast().val + addNum) / 10;
			}
			if (addNum != 0) {
				dStack.add(new ListNode(addNum));
			}
			
			ListNode head =new ListNode(0);
			ListNode tail = head;
			while(!dStack.isEmpty()) {
				tail.next = dStack.removeLast();
				tail = tail.next;
			}
			return head.next;
		}

	}

	public static void main(String[] args) {
		int a = 1;
		for (char one : String.valueOf(a).toCharArray()) {
			int b = Integer.parseInt(String.valueOf(one));
			System.out.println(b);
			a++;
			if (a == 2)
				break;
		}
	}

}
