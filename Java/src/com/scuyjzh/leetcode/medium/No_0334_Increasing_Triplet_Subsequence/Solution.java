package com.scuyjzh.leetcode.medium.No_0334_Increasing_Triplet_Subsequence;

/**
 * 334. 递增的三元子序列
 * <p>
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k)且满足 i < j < k ，使得nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 */
class Solution {
    /**
     * 方法：贪心
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。需要遍历数组 nums 一次。
     * 空间复杂度：O(1)。只需要使用常数的额外空间。
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }

        // 维护两个变量 one 和 two，代表递增子序列的第一个数和第二个数
        int one = Integer.MAX_VALUE;
        // two 附带隐含信息，即在 two 之前有个数比 two 小
        int two = Integer.MAX_VALUE;
        for (int three : nums) {
            // three <= one
            if (three <= one) {
                one = three;
            }
            // three > one && three < two
            else if (three <= two) {
                two = three;
            }
            // three > one
            else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().increasingTriplet(new int[]{2, 1, 5, 0, 7, 6}));
        System.out.println(new Solution().increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println(new Solution().increasingTriplet(new int[]{5, 4, 3, 2, 1}));
    }
}
