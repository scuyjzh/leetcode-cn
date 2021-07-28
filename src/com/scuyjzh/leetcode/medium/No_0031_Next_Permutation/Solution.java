package com.scuyjzh.leetcode.medium.No_0031_Next_Permutation;

import java.util.*;

/**
 * 31. 下一个排列
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 */
class Solution {
    public void nextPermutation(int[] nums) {
        /*
         * “下一个排列”的定义是：给定数字序列的字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
         * 算法推导：
         * 1 希望下一个数比当前数大，只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。
         * 2 还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。因此需要：
         *   2.1 在尽可能靠右的低位进行交换，需要从后向前查找；
         *   2.2 将一个尽可能小的「大数」与前面的「小数」交换（比如 123465 的下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换）；
         *   2.3 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。
         * 算法过程：
         * 1 从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
         * 2 在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」、「大数」
         * 3 将 A[i] 与 A[k] 交换
         * 4 可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
         * 5 如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
         */
        int len = nums.length;
        for (int i = len - 1; i > 0; --i) {
            if (nums[i] > nums[i - 1]) {
                // 找到 i 后先将 [j,end) 升序
                Arrays.sort(nums, i, len);
                // 然后从前向后查找第一个满足 A[i] < A[k] 的 k
                for (int j = i; j < len; ++j) {
                    if (nums[j] > nums[i - 1]) {
                        int temp = nums[j];
                        nums[j] = nums[i - 1];
                        nums[i - 1] = temp;
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 8, 5, 7, 6, 4};
        solution.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
