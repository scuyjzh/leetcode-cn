package com.scuyjzh.leetcode.medium.No_0031_Next_Permutation;

import java.util.*;

/**
 * 31. 下一个排列
 *
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个
 * 更大的排列（即，组合出下一个更大的整数）。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 */
class Solution {
    public void nextPermutation(int[] nums) {
        /*
         * “下一个排列”的定义是：给定数字序列的字典序中下一个更大的排列。如果不存在下一个更大的排列，则将
         * 数字重新排列成最小的排列（即升序排列）。
         *
         * 可以将该问题形式化地描述为：给定若干个数字，将其组合为一个整数。如何将这些数字重新排列，以
         * 得到下一个更大的整数。如 123 下一个更大的数为 132。如果没有更大的整数，则输出最小的整数。
         *
         * 以 1,2,3,4,5,6 为例，其排列依次为：
         *     123456
         *     123456
         *     123456
         *     ...
         *     654321
         * 可以看到有这样的关系：123456 < 123465 < 123546 < ... < 654321。
         *
         * 如何得到这样的排列顺序？可以这样来分析：
         *   1.希望下一个数比当前数大，这样才满足“下一个排列”的定义。因此只需要将后面的「大数」与前面
         *     的「小数」交换，就能得到一个更大的数。比如 123456，将 5 和 6 交换就能得到一个更大的数
         *     123465。
         *   2.还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满
         *     足这个要求，需要：
         *       1.在尽可能靠右的低位进行交换，需要从后向前查找
         *       2.将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和
         *         4 交换而不是把 6 和 4 交换
         *       3.将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。
         *         以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重
         *         置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
         *
         * 标准的“下一个排列”算法可以描述为：
         *   1.从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
         *   2.在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小
         *     数」、「大数」
         *   3.将 A[i] 与 A[k] 交换
         *   4.可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
         *   5.如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
         */
        int len = nums.length;
        for (int i = len - 1; i > 0; --i) {
            if (nums[i] > nums[i - 1]) {
                // 找到 i 后逆置 [j,end)，使其升序
                reverse(nums, i);
                // 然后从前向后查找第一个满足 A[i] < A[k] 的 k
                for (int j = i; j < len; ++j) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, j, i - 1);
                        return;
                    }
                }
            }
        }
        reverse(nums, 0);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        new Solution().nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {3, 2, 1};
        new Solution().nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));
    }
}
