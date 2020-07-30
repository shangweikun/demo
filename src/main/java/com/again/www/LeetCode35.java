package com.again.www;

public class LeetCode35 {
    class Solution {
        public int searchInsert(int[] nums, int target) {

            if (nums.length == 0) return 0;
            if (nums[0] > target) return 0;
            if (nums[nums.length - 1] < target) return nums.length;

            int left = 0, right = nums.length - 1;
            int mid;
            while (left <= right) {
                mid = (left + right) >> 1;
                if (nums[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            /**
             * [1,3,4,8]
             * 0
             * 需要right == 0的判断
             */
            if (right >= 0 && nums[right] == target) return right;
            return left;
        }
    }
}
