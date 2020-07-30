package com.again.www;

import java.util.Deque;
import java.util.LinkedList;

public class LeetcodedailyTemperatures {
    class Solution {
        public int[] dailyTemperatures(int[] T) {
            int[] result = null;
            if (T.length == 0) return result;
            result = new int[T.length];
            int tmp;
            Deque<Integer> deque = new LinkedList<>();
            deque.add(0);
            for (int i = 1; i < T.length; i++) {

                while (!deque.isEmpty() && T[deque.peekLast()] < T[i]) {
                    tmp = deque.pollLast();
                    result[tmp] = i - tmp;
                }
                deque.add(i);

            }
            while (!deque.isEmpty())
                result[deque.pollLast()] = 0;

            return result;
        }
    }
}
