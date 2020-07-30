package com.example.demo.leetcode;

import java.util.PriorityQueue;

public class App9 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * PriorityQueue队列的实现
	 * 
	 * @author swk
	 *
	 */
	class Solution {
		public ListNode mergeKLists(ListNode[] lists) {

			ListNode result = new ListNode(0);

			PriorityQueue<ListNode> Pqueue = new PriorityQueue<App9.ListNode>((l1, l2) -> l1.val - l2.val);

			for (int i = 0; i < lists.length; i++) {
				while (lists[i] != null) {
					Pqueue.offer(lists[i]);
					lists[i] = lists[i].next;
				}
			}

			ListNode tail = result;
			while (!Pqueue.isEmpty()) {
				tail.next = Pqueue.poll();
				tail = tail.next;
			}
			tail.next = null;

			return result.next;

		}
	}

	interface FunctionMerge {
		ListNode merge(ListNode one);
	}

	class Solution0 {

		public ListNode mergeKLists(ListNode[] lists) {

			if (lists.length == 0) {
				return null;
			}
			return meger(lists, 0, lists.length - 1);

		}

		private ListNode meger(ListNode[] lists, int left, int right) {

			if (left == right) {
				return lists[left];
			}
			int mid = (right - left) / 2 + left;

			ListNode l1 = meger(lists, left, mid);
			ListNode l2 = meger(lists, mid + 1, right);

			return megerListMain(l1, l2);

		}

		public ListNode megerListMain(ListNode left, ListNode right) {

			ListNode result = new ListNode(0);

			if (left == null && right == null)
				return null;
			else if (left == null)
				return right;
			else if (right == null)
				return left;

			ListNode trail = result;
			while (left != null && right != null) {
				if (left.val < right.val) {
					trail.next = left;
					left = left.next;
				} else {
					trail.next = right;
					right = right.next;
				}
				trail = trail.next;
			}
			if (left != null) {
				trail.next = left;
			} else {
				trail.next = right;
			}
			return result.next;

		}

	}

	static class Test {
		public static void main(String[] args) {
			App9 app = new App9();
			Solution sol = app.new Solution();

			ListNode tmp0 = app.new ListNode(1);
			ListNode tmp1 = app.new ListNode(4);
			ListNode tmp2 = app.new ListNode(5);
			tmp0.next = tmp1;
			tmp1.next = tmp2;

			ListNode tmp4 = app.new ListNode(1);
			ListNode tmp5 = app.new ListNode(3);
			ListNode tmp6 = app.new ListNode(4);
			tmp4.next = tmp5;
			tmp5.next = tmp6;

			ListNode tmp7 = app.new ListNode(2);
			ListNode tmp8 = app.new ListNode(6);
			tmp7.next = tmp8;

			sol.mergeKLists(new ListNode[] { null, tmp4, tmp7 });
		}
	}

}
