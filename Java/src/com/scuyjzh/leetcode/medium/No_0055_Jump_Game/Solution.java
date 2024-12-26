package com.scuyjzh.leetcode.medium.No_0055_Jump_Game;

/**
 * 55. 跳跃游戏
 * <p>
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
class Solution {
    /**
     * 方法：贪心
     * 时间复杂度：O(n)，其中 n 为数组的大小。只需要访问 nums 数组一遍，共 n 个位置。
     * 空间复杂度：O(1)，不需要额外的空间开销。
     */
    public boolean canJump(int[] nums) {
        /*
         * 思路：
         * 对于每一个可以到达的位置 x，它使得 x+1, x+2, ..., x+nums[x] 这些连续的位置都可以到达。
         * 这样一来，依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。
         * 对于当前遍历到的位置 x，如果它在 最远可以到达的位置 的范围内，就可以用 x+nums[x] 更新 最远可以到达的位置。
         * 在遍历过程中，如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达，就可以直接返回 True 作为答案。
         * 反之，如果在遍历结束后，最后一个位置仍然不可达，就返回 False 作为答案。
         */
        if (nums == null) {
            return false;
        }
        // 最远可以到达的位置
        int rightmost = 0;
        for (int i = 0; i <= rightmost; i++) {
            // 第i个元素能够到达的最远距离
            int temp = i + nums[i];
            // 更新 最远可以到达的位置
            rightmost = Math.max(rightmost, temp);
            // 如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达
            if (rightmost >= nums.length - 1) {
                return true;
            }
        }
        // 最远距离不再改变，且没有到末尾元素
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));
    }
}
