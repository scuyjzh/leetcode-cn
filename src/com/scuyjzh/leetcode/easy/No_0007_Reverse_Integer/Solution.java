package com.scuyjzh.leetcode.easy.No_0007_Reverse_Integer;

/**
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例：
 * 输入：x = 123
 * 输出：321
 */
class Solution {
    /**
     * 方法一：数学
     * 时间复杂度：O(log∣x∣)。翻转的次数即 x 十进制的位数。
     * 空间复杂度：O(1)。
     */
    public int reverse(int x) {
        /*
          思路：记 ans 为翻转后的数字，为完成翻转，我们可以重复「弹出」x 的末尾数字，将其「推入」ans 的末尾，直至 x 为 0。
               要在没有辅助栈或数组的帮助下「弹出」和「推入」数字，我们可以使用如下数学方法：
         */
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
}
