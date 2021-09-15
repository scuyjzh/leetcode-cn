package com.scuyjzh.leetcode.easy.No_0053_Maximum_Subarray;

/**
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
class Solution {
    /**
     * 方法：动态规划
     * 时间复杂度：O(n)，其中 n 为 nums 数组的长度。只需要遍历一遍数组即可求得答案。
     * 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public int maxSubArray(int[] nums) {
        /*
         * 思路和算法：
         * 假设 nums 数组的长度是 n，下标从 0 到 n−1。
         * 用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然要求的答案就是：
         *     max{f(0),f(1),f(2),...,f(n-1)}
         *
         * 因此只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。
         * 那么如何求 f(i) 呢？可以考虑 nums[i] 单独成为一段还是加入 f(i−1) 对应的那一段，
         * 这取决于 nums[i] 和 f(i−1)+nums[i] 的大小，希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
         *     f(i)=max{f(i−1)+nums[i],nums[i]}
         *
         * 不难给出一个时间复杂度 O(n)、空间复杂度 O(n) 的实现，即用一个 f 数组来保存 f(i) 的值，用一个循环求出所有 f(i)。
         * 考虑到 f(i) 只和 f(i−1) 相关，于是可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i−1) 的值是多少，从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想。
         */
        int pre = 0, maxAns = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}