package com.scuyjzh.leetcode.medium.No_0377_Combination_Sum_IV;

/**
 * 377. 组合总和 Ⅳ
 *
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums
 * 中找出并返回总和为 target 的元素组合的个数。
 * 题目数据保证答案符合 32 位整数范围。
 */
class Solution {
    /**
     * 方法：动态规划
     *
     * • 时间复杂度：O(target×n)，其中 target 是目标值，n 是数组 nums 的长度。需要计算长度为 target+
     *   1 的数组 dp 的每个元素的值，对于每个元素，需要遍历数组 nums 之后计算元素值。
     * • 空间复杂度：O(target)。需要创建长度为 target+1 的数组 dp。
     */
    public int combinationSum4(int[] nums, int target) {
        /*
         * 这道题中，给定数组 nums 和目标值 target，要求计算从 nums 中选取若干个元素，使得它们的和等于 target
         * 的方案数。其中，nums 的每个元素可以选取多次，且需要考虑选取元素的顺序。由于需要考虑选取元素的
         * 顺序，因此这道题需要计算的是选取元素的排列数。
         *
         * 可以通过动态规划的方法计算可能的方案数。用 dp[x] 表示选取的元素之和等于 x 的方案数，目标是求
         * dp[target]。
         *
         * 动态规划的边界是 dp[0]=1。只有当不选取任何元素时，元素之和才为 0，因此只有 1 种方案。
         *
         * 当 1≤i≤target 时，如果存在一种排列，其中的元素之和等于 i，则该排列的最后一个元素一定是数组
         * nums 中的一个元素。假设该排列的最后一个元素是 num，则一定有 num≤i，对于元素之和等于 i−num
         * 的每一种排列，在最后添加 num 之后即可得到一个元素之和等于 i 的排列，因此在计算 dp[i] 时，应该计算
         * 所有的 dp[i−num] 之和。
         *
         * 由此可以得到动态规划的做法：
         *   • 初始化 dp[0]=1；
         *   • 遍历 i 从 1 到 target，对于每个 i，进行如下操作：
         *       ○ 遍历数组 nums 中的每个元素 num，当 num≤i 时，将 dp[i−num] 的值加到 dp[i]。
         *   • 最终得到 dp[target] 的值即为答案。
         *
         * 上述做法是否考虑到选取元素的顺序？答案是肯定的。因为外层循环是遍历从 1 到 target 的值，内层循环是
         * 遍历数组 nums 的值，在计算 dp[i] 的值时，nums 中的每个小于等于 i 的元素都可能作为元素之和等于 i 的
         * 排列的最后一个元素。例如，1 和 3 都在数组 nums 中，计算 dp[4] 的时候，排列的最后一个元素可以是 1 也
         * 可以是 3，因此 dp[1] 和 dp[3] 都会被考虑到，即不同的顺序都会被考虑到。
         */
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; ++i) {
            for (int num : nums) {
                if (num <= i) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum4(new int[]{1, 2, 3}, 4));
        System.out.println(new Solution().combinationSum4(new int[]{9}, 3));
    }
}
