package com.again.www;

public class LeetcodeisPalindrome {

    class Solution {
        public boolean isPalindrome(int x) {
            if(x<0) return false;
            char[] s = String.valueOf(x).toCharArray();
            for (int i = 0, j = s.length - 1; i <= j; i++, j--) {
                if(s[i] != s[j]) return false;
            }
            return true;
        }
    }
}
