package com.scuyjzh.leetcode.medium.No_0325_Maximum_Size_Subarray_Sum_Equals_k;

import java.util.*;

/**
 * 325. 和等于 k 的最长子数组长度
 * <p>
 * 给定一个数组 nums 和一个目标值 k，找到和等于 k 的最长连续子数组长度。
 * 如果不存在任意一个符合要求的子数组，则返回 0。
 */
class Solution {
    /**
     * 方法：前缀和 + 哈希表
     */
    public int maxSubArrayLen(int[] nums, int k) {
        // 使用前缀和 preSum 来记录截止到 i 位置时，得到的 nums[0:i] 之和
        int preSum = 0;
        // 使用哈希表来记录第一次 preSum 出现的位置 i
        Map<Integer, Integer> preSumIndex = new HashMap<>();
        // 0 出现在 -1 位置处
        preSumIndex.put(0, -1);
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            // 累加前缀和
            preSum += nums[i];
            // 确保记录 preSum 第一次出现的位置
            if (!preSumIndex.containsKey(preSum)) {
                preSumIndex.put(preSum, i);
            }
            // 检查是否需要更新答案
            if (preSumIndex.containsKey(preSum - k)) {
                ans = Math.max(ans, i - preSumIndex.get(preSum - k));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxSubArrayLen(new int[]{1, -1, 5, -2, 3}, 3));
    }
}