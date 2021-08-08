package com.scuyjzh.leetcode.hard.No_0044_Wildcard_Matching;

/**
 * 44. 通配符匹配
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 */
class Solution {
    /**
     * 方法一：动态规划
     * 时间复杂度：O(mn)，其中 m 和 n 分别是字符串 s 和模式 p 的长度。
     * 空间复杂度：O(mn)，即为存储所有 (m+1)(n+1) 个状态需要的空间。此外，在状态转移方程中，由于 dp[i][j] 只会从 dp[i][..] 以及 dp[i−1][..] 转移而来，因此可以使用滚动数组对空间进行优化，即用两个长度为 n+1 的一维数组代替整个二维数组进行状态转移，空间复杂度为 O(n)。
     */
    public boolean isMatch1(String s, String p) {
        /*
         * 思路：
         * 用 dp[i][j] 表示字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否能匹配。
         * 在进行状态转移时，可以考虑模式 p 的第 j 个字符 p_j，与之对应的是字符串 s 中的第 i 个字符 s_i：
         * ① 如果 p_j 是小写字母，那么 s_i 必须也为相同的小写字母，状态转移方程为：
         *            dp[i][j] = (s_i与p_j相同) ∧ dp[i−1][j−1]
         *    其中 ∧ 表示逻辑与运算。也就是说，dp[i][j] 为真，当且仅当 dp[i−1][j−1] 为真，并且 s_i 与 p_j 相同。
         * ② 如果 p_j 是问号，那么对 s_i 没有任何要求，状态转移方程为：
         *            dp[i][j] = dp[i−1][j−1]
         * ③ 如果 p_j 是星号，那么同样对 s_i 没有任何要求，但是星号可以匹配零或任意多个小写字母，因此状态转移方程分为两种情况，即使用或不使用这个星号：
         *            dp[i][j] = dp[i][j−1] ∨ dp[i−1][j]
         *    其中 ∨ 表示逻辑或运算。如果不使用这个星号，那么就会从 dp[i-1][j] 转移而来；如果使用这个星号，那么就会从 dp[i−1][j−1] 转移而来。
         *
         * 细节：
         * 在上述的状态转移方程中，所有的 dp[0][j] 和 dp[i][0] 都是边界条件，因为它们涉及到空字符串或者空模式的情况：
         * • dp[0][0]=True，即当字符串 s 和模式 p 均为空时，匹配成功；
         * • dp[i][0]=False，即空模式无法匹配非空字符串；
         * • dp[0][j] 需要分情况讨论：因为星号才能匹配空字符串，所以只有当模式 p 的前 j 个字符均为星号时，dp[0][j] 才为真；
         */
        int m = s.length();
        int n = p.length();
        // 最终的答案即为 dp[m][n]，其中 m 和 n 分别是字符串 s 和模式 p 的长度
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 边界条件
        dp[0][0] = true;
        for (int i = 1; i <= n; ++i) {
            if (p.charAt(i - 1) != '*') {
                break;
            }
            dp[0][i] = true;
        }

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // ① 如果 p_j 是小写字母，那么 s_i 必须也为相同的小写字母
                // ② 如果 p_j 是问号，那么对 s_i 没有任何要求
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // ③ 如果 p_j 是星号，那么同样对 s_i 没有任何要求，但是星号可以匹配零或任意多个小写字母
                else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 方法二：贪心
     */
    public boolean isMatch2(String s, String p) {
        // greedy solution with idea of DFS
        // star stores the position of last * in p
        // last_match stores the position of the previous matched char in s after a *
        // e.g.
        // s: a c d s c d
        // p: * c d
        // after the first match of the *, star = 0 and last_match = 1
        // when we come to i = 3 and j = 3, we know that the previous match of * is actually wrong,
        // (the first branch of DFS we take is wrong)
        // then it resets j = star + 1
        // since we already know i = last_match will give us the wrong answer
        // so this time i = last_match + 1, and we try to find a longer match of *
        // then after another match we have star = 0 and last_match = 4, which is the right solution
        // since we don't know where the right match for * ends, we need to take a guess (one branch in DFS),
        // and store the information(star and last_match) so we can always back up to the last correct place and take another guess.
        int sp = 0, pp = 0;
        int match = 0, star = -1;
        while (sp < s.length()) {
            // advancing both pointers when (both characters match) or ('?' found in pattern)
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            }
            // * found, only advancing pattern pointer
            else if (pp < p.length() && p.charAt(pp) == '*') {
                star = pp;
                match = sp;
                pp++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (star != -1) {
                pp = star + 1;
                match++;
                sp = match;
            }
            // current pattern pointer is not star, last patter pointer was not *
            // characters do not match
            else {
                return false;
            }
        }
        // check for remaining characters in pattern
        while (pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }
        return pp == p.length();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch1("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "*aaaaaab"));
        System.out.println(solution.isMatch2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "*aaaaaab"));
        System.out.println(solution.isMatch1("acdscd", "*cd"));
        System.out.println(solution.isMatch2("acdscd", "*cd"));
    }
}
