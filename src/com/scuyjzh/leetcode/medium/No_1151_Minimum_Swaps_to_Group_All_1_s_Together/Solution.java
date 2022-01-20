package com.scuyjzh.leetcode.medium.No_1151_Minimum_Swaps_to_Group_All_1_s_Together;

/**
 * 1151. 最少交换次数来组合所有的 1
 *
 * 给出一个二进制数组 data，你需要通过交换位置，将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。
 */
class Solution {
    public int minSwaps(int[] data) {
        // 计算原数组 data 中 1 的个数 totalOne
        int totalOne = 0;
        for (int num : data) {
            if (num == 1) {
                ++totalOne;
            }
        }
        int countOne = 0;
        // 维护一个长度为 totalOne 的窗口，计算窗口中 1 的个数
        // 先遍历求出第一个窗口 1 的个数，并保存好这个数字，记为 countOne
        for (int right = 0; right < totalOne; ++right) {
            if (data[right] == 1) {
                ++countOne;
            }
        }
        // 通过遍历求出除了第 1 个窗口的其他窗口中 1 的个数
        int maxOne = countOne;
        // 循环不变量定义：[left..right) 是长度为 totalOne 的窗口
        for (int right = totalOne; right < data.length; ++right) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            countOne = countOne + data[right] - data[right - totalOne];
            maxOne = Math.max(maxOne, countOne);
        }
        // 求 countOne 的最大值，和 totalOne 相减就是要求的结果
        return totalOne - maxOne;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minSwaps(new int[]{1, 0, 1, 0, 1}));
    }
}
