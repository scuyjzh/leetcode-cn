package com.scuyjzh.leetcode.medium.No_0139_Word_Break;

import java.util.*;

/**
 * 139. 单词拆分
 *
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定s 是否可以由空
 * 格拆分为一个或多个在字典中出现的单词。
 * 说明：拆分时可以重复使用字典中的单词。
 */
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
         * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单
         * 词。从前往后计算考虑转移方程，每次转移的时候需要枚举包含位置 i−1 的最后一个单词，看它是否
         * 出现在字典中以及除去这部分的字符串是否合法即可。公式化来说，需要枚举 s[0..i−1] 中的分割点 j
         * ，看 s[0..j−1] 组成的字符串 s1（默认 j=0 时 s1 为空串）和 s[j..i−1] 组成的字符串 s2 是否都合法，如果
         * 两个字符串均合法，那么按照定义 s1 和 s2 拼接成的字符串也同样合法。由于计算到 dp[i] 时已经计算出
         * 了 dp[0..i−1] 的值，因此字符串 s1 是否合法可以直接由 dp[j] 得知，剩下的只需要看 s2 是否合法即
         * 可，因此可以得出如下转移方程：
         *         dp[i]=dp[j] && check(s[j..i−1])
         * 其中 check(s[j..i−1]) 表示子串 s[j..i−1] 是否出现在字典中。
         *
         * 对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，同时也可以做一些简
         * 单的剪枝，枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，
         * 那么就结束枚举。
         *
         * 对于边界条件，定义 dp[0]=true 表示空串且合法。
         */
        int len = s.length(), maxWordLen = 0;
        // 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词
        boolean[] dp = new boolean[len + 1];
        // 对于边界条件，定义 dp[0]=true 表示空串且合法
        dp[0] = true;
        // 对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断
        Set<String> set = new HashSet<>();
        for (String str : wordDict) {
            set.add(str);
            maxWordLen = Math.max(maxWordLen, str.length());
        }
        for (int i = 1; i < len + 1; ++i) {
            // 枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举
            for (int j = i; j >= 0 && j >= i - maxWordLen; --j) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(new Solution().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(new Solution().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
