package com.scuyjzh.leetcode.medium.No_0371_Sum_of_Two_Integers;

/**
 * 371. 两整数之和
 *
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
 */
class Solution {
    /**
     * 方法：位运算
     */
    public int getSum(int a, int b) {
        /*
         * 有符号整数通常用补码来表示和存储，补码具有如下特征：
         *   • 正整数的补码与原码相同；负整数的补码为其原码除符号位外的所有位取反后加 1。
         *   • 可以将减法运算转化为补码的加法运算来实现。
         *   • 符号位与数值位可以一起参与运算。
         *
         * 使用位运算来处理这个问题。
         * 首先，考虑两个二进制位相加的四种情况如下：
         *   0 + 0 = 0
         *   0 + 1 = 1
         *   1 + 0 = 1
         *   1 + 1 = 0 (进位)
         *
         * 可以发现，对于整数 a 和 b：
         *   • 在不考虑进位的情况下，其无进位加法结果为 a⊕b（异或）。
         *   • 而所有需要进位的位为 a & b，进位后的进位结果为 (a & b) << 1。
         *
         * 于是，可以将整数 a 和 b 的和，拆分为 a 和 b 的无进位加法结果与进位结果的和。因为每一次拆分都可
         * 以让需要进位的最低位至少左移一位，又因为 a 和 b 可以取到负数，所以最多需要 log(max_int) 次拆
         * 分即可完成运算。
         *
         * 因为有符号整数用补码来表示，所以以上算法也可以推广到 0 和负数。
         */
        while (b != 0) {
            // a + b 的问题拆分为（a 和 b 无进位的结果）和（a 和 b 的进位结果）
            // 进位结果使用 与运算 和 移位运算 计算得出
            int carry = (a & b) << 1;
            // 无进位加法使用 异或运算 计算得出
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getSum(1, 2));
        System.out.println(new Solution().getSum(2, 3));
    }
}
