package com.scuyjzh.leetcode.easy.No_0294_Flip_Game_II;

import java.util.*;

/**
 * 294. 翻转游戏 II
 * <p>
 * 你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：
 * 给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流
 * 将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效的翻转时便意味
 * 着游戏结束，则另一方获胜。默认每个人都会采取最优策略。
 * 请你写出一个函数来判定起始玩家 是否存在必胜的方案 ：如果存在，返回
 * true ；否则，返回 false 。
 */
class Solution {
    private final Map<String, Boolean> cache = new HashMap<>();

    public boolean canWin(String currentState) {
        if (cache.containsKey(currentState)) {
            return cache.get(currentState);
        }
        for (int i = 1; i < currentState.length(); ++i) {
            if (currentState.charAt(i) == '+' && currentState.charAt(i - 1) == '+') {
                String nextState = currentState.substring(0, i - 1) + "--" + currentState.substring(i + 1);
                if (!canWin(nextState)) {
                    cache.put(nextState, false);
                    return true;
                }
                cache.put(nextState, true);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canWin("++++"));
        System.out.println(new Solution().canWin("+"));
    }
}