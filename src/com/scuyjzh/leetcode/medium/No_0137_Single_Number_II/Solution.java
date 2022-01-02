package com.scuyjzh.leetcode.medium.No_0137_Single_Number_II;

import java.util.*;

/**
 * 137. 只出现一次的数字 II
 *
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰
 * 出现 三次 。请你找出并返回那个只出现了一次的元素。
 *
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
class Solution {
    /**
     * 方法一：哈希表
     *
     * • 时间复杂度：O(n)，其中 n 是数组的长度。
     * • 空间复杂度：O(n)。哈希映射中包含最多 ⌊n/3⌋+1 个元素，即需要的空间为 O(n)。
     */
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int num = entry.getKey(), occ = entry.getValue();
            if (occ == 1) {
                ans = num;
                break;
            }
        }
        return ans;
    }

    /**
     * 方法二：依次确定每一个二进制位
     *
     * • 时间复杂度：O(nlogC)，其中 n 是数组的长度，C 是元素的数据范围，在本题中 logC = 32，
     *   也就是需要遍历第 0∼31 个二进制位。
     * • 空间复杂度：O(1)。
     */
    public int singleNumber2(int[] nums) {
        /*
         * 为了方便叙述，称「只出现了一次的元素」为「答案」。
         *
         * 由于数组中的元素都在 int（即 32 位整数）范围内，因此可以依次计算答案的每一个二进制位是 0 还是 1。
         *
         * 具体地，考虑答案的第 i 个二进制位（i 从 0 开始编号），它可能为 0 或 1。对于数组中非答案的元素，每一
         * 个元素都出现了 3 次，对应着第 i 个二进制位的 3 个 0 或 3 个 1，无论是哪一种情况，它们的和都是 3 的倍
         * 数（即和为 0 或 3）。因此：
         *     答案的第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数。
         *
         * 这样一来，对于数组中的每一个元素 x，使用位运算 (x >> i) & 1 得到 x 的第 i 个二进制位，并将它
         * 们相加再对 3 取余，得到的结果一定为 0 或 1，即为答案的第 i 个二进制位。
         */
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().singleNumber1(new int[]{2, 2, 3, 2}));
        System.out.println(new Solution().singleNumber2(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }
}
