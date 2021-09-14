package com.scuyjzh.leetcode.medium.No_0056_Merge_Intervals;

import java.util.*;

/**
 * 56. 合并区间
 * <p>
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [start_i, end_i] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 */
class Solution {
    /**
     * 方法：排序
     * 时间复杂度：O(NlogN)，其中 N 为区间的数量。除去排序的开销，只需要一次线性扫描，所以主要的时间开销是排序的 O(NlogN)。
     * 空间复杂度：O(NlogN)，其中 N 为区间的数量。这里计算的是存储答案之外，使用的额外空间。O(logN) 即为排序所需要的空间复杂度。
     */
    public int[][] merge(int[][] intervals) {
        /*
         * 思路：
         * 如果按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的。
         *
         * 算法：
         * 用数组 merged 存储最终的答案。
         * 首先，将列表中的区间按照左端点升序排序。
         * 然后将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
         *   • 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，可以直接将这个区间加入数组 merged 的末尾；
         *   • 否则，它们重合，需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
         */
        if (intervals.length == 0) {
            return new int[0][2];
        }
        // 按照区间的左端点排序
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        // 用数组 merged 存储最终的答案
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            int left = interval[0], right = interval[1];
            // 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，可以直接将这个区间加入数组 merged 的末尾
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                merged.add(new int[]{left, right});
            }
            // 否则，它们重合，需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值
            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().merge(new int[][]{{2, 6}, {1, 3}, {8, 10}, {15, 18}})));
    }
}