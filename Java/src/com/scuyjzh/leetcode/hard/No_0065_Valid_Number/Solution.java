package com.scuyjzh.leetcode.hard.No_0065_Valid_Number;

/**
 * 65. 有效数字
 *
 * 有效数字（按顺序）可以分成以下几个部分：
 *     1.一个 小数 或者 整数
 *     2.（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 小数（按顺序）可以分成以下几个部分：
 *     1.（可选）一个符号字符（'+' 或 '-'）
 *     2.下述格式之一：
 *           1.至少一位数字，后面跟着一个点 '.'
 *           2.至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位
 *             数字
 *           3.一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *     1.（可选）一个符号字符（'+' 或 '-'）
 *     2.至少一位数字
 * 部分有效数字列举如下：
 *     • ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10",
 *       "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * 部分无效数字列举如下：
 *     • ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3",
 *       "95a54e53"]
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 */
class Solution {
    boolean hasE, hasNum, isFloat;

    public boolean isNumber(String s) {
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            // 符号位只能在首位或 E 的后一位
            if ((ch == '-' || ch == '+') && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E')) {

            }
            // 只存在一个 E, 前面必须有数字, 后面也必须有数字
            else if ((ch == 'e' || ch == 'E') && !hasE && hasNum) {
                hasE = true;
                hasNum = false;
            }
            // 只存在一个小数点, 不能在 E 的后面
            else if (ch == '.' && !isFloat && !hasE) {
                isFloat = true;
            } else if (Character.isDigit(ch)) {
                hasNum = true;
            } else {
                return false;
            }
        }
        return hasNum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("0"));
        System.out.println(new Solution().isNumber("e"));
        System.out.println(new Solution().isNumber("."));
        System.out.println(new Solution().isNumber(".1"));
    }
}
