package com.scuyjzh.leetcode.easy.No_0448_Find_All_Numbers_Disappeared_in_an_Array;

import java.util.*;

/**
 * 448. 找到所有数组中消失的数字
 *
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * 进阶：
 * 你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗?
 * 你可以假定返回的数组不算在额外空间内。
 */
class Solution {
    /**
     * 方法：原地修改
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        /*
         * 可以用一个哈希表记录数组 nums 中的数字，由于数字范围均在 [1,n] 中，记录数字后再利用哈希
         * 表检查 [1,n] 中的每一个数是否出现，从而找到缺失的数字。
         *
         * 由于数字范围均在 [1,n] 中，也可以用一个长度为 n 的数组来代替哈希表。这一做法的空间复杂度是
         * O(n) 的。目标是优化空间复杂度到 O(1)。
         *
         * 注意到 nums 的长度恰好也为 n，能否让 nums 充当哈希表呢？
         *
         * 由于 nums 的数字范围均在 [1,n] 中，可以利用这一范围之外的数字，来表达「是否存在」的含义。
         *
         * 具体来说，遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。由于 nums 中所有数均在 [1,n] 中，增
         * 加以后，这些数必然大于 n。最后遍历 nums，若 nums[i] 未大于 n，就说明没有遇到过数 i+1。这样
         * 就找到了缺失的数字。
         *
         * 注意，当遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值。
         */
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                res.add(i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }
}
