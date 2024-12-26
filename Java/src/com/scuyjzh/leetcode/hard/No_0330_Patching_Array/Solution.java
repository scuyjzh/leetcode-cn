package com.scuyjzh.leetcode.hard.No_0330_Patching_Array;

/**
 * 330. 按要求补齐数组
 * <p>
 * 给定一个已排序的正整数数组 nums，和一个正整数 n 。
 * 从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。
 * 请输出满足上述要求的最少需要补充的数字个数。
 */
class Solution {
    /**
     * 方法：贪心
     * 时间复杂度：O(m+log n)，其中 m 是数组 nums 的长度，n 是给定的正整数。
     * 空间复杂度：O(1)。只需要使用有限的额外空间。
     */
    public int minPatches(int[] nums, int n) {
        // 累加的总和
        long total = 0;
        // 需要补充的数字个数
        int count = 0;
        // 访问的数组下标索引
        int k = 0;
        while (total < n) {
            // 如果数组中前k个数字能组成的区间范围是 [1,total]，那么加上第k+1个数字 nums[k]，
            // 范围就变成了 [1,total]∪[1+nums[k],total+nums[k]]∪[nums[k],nums[k]]，
            // 并集得 [1,total]∪[nums[k],total+nums[k]]
            if (k < nums.length && total >= nums[k] - 1) {
                // 如果左边的 total >= nums[k]-1，那么就可以构成完整的区间 [1,total+nums[k]]
                total += nums[k];
                k++;
            } else {
                // 如果左边的 total < nums[k]-1，那么集合中间肯定存在空缺，无法构成完整的 [1,total+nums[k]]
                // 根据贪心策略，此时补充一个数字 total+1，可以直接构成一个更大的范围 [1,total+total+1]，不会漏掉中间的数字
                total += total + 1;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minPatches(new int[]{1, 3}, 6));
        System.out.println(new Solution().minPatches(new int[]{1, 5, 10}, 20));
    }
}