package com.again.www;

public class LeetCode167 {
    class Solution {
        public int[] twoSum(int[] numbers, int target) {
            int[] result = new int[2];
            for (int i = 0; i < numbers.length - 1; i++) {
                int left = i + 1;
                int right = numbers.length - 1;
                int mid;
                while (left >= right) {
                    mid = (left + right) >> 1;
                    if (numbers[mid] <= target - numbers[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                if (numbers[left] == target - numbers[i]) {

                    result[0] = i;
                    result[1] = left;
                    break;
                }
            }

            return result;
        }
    }

    class SolutionSub extends Solution {
        @Override
        public int[] twoSum(int[] numbers, int target) {

            int left = 0;
            int right = numbers.length - 1;

            while (left < right) {
                if (numbers[left] + numbers[right] > target) {
                    right--;
                } else if (numbers[left] + numbers[right] < target) {
                    left++;
                } else {
                    break;
                }
            }
            int[] result = new int[2];
            result[0] = left + 1;
            result[1] = right + 1;
            return result;
        }
    }
}
