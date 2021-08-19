package com.scuyjzh.leetcode.easy.No_0119_Pascals_Triangle_II;

import java.util.*;

/**
 * 119. 杨辉三角 II
 * <p>
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 进阶：
 * 你可以优化你的算法到 O(rowIndex) 空间复杂度吗？
 */
class Solution {
    /**
     * 方法一：递推
     * 时间复杂度：O(rowIndex^2)。
     * 空间复杂度：O(rowIndex)。
     */
    public List<Integer> getRow1(int rowIndex) {
        // 注意到对第 i+1 行的计算仅用到了第 i 行的数据，因此可以使用滚动数组的思想优化空间复杂度
        List<Integer> row = new ArrayList<>();
        // 首尾项为 1
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            // 当前行第 i 项的计算只与上一行第 i−1 项及第 i 项有关，因此可以倒着计算当前行，这样计算到第 i 项时，第 i−1 项仍然是上一行的值
            for (int j = i - 1; j >= 1; --j) {
                // 从倒数第二个开始遍历到第二个
                row.set(j, row.get(j) + row.get(j - 1));
            }
            // 首尾项为 1
            row.add(1);
            // 调试语句
            System.out.println(row);
        }
        return row;
    }

    /**
     * 方法二：线性递推
     * 时间复杂度：O(rowIndex)。
     * 空间复杂度：O(rowIndex)。
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        // C(n, 0) = 1
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            // 杨辉三角的第 n 行第 m 列的值为组合数 C(n, m)
            // 由组合数公式可以得到同一行的相邻组合数的关系 C(n, m) = C(n, m - 1) * (n - m + 1) / m
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getRow1(4));
        System.out.println(new Solution().getRow2(4));
    }
}
