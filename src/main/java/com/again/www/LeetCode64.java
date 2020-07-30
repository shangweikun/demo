package com.again.www;

import java.util.Arrays;

public class LeetCode64 {

    class Solution {
        public int minPathSum(int[][] grid) {

            if (grid.length == 0) return 0;

            int[][] result = new int[grid.length][grid[0].length];

            result[0][0] = grid[0][0];

            for (int i = 1; i < grid[0].length; i++) {
                result[0][i] = result[0][i - 1] + grid[0][i];
            }

            for (int i = 1; i < grid.length; i++) {
                result[i][0] = result[i - 1][0] + grid[i][0];
            }


            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[0].length; j++) {
                    result[i][j] = Math.min(result[i - 1][j], result[i][j - 1]) + grid[i][j];
                }
            }

            return result[grid.length - 1][grid[0].length - 1];

        }
    }

    class SubSolution extends Solution {
        @Override
        public int minPathSum(int[][] grid) {

            if (grid.length == 0) return 0;

            int[] result = new int[grid[0].length];
            Arrays.fill(result, 0);

            result[0] = grid[0][0];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0) {
                        if (j == 0)
                            continue;
                        else
                            result[j] = result[j - 1] + grid[i][j];
                    } else {
                        if (j == 0)
                            result[j] = result[j] + grid[i][j];
                        else
                            result[j] = Math.min(result[j], result[j - 1]) + grid[i][j];
                    }
                }
            }

            return result[result.length - 1];
        }
    }

}
