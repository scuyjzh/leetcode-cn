package com.scuyjzh.leetcode.hard.No_0354_Russian_Doll_Envelopes;

import java.util.*;

/**
 * 354. 俄罗斯套娃信封问题
 *
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，
 * 表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另
 * 一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个
 * 信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 */
class Solution {
    /**
     * 方法一：动态规划
     */
    public int maxEnvelopes1(int[][] envelopes) {
        /*
         * 根据题目的要求，如果选择了 k 个信封，它们的的宽度依次为 w_0,w_1,...,w_{k-1}，高度依次为
         * h_0,h_1,...,h_{k-1}，那么需要满足：
         *                              w_0<w_1<...<w_{k-1}
         *                              h_0<h_1<...<h_{k-1}
         *
         * 同时控制 w 和 h 两个维度并不是那么容易，因此考虑固定一个维度，再在另一个维度上进行选择。例
         * 如，固定 w 维度，那么将数组 envelopes 中的所有信封按照 w 升序排序。这样一来，只要按照
         * 信封在数组中的出现顺序依次进行选取，就一定保证满足：
         *                              w_0≤w_1≤...≤w_{k-1}
         * 了。然而小于等于 ≤ 和小于 < 还是有区别的，但不妨首先考虑一个简化版本的问题：
         *   • 如果保证所有信封的 w 值互不相同，那么可以设计出一种得到答案的方法吗？
         *
         * 在 w 值互不相同的前提下，小于等于 ≤ 和小于 < 是等价的，那么在排序后，就可以完全忽略 w 维度，
         * 只需要考虑 h 维度了。此时，需要解决的问题即为：
         *   • 给定一个序列，需要找到一个最长的子序列，使得这个子序列中的元素严格单调递增，即上面要求
         *     的：
         *                              h_0<h_1<...<h_{k-1}
         *
         * 那么这个问题就是经典的「300. 最长递增子序列」问题了。
         *
         * 当解决了简化版本的问题之后，来想一想使用上面的方法解决原问题，会产生什么错误。当 w 值相
         * 同时，如果不规定 h 值的排序顺序，那么可能会有如下的情况：
         *   • 排完序的结果为 [(w,h)]=[(1,1),(1,2),(1,3),(1,4)]，由于这些信封的 w 值都相同，不存在一个信封可
         *     以装下另一个信封，那么只能在其中选择 1 个信封。然而如果完全忽略 w 维度，剩下的 h 维
         *     度为 [1,2,3,4]，这是一个严格递增的序列，那么就可以选择所有的 4 个信封了，这就产生了错误。
         *
         * 因此，必须要保证对于每一种 w 值，最多只能选择 1 个信封。
         *
         * 可以将 h 值作为排序的第二关键字进行降序排序，这样一来，对于每一种 w 值，其对应的信封在排序后
         * 的数组中是按照 h 值递减的顺序出现的，那么这些 h 值不可能组成长度超过 1 的严格递增的序列，这就从根
         * 本上杜绝了错误的出现。
         *
         * 因此就可以得到解决本题需要的方法：
         *   • 首先将所有的信封按照 w 值第一关键字升序、h 值第二关键字降序进行排序；
         *   • 随后就可以忽略 w 维度，求出 h 维度的最长严格递增子序列，其长度即为答案。
         *
         * 经典的「最长严格递增子序列」问题可以参考「300. 最长递增子序列」的官方题解。
         *
         * 设 f[i] 表示 h 的前 i 个元素可以组成的最长严格递增子序列的长度，并且必须选择第 i 个元素 h_i。在进
         * 行状态转移时，可以考虑倒数第二个选择的元素 h_j，必须满足 h_j<h_j 且 j<i，因此可以写出状态转移
         * 方程：
         *                      f[i] = max(f[j])+1, 其中 0≤j<i 且 h_j<h_i
         * 如果不存在比 h_i 小的元素 h_j，那么 f[i] 的值为 1，即只选择了唯一的第 i 个元素。
         *
         * 在计算完所有的 f 值之后，其中的最大值即为最长严格递增子序列的长度。
         */
        int len = envelopes.length;
        if (len == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);

        int[] f = new int[len];
        Arrays.fill(f, 1);
        int ans = 1;
        for (int i = 1; i < len; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }

    /**
     * 方法二：贪心 + 二分查找
     */
    public int maxEnvelopes2(int[][] envelopes) {
        /*
         * 设 f[j] 表示 h 的前 i 个元素可以组成的长度为 j 的最长严格递增子序列的末尾元素的最小值，如果不存在长
         * 度为 j 的最长严格递增子序列，对应的 f 值无定义。在定义范围内，可以看出 f 值是严格单调递增的，因为
         * 越长的子序列的末尾元素显然越大。
         *
         * 在进行状态转移时，考虑当前的元素 h_i：
         *   • 如果 h_i 大于 f 中的最大值，那么 h_i 就可以接在 f 中的最大值之后，形成一个长度更长的严格递增子
         *     序列；
         *   • 否则找出 f 中比 h_i 严格小的最大的元素 f[j_0]，即 f[j_0]<h_i≤f[j_0+1]，那么 h_i 可以接在 f[j_0]
         *     之后，形成一个长度为 j_0+1 的严格递增子序列，因此需要对 f[j_0+1] 进行更新：
         *                                  f[j_0+1]=h_i
         *     可以在 f 上进行二分查找，找出满足要求的 j_0。
         *
         * 在遍历所有的 h_i 之后，f 中最后一个有定义的元素的下标增加 1（下标从 0 开始）即为最长严格递增子序列
         * 的长度。
         */
        int len = envelopes.length;
        if (len == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (e1, e2) -> e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);

        List<Integer> f = new ArrayList<>();
        f.add(envelopes[0][1]);
        for (int i = 1; i < len; ++i) {
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
        int left = 0, right = f.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxEnvelopes1(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}}));
        System.out.println(new Solution().maxEnvelopes2(new int[][]{{1, 1}, {1, 1}, {1, 1}}));
    }
}
