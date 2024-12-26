package com.scuyjzh.leetcode.easy.No_0821_Shortest_Distance_to_a_Character;

import java.util.*;

class Solution {
    public int[] shortestToChar(String S, char C) {
        int[] res = new int[S.length()];
        int pos = -S.length();
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = i - pos;
        }
        for (int i = S.length() - 1; i >= 0; --i) {
            if (S.charAt(i) == C) {
                pos = i;
            }
            res[i] = Math.min(res[i], Math.abs(i - pos));
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String S = "livecode";
        char C = 'e';
        int[] res = solution.shortestToChar(S, C);
        System.out.println(Arrays.toString(res));
    }
}
