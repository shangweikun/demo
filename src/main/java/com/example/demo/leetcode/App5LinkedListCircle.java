package com.example.demo.leetcode;

public class App5LinkedListCircle {

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	class Solution {
		public boolean hasCycle(ListNode head) {
			if (head == null || head.next == null)
				return false;
			if (head.next == head)
				return true;
			ListNode slow, fast;
			boolean result = false;
			slow = head;
			fast = head;
			while (fast.next != null && fast.next.next != null) {

				slow = slow.next;
				fast = fast.next.next;

				if (slow == fast) {
					result = true;
					break;
				}
			}
			return result;

		}
	}
}
