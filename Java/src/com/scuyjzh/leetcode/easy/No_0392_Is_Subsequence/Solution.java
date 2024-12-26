package com.scuyjzh.leetcode.easy.No_0392_Is_Subsequence;

/**
 * 392. 判断子序列
 *
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变
 * 剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子
 * 序列，而"aec"不是）。
 *
 * 进阶：
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查
 * 它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 */
class Solution {
    /**
     * 方法一：双指针
     *
     * • 时间复杂度：O(n+m)，其中 n 为 s 的长度，m 为 t 的长度。每次无论是匹配成功还是失败，都有至
     *   少一个指针发生右移，两指针能够位移的总距离为 n+m。
     * • 空间复杂度：O(1)。
     */
    public boolean isSubsequence1(String s, String t) {
        /*
         * 思路及算法：
         * 本题询问的是，s 是否是 t 的子序列，因此只要能找到任意一种 s 在 t 中出现的方式，即可认为 s 是 t 的子
         * 序列。
         *
         * 而当从前往后匹配，可以发现每次贪心地匹配靠前的字符是最优决策。
         * 这样，初始化两个指针 i 和 j，分别指向 s 和 t 的初始位置。每次贪心地匹配，匹配成功则 i 和 j 同时
         * 右移，匹配 s 的下一个位置，匹配失败则 j 右移，i 不变，尝试用 t 的下一个字符匹配 s。
         *
         * 最终如果 i 移动到 s 的末尾，就说明 s 是 t 的子序列。
         */
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (chs[i] == cht[j]) {
                i++;
            }
            j++;
        }
        return i == n;
    }

    /**
     * 方法二：动态规划
     *
     * • 时间复杂度：O(m×∣Σ∣+n)，其中 n 为 s 的长度，m 为 t 的长度，Σ 为字符集，在本题中字符串只
     *   包含小写字母，∣Σ∣=26。预处理时间复杂度 O(m)，判断子序列时间复杂度 O(n)。
     *     ○ 如果是计算 k 个平均长度为 n 的字符串是否为 t 的子序列，则时间复杂度为 O(m×∣Σ∣+k×n)。
     * • 空间复杂度：O(m×∣Σ∣)，为动态规划数组的开销。
     */
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();

        // 状态数组 dp[i][j] 表示字符串 t 以 i 位置开始第一次出现字符 j 的位置
        int[][] dp = new int[m + 1][26];
        // 初始化边界条件，dp[i][j] = m 表示 t 中不存在字符 j
        for (int i = 0; i < 26; ++i) {
            dp[m][i] = m;
        }
        // 从后往前递推初始化 dp 数组
        for (int i = m - 1; i >= 0; --i) {
            for (int j = 0; j < 26; ++j) {
                if (cht[i] == j + 'a') {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        int add = 0;
        for (int i = 0; i < n; ++i) {
            // t 中没有 s[i]，直接返回 false
            if (dp[add][chs[i] - 'a'] == m) {
                return false;
            }
            // 否则直接跳到 t 中 s[i] 第一次出现的位置之后一位
            add = dp[add][chs[i] - 'a'] + 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isSubsequence1("abc", "ahbgdc"));
        System.out.println(new Solution().isSubsequence2("axc", "ahbgdc"));
    }
}
