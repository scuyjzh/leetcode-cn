package com.scuyjzh.leetcode.medium.No_0275_H_Index_II;

/**
 * 275. H 指数 II
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数，citations 已经按照升序排列。计算并返回该研究者的 h指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 n - h篇论文每篇被引用次数不超过 h 次。
 * 提示：如果 h 有多种可能的值，h 指数 是其中最大的那个。
 * 请你设计并实现对数时间复杂度的算法解决此问题。
 */
class Solution {
    /**
     * 方法：二分查找
     * 时间复杂度：O(log n)，其中 n 为数组 citations 的长度。二分查找的时间复杂度为 O(log n)。
     * 空间复杂度：O(1)。
     */
    public int hIndex(int[] citations) {
        /*
         * 思路：
         * 对于下标 i（满足它左侧的元素小于它），被引用次数大于等于 citations[i] 的有 N-i 篇。
         * 即这 N-i 篇的被引用次数 >= citations[i]。即对于citations[i]，h 指数是 N-i 。
         * 因此，要找到最大的 N-i（即最小的 i），使 citations[i] >= N-i（保证满足条件的论文数大于等于 N-i 篇）-> i+citations[i] >= N。
         * 又由于 i+citations[i] 递增，可以使用二分查找法搜索 i 。
         */
        int res = 0;
        int n = citations.length;
        // 设查找范围的初始左边界 left 为 0, 初始右边界 right 为 n−1，其中 n 为数组 citations 的长度
        int left = 0, right = n - 1;
        // <= 是为了处理 citations.length=1 的情况
        while (left <= right) {
            // 每次在查找范围内取中点 mid，则有 n−mid 篇论文被引用了至少 citations[mid] 次
            int mid = left + (right - left) / 2;
            if (mid + citations[mid] >= n) {
                right = mid - 1;
                res = n - mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hIndex(new int[]{0, 1, 3, 5, 6}));
    }
}
