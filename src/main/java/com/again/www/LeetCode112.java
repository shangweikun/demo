package com.again.www;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LeetCode112 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {

        boolean hasPath = false;

        public boolean hasPathSum(TreeNode root, int sum) {
            hasPathMethod(root, 0, sum);
            return hasPath;
        }

        private void hasPathMethod(TreeNode root, int count, int sum) {

            if (root == null || hasPath)
                return;
            count += root.val;
            if (count == sum && root.left == null && root.right == null) {
                hasPath = true;
                return;
            }
            hasPathMethod(root.left, count, sum);
            hasPathMethod(root.right, count, sum);
        }
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class Solution0 {
        public int trap(int[] height) {

            int max_left = 0, max_right = 0;
            int result = 0;
            int tmp;
            for (int i = 0; i < height.length; i++) {
                max_left = calculateMax(height, i, Integer.MIN_VALUE, true);
                max_right = calculateMax(height, i, Integer.MIN_VALUE, false);
                if (Math.min(max_left, max_right) > height[i]) result += Math.min(max_left, max_right) - height[i];
            }
            return result;
        }

        private int calculateMax(int[] height, int i, int max, boolean b) {
            int result = max;
            if (b) {
                for (int j = i - 1; j > 0; j--) {
                    if (result < height[j]) result = height[j];
                }
            } else {
                for (int j = i + 1; j < height.length; j++) {
                    if (result < height[j]) result = height[j];
                }
            }
            return result;
        }
    }

    class Solution1 {
        public int trap(int[] height) {

            if (height.length == 0) return 0;
            int max_left = 0, max_right = 0;
            int result = 0;
            Deque<Integer> deque = new LinkedList<>();
            deque.addLast(0);
            int tmp_height, tmp, distance;

            for (int i = 1; i < height.length; i++) {
                if (deque.isEmpty() || height[deque.peekLast()] >= height[i]) deque.addLast(i);
                else {
                    tmp = 0;
                    while (!deque.isEmpty() && height[deque.peekLast()] < height[i]) {
                        /**
                         * distance 和 tmp_height 的处理
                         * 题解处理很巧妙
                         */
                        distance = i;
                        tmp = deque.pollLast();
                        if (deque.isEmpty()) break;
                        distance = distance - (deque.peekLast() + 1);
                        tmp_height = Math.min(height[deque.peekLast()], height[i]);
                        result += (tmp_height - height[tmp]) * distance;
                    }
                    deque.addLast(i);
                }

            }

            return result;
        }

    }

    class Solution2 {

        @Deprecated
        public int trap(int[] height) {

            if (height.length == 0) return 0;
            int max_left = 0, max_right = 0;
            int result = 0;
            Deque<Integer> deque = new LinkedList<>();
            deque.addLast(0);
            int ans, tmp, distance;

            for (int i = 1; i < height.length; i++) {
                if (deque.isEmpty() || height[deque.peekLast()] >= height[i]) deque.addLast(i);
                else {
                    /**
                     * error：
                     * [4,2,0,3,2,5] 无法处理 0 - 4 之间，大于3的水位
                     */
                    tmp = 0;
                    while (!deque.isEmpty() && height[deque.peekLast()] < height[i]) {
                        ans = Math.min(height[deque.peekFirst()], height[i]);
                        distance = tmp == 0 ? i : tmp;
                        tmp = deque.pollLast();
                        distance -= tmp;
                        result += (ans - height[tmp]) * distance;
                    }
                    deque.addLast(i);
                }

            }

            return result;
        }
    }
}
