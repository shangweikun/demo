package com.again1.www;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode366 {

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
        public List<List<Integer>> findLeaves(TreeNode root) {

            List result = new LinkedList();

            if (root == null) return result;

            while (true) {
                TreeNode head = root;
                if (root.left != null || root.right != null) {
                    List lst = new LinkedList();
                    buildNewTree(head, lst);
                    result.add(lst);
                } else {
                    List lst = new ArrayList<Integer>();
                    result.add(lst);
                    lst.add(head.val);
                    break;
                }
            }
            return result;
        }


        //不加标志位，递归返回时就回将非子节点至空
        /**
         * 输入: [1,2,3,4,5]
         *  
         *           1
         *          / \
         *         2   3
         *        / \
         *       4   5
         *
         * 输出: [[4,5,3],[2],[1]]
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/find-leaves-of-binary-tree
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         *
         * 结果：
         [[4,5,3],[1]]
         */
        private boolean buildNewTree(TreeNode head, List lst) {

            if (head.left == null && head.right == null) {
                lst.add(head.val);
                return true;
            }
            if (head.left != null) {
                if (buildNewTree(head.left, lst))
                    head.left = null;
            }
            if (head.right != null) {
                if (buildNewTree(head.right, lst))
                    head.right = null;
            }
            return false;
        }

    }
}
