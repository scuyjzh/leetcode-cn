package com.scuyjzh.leetcode.medium.No_0167_Two_Sum_II;

import java.util.*;

/**
 * 167. 两数之和 II - 输入有序数组
 *
 * 给定一个已按照 非递减顺序排列 的整数数组numbers ，请你从数组中找
 * 出两个数满足相加之和等于目标数target 。
 *
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers
 * 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] <
 * answer[1] <= numbers.length 。
 *
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的
 * 元素。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        // 初始时两个指针分别指向第一个元素位置和最后一个元素的位置
        int left = 0, right = numbers.length - 1;
        // 使用双指针的实质是缩小查找范围
        while (left < right) {
            // 每次计算两个指针指向的两个元素之和，并和目标值比较
            int sum = numbers[left] + numbers[right];
            // 如果两个元素之和等于目标值，则发现了唯一解
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            // 如果两个元素之和小于目标值，则将左侧指针右移一位
            else if (sum < target) {
                ++left;
            }
            // 如果两个元素之和大于目标值，则将右侧指针左移一位
            else {
                --right;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(new Solution().twoSum(new int[]{-1, 0}, -1)));
    }
}
