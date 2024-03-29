package com.scuyjzh.leetcode.medium.No_0904_Fruit_Into_Baskets;

/**
 * 904. 水果成篮
 *
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数
 * 组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 *
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你
 * 必须按照要求采摘水果：
 *   • 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮
 *     子能够装的水果总量没有限制。
 *   • 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘
 *     的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类
 *     型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 *   • 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须
 *     停止采摘。
 *
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 */
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        // 题目提示：0 <= fruits[i] < fruits.length
        int[] freq = new int[n + 1];

        int left = 0, right = 0;
        int res = 0;
        // [left, right] 里的水果种类数目
        int count = 0;
        // 循环不变量：[left, right] 里的水果种类数目小于等于 2
        while (right < n) {
            if (freq[fruits[right]] == 0) {
                count++;
            }
            freq[fruits[right]]++;

            while (count > 2) {
                freq[fruits[left]]--;
                if (freq[fruits[left]] == 0) {
                    count--;
                }
                left++;
            }

            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().totalFruit(new int[]{1, 2, 1}));
    }
}
