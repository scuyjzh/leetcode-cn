package com.scuyjzh.leetcode.easy.No_0349_Intersection_of_Two_Arrays;

import java.util.*;

/**
 * 349. 两个数组的交集
 *
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 说明：
 *   • 输出结果中的每个元素一定是唯一的。
 *   • 可以不考虑输出结果的顺序。
 */
class Solution {
    /**
     * 方法一：集合
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();

        for (int n1 : nums1) {
            set.add(n1);
        }
        for (int n2 : nums2) {
            if (set.contains(n2)) {
                list.add(n2);
                set.remove(n2);
            }
        }
        int[] intersection = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            intersection[i] = list.get(i);
        }
        return intersection;
    }

    /**
     * 方法二：排序 + 双指针
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        /*
         * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
         *
         * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。可以预见的是加入答案的数组的元素一定是递
         * 增的，为了保证加入元素的唯一性，需要额外记录变量 pre 表示上一次加入答案数组的元素。
         *
         * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两个数组中的数字，如果两个数字不
         * 相等，则将指向较小数字的指针右移一位，如果两个数字相等，且该数字不等于 pre，将该数字添加到答案
         * 并更新 pre 变量，同时将两个指针都右移一位。当至少有一个指针超出数组范围时，遍历结束。
         */
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().intersection1(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(new Solution().intersection2(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }
}
