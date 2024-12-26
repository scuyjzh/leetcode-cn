package com.scuyjzh.leetcode.medium.No_0378_Kth_Smallest_Element_in_a_Sorted_Matrix;

import java.util.*;

/**
 * 378. 有序矩阵中第 K 小的元素
 *
 * 给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到
 * 矩阵中第 k 小的元素。
 * 请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 */
class Solution {
    /**
     * 方法一：直接排序
     */
    public int kthSmallest1(int[][] matrix, int k) {
        int rows = matrix.length, columns = matrix[0].length;
        int[] sorted = new int[rows * columns];
        int index = 0;
        // 最直接的做法是将这个二维数组转成一维数组
        for (int[] row : matrix) {
            for (int num : row) {
                sorted[index++] = num;
            }
        }
        // 并对该一维数组进行排序
        Arrays.sort(sorted);
        // 最后这个一维数组中的第 k 个数即为答案
        return sorted[k - 1];
    }

    /**
     * 方法二：归并排序
     */
    public int kthSmallest2(int[][] matrix, int k) {
        // 创建小根堆
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int n = matrix.length;
        // 问题即转化为从这 n 个有序数组中找第 k 大的数
        for (int i = 0; i < n; ++i) {
            // matrix[i][0] 代表元素值，(i,0) 记录候选元素在矩阵中的位置，方便每次右移添加下个候选元素
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; ++i) {
            int[] triple = pq.poll();
            if (triple[2] != n - 1) {
                pq.offer(new int[]{matrix[triple[1]][triple[2] + 1], triple[1], triple[2] + 1});
            }
        }
        return pq.poll()[0];
    }

    /**
     * 方法三：二分查找
     */
    public int kthSmallest3(int[][] matrix, int k) {
        int n = matrix.length;
        // 二维数组中 matrix[0][0] 为最小值
        int left = matrix[0][0];
        // matrix[n−1][n−1] 为最大值
        int right = matrix[n - 1][n - 1];
        // 任取一个数 mid 满足 l≤mid≤r，那么矩阵中不大于 mid 的数，肯定全部分布在矩阵的左上角
        // 可以线性计算对于任意一个 mid，矩阵中有多少数不大于它。这满足了二分查找的性质
        // 不妨假设答案为 x，那么可以知道 l≤x≤r，这样就确定了二分查找的上下界
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 每次对于「猜测」的答案 mid，计算矩阵中不大于 mid 的数的个数
            if (check(matrix, mid) >= k) {
                // 如果矩阵中不大于 mid 的数的个数不少于 k，那么说明最终答案 x 不大于 mid
                right = mid;
            } else {
                // 如果矩阵中不大于 mid 的数的个数少于 k，那么说明最终答案 x 大于 mid
                left = mid + 1;
            }
        }
        // 这样就可以计算出最终的结果 x 了
        return left;
    }

    private int check(int[][] matrix, int mid) {
        int n = matrix.length;
        // 初始位置在 matrix[n−1][0]（即左下角）
        int i = n - 1, j = 0;
        // 统计矩阵中不大于 mid 的数的个数
        int cnt = 0;
        while (i >= 0 && j < n) {
            // 若 matrix[i][j]≤mid
            if (matrix[i][j] <= mid) {
                // 则将当前所在列的不大于 mid 的数的数量（即 i+1）累加到答案中
                cnt += i + 1;
                // 并向右移动
                ++j;
            } else {
                // 否则向上移动
                --i;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        /*
         * 上述三种解法，
         * 第一种没有利用矩阵的性质，所以时间复杂度最差；
         * 第二种解法只利用了一部分性质（每一行是一个有序数列，而忽视了列之间的关系）；
         * 第三种解法则利用了全部性质，所以时间复杂度最佳。
         */
        System.out.println(new Solution().kthSmallest1(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        System.out.println(new Solution().kthSmallest2(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        System.out.println(new Solution().kthSmallest3(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
    }
}
