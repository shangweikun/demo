package com.again.www;

public class LeetCode0 {
    class Solution {

        /**
         *
         63. 不同路径 II

         一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

         机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

         现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
         */

        int reuslt;

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {

            int[][] f = new int[obstacleGrid.length][obstacleGrid[0].length];

            if (obstacleGrid[0][0] == 0) f[0][0] = 1;

            for (int i = 1; i < obstacleGrid.length; i++) {
                if (obstacleGrid[i][0] == 1) f[i][0] = 0;
                else f[i][0] = f[i - 1][0];
            }

            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[0][j] == 1) f[0][j] = 0;
                else f[0][j] = f[0][j - 1];
            }

            for (int i = 1; i < obstacleGrid.length; i++) {
                for (int j = 1; j < obstacleGrid[0].length; j++) {
                    if (obstacleGrid[i][j] == 1) f[i][j] = 0;
                    else f[i][j] = f[i - 1][j] + f[i][j - 1];
                }
            }

            return f[obstacleGrid.length - 1][obstacleGrid[0].length - 1];

        }

        public int _uniquePathsWithObstacles(int[][] obstacleGrid) {

            int[] f = new int[obstacleGrid[0].length];

            f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;

            /**
             * 旧f[i] 相当于f[i-1][j],上一行计算完毕的
             * f[j-1] 相当于是f[i][j-1]前一个刚计算完的前置节点
             */
            for (int i = 0; i < obstacleGrid.length; i++) {
                for (int j = 0; j < obstacleGrid[0].length; j++) {

                    if (obstacleGrid[i][j] == 1) {
                        f[j] = 0;
                        continue;
                    }
                    if (j > 0 && obstacleGrid[i][j] == 0) {
                        f[j] += f[j - 1];
                    }
                }
            }

            return f[obstacleGrid[0].length - 1];

        }


        public int uniquePathsWithObstacles(int[][] obstacleGrid, boolean flag) {

            if (obstacleGrid.length == 0) return 0;
            calculate(obstacleGrid, 0, 0);
            return reuslt;
        }

        private void calculate(int[][] a, int i, int i1) {

            if (i == a[0].length - 1 && i1 == a.length - 1) {
                if (a[i][i1] == 0) {
                    reuslt++;
                    return;
                }
            }
            if (a[i][i1] == 1) return;
            if (i < a[0].length - 1)
                calculate(a, i + 1, i1);

            if (i1 < a.length - 1)
                calculate(a, i, i1 + 1);
        }

    }

    public static void main(String[] args) {
        int[][] a = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(a.length);
        System.out.println(a[0].length);
    }
}
