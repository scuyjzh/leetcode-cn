package com.scuyjzh.leetcode.medium.No_0139_Word_Break;

import java.util.*;

/**
 * 139. 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * @author scuyjzh
 * @date 2020/6/25 0:19
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
         * 动态规划算法，dp[i]表示s前i个字符能否拆分
         * 转移方程：dp[j] = dp[i] && check(s[i+1, j]);
         * check(s[i+1, j])就是判断i+1到j这一段字符是否能够拆分
         * 其实，调整遍历顺序，这等价于s[i+1, j]是否是wordDict中的元素
         * 这个举个例子就很容易理解。
         * 假如wordDict=["apple", "pen", "code"],s = "applepencode";
         * dp[8] = dp[5] + check("pen")
         * 翻译一下：前八位能否拆分取决于前五位能否拆分，加上五到八位是否属于字典
         * （注意：i的顺序是从j-1 -> 0哦~
         */
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> wordDict = Arrays.asList("apple", "pen");
        System.out.println(solution.wordBreak("applepenapple", wordDict));
    }
}
