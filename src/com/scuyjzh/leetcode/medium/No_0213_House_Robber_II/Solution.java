package com.scuyjzh.leetcode.medium.No_0213_House_Robber_II;

/**
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是
 * 紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房
 * 屋在同一晚上被小偷闯入，系统会自动报警 。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装
 * 置的情况下 ，今晚能够偷窃到的最高金额。
 */
class Solution {
    /**
     * 方法：动态规划（空间优化）
     */
    public int rob(int[] nums) {
        /*
         * 这道题是「198. 打家劫舍」的进阶，和第 198 题的不同之处是，这道题中的房屋是首尾相连的，第一间房屋
         * 和最后一间房屋相邻，因此第一间房屋和最后一间房屋不能在同一晚上偷窃。
         * 和第 198 题相似，这道题也可以使用动态规划解决。
         *
         * 首先考虑最简单的情况。如果只有一间房屋，则偷窃该房屋，可以偷窃到最高总金额。如果只有两间房屋，
         * 则由于两间房屋相邻，不能同时偷窃，只能偷窃其中的一间房屋，因此选择其中金额较高的房屋进行偷窃，
         * 可以偷窃到最高总金额。
         *
         * 注意到当房屋数量不超过两间时，最多只能偷窃一间房屋，因此不需要考虑首尾相连的问题。如果房屋数量
         * 大于两间，就必须考虑首尾相连的问题，第一间房屋和最后一间房屋不能同时偷窃。
         *
         * 如何才能保证第一间房屋和最后一间房屋不同时偷窃呢？如果偷窃了第一间房屋，则不能偷窃最后一间房
         * 屋，因此偷窃房屋的范围是第一间房屋到最后第二间房屋；如果偷窃了最后一间房屋，则不能偷窃第一间房
         * 屋，因此偷窃房屋的范围是第二间房屋到最后一间房屋。
         *
         * 假设数组 nums 的长度为 n。如果不偷窃最后一间房屋，则偷窃房屋的下标范围是 [0,n−2]；如果不偷窃第
         * 一间房屋，则偷窃房屋的下标范围是 [1,n−1]。在确定偷窃房屋的下标范围之后，即可用第 198 题的方法解
         * 决。对于两段下标范围分别计算可以偷窃到的最高总金额，其中的最大值即为在 n 间房屋中可以偷窃到的最
         * 高总金额。
         *
         * 假设偷窃房屋的下标范围是 [start,end]，用 dp[i] 表示在下标范围 [start,i] 内可以偷窃到的最高总金额，那么
         * 就有如下的状态转移方程：
         *     dp[i]=max(dp[i−2]+nums[i],dp[i−1])
         *
         * 边界条件为：
         *     dp[start]=nums[start]（只有一间房屋，则偷窃该房屋）
         *     dp[start+1]=max(nums[start],nums[start+1])（只有两间房屋，偷窃其中金额较高的房屋）
         *
         * 计算得到 dp[end] 即为下标范围 [start,end] 内可以偷窃到的最高总金额。
         * 分别取 (start,end)=(0,n−2) 和 (start,end)=(1,n−1) 进行计算，取两个 dp[end] 中的最大值，即可得到
         * 最终结果。
         *
         * 根据上述思路，可以得到时间复杂度 O(n) 和空间复杂度 O(n) 的实现。考虑到每间房屋的最高总金额只和该
         * 房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，在每个时刻只需要存储前两间房屋的最高总
         * 金额，将空间复杂度降到 O(1)。
         */
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        } else if (n == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int first = nums[start], second = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; ++i) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rob(new int[]{2, 3, 2}));
        System.out.println(new Solution().rob(new int[]{1, 2, 3, 1}));
    }
}
