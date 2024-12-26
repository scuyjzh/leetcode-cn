package com.scuyjzh.leetcode.medium.No_0245_Shortest_Word_Distance_III;

import java.util.*;

/**
 * 245. 最短单词距离 III
 * <p>
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * word1 和 word2 是有可能相同的，并且它们将分别表示为列表中两个独立的单词。
 */
class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (word1.equals(words[i])) {
                p1 = word1.equals(word2) ? p2 : i;
            }
            if (word2.equals(words[i])) {
                p2 = i;
            }
            if (p1 != -1 && p2 != -1) {
                res = Math.min(res, Math.abs(p1 - p2));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
        System.out.println(new Solution().shortestWordDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "makes"));
    }
}
