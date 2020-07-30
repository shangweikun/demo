package com.again.www;

public class LeetCode154 {
    class Solution {
        public int minArray(int[] numbers) {
            if (numbers.length == 1) return numbers[0];
            int result = 0;
            int left = 0, right = numbers.length - 1;
            while (left <= right) {
                if (numbers[left] > numbers[left + 1]) {
                    result = left + 1;
                    break;
                }
                if (numbers[right] < numbers[right - 1]) {
                    result = right;
                    break;
                }
                left++;
                right--;
            }
            return numbers[result];
        }
    }

    interface Calculate {
        int getHigh(int[] a);

        int getLow(int[] a);
    }

    class SubSolution extends Solution {

        @Override
        public int minArray(int[] numbers) {

            if (numbers.length == 1) return numbers[0];
            if(numbers.length==2) return  Math.min(numbers[0], numbers[1]);
            if (numbers[0] < numbers[numbers.length - 1]) return numbers[0];

            int i = numbers.length;
            while (i > 0 && numbers[numbers.length - 1] == numbers[--i]) ;
            int low = i;

            i = -1;
            while (i < numbers.length - 1 && numbers[numbers.length - 1] == numbers[++i]) ;
            int hight = i;
//            int hight = 0;
//            int low = numbers.length - 1;

            int mid = 0;
            while (hight <= low) {
                mid = (hight + low) >> 1;
                if (numbers[mid] > numbers[numbers.length - 1])
                    hight = mid + 1;
                else
                    low = mid - 1;
            }

            int mid_1 = mid > 0 ? numbers[mid] > numbers[mid - 1] ? mid - 1 : mid : mid;
            int mid_2 = mid < numbers.length - 1 ? numbers[mid] > numbers[mid + 1] ? mid + 1 : mid : mid;
            return numbers[mid_1] > numbers[mid_2] ? numbers[mid_2] : numbers[mid_1];
        }
    }
}
