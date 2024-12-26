package com.scuyjzh.leetcode.medium.No_1423_Maximum_Points_You_Can_Obtain_from_Cards;

import java.util.Arrays;

/**
 * 1423. 可获得的最大点数
 *
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 *
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 *
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 *
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 */
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        // 由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 n−k 张卡牌
        // 可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值
        // 由于剩余卡牌是连续的，使用一个固定长度为 n−k 的滑动窗口对数组 cardPoints 进行遍历，求出滑动窗口最小值
        // 然后用所有卡牌的点数之和减去该最小值，即得到了拿走卡牌点数之和的最大值
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 先求出第 1 个窗口的和，选前 n-k 个作为初始值
        int windowSum = 0;
        for (int i = 0; i < windowSize; ++i) {
            windowSum += cardPoints[i];
        }
        // 通过遍历求出除了第 1 个窗口的和
        int minSum = windowSum;
        // 循环不变量定义：[left..right) 是长度为 windowSize 的窗口
        for (int right = windowSize; right < n; ++right) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            windowSum = windowSum + cardPoints[right] - cardPoints[right - windowSize];
            minSum = Math.min(minSum, windowSum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
    }
}
