package com.scuyjzh.leetcode.easy.No_0202_Happy_Number;

import java.util.*;

/**
 * 202. 快乐数
 *
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：
 *   • 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方
 *     和。
 *   • 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变
 *     不到 1。
 *   • 如果 可以变为 1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 */
class Solution {
    /**
     * 方法一：用哈希集合检测循环
     */
    public boolean isHappy1(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = bitSquareSum(n);
        }
        return n == 1;
    }

    /**
     * 方法二：快慢指针法
     */
    public boolean isHappy2(int n) {
        // 使用 “快慢指针” 思想，找出循环：“快指针” 每次走两步，“慢指针” 每次走一步，当二者相等时，即为一个循
        // 环周期。
        // 如果给定的数字最后会一直循环重复，那么快的指针（值）一定会追上慢的指针（值），也就是两者一定会相等。
        // 如果没有循环重复，那么最后快慢指针也会相等，且都等于 1。
        int fast = n;
        int slow = n;
        do {
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        } while (slow != fast);
        return fast == 1;
    }

    private int bitSquareSum(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isHappy1(19));
        System.out.println(new Solution().isHappy2(2));
    }
}
