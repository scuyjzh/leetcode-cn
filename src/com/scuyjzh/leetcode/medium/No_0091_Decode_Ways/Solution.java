package com.scuyjzh.leetcode.medium.No_0091_Decode_Ways;

class Solution {
    public int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) - '0' == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        // 初始化第一种解码方式
        dp[0] = 1;
        // 如果第一位是0，则无法解码
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            // 如果字符串的第i-1位和第i位能组成一个10到26的数字，
            // 则可以在第i-2位的解码方法上继续解码
            if (Integer.parseInt(s.substring(i - 2, i)) >= 10
                    && Integer.parseInt(s.substring(i - 2, i)) <= 26) {
                dp[i] += dp[i - 2];
            }
            // 如果字符串的第i-1位和第i位不能组成有效二位数字，
            // 则在第i-1位的解码方法上继续解码
            if (Integer.parseInt(s.substring(i - 1, i)) != 0) {
                dp[i] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}