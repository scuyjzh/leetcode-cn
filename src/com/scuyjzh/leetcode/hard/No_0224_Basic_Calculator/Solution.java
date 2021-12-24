package com.scuyjzh.leetcode.hard.No_0224_Basic_Calculator;

import java.util.*;

/**
 * 224. 基本计算器
 *
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的
 * 值。
 */
class Solution {
    /**
     * 方法一：括号展开 + 栈
     */
    public int calculate1(String s) {
        // 维护一个栈 signs，其中栈顶元素记录了当前位置所处的每个括号所「共同形成」的符号
        Deque<Integer> signs = new ArrayDeque<>();
        signs.push(1);

        // s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
        char[] charArray = s.toCharArray();
        int len = charArray.length;

        // 使用一个取值为 {−1,+1} 的整数 sign 代表「当前」的符号
        int sign = 1;
        int ans = 0;
        for (int i = 0; i < len; ++i) {
            char ch = charArray[i];
            if (ch == ' ') {
                // 跳过空格
                continue;
            }
            if (ch == '+') {
                // 当前遇到了 + 号，则更新 sign ← signs.peek()
                sign = signs.peek();
            } else if (ch == '-') {
                // 遇到了 − 号，则更新 sign ← −signs.peek()
                sign = -signs.peek();
            } else if (ch == '(') {
                // 每当遇到 ( 时，都要将当前的 sign 取值压入栈中
                signs.push(sign);
            } else if (ch == ')') {
                // 每当遇到 ) 时，都从栈中弹出一个元素
                signs.pop();
            } else {
                int digit = ch - '0';
                while (i < len - 1 && Character.isDigit(charArray[i + 1])) {
                    digit = digit * 10 + charArray[++i] - '0';
                }
                ans += sign * digit;
            }
        }
        return ans;
    }

    /**
     * 方法二：双栈
     */
    public int calculate2(String s) {
        Deque<Integer> digits = new ArrayDeque<>();
        Deque<Integer> signs = new ArrayDeque<>();

        // s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
        char[] charArray = s.toCharArray();
        int len = charArray.length;

        int ans = 0;
        int sign = 1;
        for (int i = 0; i < len; ++i) {
            char ch = charArray[i];
            if (ch == ' ') {
                // 跳过空格
                continue;
            }
            if (ch == '+' || ch == '-') {
                // 更新正负运算符
                sign = ch == '+' ? 1 : -1;
            } else if (ch == '(') {
                // 遇到左括号入栈
                digits.push(ans);
                signs.push(sign);
                ans = 0;
                sign = 1;
            } else if (ch == ')') {
                // 遇到右括号出栈
                ans = digits.pop() + ans * signs.pop();
            } else {
                // 计算当前括号内的结果
                int digit = ch - '0';
                while (i < len - 1 && Character.isDigit(charArray[i + 1])) {
                    digit = digit * 10 + (charArray[++i] - '0');
                }
                ans += sign * digit;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().calculate1("1+(4-(5-22))-(6+8)"));
        System.out.println(new Solution().calculate2("1+(4-(5-22))-(6+8)"));
    }
}
