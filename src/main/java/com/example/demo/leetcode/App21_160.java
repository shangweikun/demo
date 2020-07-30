package com.example.demo.leetcode;

import java.util.HashSet;
import java.util.Set;

public class App21_160 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public class Solution {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
			ListNode A = headA;
			ListNode B = headB;
			Set<ListNode> set = new HashSet<ListNode>();
			while (A != null && B != null) {
				if (!set.add(A))
					return A;
				A = A.next;

				if (!set.add(B))
					return B;
				B = B.next;
			}
			if (A != null)
				while (A != null) {
					if (!set.add(A))
						return A;
					A = A.next;
				}

			if (B != null)
				while (B != null) {
					if (!set.add(B))
						return B;
					B = B.next;
				}
			return null;

		}
	}

	public class Solution_Answer {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
			ListNode A = headA;
			ListNode B = headB;
			while (A != B) {
				A = A == null ? A = headB : A.next;
				B = B == null ? B = headA : B.next;
			}
			return A;
		}
	}

	public static void main(String[] args) {
		Set<Integer> a = new HashSet<Integer>();
		System.out.println(a.add(1));
		System.out.println(a.add(1));

	}
}
