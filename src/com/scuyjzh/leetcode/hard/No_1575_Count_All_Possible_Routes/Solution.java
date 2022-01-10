package com.scuyjzh.leetcode.hard.No_1575_Count_All_Possible_Routes;

import java.util.Arrays;

/**
 * 1575. 统计所有可行路径
 *
 * 给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的
 * 位置。同时给你 start，finish 和 fuel 分别表示出发城市、目的地城
 * 市和你初始拥有的汽油总量。
 *
 * 每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足  j != i
 * 且 0 <= j < locations.length ，并移动到城市 j 。从城市 i 移动到
 * j 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的
 * 绝对值。
 *
 * 请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次
 * （包括 start 和 finish ）。
 *
 * 请你返回从 start 到 finish 所有可能路径的数目。
 *
 * 由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
 */
class Solution {
    int mod = 1000000007;

    int n;
    int[] locations;
    int finish;

    int[][] cache;

    public int countRoutes1(int[] locations, int start, int finish, int fuel) {
        n = locations.length;

        this.locations = locations;
        this.finish = finish;

        // 缓存器：用于记录「特定状态」下的结果
        // cache[u][fuel] 代表从位置 u 出发，当前剩余的油量为 fuel 的前提下，到达目标位置的「路径数量」
        // 之所以要初始化为 -1，是为了区分「某个状态下路径数量为 0」和「某个状态尚未没计算过」两种情况
        cache = new int[n][fuel + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(cache[i], -1);
        }

        // 记忆化搜索
        return dfs(start, fuel);
    }

    /**
     * 计算「路径数量」
     *
     * @param u    当前所在位置
     * @param fuel 剩余油量
     * @return 在位置 u 出发，剩余油量为 fuel 的前提下，到达目标位置的「路径数量」
     */
    private int dfs(int u, int fuel) {
        // 如果缓存器中已经有答案，直接返回
        if (cache[u][fuel] != -1) {
            return cache[u][fuel];
        }

        // 如果一步到达不了，说明从位置 u 不能到达 finish 位置
        // 将结果 0 写入缓存器并返回
        if (Math.abs(locations[u] - locations[finish]) > fuel) {
            cache[u][fuel] = 0;
            return 0;
        }

        // 计算剩余油量为 fuel，从位置 u 到目标位置的「路径数量」
        // 由于每个点都可以经过多次，如果 u = finish，那么本身就算一条路径
        int routes = u == finish ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            if (i != u) {
                int need = Math.abs(locations[i] - locations[u]);
                if (fuel >= need) {
                    routes += dfs(i, fuel - need);
                    routes %= mod;
                }
            }
        }
        cache[u][fuel] = routes;
        return routes;
    }

    public int countRoutes2(int[] locations, int start, int finish, int fuel) {
        // 将「记忆化搜索」转化成「动态规划」
        int n = locations.length;

        // 状态定义：dp[i][j] 代表从位置 i 出发，当前剩余油量为 j 时，到达目标位置的「路径数量」
        int[][] dp = new int[n][fuel + 1];

        // 边界条件：对于本身位置就在目的地的状态，路径数为 1
        for (int i = 0; i <= fuel; ++i) {
            dp[finish][i] = 1;
        }

        // 从状态转移方程可以发现 f[i][fuel]=f[i][fuel]+f[k][fuel-need]
        // 在计算 f[i][fuel] 的时候依赖于 f[k][fuel-need]
        // 其中 i 和 k 并无严格的大小关系
        // 而 fuel 和 fuel-need 具有严格大小关系：fuel >= fuel-need
        // 因此需要先从小到大枚举油量
        for (int cur = 0; cur <= fuel; ++cur) {
            for (int i = 0; i < n; ++i) {
                for (int k = 0; k < n; ++k) {
                    if (i != k) {
                        int need = Math.abs(locations[i] - locations[k]);
                        if (cur >= need) {
                            dp[i][cur] += dp[k][cur - need];
                            dp[i][cur] %= mod;
                        }
                    }
                }
            }
        }
        return dp[start][fuel];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countRoutes1(new int[]{2, 3, 6, 8, 4}, 1, 3, 5));
        System.out.println(new Solution().countRoutes2(new int[]{2, 3, 6, 8, 4}, 1, 3, 5));
    }
}
