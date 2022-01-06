package com.scuyjzh.leetcode.medium.No_0560_Subarray_Sum_Equals_K;

import java.util.*;

/**
 * 560. 和为 K 的子数组
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 */
class Solution {
    /**
     * 方法一：暴力枚举
     */
    public int subarraySum1(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        // 枚举左右边界
        for (int left = 0; left < len; ++left) {
            for (int right = left; right < len; ++right) {
                int sum = 0;
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                if (sum == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    /**
     * 方法二：暴力枚举的优化
     */
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        for (int left = 0; left < len; ++left) {
            int sum = 0;
            // 区间里可能会有一些互相抵销的元素
            for (int right = left; right < len; ++right) {
                sum += nums[right];
                if (sum == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    /**
     * 方法三：前缀和
     */
    public int subarraySum3(int[] nums, int k) {
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; ++i) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int ans = 0;
        for (int left = 0; left < len; ++left) {
            for (int right = left; right < len; ++right) {
                // 区间和 [left..right]，注意下标偏移
                if (preSum[right + 1] - preSum[left] == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    /**
     * 方法四：前缀和 + 哈希表优化
     */
    public int subarraySum4(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int ans = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                ans += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum1(new int[]{1, 1, 1}, 2));
        System.out.println(new Solution().subarraySum2(new int[]{1, 1, 1}, 2));
        System.out.println(new Solution().subarraySum3(new int[]{1, 2, 3}, 3));
        System.out.println(new Solution().subarraySum4(new int[]{1, 2, 3}, 3));
    }
}
