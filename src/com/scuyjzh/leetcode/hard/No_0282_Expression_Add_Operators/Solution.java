package com.scuyjzh.leetcode.hard.No_0282_Expression_Add_Operators;

import java.util.*;

/**
 * 282. 给表达式添加运算符
 *
 * 给定一个仅包含数字 0-9 的字符串 num 和一个目标值整数 target ，在 num 的
 * 数字之间添加 二元 运算符（不是一元）+、- 或 * ，返回所有能够得到目标值的
 * 表达式。
 */
class Solution {
    int n;
    String num;
    int target;
    List<String> ans;

    public List<String> addOperators(String num, int target) {
        /*
         * 设字符串 num 的长度为 n，为构建表达式，可以往 num 中间的 n−1 个空隙添加 + 号、- 号或 * 号，
         * 或者不添加符号。
         *
         * 可以用「回溯法」来模拟这个过程。从左向右构建表达式，并实时计算表达式的结果。由于乘法运算优
         * 先级高于加法和减法运算，还需要保存最后一个连乘串（如 2*3*4）的运算结果。
         *
         * 定义递归函数 backtrack(expr, i, res, mul)，其中：
         *   • expr 为当前构建出的表达式；
         *   • i 表示当前的枚举到了 num 的第 i 个数字；
         *   • res 为当前表达式的计算结果；
         *   • mul 为表达式最后一个连乘串的计算结果。
         *
         * 该递归函数分为两种情况：
         *   • 如果 i=n，说明表达式已经构造完成，若此时有 res=target，则找到了一个可行解，将 expr 放
         *     入答案数组中，递归结束；
         *   • 如果 i<n，需要枚举当前表达式末尾要添加的符号（+ 号、- 号或 * 号），以及该符号之后需要截取
         *     多少位数字。设该符号之后的数字为 val，按符号分类讨论：
         *       ○ 若添加 + 号，则 res 增加 val，且 val 单独组成表达式最后一个连乘串；
         *       ○ 若添加 - 号，则 res 减少 val，且 −val 单独组成表达式最后一个连乘串；
         *       ○ 若添加 * 号，由于乘法运算优先级高于加法和减法运算，需要对 res 撤销之前 mul 的计算结
         *         果，并添加新的连乘结果 mul∗val，也就是将 res 减少 mul 并增加 mul∗val。
         *
         * 代码实现时，为避免字符串拼接所带来的额外时间开销，采用字符数组的形式来构建表达式。此外，运
         * 算过程中可能会产生超过 32 位整数的结果，要用 64 位整数存储中间运算结果。
         */
        this.n = num.length();
        this.num = num;
        this.target = target;
        this.ans = new ArrayList<>();
        StringBuffer expr = new StringBuffer();
        backtrack(expr, 0, 0, 0);
        return ans;
    }

    public void backtrack(StringBuffer expr, int i, long res, long mul) {
        if (i == n) {
            if (res == target) {
                ans.add(expr.toString());
            }
            return;
        }

        int signIndex = expr.length();
        if (i > 0) {
            // 占位，下面填充符号
            expr.append(0);
        }
        long val = 0;
        // 枚举截取的数字长度（取多少位），注意数字可以是单个 0 但不能有前导零
        for (int j = i; j < n && (j == i || num.charAt(i) != '0'); ++j) {
            // 计算截取的数字
            val = val * 10 + num.charAt(j) - '0';
            // 将当前数字添加到当前构建出的表达式
            expr.append(num.charAt(j));
            if (i == 0) {
                // 表达式开头不能添加符号
                backtrack(expr, j + 1, val, val);
            } else {
                // 若添加 + 号，则 res 增加 val，且 val 单独组成表达式最后一个连乘串
                expr.setCharAt(signIndex, '+');
                backtrack(expr, j + 1, res + val, val);

                // 若添加 - 号，则 res 减少 val，且 −val 单独组成表达式最后一个连乘串
                expr.setCharAt(signIndex, '-');
                backtrack(expr, j + 1, res - val, -val);

                // 若添加 * 号，由于乘法运算优先级高于加法和减法运算，需要对 res 撤销之前 mul 的计算结果
                // 并添加新的连乘结果 mul∗val，也就是将 res 减少 mul 并增加 mul∗val
                expr.setCharAt(signIndex, '*');
                backtrack(expr, j + 1, res - mul + mul * val, mul * val);
            }
        }
        // 回撤
        expr.setLength(signIndex);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().addOperators("123", 6));
        System.out.println(new Solution().addOperators("232", 8));
        System.out.println(new Solution().addOperators("105", 5));
        System.out.println(new Solution().addOperators("00", 0));
        System.out.println(new Solution().addOperators("3456237490", 9191));
    }
}
