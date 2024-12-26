package com.scuyjzh.leetcode.medium.No_0351_Android_Unlock_Patterns;

/**
 * 351. 安卓系统手势解锁
 *
 * https://leetcode-cn.com/problems/android-unlock-patterns/
 */
class Solution {
    int[][] skip;

    public int numberOfPatterns(int m, int n) {
        // skip 数组记录两点之间经过的其他点
        skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[4][6] = skip[6][4] = skip[2][8] = skip[8][2] = 5;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        skip[7][9] = skip[9][7] = 8;
        int result = 0;
        boolean[] visited = new boolean[10];
        // 深度遍历经过的点数分别为 m,m+1,..,n-1,n 时的序列数
        for (int i = m; i <= n; ++i) {
            // 从当前点出发就算走了一步，因此剩余步数需要减一
            // 1,3,7,9 对称
            result += dfs(1, i - 1, visited) * 4;
            // 2,4,6,8 对称
            result += dfs(2, i - 1, visited) * 4;
            // 5 独立
            result += dfs(5, i - 1, visited);
        }
        return result;
    }

    /**
     * @param current        起点
     * @param remainKeyCount 剩余步数
     * @param visited        记录所有点是否走过的布尔数组
     */
    private int dfs(int current, int remainKeyCount, boolean[] visited) {
        if (remainKeyCount == 0) {
            return 1;
        }
        // 返回从这个点开始，往后走完 remainKeyCount 步的所有可能情况数
        int result = 0;
        // 当前点已经走过了，将状态设为 true
        visited[current] = true;
        // 遍历 current 的下一步所有的可能目标
        for (int i = 1; i <= 9; ++i) {
            // 当前点到 i 节点的路径中是否经过其他点
            int crossThroughNumber = skip[current][i];
            // !visited[i] 表示 i 节点没有走过
            // visited[crossThroughNumber] 表示中间节点已经经过
            // crossThroughNumber == 0 表示这两个节点相邻（没有中间节点）
            if (!visited[i] && (visited[crossThroughNumber] || crossThroughNumber == 0)) {
                // current 点已经处理好，剩下的路就交给 i 点了，已走了一步，因此 remainKeyCount - 1
                result += dfs(i, remainKeyCount - 1, visited);
            }
        }
        // 已经统计好 current 往下走的所有可能情况数，将状态重置为 false
        visited[current] = false;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numberOfPatterns(1, 1));
        System.out.println(new Solution().numberOfPatterns(1, 2));
    }
}
