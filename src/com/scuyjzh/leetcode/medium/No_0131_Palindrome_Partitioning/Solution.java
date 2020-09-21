package com.scuyjzh.leetcode.medium.No_0131_Palindrome_Partitioning;

import java.util.*;

/**
 * @author scuyjzh
 * @date 2020/8/22 1:44
 */
class Solution {
    /**
     * 方法一：动态规划 + 分治
     */
    public List<List<String>> partition1(String s) {
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        validPalindrome(s, dp);

        return partitionHelper(s, 0, dp);
    }

    /**
     * 方法二：中心扩散 + 分治
     */
    public List<List<String>> partition2(String s) {
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i, dp);
            expandAroundCenter(s, i, i + 1, dp);
        }

        return partitionHelper(s, 0, dp);
    }

    private List<List<String>> partitionHelper(String s, int start, boolean[][] dp) {
        int len = s.length();
        // 递归出口，空字符串
        if (start == len) {
            List<List<String>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = start; i < len; i++) {
            // 当前切割后是回文串才考虑
            if (dp[start][i]) {
                String left = s.substring(start, i + 1);
                // 遍历后边字符串的所有结果，将当前的字符串加到头部
                for (List<String> list : partitionHelper(s, i + 1, dp)) {
                    list.add(0, left);
                    ans.add(list);
                }
            }

        }
        return ans;
    }

    /**
     * 方法三：动态规划 + 深度优先搜索 + 回溯
     */
    public List<List<String>> partition3(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        validPalindrome(s, dp);

        // 采用一个路径变量 path 搜索，path 全局使用一个，在递归执行方法结束以后需要回溯
        // path 的操作只在列表的末端，因此合适的数据结构是栈
        // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
        // 注意：只使用 Stack 相关的接口
        Deque<String> path = new ArrayDeque<>();
        dfs(s, 0, dp, path, res);
        return res;
    }

    /**
     * 方法四：中心扩散 + 深度优先搜索 + 回溯
     */
    public List<List<String>> partition4(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            expandAroundCenter(s, i, i, dp);
            expandAroundCenter(s, i, i + 1, dp);
        }

        // 采用一个路径变量 path 搜索，path 全局使用一个，在递归执行方法结束以后需要回溯
        // path 的操作只在列表的末端，因此合适的数据结构是栈
        // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
        // 注意：只使用 Stack 相关的接口
        Deque<String> path = new ArrayDeque<>();
        dfs(s, 0, dp, path, res);
        return res;
    }

    private void dfs(String s, int start, boolean[][] dp, Deque<String> path, List<List<String>> res) {
        int len = s.length();
        // 在叶子结点是空字符串的时候结算
        if (start == len) {
            // 注意结算的时候，需要生成一个拷贝
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {
            // 如果前缀字符串不是回文，则不产生分支和结点，这一步是剪枝操作
            if (!dp[start][i]) {
                continue;
            }
            path.addLast(s.substring(start, i + 1));
            dfs(s, i + 1, dp, path, res);
            // 回溯
            path.removeLast();
        }
    }

    private void validPalindrome(String s, boolean[][] dp) {
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组，这一步非必需
        char[] charArray = s.toCharArray();
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int j = 0; j < charArray.length; j++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int i = 0; i <= j; i++) {
                // 边界条件：j - 1 - (i + 1) + 1 < 2
                dp[i][j] = charArray[i] == charArray[j] && (j - i < 3 || dp[i + 1][j - 1]);
            }
        }
    }

    private void expandAroundCenter(String s, int left, int right, boolean[][] dp) {
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组，这一步非必需
        char[] charArray = s.toCharArray();
        // 当 left = right 的时候，回文中心是一个字符，回文串的长度是奇数
        // 当 right = left + 1 的时候，回文中心是一个空隙，回文串的长度是偶数
        int len = charArray.length;
        int i = left;
        int j = right;
        while (i >= 0 && j < len && charArray[i] == charArray[j]) {
            dp[i][j] = true;
            i--;
            j++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.partition1("madam"));
        System.out.println(solution.partition2("madam"));
        System.out.println(solution.partition3("madam"));
        System.out.println(solution.partition4("madam"));
        System.out.println(solution.partition1("revive"));
        System.out.println(solution.partition2("revive"));
        System.out.println(solution.partition3("revive"));
        System.out.println(solution.partition4("revive"));
    }
}
