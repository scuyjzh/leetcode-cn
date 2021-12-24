package com.scuyjzh.leetcode.medium.No_0227_Basic_Calculator_II;

import java.util.*;

/**
 * 227. 基本计算器 II
 *
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的
 * 值。
 * 整数除法仅保留整数部分。
 */
class Solution {
    /**
     * 方法：栈
     */
    public int calculate(String s) {
        /*
         * 由于乘除优先于加减计算，因此不妨考虑先进行所有乘除运算，并将这些乘除运算后的整数值放回原表达式
         * 的相应位置，则随后整个表达式的值，就等于一系列整数加减后的值。
         *
         * 基于此，可以用一个栈，保存这些（进行乘除运算后的）整数的值。对于加减号后的数字，将其直接压
         * 入栈中；对于乘除号后的数字，可以直接与栈顶元素计算，并替换栈顶元素为计算后的结果。
         *
         * 具体来说，遍历字符串 s，并用变量 preSign 记录每个数字之前的运算符，对于第一个数字，其之前的运算
         * 符视为加号。每次遍历到数字末尾时，根据 sign 来决定计算方式：
         *   • 加号：将数字压入栈；
         *   • 减号：将数字的相反数压入栈；
         *   • 乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果。
         *
         * 代码实现中，若读到一个运算符，或者遍历到字符串末尾，即认为是遍历到了数字末尾。处理完该数字后，
         * 更新 preSign 为当前遍历的字符。
         *
         * 遍历完字符串 s 后，将栈中元素累加，即为该字符串表达式的值。
         */
        Deque<Integer> digits = new ArrayDeque<>();

        // s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
        char[] charArray = s.toCharArray();
        int len = charArray.length;

        // 变量 preSign 记录每个数字之前的运算符，对于第一个数字，其之前的运算符视为加号
        char preSign = '+';
        int digit = 0;
        for (int i = 0; i < len; ++i) {
            char ch = charArray[i];
            if (Character.isDigit(ch)) {
                digit = digit * 10 + ch - '0';
            }
            if (!Character.isDigit(ch) && s.charAt(i) != ' ' || i == len - 1) {
                if (preSign == '+') {
                    // 对于加减号后的数字，将其直接压入栈中
                    digits.push(digit);
                } else if (preSign == '-') {
                    // 对于加减号后的数字，将其直接压入栈中
                    digits.push(-digit);
                } else if (preSign == '*') {
                    // 对于乘除号后的数字，直接与栈顶元素计算，并替换栈顶元素为计算后的结果
                    digits.push(digits.pop() * digit);
                } else {
                    // 对于乘除号后的数字，直接与栈顶元素计算，并替换栈顶元素为计算后的结果
                    digits.push(digits.pop() / digit);
                }
                preSign = ch;
                digit = 0;
            }
        }

        int ans = 0;
        while (!digits.isEmpty()) {
            ans += digits.pop();
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate("3+2*2"));
        System.out.println(new Solution().calculate(" 3/2 "));
        System.out.println(new Solution().calculate(" 3+5 / 2 "));
    }
}
