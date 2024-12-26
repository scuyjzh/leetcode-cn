package com.scuyjzh.leetcode.medium.No_0396_Rotate_Function;

/**
 * 396. 旋转函数
 *
 * 给定一个长度为 n 的整数数组 A 。
 * 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，定义 A 的“旋转函
 * 数” F 为：
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
 * 计算F(0), F(1), ..., F(n-1)中的最大值。
 * 注意:
 * 可以认为 n 的值小于 10^5。
 */
class Solution {
    public int maxRotateFunction(int[] nums) {
        // 经推导可得：F(i+1) = F(i) - sum(nums) + N * nums[i]
        int F = 0, sum = 0;
        int N = nums.length;
        for (int i = 0; i < N; ++i) {
            sum += nums[i];
            F += i * nums[i];
        }
        int ans = F;
        for (int i = 1; i < N; ++i) {
            F = F - sum + N * nums[i - 1];
            ans = Math.max(ans, F);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {4, 3, 2, 6};
        System.out.println(s.maxRotateFunction(nums));
    }
}
