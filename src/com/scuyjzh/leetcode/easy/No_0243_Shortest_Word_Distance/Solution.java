package com.scuyjzh.leetcode.easy.No_0243_Shortest_Word_Distance;

/**
 * 243. 最短单词距离
 * <p>
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * 注意:
 * 你可以假设 word1 不等于 word2, 并且 word1 和 word2 都在列表里。
 */
class Solution {
    public int shortestDistance1(String[] words, String word1, String word2) {
        int m = -1, n = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            if (word1.equals(s)) {
                m = i;
                if (n != -1) {
                    res = Math.min(res, m - n);
                }
            } else if (word2.equals(s)) {
                n = i;
                if (m != -1) {
                    res = Math.min(res, n - m);
                }
            }
        }
        return res;
    }

    public int shortestDistance2(String[] words, String word1, String word2) {
        int idx = -1;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; ++i) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (idx != -1 && !words[idx].equals(words[i])) {
                    res = Math.min(res, i - idx);
                }
                idx = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestDistance1(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "coding", "practice"));
        System.out.println(new Solution().shortestDistance2(new String[]{"practice", "makes", "perfect", "coding", "makes"}, "makes", "coding"));
    }
}
