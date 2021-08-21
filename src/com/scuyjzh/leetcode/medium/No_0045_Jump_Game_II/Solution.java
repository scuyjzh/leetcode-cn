package com.scuyjzh.leetcode.medium.No_0045_Jump_Game_II;

/**
 * 45. 跳跃游戏 II
 * <p>
 * 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 */
class Solution {
    /**
     * 方法一：反向查找出发位置
     * 时间复杂度：O(n^2)，其中 n 是数组长度。有两层嵌套循环，在最坏的情况下，例如数组中的所有元素都是 1，position 需要遍历数组中的每个位置，对于 position 的每个值都有一次循环。
     * 空间复杂度：O(1)。
     */
    public int jump1(int[] nums) {
        /*
         * 思路：
         * 目标是到达数组的最后一个位置，因此可以考虑最后一步跳跃前所在的位置，该位置通过跳跃能够到达最后一个位置。
         * 直观上来看，可以「贪心」地选择距离最后一个位置最远的那个位置，也就是对应下标最小的那个位置。
         * 因此，可以从左到右遍历数组，选择第一个满足要求的位置。
         * 找到最后一步跳跃前所在的位置之后，继续贪心地寻找倒数第二步跳跃前所在的位置，以此类推，直到找到数组的开始位置。
         */
        int position = nums.length - 1;
        int steps = 0;
        // 直到找到数组的开始位置
        while (position != 0) {
            for (int i = 0; i < position; ++i) {
                if (i + nums[i] >= position) {
                    // 更新要找的位置
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }

    /**
     * 方法二：正向查找可到达的最大位置
     * 时间复杂度：O(n)，其中 n 是数组长度。
     * 空间复杂度：O(1)。
     */
    public int jump2(int[] nums) {
        /*
         * 思路：
         * 如果「贪心」地进行正向查找，每次找到可到达的最远位置，就可以在线性时间内得到最少的跳跃次数。
         * 例如，对于数组 [2,3,1,2,4,2,3]，初始位置是下标 0，从下标 0 出发，最远可到达下标 2。
         * 下标 0 可到达的位置中，下标 1 的值是 3，从下标 1 出发可以达到更远的位置，因此第一步到达下标 1。
         * 从下标 1 出发，最远可到达下标 4。下标 1 可到达的位置中，下标 4 的值是 4 ，从下标 4 出发可以达到更远的位置，因此第二步到达下标 4。
         *
         * 在具体的实现中，维护当前能够到达的最大下标位置，记为边界。
         * 从左到右遍历数组，到达边界时，更新边界并将跳跃次数增加 1。
         *
         * 在遍历数组时，不访问最后一个元素，这是因为在访问最后一个元素之前，边界一定大于等于最后一个位置，否则就无法跳到最后一个位置了。
         * 如果访问最后一个元素，在边界正好为最后一个位置的情况下，会增加一次「不必要的跳跃次数」，因此不必访问最后一个元素。
         */
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 维护当前能够到达的最大下标位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                // 到达边界时，更新边界并将跳跃次数增加 1
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.jump1(new int[]{2, 3, 1, 1, 4}));
        System.out.println(solution.jump2(new int[]{2, 3, 0, 1, 4}));
    }
}
