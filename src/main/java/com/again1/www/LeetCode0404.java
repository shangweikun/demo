package com.again1.www;

public class LeetCode0404 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {

        boolean flag = true;

        public boolean isBalanced(TreeNode root) {
            if (root.left == null && root.right == null) return true;
            int left = calDeepth(root.left);
            int right = calDeepth(root.right);
            if (flag && Math.abs(left - right) <= 1) return true;
            return false;
        }

        private int calDeepth(TreeNode root) {
            if (!flag || root == null) return 0;
            int left = calDeepth(root.left);
            int right = calDeepth(root.right);
            if (Math.abs(left - right) > 1) {
                flag = false;
                return 0;
            }
            return 1 + Math.max(left, right);

        }


    }
}
