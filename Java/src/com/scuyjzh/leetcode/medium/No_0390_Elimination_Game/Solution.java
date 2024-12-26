package com.scuyjzh.leetcode.medium.No_0390_Elimination_Game;

/**
 * 390. 消除游戏
 *
 * 给定一个从1 到 n 排序的整数列表。
 * 首先，从左到右，从第一个数字开始，每隔一个数字进行删除，直到列表的
 * 末尾。
 * 第二步，在剩下的数字中，从右到左，从倒数第一个数字开始，每隔一个数
 * 字进行删除，直到列表开头。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。
 * 返回长度为 n 的列表中，最后剩下的数字。
 */
class Solution {
    public int lastRemaining(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/elimination-game/solution/zhao-gui-lu-by-yuruiyin/
         */
        int count = n;
        int first = 1;
        int diff = 1;
        boolean isFromLeft = true;
        while (count > 1) {
            if (isFromLeft) {
                first += diff;
            } else {
                if ((count & 1) == 1) {
                    first += diff;
                }
            }
            count /= 2;
            diff *= 2;
            isFromLeft = !isFromLeft;
        }
        return first;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lastRemaining(9));
    }
}
