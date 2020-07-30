package com.again1.www;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LeetCode437 {

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

        Deque stack = new LinkedList();
        int result = 0;

        public int pathSum(TreeNode root, int sum) {

            if (root == null) return 0;

            calculateSum(root, sum);
            pathSum(root.left, sum);
            pathSum(root.right, sum);

            return result;
        }

        private void calculateSum(TreeNode root, int sum) {
            if (root == null)
                return;
            sum -= root.val;
            if (sum == 0) {
                result++;
            }
            calculateSum(root.left, sum);
            calculateSum(root.right, sum);
            sum += root.val;
        }
    }

    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, sum, new int[1000], 0);
    }

    public int pathSum(TreeNode root, int sum, int[] array/*保存路径*/, int p/*指向路径终点*/) {
        if (root == null) {
            return 0;
        }
        int tmp = root.val;
        int n = root.val == sum ? 1 : 0;
        for (int i = p - 1; i >= 0; i--) {
            tmp += array[i];
            if (tmp == sum) {
                n++;
            }
        }
        array[p] = root.val;
        int n1 = pathSum(root.left, sum, array, p + 1);
        int n2 = pathSum(root.right, sum, array, p + 1);
        return n + n1 + n2;
    }

}
