package com.scuyjzh.leetcode.medium.No_0416_Partition_Equal_Subset_Sum;

/**
 * 416. 分割等和子集
 *
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数
 * 组分割成两个子集，使得两个子集的元素和相等。
 */
class Solution {
    public boolean canPartition1(int[] nums) {
        // 问题等效于「能否从数组中挑选若干个元素，使得其总和等于数组所有元素总和的一半」
        // 抽象成「背包问题」：背包容量为 target=sum/2，每个数组元素的「价值」与「成本」都是其数值大小，求能否装满背包
        int n = nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        // dp[i][j] 代表考虑前 i 个数值，其选择数字总和不超过 j 的最大价值
        int[][] dp = new int[n][target + 1];

        // 先处理考虑第 1 件物品（i=0）的情况
        for (int j = 0; j <= target; j++) {
            dp[0][j] = j >= nums[0] ? nums[0] : 0;
        }

        // 再处理考虑其余物品（1≤i<n）的情况
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= target; ++j) {
                // 不选第 i 件物品
                int no = dp[i - 1][j];
                // 选第 i 件物品
                int yes = j >= nums[i] ? dp[i - 1][j - nums[i]] + nums[i] : 0;
                dp[i][j] = Math.max(no, yes);
            }
        }
        // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        return dp[n - 1][target] == target;
    }

    public boolean canPartition2(int[] nums) {
        // 「滚动数组」解法
        int n = nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        // 将「物品维度」修改为 2
        int[][] dp = new int[2][target + 1];
        // 先处理考虑第 1 件物品（i=0）的情况
        for (int j = 0; j <= target; ++j) {
            dp[0][j] = j >= nums[0] ? nums[0] : 0;
        }

        // 再处理考虑其余物品（1≤i<n）的情况
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= target; ++j) {
                // 不选第 i 件物品，将物品维度的使用加上「&1」
                int no = dp[(i - 1) & 1][j];
                // 选第 i 件物品，将物品维度的使用加上「&1」
                int yes = j >= nums[i] ? dp[(i - 1) & 1][j - nums[i]] + nums[i] : 0;
                // 根据当前计算的行号是偶数还是奇数来交替使用第 0 行和第 1 行
                dp[i & 1][j] = Math.max(no, yes);
            }
        }
        // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        // 将物品维度的使用加上「&1」
        return dp[(n - 1) & 1][target] == target;
    }

    public boolean canPartition3(int[] nums) {
        // 「一维空间优化」解法：
        // 只保留代表「剩余容量」的维度，同时将容量遍历方向修改为「从大到小」
        int n = nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        // 将「物品维度」取消，只保留代表「剩余容量」的维度
        int[] dp = new int[target + 1];

        // 先处理考虑第 1 件物品（i=0）的情况
        for (int j = 0; j <= target; ++j) {
            dp[j] = j >= nums[0] ? nums[0] : 0;
        }

        // 再处理考虑其余物品（1≤i<n）的情况
        for (int i = 1; i < n; ++i) {
            // 将容量遍历方向修改为「从大到小」
            for (int j = target; j >= nums[i]; --j) {
                // 不选第 i 件物品
                int no = dp[j];
                // 选第 i 件物品
                int yes = dp[j - nums[i]] + nums[i];
                dp[j] = Math.max(no, yes);
            }
        }
        // 如果最大价值等于 target，说明可以拆分成两个「等和子集」
        return dp[target] == target;
    }

    public boolean canPartition4(int[] nums) {
        // 问题等效于「能否从数组中挑选若干个元素，使得元素总和等于所有元素总和的一半」
        int n = nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        // dp[i][j] 表示从数组的 [0,i) 下标范围内选取若干个正整数（可以是 0 个），
        // 是否存在一种选取方案使得被选取的正整数的和等于 j
        boolean[][] dp = new boolean[n + 1][target + 1];

        // 初始化一个「有效值」
        dp[0][0] = true;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= target; ++j) {
                // 不选第 i 件物品
                boolean no = dp[i - 1][j];
                // 选第 i 件物品
                boolean yes = j >= nums[i - 1] ? dp[i - 1][j - nums[i - 1]] : false;
                // 转移方程：dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]]
                dp[i][j] = no | yes;
            }
        }

        return dp[n][target];
    }

    public boolean canPartition5(int[] nums) {
        // 「滚动数组」解法
        int n = nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        // dp[i][j] 表示从数组的 [0,i) 下标范围内选取若干个正整数（可以是 0 个），
        // 是否存在一种选取方案使得被选取的正整数的和等于 j
        // 修改「物品维度」为 2
        boolean[][] dp = new boolean[2][target + 1];

        // 初始化一个「有效值」
        dp[0][0] = true;

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= target; ++j) {
                // 不选第 i 件物品
                boolean no = dp[(i - 1) & 1][j];
                // 选第 i 件物品
                boolean yes = j >= nums[i - 1] ? dp[(i - 1) & 1][j - nums[i - 1]] : false;
                dp[i & 1][j] = no | yes;
            }
        }

        return dp[n & 1][target];
    }

    public boolean canPartition6(int[] nums) {
        // 「一维空间优化」解法
        int n = nums.length;

        //「等和子集」的和必然是总和的一半
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int target = sum / 2;

        // 对应了总和为奇数的情况，注定不能被分为两个「等和子集」
        if (target * 2 != sum) {
            return false;
        }

        // dp[i][j] 表示从数组的 [0,i) 下标范围内选取若干个正整数（可以是 0 个），
        // 是否存在一种选取方案使得被选取的正整数的和等于 j
        // 取消「物品维度」，只保留代表「剩余容量」的维度
        boolean[] dp = new boolean[target + 1];

        // 初始化一个「有效值」
        dp[0] = true;

        for (int i = 1; i <= n; ++i) {
            // 将容量遍历方向修改为「从大到小」
            for (int j = target; j >=  nums[i - 1]; --j) {
                // 不选第 i 件物品
                boolean no = dp[j];
                // 选第 i 件物品
                boolean yes = dp[j - nums[i - 1]];
                dp[j] = no | yes;
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canPartition1(new int[]{1, 5, 11, 5}));
        System.out.println(new Solution().canPartition2(new int[]{1, 5, 11, 5}));
        System.out.println(new Solution().canPartition3(new int[]{1, 5, 11, 5}));
        System.out.println(new Solution().canPartition4(new int[]{1, 2, 3, 5}));
        System.out.println(new Solution().canPartition5(new int[]{1, 2, 3, 5}));
        System.out.println(new Solution().canPartition6(new int[]{1, 2, 3, 5}));
    }
}
