package com.scuyjzh.leetcode.hard.No_0354_Russian_Doll_Envelopes;

import java.util.*;

/**
 * 354. 俄罗斯套娃信封问题
 * <p>
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 */
class Solution {
    /**
     * 方法一：动态规划
     * 时间复杂度：O(N^2)，其中 N 是数组 envelopes 的长度，排序需要的时间复杂度为 O(NlogN)，动态规划需要的时间复杂度为 O(N^2)，前者在渐近意义下小于后者，可以忽略。
     * 空间复杂度：O(N)，即为数组 dp 需要的空间。
     */
    public int maxEnvelopes1(int[][] envelopes) {
        /*
         * 思路与算法：
         * 将数组 envelopes 中的所有信封按照 w 升序排序，将 h 值作为排序的第二关键字
         * 进行降序排序，这样一来，对于每一种 w 值，其对应的信封在排序后的数组中是按照
         * h 值递减的顺序出现的，那么这些 h 值不可能组成长度超过 1 的严格递增的序列。
         *
         * 因此就可以得到解决本题需要的方法：
         *   • 首先将所有的信封按照 w 值第一关键字升序、h 值第二关键字降序进行排序；
         *   • 随后就可以忽略 w 维度，求出 h 维度的最长严格递增子序列，其长度即为答案。
         *
         * 经典的「最长严格递增子序列」问题可以参考「300. 最长递增子序列的官方题解」。
         */
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 方法二：基于二分查找的动态规划
     * 时间复杂度：O(NlogN)，其中 N 是数组 envelopes 的长度，排序需要的时间复杂度为 O(NlogN)，动态规划需要的时间复杂度同样为 O(NlogN)。
     * 空间复杂度：O(N)，即为数组 f 需要的空间。
     */
    public int maxEnvelopes2(int[][] envelopes) {
        /*
         * 思路与算法：
         * 设 f[j] 表示 h 的前 i 个元素可以组成的长度为 j 的最长严格递增子序列的末尾元素
         * 的最小值，如果不存在长度为 j 的最长严格递增子序列，对应的 f 值无定义。在定义范
         * 围内，可以看出 f 值是严格单调递增的，因为越长的子序列的末尾元素显然越大。
         *
         * 在进行状态转移时，考虑当前的元素 h_i：
         *   • 如果 h_i 大于 f 中的最大值，那么 h_i 就可以接在 f 中的最大值之后，形成
         *     一个长度更长的严格递增子序列；
         *   • 否则找出 f 中比 h_i 严格小的最大的元素 f[j_0]，即 f[j_0]<h_i≤f[j_0+1]，
         *     那么 h_i 可以接在 f[j_0] 之后，形成一个长度为 j_0+1 的严格递增子序列，
         *     因此需要对 f[j_0+1] 进行更新：
         *         f[j_0+1]=h_i
         *     可以在 f 上进行二分查找，找出满足要求的 j_0。
         *
         * 在遍历所有的 h_i 之后，f 中最后一个有定义的元素的下标增加 1（下标从 0 开始）即
         * 为最长严格递增子序列的长度。
         */
        if (envelopes.length == 0) {
            return 0;
        }

        int n = envelopes.length;
        Arrays.sort(envelopes, (e1, e2) -> {
            if (e1[0] != e2[0]) {
                return e1[0] - e2[0];
            } else {
                return e2[1] - e1[1];
            }
        });

        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < n; ++i) {
            int num = envelopes[i][1];
            if (num > f.get(f.size() - 1)) {
                f.add(num);
            } else {
                int index = binarySearch(f, num);
                f.set(index, num);
            }
        }
        return f.size();
    }

    private int binarySearch(List<Integer> f, int target) {
        int low = 0, high = f.size() - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (f.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxEnvelopes1(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        System.out.println(new Solution().maxEnvelopes2(new int[][]{{1, 1}, {1, 1}, {1, 1}}));
    }
}