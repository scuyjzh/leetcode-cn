package com.scuyjzh.leetcode.medium.No_1052_Grumpy_Bookstore_Owner;

/**
 * 1052. 爱生气的书店老板
 *
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都
 * 有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟
 * 结束后离开。
 *
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么
 * grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的
 * 顾客就会不满意，不生气则他们是满意的。
 *
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟
 * 不生气，但却只能使用一次。
 *
 * 请你返回这一天营业下来，最多有多少客户能够感到满意。
 */
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int customersSize = customers.length;
        int satisfied = 0;
        for (int i = 0; i < customersSize; ++i) {
            if (grumpy[i] == 0) {
                // 先将原本就满意的顾客数加入答案
                satisfied += customers[i];
                // 同时将对应的 customers[i] 置为 0
                customers[i] = 0;
            }
        }
        int windowSum = 0;
        // 先求出第 1 个窗口的和
        for (int i = 0; i < minutes; ++i) {
            windowSum += customers[i];
        }
        // 通过遍历求出除了第 1 个窗口的和
        int max = windowSum;
        // 循环不变量定义：[left..right) 是长度为 minutes 的窗口
        for (int right = minutes; right < customersSize; ++right) {
            // 加上一个数再减去一个数
            windowSum = windowSum + customers[right] - customers[right - minutes];
            max = Math.max(max, windowSum);
        }
        return satisfied + max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
    }
}
