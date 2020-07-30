package com.example.demo.leetcode.week.www;

import java.util.HashMap;
import java.util.Map;

public class App2pseudoPalindromicPaths {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	class Solution {
		
		int num = 0;

		public int pseudoPalindromicPaths(TreeNode root) {

			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			calculatePathNum(root, map);

			return num;

		}

		private void calculatePathNum(TreeNode parrent, Map<Integer, Integer> map) {

			if (parrent == null)
				return;

			if (map.get(parrent.val) != null) {
				map.put(parrent.val, map.get(parrent.val) + 1);
			} else {
				map.put(parrent.val, 1);
			}

			if (parrent.left == null && parrent.right == null) {
				if (Calculate(map))
					num++;
			}

			if (parrent.left != null) {
				calculatePathNum(parrent.left, new HashMap<Integer, Integer>(map));
			}

			if (parrent.right != null) {
				calculatePathNum(parrent.right, new HashMap<Integer, Integer>(map));
			}

		}

		private boolean Calculate(Map<Integer, Integer> map) {
			int count = 0;
			for (Integer one : map.keySet()) {
				if (map.get(one) % 2 == 1)
					count++;
				if (count >= 2)
					return false;
			}

			return true;
		}
	}
}
