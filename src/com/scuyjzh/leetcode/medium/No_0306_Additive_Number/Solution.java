package com.scuyjzh.leetcode.medium.No_0306_Additive_Number;

/**
 * 306. 累加数
 *
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字
 * 符串中的其他数都等于它之前两个数相加的和。
 * 给定一个只包含数字'0'-'9'的字符串，编写一个算法来判断给定输入是
 * 否是累加数。
 * 说明:累加序列里的数不会以 0 开头，所以不会出现1, 2, 03 或者1,
 * 02, 3的情况。
 */
class Solution {
    public boolean isAdditiveNumber(String num) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/additive-number/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--57/
         */
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i) {
            for (int j = 1; Math.max(j, i) <= n - i - j; ++j) {
                if (isValid(i, j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValid(int i, int j, String num) {
        if (num.charAt(0) == '0' && i > 1) {
            return false;
        }
        if (num.charAt(i) == '0' && j > 1) {
            return false;
        }
        String sum;
        Long x1 = Long.parseLong(num.substring(0, i));
        Long x2 = Long.parseLong(num.substring(i, i + j));
        for (int start = i + j; start != num.length(); start += sum.length()) {
            x2 = x2 + x1;
            x1 = x2 - x1;
            sum = x2.toString();
            if (!num.startsWith(sum, start)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isAdditiveNumber("112358"));
        System.out.println(new Solution().isAdditiveNumber("199100199"));
    }
}
