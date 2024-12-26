package com.scuyjzh.leetcode.easy.No_0228_Summary_Ranges;

import java.util.*;

/**
 * 228. 汇总区间
 * <p>
 * 给定一个无重复元素的有序整数数组 nums 。
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。
 * 也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 * • "a->b" ，如果 a != b
 * • "a" ，如果 a == b
 */
class Solution {
    /**
     * 方法：一次遍历
     * 时间复杂度：O(N)，其中 N 为数组的长度。
     * 空间复杂度：O(1)。除了用于输出的空间外，额外使用的空间为常数。
     */
    public List<String> summaryRanges(int[] nums) {
        /*
         * 从数组的位置 0 出发，向右遍历。
         * 每次遇到相邻元素之间的差值大于 1 时，就找到了一个区间。
         * 遍历完数组之后，就能得到一系列的区间的列表。
         *
         * 在遍历过程中，维护下标 left 和 right 分别记录区间的起点和终点，对于任何区间都有 left ≤ right。
         * 当得到一个区间时，根据 left 和 right 的值生成区间的字符串表示。
         *   • 当 left < right 时，区间的字符串表示为 “left→right”；
         *   • 当 left = right 时，区间的字符串表示为 “left”。
         */
        List<String> ret = new ArrayList<>();
        int i = 0;
        int n = nums.length;
        while (i < n) {
            int left = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            int right = i - 1;
            StringBuilder temp = new StringBuilder(Integer.toString(nums[left]));
            if (left < right) {
                temp.append("->");
                temp.append(nums[right]);
            }
            ret.add(temp.toString());
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        System.out.println(new Solution().summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    }
}