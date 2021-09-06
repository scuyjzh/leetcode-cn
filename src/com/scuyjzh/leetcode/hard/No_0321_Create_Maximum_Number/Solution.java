package com.scuyjzh.leetcode.hard.No_0321_Create_Maximum_Number;

import java.util.*;

/**
 * 321. 拼接最大数
 * <p>
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * <p>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 */
class Solution {
    /**
     * 方法：单调栈
     * 时间复杂度：O(k(m+n+k^2))，其中 m 和 n 分别是数组 nums1 和 nums2 的长度，k 是拼接最大数的长度。两个子序列的长度之和为 k，最多有 k 种不同的长度组合。对于每一种长度组合，需要首先得到两个最大子序列，然后进行合并。得到两个最大子序列的时间复杂度为线性，即 O(m+n)。合并两个最大子序列，需要进行 k 次合并，每次合并需要进行比较，最坏情况下，比较的时间复杂度为 O(k)，因此合并操作的时间复杂度为 O(k^2)。因此对于每一种长度组合，时间复杂度为 O(m+n+k^2)，总时间复杂度为 O(k(m+n+k^2))。
     * 空间复杂度：O(k)，其中 k 是拼接最大数的长度。每次从两个数组得到两个子序列，两个子序列的长度之和为 k。
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        /*
         * 为了找到长度为 k 的最大数，需要从两个数组中分别选出最大的子序列，这两个子序列的长度之和为 k，然后将这两个子序列合并得到最大数。
         * 两个子序列的长度最小为 0，最大不能超过 k 且不能超过对应的数组长度。
         *
         * 令数组 nums1 的长度为 m，数组 nums2 的长度为 n，
         * 则需要从数组 nums1 中选出长度为 x 的子序列，以及从数组 nums2 中选出长度为 y 的子序列，其中 x+y=k，且满足 0≤x≤m 和 0≤y≤n。
         * 需要遍历所有可能的 x 和 y 的值，对于每一组 x 和 y 的值，得到最大数。
         * 在整个过程中维护可以通过拼接得到的最大数。
         *
         * 对于每一组 x 和 y 的值，得到最大数的过程分成两步，第一步是分别从两个数组中得到指定长度的最大子序列，第二步是将两个最大子序列合并。
         * 第一步可以通过单调栈实现。
         * 单调栈满足从栈底到栈顶的元素单调递减，从左到右遍历数组，遍历过程中维护单调栈内的元素，需要保证遍历结束之后单调栈内的元素个数等于指定的最大子序列的长度。
         * 遍历结束之后，将从栈底到栈顶的元素依次拼接，即得到最大子序列。
         * 单调栈使用数组实现，数组最左侧为栈底。使用数组实现，可以直接从左到右遍历数组得到最大子序列。
         *
         * 第二步需要自定义比较方法。
         * 首先比较两个子序列的当前元素，如果两个当前元素不同，则选其中较大的元素作为下一个合并的元素，
         * 否则需要比较后面的所有元素才能决定选哪个元素作为下一个合并的元素。
         */
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; ++i) {
            // 第一步：分别从两个数组中得到指定长度的最大子序列
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            // 第二步：将两个最大子序列合并
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            // 维护可以通过拼接得到的最大数
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    private int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; ++i) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    private int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; ++i) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    private int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().maxNumber(new int[]{3, 4, 6, 5}, new int[]{9, 1, 2, 5, 8, 3}, 5)));
    }
}