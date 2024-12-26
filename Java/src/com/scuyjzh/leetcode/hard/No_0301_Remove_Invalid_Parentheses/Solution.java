package com.scuyjzh.leetcode.hard.No_0301_Remove_Invalid_Parentheses;

import java.util.*;

/**
 * 301. 删除无效的括号
 *
 * 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，
 * 使得输入的字符串有效。
 * 返回所有可能的结果。答案可以按 任意顺序 返回。
 */
class Solution {
    /**
     * 方法一：回溯 + 剪枝
     */
    public List<String> removeInvalidParentheses1(String s) {
        /*
         * 思路与算法：
         * 题目让删除括号使得剩下的括号匹配，要求删除最少的括号数，并且要求得到所有的结果。可
         * 以使用回溯算法，尝试遍历所有可能的去掉非法括号的方案。
         *
         * 首先利用括号匹配的规则求出该字符串 s 中最少需要去掉的左括号的数目 lremove 和右括号的数目
         * rremove，然后尝试在原字符串 s 中去掉 lremove 个左括号和 rremove 个右括号，然后检测剩余的字符
         * 串是否合法匹配，如果合法匹配则则认为该字符串为可能的结果，利用回溯算法来尝试搜索所有可
         * 能的去除括号的方案。
         *
         * 在进行回溯时可以利用以下的剪枝技巧来增加搜索的效率：
         *   • 从字符串中每去掉一个括号，则更新 lremove 或者 rremove，当发现剩余未尝试的字符串的长
         *     度小于 lremove + rremove 时，则停止本次搜索。
         *   • 当 lremove 和 rremove 同时为 0 时，则检测当前的字符串是否合法匹配，如果合法匹配则将
         *     其记录下来。
         *
         * 由于记录的字符串可能存在重复，因此需要对重复的结果进行去重，去重的办法有如下两种：
         *   • 利用哈希表对最终生成的字符串去重。
         *   • 在每次进行搜索时，如果遇到连续相同的括号只需要搜索一次即可，比如当前遇到的字符串为
         *     "(((())"，去掉前四个左括号中的任意一个，生成的字符串是一样的，均为 "((())"，因此在尝
         *     试搜索时，只需去掉一个左括号进行下一轮搜索，不需要将前四个左括号都尝试一遍。
         */
        List<String> res = new ArrayList<>();

        // 最少需要去掉的左括号的数目
        int lremove = 0;
        // 最少需要去掉的右括号的数目
        int rremove = 0;

        char[] chs = s.toCharArray();
        /*
         * 利用括号匹配的规则求出 lremove 和 rremove：
         * • 当遍历到「左括号」的时候：
         *     ○ 「左括号」数量加 1。
         * • 当遍历到「右括号」的时候：
         *     ○ 如果此时「左括号」的数量不为 0，因为「右括号」可以与之前遍历到的「左括号」匹配，此时
         *       「左括号」出现的次数 −1；
         *     ○ 如果此时「左括号」的数量为 0，「右括号」数量加 1。
         * 通过这样的计数规则，得到的「左括号」和「右括号」的数量就是各自最少应该删除的数量。
         */
        for (char ch : chs) {
            if (ch == '(') {
                lremove++;
            } else if (ch == ')') {
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }

        // 利用回溯算法来尝试搜索所有可能的去除括号的方案
        helper(res, s, 0, lremove, rremove);
        return res;
    }

    private void helper(List<String> res, String s, int start, int lremove, int rremove) {
        // 递归出口
        if (lremove == 0 && rremove == 0) {
            // 检测剩余的字符串是否合法匹配
            if (isValid(s)) {
                // 如果合法匹配则则认为该字符串为可能的结果
                res.add(s);
            }
            return;
        }

        int len = s.length();
        char[] chs = s.toCharArray();
        for (int i = start; i < len; ++i) {
            // 由于记录的字符串可能存在重复，因此需要对重复的结果进行去重
            // 在每次进行搜索时，如果遇到连续相同的括号只需要搜索一次即可
            if (i > start && chs[i] == chs[i - 1]) {
                continue;
            }
            // 剪枝，如果剩余的字符无法满足去掉的数量要求，直接返回
            if (lremove + rremove > len - i) {
                return;
            }
            // 尝试去掉一个左括号
            if (lremove > 0 && chs[i] == '(') {
                helper(res, s.substring(0, i) + s.substring(i + 1), i, lremove - 1, rremove);
            }
            // 尝试去掉一个右括号
            if (rremove > 0 && chs[i] == ')') {
                helper(res, s.substring(0, i) + s.substring(i + 1), i, lremove, rremove - 1);
            }
        }
    }

    /**
     * 方法二：广度优先搜索
     */
    public List<String> removeInvalidParentheses2(String s) {
        /*
         * 思路与算法：
         * 注意到题目中要求最少删除，这样的描述正是广度优先搜索算法应用的场景，并且题目也要求输出所有
         * 的结果。在进行广度优先搜索时每一轮删除字符串中的 1 个括号，直到出现合法匹配的字符串为止，此
         * 时进行轮转的次数即为最少需要删除括号的个数。
         *
         * 进行广度优先搜索时，每次保存上一轮搜索的结果，然后对上一轮已经保存的结果中的每一个字符串尝
         * 试所有可能的删除一个括号的方法，然后将保存的结果进行下一轮搜索。在保存结果时，可以利用哈希
         * 表对上一轮生成的结果去重，从而提高效率。
         */
        List<String> res = new ArrayList<>();
        // 利用哈希表对上一轮生成的结果去重，从而提高效率
        Set<String> currSet = new HashSet<>();

        currSet.add(s);
        while (true) {
            for (String str : currSet) {
                if (isValid(str)) {
                    res.add(str);
                }
            }
            if (res.size() > 0) {
                return res;
            }
            Set<String> nextSet = new HashSet<>();
            for (String str : currSet) {
                int len = str.length();
                char[] chs = str.toCharArray();
                // 对上一轮已经保存的结果中的每一个字符串尝试所有可能的删除一个括号的方法
                for (int i = 0; i < len; ++i) {
                    // 由于记录的字符串可能存在重复，因此需要对重复的结果进行去重
                    // 在每次进行搜索时，如果遇到连续相同的括号只需要搜索一次即可
                    if (i > 0 && chs[i] == chs[i - 1]) {
                        continue;
                    }
                    if (chs[i] == '(' || chs[i] == ')') {
                        nextSet.add(str.substring(0, i) + str.substring(i + 1));
                    }
                }
            }
            // 然后将保存的结果进行下一轮搜索
            currSet = nextSet;
        }
    }

    private boolean isValid(String s) {
        int cnt = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (chs[i] == '(') {
                cnt++;
            } else if (chs[i] == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeInvalidParentheses1("()())()"));
        System.out.println(new Solution().removeInvalidParentheses2("(a)())()"));
        System.out.println(new Solution().removeInvalidParentheses2(")("));
    }
}
