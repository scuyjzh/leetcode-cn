package com.scuyjzh.leetcode.medium.No_647_Palindromic_Substrings;

/**
 * @author scuyjzh
 * @date 2020/8/19 23:33
 */
class Solution {
    /**
     * 方法一：中心拓展
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     */
    public int countSubstrings1(String s) {
        int n = s.length(), ans = 0;
        // 长度为 n 的字符串会生成 2n-1 组回文中心 [l_i, r_i]
        for (int i = 0; i < 2 * n - 1; ++i) {
            // r_i = l_i + (i mod 2)
            int l = i / 2, r = l + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countSubstrings1("madam"));
    }
}
