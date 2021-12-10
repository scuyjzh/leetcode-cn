package com.scuyjzh.leetcode.medium.No_0093_Restore_IP_Addresses;

import java.util.*;

/**
 * 93. 复原 IP 地址
 *
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不
 * 能含有前导 0），整数之间用 '.' 分隔。
 *   • 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是
 *     "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地
 *     址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能
 * 的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你不能重新
 * 排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 */
class Solution {
    int n;
    String s;
    List<String> res;

    public List<String> restoreIpAddresses(String s) {
        this.n = s.length();
        this.s = s;
        this.res = new ArrayList<>();

        if (n > 12 || n < 4) {
            return res;
        }

        LinkedList<String> path = new LinkedList<>();
        dfs(0, 4, path);
        return res;
    }

    private void dfs(int begin, int residue, LinkedList<String> path) {
        if (begin == n) {
            if (residue == 0) {
                // 当遍历到最后一个字符且剩余段数为 0，将地址添加到结果中
                res.add(String.join(".", path));
            }
            return;
        }

        //  每个 ip 段最多截取 3 个字符
        for (int i = begin; i < begin + 3; ++i) {
            if (i >= n) {
                break;
            }

            // 剪枝
            if ((residue - 1) * 3 < n - i - 1) {
                continue;
            }

            if (judgeIpSegment(s, begin, i)) {
                path.addLast(s.substring(begin, i + 1));
                dfs(i + 1, residue - 1, path);
                // 回溯
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        // 不能含有前导 0
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        // 将当前截取的字符串转化成 ip 段
        int res = len <= 0 ? 0 : Integer.parseInt(s.substring(left, right + 1));
        // 判断截取到的 ip 段是否合法
        return res >= 0 && res <= 255;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().restoreIpAddresses("25525511135"));
        System.out.println(new Solution().restoreIpAddresses("0000"));
        System.out.println(new Solution().restoreIpAddresses("1111"));
    }
}
