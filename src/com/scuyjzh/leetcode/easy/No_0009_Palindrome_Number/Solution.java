package com.scuyjzh.leetcode.easy.No_0009_Palindrome_Number;

/**
 * 9. 回文数
 *
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回
 * false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例
 * 如，121 是回文，而 123 不是。
 */
class Solution {
    /**
     * 方法：反转一半数字
     *
     * • 时间复杂度：O(log n)，对于每次迭代，会将输入除以 10，因此时间复杂度为 O(log n)。
     * • 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(121));
        System.out.println(new Solution().isPalindrome(-121));
        System.out.println(new Solution().isPalindrome(10));
        System.out.println(new Solution().isPalindrome(-101));
    }
}
