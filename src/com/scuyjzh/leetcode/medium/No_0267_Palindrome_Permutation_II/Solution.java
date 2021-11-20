package com.scuyjzh.leetcode.medium.No_0267_Palindrome_Permutation_II;

import java.util.*;

/**
 * 267. 回文排列 II
 *
 * 给定一个字符串 s ，返回其通过重新排列组合后所有可能的回文字符串，
 * 并去除重复的组合。
 * 如不能形成任何回文排列时，则返回一个空列表。
 */
class Solution {
    /**
     * 方法：回溯
     */
    public List<String> generatePalindromes(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            return res;
        }

        // 使用哈希表（map）帮助统计字符串中每个字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        // 哈希表中的键（key）存放字符，值（value）存放字符出现的次数
        for (int i = 0; i < s.length(); ++i) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        // 统计出现次数为奇数的字符个数
        int count = 0;
        int size = 0;
        // 记录出现奇数次数的字符 ch
        Character ch = null;
        // 遍历映射表，如果发现超过一个字符出现了奇数次，那么字符串就不可以组成一个回文串
        for (char key : map.keySet()) {
            if (map.get(key) % 2 == 1) {
                ch = key;
                count += 1;
                // 将哈希表中出现奇数次数的字符次数减 1
                map.put(key, map.get(key) - 1);
            }
            size += map.get(key) / 2;
        }
        if (count > 1) {
            return res;
        }

        // 获取需要进行全排列的字符数组（原字符串中出现次数为奇数的字符减 1 后，剩下字符取一半）
        char[] chs = new char[size];
        int idx = 0;
        for (char key : map.keySet()) {
            for (int k = 0; k < map.get(key) / 2; ++k) {
                chs[idx++] = key;
            }
        }

        // 布尔数组 visited 初始化为 false，表示这些字符还没有被选择过
        boolean[] visited = new boolean[size];
        // 开始经典回溯，通过深度优先遍历实现
        // 参考「力扣」第 47 题：全排列 II 回溯 + 剪枝 的解法
        dfs(res, chs, new StringBuilder(), visited, ch);
        return res;
    }

    private void dfs(List<String> res, char[] chs, StringBuilder sb, boolean[] visited, Character ch) {
        // 递归终止条件
        if (sb.length() == chs.length) {
            // 拷贝
            StringBuilder temp = new StringBuilder(sb.toString());
            // 生成回文串
            String reverse = new StringBuilder(sb.toString()).reverse().toString();
            res.add(ch == null ? temp.append(reverse).toString() : temp.append(ch).append(reverse).toString());
            return;
        }

        for (int i = 0; i < chs.length; ++i) {
            // 判断当前数是否被选择过
            if (visited[i]) {
                continue;
            }

            // 剪枝条件：
            // i > 0 是为了保证 chs[i - 1] 有意义
            // 写 !visited[i - 1] 是因为 chs[i - 1] 在深度优先遍历的过程中刚刚被撤销选择（回撤），即此时 chs[i - 1] 还未被使用
            // 因此需要剪枝，否则下面的搜索中还会使用到，就会产生重复
            if (i > 0 && chs[i] == chs[i - 1] && !visited[i - 1]) {
                continue;
            }

            // 添加当前字符并记录访问状态
            sb.append(chs[i]);
            visited[i] = true;

            // 继续递归填下一个字符
            dfs(res, chs, sb, visited, ch);

            // 回溯部分的代码，和 dfs 之前的代码是对称的
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generatePalindromes("aabb"));
        System.out.println(new Solution().generatePalindromes("abc"));
    }
}
