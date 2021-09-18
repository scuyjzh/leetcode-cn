package com.scuyjzh.leetcode.medium.No_0376_Wiggle_Subsequence;

/**
 * 376. 摆动序列
 * <p>
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。
 * 第一个差（如果存在的话）可能是正数或负数。
 * 仅有一个元素或者含两个不等元素的序列也视作摆动序列。
 * • 例如，[1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
 * • 相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
 * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
 */
class Solution {
    /**
     * 方法一：优化的动态规划
     * 时间复杂度：O(N)，其中 N 是序列的长度。只需要遍历该序列一次。
     * 空间复杂度：O(1)。只需要常数空间来存放若干变量。
     */
    public int wiggleMaxLength1(int[] nums) {
        /*
         * 约定：
         *   1.某个序列被称为「上升摆动序列」，当且仅当该序列是摆动序列，且最后一个元素呈上升趋势。
         *   2.某个序列被称为「下降摆动序列」，当且仅当该序列是摆动序列，且最后一个元素呈下降趋势。
         *   3.特别地，对于长度为 1 的序列，它既是「上升摆动序列」，也是「下降摆动序列」。
         *   4.序列中的某个元素被称为「峰」，当且仅当该元素两侧的相邻元素均小于它。
         *   5.序列中的某个元素被称为「谷」，当且仅当该元素两侧的相邻元素均大于它。
         *   6.特别地，对于位于序列两端的元素，只有一侧的相邻元素小于或大于它，也称其为「峰」或「谷」。
         *
         * 思路及解法：
         * 每当选择一个元素作为摆动序列的一部分时，这个元素要么是上升的，要么是下降的，这取决于前一个元素的大小。
         *
         * 一、状态定义
         *   • up[i] 表示以 nums[0:i] 中的某一个元素为结尾的最长的「上升摆动序列」的长度。
         *   • down[i] 表示以 nums[0:i] 中的某一个元素为结尾的最长的「下降摆动序列」的长度。
         *
         * 二、状态转移
         *   • nums[i+1] > nums[i]：
         *     - 假设 down[i] 表示的最长「下降摆动序列」的末尾元素下标恰好为 i，遇到上升元素后，up[i+1] = down[i]+1。
         *       这是因为 up 一定从 down 中产生（初始除外），并且 down[i] 此时最大。
         *     - 假设 down[i] 表示的最长「下降摆动序列」的末尾元素下标小于 i，设其为 j，那么 nums[j:i] 一定是递增的。
         *       这是因为 nums[j:i] 若完全递减，末尾元素下标会等于 i；若存在波动，则会有 down[i] > down[j]。
         *       而由于 nums[j:i] 递增，down[j:i] 会一直等于 down[j]，因此依然满足 up[i+1] = down[i]+1 。
         *   • nums[i+1] < nums[i]：
         *     - 假设 up[i] 表示的最长「上升摆动序列」的末尾元素下标恰好为 i，遇到下降元素后，down[i+1] = up[i]+1。
         *       这是因为 down 一定从 up 中产生（初始除外），并且 up[i] 此时最大。
         *     - 假设 up[i] 表示的最长「上升摆动序列」的末尾元素下标小于 i，设其为 j，那么 nums[j:i] 一定是递减的。
         *       这是因为 nums[j:i] 若完全递增，末尾元素下标会等于 i；若存在波动，则会有 up[i] > up[j]。
         *       而由于 nums[j:i] 递减，up[j:i] 会一直等于 up[j]，因此依然满足 down[i+1] = up[i]+1 。
         *   • nums[i+1] == nums[i]：
         *     - 新的元素无法加入任何摆动序列，因此 up 和 down 保持不变。
         *
         * 三、状态初始化
         * 对于初始状态，根据状态定义可以知道 up=1, down=1。
         *
         * 空间优化：
         * 注意到 up 和 down 仅需要前一个状态来进行转移，所以可以优化存储，维护两个变量即可。
         */
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1, down = 1;
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    /**
     * 方法二：贪心
     * 时间复杂度：O(N)，其中 N 是序列的长度。只需要遍历该序列一次。
     * 空间复杂度：O(1)。只需要常数空间来存放若干变量。
     */
    public int wiggleMaxLength2(int[] nums) {
        /*
         * 思路及解法：
         * 观察这个序列可以发现，不断地交错选择「峰」与「谷」，可以使得该序列尽可能长。
         *
         * 这样，只需要统计该序列中「峰」与「谷」的数量即可（注意序列两端的数也是「峰」或「谷」），
         * 但需要注意处理相邻的相同元素。
         *
         * 在实际代码中，记录当前序列的上升下降趋势。每次加入一个新元素时，用新的上升下降趋势与之前对比，
         * 如果出现了「峰」或「谷」，答案加一，并更新当前序列的上升下降趋势。
         */
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int pre = nums[1] - nums[0];
        int ret = pre != 0 ? 2 : 1;
        for (int i = 2; i < n; ++i) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && pre <= 0) || (diff < 0 && pre >= 0)) {
                ret++;
                pre = diff;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().wiggleMaxLength1(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(new Solution().wiggleMaxLength1(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(new Solution().wiggleMaxLength1(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));

        System.out.println(new Solution().wiggleMaxLength2(new int[]{1, 7, 4, 9, 2, 5}));
        System.out.println(new Solution().wiggleMaxLength2(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8}));
        System.out.println(new Solution().wiggleMaxLength2(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
    }
}