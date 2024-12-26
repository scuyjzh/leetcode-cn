package com.scuyjzh.leetcode.medium.No_0494_Target_Sum;

import java.util.*;

/**
 * 494. 目标和
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构
 * 造一个 表达式 ：
 *   • 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添
 *     加 '-' ，然后串联起来得到表达式 "+2-1" 。
 *
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */
class Solution {
    /**
     * 方法一：深度优先搜索（回溯）
     */
    public int findTargetSumWays1(int[] nums, int target) {
        /*
         * 数组 nums 的每个元素都可以添加符号 + 或 -，因此每个元素有 2 种添加符号的方法，n 个数共有 2^n 种添加
         * 符号的方法，对应 2^n 种不同的表达式。当 n 个元素都添加符号之后，即得到一种表达式，如果表达式的结
         * 果等于目标数 target，则该表达式即为符合要求的表达式。
         */
        return dfs1(nums, target, 0, 0);
    }

    private int dfs1(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }
        int left = dfs1(nums, target, index + 1, sum + nums[index]);
        int right = dfs1(nums, target, index + 1, sum - nums[index]);
        return left + right;
    }

    /**
     * 方法二：记忆化深度优先搜索
     */
    public int findTargetSumWays2(int[] nums, int target) {
        return dfs2(nums, target, 0, 0);
    }

    /**
     * 在 DFS 的函数签名中只有「数值下标 index」和「当前结算结果 sum」为可变参数，
     * 考虑将其作为记忆化容器的两个维度，返回值作为记忆化容器的记录值。
     * 由于 sum 存在负权值，为了方便，不设计成静态数组，而是使用「哈希表」进行记录。
     */
    Map<String, Integer> cache = new HashMap<>();

    private int dfs2(int[] nums, int target, int index, int sum) {
        String key = index + "_" + sum;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (index == nums.length) {
            cache.put(key, sum == target ? 1 : 0);
            return cache.get(key);
        }
        int left = dfs2(nums, target, index + 1, sum + nums[index]);
        int right = dfs2(nums, target, index + 1, sum - nums[index]);
        cache.put(key, left + right);
        return cache.get(key);
    }

    /**
     * 方法三：动态规划
     */
    public int findTargetSumWays3(int[] nums, int target) {
        /*
         * 记数组的元素和为 sum，添加 - 号的元素之和为 neg，则其余添加 + 的元素之和为 sum−neg，得到的表达
         * 式的结果为 (sum−neg)−neg=sum−2⋅neg=target，即 neg=(sum-target)/2。
         *
         * 由于数组 nums 中的元素都是非负整数，neg 也必须是非负整数，所以上式成立的前提是 sum−target 是非
         * 负偶数。若不符合该条件可直接返回 0。
         *
         * 若上式成立，问题转化成在数组 nums 中选取若干元素，使得这些元素之和等于 neg，计算选取元素的方案
         * 数。可以使用动态规划的方法求解。
         *
         * 定义二维数组 dp，其中 dp[i][j] 表示在数组 nums 的前 i 个数中选取元素，使得这些元素之和等于 j 的方案
         * 数。假设数组 nums 的长度为 n，则最终答案为 dp[n][neg]。
         *
         * 当没有任何元素可以选取时，元素和只能是 0，对应的方案数是 1，因此动态规划的边界条件是：
         * 当 j=0，dp[0][j]=1；当j≥1，dp[0][j]=0。
         *
         * 当 1≤i≤n 时，对于数组 nums 中的第 i 个元素 num（i 的计数从 1 开始），遍历 0≤j≤neg，计算
         * dp[i][j] 的值：
         *   • 如果 j<num，则不能选 num，此时有 dp[i][j]=dp[i−1][j]；
         *   • 如果 j≥num，则如果不选 num，方案数是 dp[i−1][j]，如果选 num，方案数是 dp[i−1][j−num]，
         *     此时有 dp[i][j]=dp[i−1][j]+dp[i−1][j−num]。
         *
         * 最终得到 dp[n][neg] 的值即为答案。
         *
         * 由此可以得到空间复杂度为 O(n×neg) 的实现。
         */
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || (diff & 1) == 1) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][neg];
    }

    /**
     * 方法四：动态规划 + 空间优化
     */
    public int findTargetSumWays4(int[] nums, int target) {
        /*
         * 由于 dp 的每一行的计算只和上一行有关，因此可以使用滚动数组的方式，去掉 dp 的第一个维度，将空间复
         * 杂度优化到 O(neg)。
         *
         * 实现时，内层循环需采用倒序遍历的方式，这种方式保证转移来的是 dp[i−1][] 中的元素值。
         */
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || (diff & 1) == 1) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            // 内层循环需采用倒序遍历的方式，保证转移来的是上一层循环得到的元素值
            for (int j = neg; j >= nums[i - 1]; --j) {
                dp[j] += dp[j - nums[i - 1]];
            }
        }
        return dp[neg];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findTargetSumWays1(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(new Solution().findTargetSumWays2(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(new Solution().findTargetSumWays3(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(new Solution().findTargetSumWays4(new int[]{1, 1, 1, 1, 1}, 3));
    }
}
