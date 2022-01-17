package com.scuyjzh.leetcode.medium.No_0075_Sort_Colors;

import java.util.*;

/**
 * 75. 颜色分类
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行
 * 排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 */
class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return;
        }

        // 可以在区间上设置两个表示分界的位置，并且定义循环不变量：
        // 所有的元素在区间 [0..zero) = 0；
        // 所有的元素在区间 [zero..i) = 1；
        // 区间 [i..two) 是程序没有看到的部分；
        // 所有的元素在区间 [two..len - 1] = 2，这里 len 表示数组的长度。
        // 为了让初始化的时候三个区间都为空区间，zero = 0，two = len，程序没有看到的部分是整个数组。
        int zero = 0;
        int two = len;
        int i = 0;
        // 程序什么时候终止呢？
        // 当 i == two 时，三个子区间正好覆盖了整个数组，程序没有看到的部分为空，因此循环可以继续的条件是：i < two 。
        while (i < two) {
            if (nums[i] == 0) {
                swap(nums, i, zero);
                ++zero;
                ++i;
            } else if (nums[i] == 1) {
                ++i;
            } else {
                --two;
                swap(nums, i, two);
                // 注意：此时 i 不向前走
            }
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        new Solution().sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
