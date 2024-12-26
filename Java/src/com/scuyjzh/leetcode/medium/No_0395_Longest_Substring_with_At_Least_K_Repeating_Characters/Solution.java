package com.scuyjzh.leetcode.medium.No_0395_Longest_Substring_with_At_Least_K_Repeating_Characters;

import java.util.*;

/**
 * 395. 至少有 K 个重复字符的最长子串
 *
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该
 * 子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 */
class Solution {
    /**
     * 方法一：分治
     *
     * • 时间复杂度：O(N⋅∣Σ∣)，其中 N 为字符串的长度，Σ 为字符集，本题中字符串仅包含小写字母，因此
     * ∣Σ∣=26。由于每次递归调用都会完全去除某个字符，因此递归深度最多为 ∣Σ∣。
     * • 空间复杂度：O(∣Σ∣^2)。递归的深度为 O(∣Σ∣)，每层递归需要开辟 O(∣Σ∣) 的额外空间。
     */
    public int longestSubstring1(String s, int k) {
        /*
         * 对于字符串 s，如果存在某个字符 ch，它的出现次数大于 0 且小于 k，则任何包含 ch 的子串都不可能满足要
         * 求。也就是说，将字符串按照 ch 切分成若干段，则满足要求的最长子串一定出现在某个被切分的段
         * 内，而不能跨越一个或多个段。因此，可以考虑分治的方式求解本题。
         */
        return dfs(s, k);
    }

    private int dfs(String s, int k) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            cnt[s.charAt(i) - 'a']++;
        }

        String split = "";
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = String.valueOf((char) (i + 'a'));
                break;
            }
        }

        if ("".equals(split)) {
            return s.length();
        }

        int ans = 0;
        String[] splits = s.split(split);
        for (String ss : splits) {
            int length = dfs(ss, k);
            ans = Math.max(ans, length);
        }

        return ans;
    }

    /**
     * 方法二：枚举 + 滑动窗口
     *
     * • 时间复杂度：O(N⋅∣Σ∣+∣Σ∣^2)，其中 N 为字符串的长度，Σ 为字符集，本题中字符串仅包含小写字
     *   母，因此 ∣Σ∣=26。需要遍历所有可能的 t，共 ∣Σ∣ 种可能性；内层循环中滑动窗口的复杂度为
     *   O(N)，且初始时需要 O(∣Σ∣) 的时间初始化 cnt 数组。
     * • 空间复杂度：O(∣Σ∣)。
     */
    public int longestSubstring2(String s, int k) {
        int ans = 0;
        int n = s.length();
        char[] cs = s.toCharArray();
        // 遍历所有可能的 t，共 26 种可能性
        for (int t = 1; t <= 26; ++t) {
            int[] cnt = new int[26];
            // tot 代表 [l, r] 区间所有的字符种类数量
            // sum 代表满足「出现次数不少于 k」的字符种类数量
            int tot = 0, sum = 0;
            int l = 0, r = 0;
            while (r < n) {
                cnt[cs[r] - 'a']++;
                // 如果添加到 cnt 之后为 1，说明字符总数 +1
                if (cnt[cs[r] - 'a'] == 1) {
                    tot++;
                }
                // 如果添加到 cnt 之后等于 k，说明该字符从不达标变为达标，达标数量 + 1
                if (cnt[cs[r] - 'a'] == k) {
                    sum++;
                }
                // 当区间所包含的字符种类数量 tot 超过了当前限定的数量 t，那么要删除掉一些字母，即「左指针」右移
                while (tot > t) {
                    cnt[cs[l] - 'a']--;
                    // 如果添加到 cnt 之后为 0，说明字符总数-1
                    if (cnt[cs[l] - 'a'] == 0) {
                        tot--;
                    }
                    // 如果添加到 cnt 之后等于 k - 1，说明该字符从达标变为不达标，达标数量 - 1
                    if (cnt[cs[l] - 'a'] == k - 1) {
                        sum--;
                    }
                    l++;
                }
                // 当所有字符都符合要求，更新答案
                if (tot == sum) {
                    ans = Math.max(ans, r - l + 1);
                }
                r++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestSubstring1("aaabb", 3));
        System.out.println(new Solution().longestSubstring2("ababbc", 2));
    }
}
