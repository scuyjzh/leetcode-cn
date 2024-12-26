package com.scuyjzh.leetcode.medium.No_0386_Lexicographical_Numbers;

import java.util.*;

/**
 * 386. 字典序排数
 *
 * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */
class Solution {
    /**
     * 方法一：深度优先搜索（递归）
     */
    public List<Integer> lexicalOrder1(int n) {
        /*
         * 思路：
         * https://leetcode-cn.com/problems/lexicographical-numbers/solution/386-zi-dian-xu-pai-shu-o1-kong-jian-fu-z-aea2/
         */
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(n, res, i);
        }
        return res;
    }

    void dfs(int n, List<Integer> res, int val) {
        if (val > n) {
            return;
        }
        res.add(val);
        for (int j = 0; j <= 9; ++j) {
            dfs(n, res, val * 10 + j);
        }
    }

    /**
     * 方法二：深度优先搜索（迭代）
     */
    public List<Integer> lexicalOrder2(int n) {
        List<Integer> res = new ArrayList<>();
        int num = 1;
        while (res.size() < n) {
            while (num <= n) {
                res.add(num);
                // 不断进入下一层
                num *= 10;
            }
            while (num % 10 == 9 || num > n) {
                // 不断返回上一层
                num /= 10;
            }
            // 遍历该层下一个数
            num += 1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lexicalOrder1(13));
        System.out.println(new Solution().lexicalOrder2(2));
    }
}
