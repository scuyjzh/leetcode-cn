package com.scuyjzh.leetcode.hard.No_0140_Word_Break_II;

import java.util.*;

/**
 * 140. 单词拆分 II
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加
 * 空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的句
 * 子。
 * 说明：
 *   • 分隔时可以重复使用字典中的单词。
 *   • 你可以假设字典中没有重复的单词。
 */
class Solution {
    String s;
    int len;
    boolean[] dp;
    Set<String> wordSet;
    List<String> res;

    public List<String> wordBreak(String s, List<String> wordDict) {
        this.s = s;
        this.len = s.length();

        // 第 1 步：动态规划计算是否有解
        // 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词
        dp = new boolean[len + 1];
        // 对于边界条件，定义 dp[0]=true 表示空串且合法
        dp[0] = true;
        // 对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断
        wordSet = new HashSet<>();
        int maxWordLen = 0;
        for (String str : wordDict) {
            wordSet.add(str);
            maxWordLen = Math.max(maxWordLen, str.length());
        }
        for (int i = 1; i <= len; ++i) {
            // 枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举
            for (int j = i - 1; j >= 0 && j >= i - maxWordLen; --j) {
                if (wordSet.contains(s.substring(j, i)) && dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        // 第 2 步：回溯算法搜索所有符合条件的解
        res = new ArrayList<>();
        if (dp[len]) {
            LinkedList<String> path = new LinkedList<>();
            dfs(len, path);
            return res;
        }
        return res;
    }

    /**
     * s[0:len) 如果可以拆分成 wordSet 中的单词，把递归求解的结果加入 res 中
     *
     * @param len     长度为 len 的 s 的前缀子串
     * @param path    从叶子结点到根结点的路径
     */
    private void dfs(int len, LinkedList<String> path) {
        if (len == 0) {
            res.add(String.join(" ", path));
            return;
        }

        // 可以拆分的左边界从 len - 1 依次枚举到 0
        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (wordSet.contains(suffix) && dp[i]) {
                path.addFirst(suffix);
                dfs(i, path);
                path.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(new Solution().wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(new Solution().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
