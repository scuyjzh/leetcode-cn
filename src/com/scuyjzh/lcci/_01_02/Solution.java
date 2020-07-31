package com.scuyjzh.lcci._01_02;

/**
 * 判定是否互为字符重排
 *
 * @author scuyjzh
 * @date 2020/6/30 16:04
 */
class Solution {
    public boolean CheckPermutation(String s1, String s2) {
        int l1 = s1.length(), l2 = s2.length();
        if (l1 != l2) {
            return false;
        }
        int[] index = new int[128];
        for (int i = 0; i < l1; i++) {
            index[s1.charAt(i)]++;
        }
        for (int i = 0; i < l2; i++) {
            if (index[s2.charAt(i)] == 0) {
                return false;
            }
            index[s2.charAt(i)]--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.CheckPermutation("abc", "cba");
    }
}
