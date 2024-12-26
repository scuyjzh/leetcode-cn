package com.scuyjzh.leetcode.easy.No_0007_Reverse_Integer;

/**
 * 7. 整数反转
 *
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，
 * 就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 */
class Solution {
    /**
     * 方法：数学
     *
     * • 时间复杂度：O(log∣x∣)。翻转的次数即 x 十进制的位数。
     * • 空间复杂度：O(1)。
     */
    public int reverse(int x) {
        /*
         * 思路：
         * • 通过循环将数字 x 的每一位拆开，在计算新值时每一步都判断是否溢出，
         * • 溢出条件有两个，一个是大于整数最大值 AX_VALUE，另一个是小于整数最小值 MIN_VALUE。
         * • 设当前计算结果为 ans，下一位为 pop。
         */
        int ans = 0;
        while (x != 0) {
            // 取个位数字
            int pop = x % 10;
            /*
             * 从 ans * 10 + pop > MAX_VALUE 这个溢出条件来看：
             *   • 当 ans > MAX_VALUE / 10 且 还有 pop 需要添加时，则一定溢出
             *   • 当 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出（7 是 2^31 - 1 = 2147483647 的个位数）
             */
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            /*
             * 从 ans * 10 + pop < MIN_VALUE 这个溢出条件来看：
             *   • 当 ans < MIN_VALUE / 10 且 还有 pop 需要添加时，则一定溢出
             *   • 当 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出（-8 是 -2^31 = -2147483648 的个位数）
             */
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            // 累加当前计算结果
            ans = ans * 10 + pop;
            // 向前进一位
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(214748364));
        System.out.println(new Solution().reverse(2147483647));
    }
}
