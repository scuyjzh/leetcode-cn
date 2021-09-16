package com.scuyjzh.leetcode.medium.No_0209_Minimum_Size_Subarray_Sum;

import java.util.*;

/**
 * 209. 长度最小的子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [nums_l, nums_{l+1}, ..., nums_{r-1}, nums_r] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 */
class Solution {
    /**
     * 方法一：暴力法
     * 时间复杂度：O(N^2)，其中 N 是数组的长度。需要遍历每个下标作为子数组的开始下标，对于每个开始下标，需要遍历其后面的下标得到长度最小的子数组。
     * 空间复杂度：O(1)。
     */
    public int minSubArrayLen1(int s, int[] nums) {
        /*
         * 暴力法是最直观的方法。
         * 初始化子数组的最小长度为无穷大，枚举数组 nums 中的每个下标作为子数组的开始下标，
         * 对于每个开始下标 i，需要找到大于或等于 i 的最小下标 j，使得从 nums[i] 到 nums[j] 的元素和大于或等于 s，
         * 并更新子数组的最小长度（此时子数组的长度是 j−i+1）。
         * 注意：使用 Python 语言实现方法一会超出时间限制。
         */
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            int sum = 0;
            for (int j = i; j < n; ++j) {
                sum += nums[j];
                if (sum >= s) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法二：前缀和 + 二分查找
     * 时间复杂度：O(NlogN)，其中 N 是数组的长度。需要遍历每个下标作为子数组的开始下标，遍历的时间复杂度是 O(N)，对于每个开始下标，需要通过二分查找得到长度最小的子数组，二分查找得时间复杂度是 O(logN)，因此总时间复杂度是 O(NlogN)。
     * 空间复杂度：O(N)，其中 N 是数组的长度。额外创建数组 sums 存储前缀和。
     */
    public int minSubArrayLen2(int s, int[] nums) {
        /*
         * 方法一的时间复杂度是 O(N^2)，因为在确定每个子数组的开始下标后，找到长度最小的子数组需要 O(N) 的时间。
         * 如果使用二分查找，则可以将时间优化到 O(logN)。
         *
         * 为了使用二分查找，需要额外创建一个数组 sums 用于存储数组 nums 的前缀和，其中 sums[i] 表示从 nums[0] 到 nums[i−1] 的元素和。
         * 得到前缀和之后，对于每个开始下标 i，可通过二分查找得到大于或等于 i 的最小下标 bound，
         * 使得 sums[bound]−sums[i−1] ≥ s，并更新子数组的最小长度（此时子数组的长度是 bound−(i−1)）。
         *
         * 因为这道题保证了数组中每个元素都为正，所以前缀和一定是递增的，这一点保证了二分的正确性。
         * 如果题目没有说明数组中每个元素都为正，这里就不能使用二分来查找这个位置了。
         *
         * 在很多语言中，都有现成的库和函数来为实现这里二分查找大于等于某个数的第一个位置的功能，
         * 比如 C++ 的 lower_bound，Java 中的 Arrays.binarySearch，C# 中的 Array.BinarySearch，Python 中的 bisect.bisect_left。
         */
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int[] sums = new int[n + 1];
        // 为了方便计算，令 size = n + 1
        // sums[0] = 0 意味着前 0 个元素的前缀和为 0
        // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
        // 以此类推
        for (int i = 1; i <= n; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for (int i = 1; i <= n; ++i) {
            int target = s + sums[i - 1];
            int bound = Arrays.binarySearch(sums, target);
            if (bound < 0) {
                bound = -bound - 1;
            }
            if (bound <= n) {
                ans = Math.min(ans, bound - (i - 1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法三：滑动窗口
     * 时间复杂度：O(N)，其中 N 是数组的长度。指针 start 和 end 最多各移动 N 次。
     * 空间复杂度：O(1)。
     */
    public int minSubArrayLen3(int s, int[] nums) {
        /*
         * 在方法一和方法二中，都是每次确定子数组的开始下标，然后得到长度最小的子数组，因此时间复杂度较高。
         * 为了降低时间复杂度，可以使用滑动窗口的方法。
         *
         * 定义两个指针 start 和 end 分别表示子数组（滑动窗口）的开始位置和结束位置，
         * 维护变量 sum 存储子数组中的元素和（即从 nums[start] 到 nums[end] 的元素和）。
         * 初始状态下，start 和 end 都指向下标 0，sum 的值为 0。
         *
         * 每一轮迭代，将 nums[end] 加到 sum，如果 sum ≥ s，则更新子数组的最小长度（此时子数组的长度是 end−start+1），
         * 然后将 nums[start] 从 sum 中减去并将 start 右移，直到 sum < s，在此过程中同样更新子数组的最小长度。
         * 在每一轮迭代的最后，将 end 右移。
         */
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < n) {
            sum += nums[end];
            while (sum >= s) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minSubArrayLen1(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(new Solution().minSubArrayLen2(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(new Solution().minSubArrayLen3(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}