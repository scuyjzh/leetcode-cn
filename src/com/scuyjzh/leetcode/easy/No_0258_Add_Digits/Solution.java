package com.scuyjzh.leetcode.easy.No_0258_Add_Digits;

/**
 * 258. 各位相加
 *
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位
 * 数。
 *
 * 进阶:
 * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
 */
class Solution {
    public int addDigits(int num) {
        // X = 100*a + 10*b + c = 99*a + 9*b + (a+b+c)；所以对 9 取余即可。
        // 一行代码写法：return (num - 1) % 9 + 1;
        if (num >= 9 && num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.addDigits(38));
    }
}
