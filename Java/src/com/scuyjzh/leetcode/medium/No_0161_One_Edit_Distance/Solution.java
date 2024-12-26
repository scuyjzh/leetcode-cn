package com.scuyjzh.leetcode.medium.No_0161_One_Edit_Distance;

/**
 * 161. 相隔为 1 的编辑距离
 * <p>
 * 给定两个字符串 s 和 t，判断他们的编辑距离是否为 1。
 * 注意：
 * 满足编辑距离等于 1 有三种可能的情形：
 *   1.往 s 中插入一个字符得到 t
 *   2.从 s 中删除一个字符得到 t
 *   3.在 s 中替换一个字符得到 t
 */
class Solution {
    /**
     * 遍历一遍的算法：
     * 时间复杂度：O(N)。最坏情况下，当字符串的长度足够接近，即 abs(N(s)−N(t))<=1，其中 N 是求一个字符串的长度的函数。最好情况下，abs(N(s)−N(t))>1 时，时间复杂度为 O(1)。
     * 空间复杂度：O(1)。
     */
    public boolean isOneEditDistance(String s, String t) {
        /*
         * 想法：
         * 首先，确认两个字符串的长度差得不会太远。如果长度差大于等于 2 个字符，那么 s 和 t 肯定不是
         * 编辑距离为 1 的字符串。
         *
         * 接下来，假设 s 总是比 t 短或者长度相等。否则，调用函数 isOneEditDistance(t, s) 来将两个
         * 字符串交换。
         *
         * 现在，遍历字符串一遍并找到第一个不同的字符。
         *
         * 如果前 len(s) 个字符没有差别，那么只有两种可能：
         *   • 两个字符串是相同的
         *   • 两个字符串的编辑距离相隔为 1
         *
         * 如果存在不同的字符 s[i] != t[i]：
         *   • 如果两个字符串的长度相等，所有 接下来的字符都应该相等，否则编辑距离不为 1 。这种情况下，
         *     需要比较 s 和 t 从第 i + 1 个字符开始的子串是否相等。
         *   • 如果 t 比 s 多一个字符， t[i] 应该是 t 串多出来的那个字符。这种情况下，需要比较 s
         *     串从第 i 个字符开始的子串和 t 串从 i + 1 开始的子串是否相等。
         */
        int ns = s.length();
        int nt = t.length();

        // Ensure that s is shorter than t.
        if (ns > nt) {
            return isOneEditDistance(t, s);
        }

        // The strings are NOT one edit away distance
        // if the length diff is more than 1.
        if (nt - ns > 1) {
            return false;
        }

        for (int i = 0; i < ns; i++) {
            if (s.charAt(i) != t.charAt(i))
            // if strings have the same length
            {
                if (ns == nt) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                // if strings have different lengths
                else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }

        // If there is no diffs on ns distance
        // the strings are one edit away only if
        // t has one more character.
        return (ns + 1 == nt);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isOneEditDistance("ab", "acb"));
        System.out.println(new Solution().isOneEditDistance("cab", "ad"));
        System.out.println(new Solution().isOneEditDistance("1203", "1213"));
    }
}
