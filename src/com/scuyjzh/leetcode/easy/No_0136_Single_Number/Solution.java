package com.scuyjzh.leetcode.easy.No_0136_Single_Number;

class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 1. N ^ N = 0; 2. N ^ 0 = N
            ans ^= nums[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.singleNumber(new int[]{2, 1, 4, 5, 2, 4, 1}));
    }
}
