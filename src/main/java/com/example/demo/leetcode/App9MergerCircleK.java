package com.example.demo.leetcode;

public class App9MergerCircleK {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	class Solution {
		public ListNode mergeKLists(ListNode[] lists) {

			/**
			 * 没用哨兵
			 * 冗余操作
			 */
			
			ListNode result = null;
			if (lists.length == 0)
				return null;
			if (lists.length == 1)
				return lists[0];
			ListNode tmp = null;
			ListNode tmpLoop = null;
			int count = 0;
			int flag = 0;

			for (int i = 0; i < lists.length; i++) {
				if(lists[i] == null ) {
					count++;
					lists[i] = new ListNode(Integer.MAX_VALUE);
				}
			}

			if (count == lists.length) return null;
			
			tmp = lists[0] ;
			for (int i = 1; i < lists.length; i++) {
				if (tmp.val > lists[i].val) {
					tmp = lists[i];
					flag = i;
				}
			}
			result = tmp;
			tmpLoop = result;

			lists[flag] = lists[flag].next;
			tmpLoop.next = null;

			if (lists[flag] == null) {
				count++;
				lists[flag] = new ListNode(Integer.MAX_VALUE);
			}

			while (true) {
				tmp = lists[0];
				flag = 0;
				for (int i = 1; i < lists.length; i++) {
					if (tmp.val > lists[i].val) {
						tmp = lists[i];
						flag = i;
					}
				}
				if (lists[flag].val != Integer.MAX_VALUE) {
					tmpLoop.next = tmp;
					tmpLoop = tmpLoop.next;
				}

				lists[flag] = lists[flag].next;
				tmpLoop.next = null;

				if (lists[flag] == null) {
					count++;
					lists[flag] = new ListNode(Integer.MAX_VALUE);
				}

				if (count >= lists.length - 1)
					break;
			}
			flag = -1;
			for (int i = 0; i < lists.length; i++) {
				if (lists[i].val != Integer.MAX_VALUE) {
					flag = i;
					break;
				}
			}
			if(flag != -1)
				tmpLoop.next = lists[flag];

			return result;

		}
	}

	static class Test {
		public static void main(String[] args) {
			App9MergerCircleK app = new App9MergerCircleK();
			Solution sol = app.new Solution();

			ListNode tmp0 = app.new ListNode(1);
			ListNode tmp1 = app.new ListNode(4);
			ListNode tmp2 = app.new ListNode(5);
			tmp0.next = tmp1;
			tmp1.next = tmp2;

			ListNode tmp4 = app.new ListNode(1);
			
			ListNode tmp5 = app.new ListNode(3);
			ListNode tmp6 = app.new ListNode(4);


			ListNode tmp7 = app.new ListNode(2);
			ListNode tmp8 = app.new ListNode(6);
			tmp7.next = tmp8;

			sol.mergeKLists(new ListNode[] { null, tmp4 });
		}
	}
}
