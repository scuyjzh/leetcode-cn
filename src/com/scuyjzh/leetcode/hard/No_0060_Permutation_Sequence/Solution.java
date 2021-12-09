package com.scuyjzh.leetcode.hard.No_0060_Permutation_Sequence;

/**
 * 60. 排列序列
 *
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *   1."123"
 *   2."132"
 *   3."213"
 *   4."231"
 *   5."312"
 *   6."321"
 * 给定 n 和 k，返回第 k 个排列。
 */
class Solution {
    private final int[] factorials = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    private boolean[] visited;

    public String getPermutation(int n, int k) {
        /*
         * 思路分析：容易想到，使用同「力扣」第 46 题： 全排列 的回溯搜索算法，依次得到全排列，输出第 k 个全
         * 排列即可。事实上，不必求出所有的全排列。
         *
         * 基于以下几点考虑：
         *   • 所求排列 一定在叶子节点处得到，进入每一个分支，可以根据已经选定的数的个数，进而计算还未选
         *     定的数的个数，然后计算阶乘，就知道这一个分支的 叶子节点 的个数：
         *       ○ 如果 k 大于这一个分支将要产生的叶子节点数，直接跳过这个分支，这个操作叫「剪枝」；
         *       ○ 如果 k 小于等于这一个分支将要产生的叶子节点数，那说明所求的全排列一定在这一个分支将要
         *         产生的叶子节点里，需要递归求解。
         *
         * 编码注意事项：
         *   • 计算阶乘的时候，可以使用循环计算。注意：0!=1，它表示了没有数可选的时候，即表示到达叶子节
         *     点了，排列数只剩下 1 个；
         *   • 题目中说「给定 n 的范围是 [1,9]」，可以把从 0 到 9 的阶乘计算好，放在一个数组里，可以根据索引
         *     直接获得阶乘值；
         *   • 编码的时候，+1 还是 −1 ，大于还是大于等于，这些不能靠猜。常见的做法是：代入一个具体的数
         *     值，认真调试。
         */
        visited = new boolean[n + 1];
        StringBuilder path = new StringBuilder();
        dfs(n, k, 0, path);
        return path.toString();
    }

    private void dfs(int n, int k, int index, StringBuilder path) {
        if (index == n) {
            return;
        }

        // 计算还未确定的数字的全排列的个数，第 1 次进入的时候是 n - 1
        int cnt = factorials[n - 1 - index];
        for (int i = 1; i <= n; ++i) {
            if (visited[i]) {
                continue;
            }
            if (cnt < k) {
                k -= cnt;
                continue;
            }
            path.append(i);
            visited[i] = true;
            dfs(n, k, index + 1, path);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子节点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getPermutation(3, 3));
        System.out.println(new Solution().getPermutation(4, 9));
        System.out.println(new Solution().getPermutation(3, 1));
    }
}
