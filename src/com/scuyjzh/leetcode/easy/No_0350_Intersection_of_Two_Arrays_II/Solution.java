package com.scuyjzh.leetcode.easy.No_0350_Intersection_of_Two_Arrays_II;

import java.util.*;

/**
 * 350. 两个数组的交集 II
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 说明：
 * • 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * • 可以不考虑输出结果的顺序。
 * 进阶：
 * • 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * • 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * • 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
class Solution {
    /**
     * 方法一：哈希表
     * 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。需要遍历两个数组并对哈希表进行操作，哈希表操作的时间复杂度是 O(1)，因此总时间复杂度与两个数组的长度和呈线性关系。
     * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。对较短的数组进行哈希表的操作，哈希表的大小不会超过较短的数组的长度。为返回值创建一个数组 intersection，其长度为较短的数组的长度。
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        /*
         * 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每
         * 个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在
         * 两个数组中出现次数的最小值。
         *
         * 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对
         * 应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如
         * 果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该
         * 数字出现的次数。
         *
         * 为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以
         * 及对应出现的次数，然后遍历较长的数组得到交集。
         */
        return getIntersection(nums1, nums2);
    }

    private int[] getIntersection(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return getIntersection(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    /**
     * 方法二：排序 + 双指针
     * 时间复杂度：O(m*log m+n*log n)，其中 m 和 n 分别是两个数组的长度。对两个数组进行排序的时间复杂度是 O(m*log m+n*log n)，遍历两个数组的时间复杂度是 O(m+n)，因此总时间复杂度是 O(m*log m+n*log n)。
     * 空间复杂度：O(min(m,n))，其中 m 和 n 分别是两个数组的长度。为返回值创建一个数组 intersection，其长度为较短的数组的长度。
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        /*
         * 如果两个数组是有序的，则可以使用双指针的方法得到两个数组的交集。
         *
         * 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
         *
         * 初始时，两个指针分别指向两个数组的头部。每次比较两个指针指向的
         * 两个数组中的数字，如果两个数字不相等，则将指向较小数字的指针右
         * 移一位，如果两个数字相等，将该数字添加到答案，并将两个指针都右
         * 移一位。当至少有一个指针超出数组范围时，遍历结束。
         */
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().intersect1(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(new Solution().intersect2(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }
}