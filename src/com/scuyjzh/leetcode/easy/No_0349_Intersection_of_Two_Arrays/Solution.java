package com.scuyjzh.leetcode.easy.No_0349_Intersection_of_Two_Arrays;

import java.util.*;

/**
 * 349. 两个数组的交集
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 说明：
 * • 输出结果中的每个元素一定是唯一的。
 * • 可以不考虑输出结果的顺序。
 */
class Solution {
    /**
     * 方法一：两个集合
     * 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。使用两个集合分别存储两个数组中的元素需要 O(m+n) 的时间，遍历较小的集合并判断元素是否在另一个集合中需要 O(min(m,n)) 的时间，因此总时间复杂度是 O(m+n)。
     * 空间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。空间复杂度主要取决于两个集合。
     */
    public int[] intersection1(int[] nums1, int[] nums2) {
        /*
         * 计算两个数组的交集，直观的方法是遍历数组 nums1，对于其中的每个
         * 元素，遍历数组 nums2 判断该元素是否在数组 nums2 中，如果存在，
         * 则将该元素添加到返回值。
         *
         * 假设数组 nums1 和 nums2 的长度分别是 m 和 n，则遍历数组
         * nums1 需要 O(m) 的时间，判断 nums1 中的每个元素是否在数组
         * nums2 中需要 O(n) 的时间，因此总时间复杂度是 O(mn)。
         *
         * 如果使用哈希集合存储元素，则可以在 O(1) 的时间内判断一个元素是
         * 否在集合中，从而降低时间复杂度。
         *
         * 首先使用两个集合分别存储两个数组中的元素，然后遍历较小的集合，判
         * 断其中的每个元素是否在另一个集合中，如果元素也在另一个集合中，则
         * 将该元素添加到返回值。该方法的时间复杂度可以降低到 O(m+n)。
         */
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
    }

    private int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1);
        }
        Set<Integer> intersectionSet = new HashSet<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }

    /**
     * 方法二：排序 + 双指针
     * 时间复杂度：O(m*log m+n*log n)，其中 m 和 n 分别是两个数组的长度。对两个数组排序的时间复杂度分别是 O(m*log m) 和 O(n*log n)，双指针寻找交集元素的时间复杂度是 O(m+n)，因此总时间复杂度是 O(m*log m+n*log n)。
     * 空间复杂度：O(log m+log n)，其中 m 和 n 分别是两个数组的长度。空间复杂度主要取决于排序使用的额外空间。
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        /*
         * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
         *
         * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。可以预见的
         * 是加入答案的数组的元素一定是递增的，为了保证加入元素的唯一性，需
         * 要额外记录变量 pre 表示上一次加入答案数组的元素。
         *
         * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的两
         * 个数组中的数字，如果两个数字不相等，则将指向较小数字的指针右移一
         * 位，如果两个数字相等，且该数字不等于 pre，将该数字添加到答案并
         * 更新 pre 变量，同时将两个指针都右移一位。当至少有一个指针超出数
         * 组范围时，遍历结束。
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