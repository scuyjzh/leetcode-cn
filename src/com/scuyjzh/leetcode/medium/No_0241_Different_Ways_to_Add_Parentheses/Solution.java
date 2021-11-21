package com.scuyjzh.leetcode.medium.No_0241_Different_Ways_to_Add_Parentheses;

import java.util.*;

/**
 * 241. 为运算表达式设计优先级
 *
 * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先
 * 级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号
 * 包含 +,-以及*。
 */
class Solution {
    /**
     * 方法一：分治
     */
    public List<Integer> diffWaysToCompute1(String expression) {
        if (expression.length() == 0) {
            return new ArrayList<>();
        }
        return helper(expression);
    }

    private List<Integer> helper(String expression) {
        List<Integer> res = new ArrayList<>();
        int len = expression.length();
        int start = 0;
        while (start < len) {
            if (isOperation(expression.charAt(start))) {
                break;
            }
            start++;
        }
        // 递归出口，表达式是全数字的情况，那么直接返回结果
        if (start == len) {
            res.add(Integer.parseInt(expression));
            return res;
        }

        for (int i = start; i < len; ++i) {
            char ch = expression.charAt(i);
            if (!isOperation(ch)) {
                continue;
            }
            List<Integer> left = helper(expression.substring(0, i));
            List<Integer> right = helper(expression.substring(i + 1, len));
            for (int num1 : left) {
                for (int num2 : right) {
                    res.add(calculate(num1, ch, num2));
                }
            }
        }
        return res;
    }

    /**
     * 方法二：动态规划
     */
    public List<Integer> diffWaysToCompute2(String expression) {
        List<Integer> numList = new ArrayList<>();
        List<Character> opList = new ArrayList<>();
        char[] chs = expression.toCharArray();
        int num = 0;
        for (char ch : chs) {
            if (isOperation(ch)) {
                numList.add(num);
                opList.add(ch);
                num = 0;
                continue;
            }
            num = num * 10 + ch - '0';
        }
        numList.add(num);

        // 数字的个数
        int N = numList.size();
        ArrayList<Integer>[][] dp = (ArrayList<Integer>[][]) new ArrayList[N][N];

        // 一个数字
        for (int i = 0; i < N; ++i) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(numList.get(i));
            dp[i][i] = result;
        }

        // 2 个数字到 N 个数字
        for (int n = 2; n <= N; ++n) {
            // 开始下标
            for (int i = 0; i < N; ++i) {
                // 结束下标
                int j = i + n - 1;
                if (j >= N) {
                    break;
                }
                ArrayList<Integer> res = new ArrayList<>();
                // 分成 i ~ s 和 s+1 ~ j 两部分
                for (int s = i; s < j; ++s) {
                    ArrayList<Integer> res1 = dp[i][s];
                    ArrayList<Integer> res2 = dp[s + 1][j];
                    for (int num1 : res1) {
                        for (int num2 : res2) {
                            // 第 s 个数字下标对应是第 s 个运算符
                            char op = opList.get(s);
                            res.add(calculate(num1, op, num2));
                        }
                    }
                }
                dp[i][j] = res;
            }
        }
        return dp[0][N - 1];
    }

    private int calculate(int num1, char ch, int num2) {
        switch (ch) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            default:
                return -1;
        }
    }

    private boolean isOperation(char ch) {
        return ch == '+' || ch == '-' || ch == '*';
    }

    public static void main(String[] args) {
        System.out.println(new Solution().diffWaysToCompute1("2-1-1"));
        System.out.println(new Solution().diffWaysToCompute2("2*3-4*5"));
    }
}
