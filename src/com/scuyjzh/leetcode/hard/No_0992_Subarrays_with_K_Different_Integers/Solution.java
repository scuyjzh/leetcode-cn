package com.scuyjzh.leetcode.hard.No_0992_Subarrays_with_K_Different_Integers;

/**
 * 992. K 个不同整数的子数组
 *
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，
 * 则称 A 的这个连续、不一定不同的子数组为好子数组。
 *
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 *
 * 返回 A 中好子数组的数目。
 */
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // 「恰好存在 K 个不同整数的子数组的个数」等于「最多存在 K 个不同整数的子数组的个数」与「最多存在 K−1 个不同整数的子数组的个数」的差
        return subarraysWithMostKDistinct(nums, k) - subarraysWithMostKDistinct(nums, k - 1);
    }

    /**
     * 最多存在 K 个不同整数的子数组的个数
     */
    private int subarraysWithMostKDistinct(int[] nums, int k) {
        int n = nums.length;
        // 题目提示：1 <= A[i] <= A.length
        int[] freq = new int[n + 1];

        int res = 0;
        // [left, right] 里不同整数的个数
        int count = 0;
        int left = 0, right = 0;
        // 循环不变量：[left, right] 里不同整数的个数小于等于 K
        while (right < n) {
            if (freq[nums[right]] == 0) {
                count++;
            }
            freq[nums[right]]++;

            while (count > k) {
                freq[nums[left]]--;
                if (freq[nums[left]] == 0) {
                    count--;
                }
                left++;
            }

            // [left, right] 区间的长度就是对结果的贡献
            // 借鉴动态规划的思想来理解这一点：
            // 例如，当满足条件的子数组从 [A,B,C] 增加到 [A,B,C,D] 时，长度为 4；
            // 新增的子数组为 [D], [C,D], [B,C,D], [A,B,C,D]，个数也为 4。
            res += right - left + 1;
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }
}
