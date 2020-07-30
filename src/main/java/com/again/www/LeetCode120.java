package com.again.www;

import java.util.*;

public class LeetCode120 {
    class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {

            int[][] dp = new int[triangle.size()][triangle.get(triangle.size() - 1).size()];
            int result = triangle.get(0).get(0);

            dp[0][0] = triangle.get(0).get(0);
            for (int i = 1; i < triangle.size(); i++) {
                dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
                dp[i][i] = dp[i - 1][i - 1] + triangle.get(i).get(i);
            }
            int tmp = 0;
            for (int i = 1; i < triangle.size(); i++) {
                for (int j = 1; j < i; j++) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }

            Arrays.sort(dp[triangle.size() - 1]);

            return dp[triangle.size() - 1][0];
        }
    }
}
