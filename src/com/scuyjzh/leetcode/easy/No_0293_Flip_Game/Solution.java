package com.scuyjzh.leetcode.easy.No_0293_Flip_Game;

import java.util.*;

/**
 * 293. 翻转游戏
 * <p>
 * 你和朋友玩一个叫做「翻转游戏」的游戏。游戏规则如下：
 * 给你一个字符串 currentState ，其中只含 '+' 和 '-' 。你和朋友轮流
 * 将 连续 的两个 "++" 反转成 "--" 。当一方无法进行有效的翻转时便意味
 * 着游戏结束，则另一方获胜。
 * 计算并返回 一次有效操作 后，字符串 currentState 所有的可能状态，返
 * 回结果可以按 任意顺序 排列。如果不存在可能的有效操作，请返回一个空
 * 列表 [] 。
 */
class Solution {
    public List<String> generatePossibleNextMoves(String currentState) {
        char[] chars = currentState.toCharArray();
        List<String> result = new ArrayList<>();
        int l = 0, r = 1;
        while (r < chars.length) {
            if (chars[r] == '+' && chars[l] == '+') {
                chars[l] = '-';
                chars[r] = '-';
                result.add(new String(chars));
                chars[l] = '+';
                chars[r] = '+';
            }
            l = r;
            r++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generatePossibleNextMoves("++++"));
        System.out.println(new Solution().generatePossibleNextMoves("+"));
    }
}