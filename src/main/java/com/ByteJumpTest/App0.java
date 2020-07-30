package com.ByteJumpTest;

import java.util.*;

public class App0 {

    class CQueue {

        Deque<Integer> head;
        Deque<Integer> foot;


        public CQueue() {
            head = new LinkedList<>();
            foot = new LinkedList<>();

        }

        public void appendTail(int value) {
            if (!head.isEmpty()) {
                exchangData(foot, head);
            }
            foot.addLast(value);

        }

        public int deleteHead() {
            if (!foot.isEmpty()) {
                exchangData(head, foot);
            }
            if (!head.isEmpty()) return head.removeLast();
            return -1;
        }

        private void exchangData(Deque<Integer> target, Deque<Integer> input) {
            Iterator<Integer> ite = input.descendingIterator();
            while (ite.hasNext()) {
                target.addLast(ite.next());
            }
            input.clear();
        }
    }

    class Solution {

        public int lengthOfLongestSubstring(String s) {

            if (s.trim().isEmpty()) return 0;

            char[] in = s.toCharArray();
            int begin = 0;
            int end = 0;
            int temp;
            int result = Integer.MIN_VALUE;
            for (int i = 0; i < in.length; i++) {
                end = i;
                if (begin < end) {
                    temp = begin;
                    while (temp < end) {
                        if (in[temp] == in[end]) {
                            begin = temp + 1;
                            break;
                        }
                        temp++;
                    }
                }
                result = result < end - begin + 1 ? end - begin + 1 : result;
            }

            return result;
        }
    }

}
