package com.scuyjzh.leetcode.medium.No_0300_Longest_Increasing_Subsequence;

/**
 * 300. 最长递增子序列
 * <p>
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 */
class Solution {
    /**
     * 方法一：动态规划
     * 时间复杂度：O(N^2)，其中 N 为数组 nums 的长度。动态规划的状态数为 N，计算状态 dp[i] 时，需要 O(N) 的时间遍历 dp[0…i−1] 的所有状态，所以总时间复杂度为 O(N^2)。
     * 空间复杂度：O(N)，需要额外使用长度为 N 的 dp 数组。
     */
    public int lengthOfLIS1(int[] nums) {
        /*
         * 思路与算法：
         * 一、状态定义
         * 定义 dp[i] 为考虑前 i 个元素，以第 i 个数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。
         *
         * 二、状态转移
         * 从小到大计算 dp 数组的值，在计算 dp[i] 之前，已经计算出 dp[0…i−1] 的值，则状态转移方程为：
         *     dp[i] = max(dp[j])+1, 其中 0≤j<i 且 num[j]<num[i]
         * 即考虑往 dp[0…i−1] 中最长的上升子序列后面再加一个 nums[i]。
         * 由于 dp[j] 代表 nums[0…j] 中以 nums[j] 结尾的最长上升子序列，所以如果能从 dp[j] 这个状态转移过来，
         * 那么 nums[i] 必然要大于 nums[j]，才能将 nums[i] 放在 nums[j] 后面以形成更长的上升子序列。
         *
         * 最后，整个数组的最长上升子序列即所有 dp[i] 中的最大值。
         *     LIS_length = max(dp[i]), 其中 0≤i<n
         *
         * 三、状态初始化
         * dp[i] = 1，1 个字符显然是长度为 1 的上升子序列。
         *
         * 四、空间优化
         * 遍历到一个新数的时候，之前所有的状态值都得保留，因此无法优化空间。
         */
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i = 1; i < nums.length; ++i) {
            dp[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    /**
     * 方法二：贪心 + 二分查找
     * 时间复杂度：O(NlogN)。数组 nums 的长度为 N，依次用数组中的元素去更新 d 数组，而更新 d 数组时需要进行 O(logN) 的二分搜索，所以总时间复杂度为 O(NlogN)。
     * 空间复杂度：O(N)，需要额外使用长度为 N 的 d 数组。
     */
    public int lengthOfLIS2(int[] nums) {
        /**
         * 思路与算法：
         * 考虑一个简单的贪心，如果要使上升子序列尽可能地长，则需要让序列上升得尽可能慢，因此
         * 希望每次在上升子序列最后加上的那个数尽可能地小。
         *
         * 基于上面的贪心思路，维护一个数组 d[i] ，表示长度为 i 的最长上升子序列的末尾元素的
         * 最小值，用 len 记录目前最长上升子序列的长度，起始时 len 为 1，d[1]=nums[0]。
         *
         * 同时可以注意到 d[i] 是关于 i 单调递增的。
         * 因为如果 d[j]≥d[i] 且 j<i，考虑从长度为 i 的最长上升子序列的末尾删除 i−j 个元素，
         * 那么这个序列长度变为 j ，且第 j 个元素 x（末尾元素）必然小于 d[i]，也就小于 d[j]。
         * 那么就找到了一个长度为 j 的最长上升子序列，并且末尾元素比 d[j] 小，从而产生了矛盾。
         * 因此数组 d 的单调性得证。
         *
         * 依次遍历数组 nums 中的每个元素，并更新数组 d 和 len 的值。如果 nums[i]>d[len]
         * 则更新 len=len+1，否则在 d[1…len]中找满足 d[i−1]<nums[j]<d[i] 的下标 i，更新
         * d[i]=nums[j]。根据 d 数组的单调性，可以使用二分查找寻找下标 i，优化时间复杂度。
         *
         * 最后整个算法流程为：
         *   • 设当前已求出的最长上升子序列的长度为 len（初始时为 1），从前往后遍历数组 nums，在遍历到 nums[i] 时：
         *     - 如果 nums[i] > d[len] ，则直接加入到 d 数组末尾，并更新 len=len+1；
         *     - 否则，在 d 数组中二分查找，找到第一个比 nums[i] 小的数 d[k] ，并更新 d[k+1]=nums[i]。
         *
         * 以输入序列 [0, 8, 4, 12, 2] 为例：
         *   • 第一步插入 0，d = [0]；
         *   • 第二步插入 8，d = [0, 8]；
         *   • 第三步插入 4，d = [0, 4]；
         *   • 第四步插入 12，d = [0, 4, 12]；
         *   • 第五步插入 2，d = [0, 2, 12]。
         * 最终得到最大递增子序列长度为 3。
         */
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                int l = 1, r = len, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLIS1(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(new Solution().lengthOfLIS1(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(new Solution().lengthOfLIS2(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }
}