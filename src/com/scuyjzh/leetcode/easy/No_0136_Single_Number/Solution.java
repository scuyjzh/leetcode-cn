package com.scuyjzh.leetcode.easy.No_0136_Single_Number;

/**
 * 136. 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出
 * 现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 */
class Solution {
    /**
     * 方法：位运算
     */
    public int singleNumber(int[] nums) {
        /*
         * 如何才能做到线性时间复杂度和常数空间复杂度呢？
         *
         * 答案是使用位运算。对于这道题，可使用异或运算 ⊕。异或运算有以下三个性质。
         *   1.任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
         *   2.任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
         *   3.异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
         */
        int ans = 0;
        for (int num : nums) {
            ans ^= num;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().singleNumber(new int[]{2, 2, 1}));
        System.out.println(new Solution().singleNumber(new int[]{4, 1, 2, 1, 2}));
    }
}
