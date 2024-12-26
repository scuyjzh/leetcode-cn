package com.scuyjzh.leetcode.easy.No_0292_Nim_Game;

/**
 * 292. Nim 游戏
 *
 * 你和你的朋友，两个人一起玩Nim 游戏：
 *   • 桌子上有一堆石头。
 *   • 你们轮流进行自己的回合，你作为先手。
 *   • 每一回合，轮到的人拿掉1 - 3 块石头。
 *   • 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石
 * 头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回
 * false 。
 */
class Solution {
    /**
     * 方法：数学推理
     */
    public boolean canWinNim(int n) {
        /*
         * 思路与算法：
         * https://leetcode-cn.com/problems/nim-game/solution/nim-you-xi-by-leetcode-solution-95g8/
         */
        return n % 4 != 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canWinNim(4));
        System.out.println(new Solution().canWinNim(1));
        System.out.println(new Solution().canWinNim(2));
    }
}
