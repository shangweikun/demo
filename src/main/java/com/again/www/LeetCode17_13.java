package com.again.www;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode17_13 {

    class Solution {
        static final long P = Integer.MAX_VALUE;
        static final long BASE = 41;

        public int respace(String[] dictionary, String sentence) {

            Set<Long> hashSets = new HashSet<>();
            for (int i = 0; i < dictionary.length; i++) {
                hashSets.add(getHash(dictionary[i]));
            }

            int[] f = new int[sentence.length() + 1];//从1开始
            Arrays.fill(f, sentence.length());
            f[0] = 0;
            for (int i = 1; i <= sentence.length(); i++) {
                f[i] = f[i - 1] + 1;
                long hashValue = 0;
                for (int j = i; j >= 1; j--) {
                    /**
                     * 倒序查询，保证识别从 从 0-i 所有的子串s'
                     */
                    int t = sentence.charAt(j - 1) - 'a' + 1; // 第j位的 字符char
                    hashValue = (hashValue * BASE + t) % P;
                    if (hashSets.contains(hashValue))
                        f[i] = Math.min(f[j - 1], f[i]); // j-1长度 的最小无识别字符
                }
            }
            return f[sentence.length()];
        }

        private long getHash(String s) {
            long hashValue = 0;
            for (int i = s.length() - 1; i >= 0; --i) {
                hashValue = (hashValue * BASE + s.charAt(i) - 'a' + 1) % P;
            }
            return hashValue;

        }
    }
}
