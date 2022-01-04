package com.scuyjzh.leetcode.medium.No_0152_Maximum_Product_Subarray;

/**
 * 152. 乘积最大子数组
 *
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子
 * 数组中至少包含一个数字），并返回该子数组所对应的乘积。
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int maxProduct1(int[] nums) {
        /*
         * 可以根据正负性进行分类讨论。
         *
         * 考虑当前位置如果是一个负数的话，那么希望以它前一个位置结尾的某个段的积也是个负数，这样就可
         * 以负负得正，并且希望这个积尽可能「负得更多」，即尽可能小。如果当前位置是一个正数的话，
         * 更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。
         *
         * 于是这里可以再维护一个 f_min(i)，它表示以第 i 个元素结尾的乘积最小子数组的乘积。
         */
        int length = nums.length;
        // 维护一个 f_max(i) 来表示以第 i 个元素结尾的乘积最大子数组的乘积
        int[] maxF = new int[length];
        // 维护一个 f_min(i) 来表示以第 i 个元素结尾的乘积最小子数组的乘积
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            // 把 nums[i] 加入第 i−1 个元素结尾的乘积最大或最小的子数组的乘积中，二者加上 nums[i]，三者取大，就是第 i 个元素结尾的乘积最大子数组的乘积 f_max(i)
            maxF[i] = Math.max(nums[i], Math.max(maxF[i - 1] * nums[i], minF[i - 1] * nums[i]));
            // 第 i 个元素结尾的乘积最小子数组的乘积 f_min(i) 同理
            minF[i] = Math.min(nums[i], Math.min(minF[i - 1] * nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int maxProduct2(int[] nums) {
        /*
         * 考虑优化空间。
         *
         * 由于第 i 个状态只和第 i−1 个状态相关，根据「滚动数组」思想，可以只用两个变量来维护 i−1 时刻
         * 的状态，一个维护 f_max，一个维护 f_min。
         */
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(nums[i], Math.max(mx * nums[i], mn * nums[i]));
            minF = Math.min(nums[i], Math.min(mn * nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct1(new int[]{2, 3, -2, 4}));
        System.out.println(new Solution().maxProduct2(new int[]{-2, 0, -1}));
    }
}
