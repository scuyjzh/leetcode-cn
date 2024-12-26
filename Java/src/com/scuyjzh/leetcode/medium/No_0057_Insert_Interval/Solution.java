package com.scuyjzh.leetcode.medium.No_0057_Insert_Interval;

import java.util.*;

/**
 * 57. 插入区间
 * <p>
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 */
class Solution {
    /**
     * 方法：模拟
     * 时间复杂度：O(N)，其中 N 是数组 intervals 的长度，即给定的区间个数。
     * 空间复杂度：O(1)。除了存储返回答案的空间以外，只需要额外的常数空间即可。
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
         * 思路与算法：
         * 在给定的区间集合 X 互不重叠的前提下，当需要插入一个新的区间 S=[left,right] 时，只需要：
         *   • 找出所有与区间 S 重叠的区间集合 X′；
         *   • 将 X′ 中的所有区间连带上区间 S 合并成一个大区间；
         *   • 最终的答案即为不与 X′ 重叠的区间以及合并后的大区间。
         * 这样做的正确性在于，给定的区间集合中任意两个区间都是没有交集的，因此所有需要合并的区间，就是所有与区间 S 重叠的区间。
         *
         * 并且，在给定的区间集合已经按照左端点排序的前提下，所有与区间 S 重叠的区间在数组 intervals 中下标范围是连续的，
         * 因此可以对所有的区间进行一次遍历，就可以找到这个连续的下标范围。
         *
         * 当遍历到区间 [l_i, r_i] 时：
         *   • 如果 r_i < left，说明 [l_i, r_i] 与 S 不重叠并且在其左侧，可以直接将 [l_i, r_i] 加入答案；
         *   • 如果 r_i > left，说明 [l_i, r_i] 与 S 不重叠并且在其右侧，可以直接将 [l_i, r_i] 加入答案；
         *   • 如果上面两种情况均不满足，说明 [l_i, r_i] 与 S 重叠，无需将 [l_i, r_i] 加入答案。
         *     此时，需要将 S 与 [l_i, r_i] 合并，即将 S 更新为其与 [l_i, r_i] 的并集。
         *
         * 那么应当在什么时候将区间 S 加入答案呢？
         * 由于需要保证答案也是按照左端点排序的，因此当遇到“第一个”满足 l_i > right 的区间时，
         * 说明以后遍历到的区间不会与 S 重叠，并且它们左端点一定会大于 S 的左端点。此时就可以将 S 加入答案。
         * 特别地，如果不存在这样的区间，需要在遍历结束后，将 S 加入答案。
         */
        int left = newInterval[0];
        int right = newInterval[1];
        // placed 变量记录区间 S 是否已加入答案
        boolean placed = false;
        List<int[]> ansList = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[0] > right) {
                // 在插入区间的右侧且无交集
                if (!placed) {
                    ansList.add(new int[]{left, right});
                    placed = true;
                }
                ansList.add(interval);
            } else if (interval[1] < left) {
                // 在插入区间的左侧且无交集
                ansList.add(interval);
            } else {
                // 与插入区间有交集，计算它们的并集
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!placed) {
            ansList.add(new int[]{left, right});
        }
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ansList.size(); ++i) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
        System.out.println(Arrays.deepToString(new Solution().insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
        System.out.println(Arrays.deepToString(new Solution().insert(new int[][]{}, new int[]{5, 7})));
        System.out.println(Arrays.deepToString(new Solution().insert(new int[][]{{1, 5}}, new int[]{2, 3})));
        System.out.println(Arrays.deepToString(new Solution().insert(new int[][]{{1, 5}}, new int[]{2, 7})));
    }
}