package com.scuyjzh.leetcode.medium.No_0220_Contains_Duplicate_III;

import java.util.*;

/**
 * 220. 存在重复元素 III
 *
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，
 * 使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 */
class Solution {
    /**
     * 方法：滑动窗口 + 有序集合
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        /*
         * 对于序列中每一个元素 x 左侧的至多 k 个元素，如果这 k 个元素中存在一个元素落在区间 [x−t, x+t] 中，
         * 就找到了一对符合条件的元素。注意到对于两个相邻的元素，它们各自的左侧的 k 个元素中有 k−1 个
         * 是重合的。于是可以使用滑动窗口的思路，维护一个大小为 k 的滑动窗口，每次遍历到元素 x 时，滑动
         * 窗口中包含元素 x 前面的最多 k 个元素，检查窗口中是否存在元素落在区间 [x−t, x+t] 中即可。
         *
         * 如果使用队列维护滑动窗口内的元素，由于元素是无序的，只能对于每个元素都遍历一次队列来检查是
         * 否有元素符合条件。如果数组的长度为 n，则使用队列的时间复杂度为 O(nk)，会超出时间限制。
         *
         * 因此希望能够找到一个数据结构维护滑动窗口内的元素，该数据结构需要满足以下操作：
         *   • 支持添加和删除指定元素的操作，否则无法维护滑动窗口；
         *   • 内部元素有序，支持二分查找的操作，这样可以快速判断滑动窗口中是否存在元素满足条件。具体
         *     而言，对于元素 x，当希望判断滑动窗口中是否存在某个数 y 落在区间 [x−t, x+t] 中，只需要判
         *     断滑动窗口中所有大于等于 x−t 的元素中的最小元素是否小于等于 x+t 即可。
         *
         * 可以使用有序集合来支持这些操作。
         *
         * 实现方面，在有序集合中查找大于等于 x−t 的最小的元素 y，如果 y 存在，且 y ≤ x+t，就找到
         * 了一对符合条件的元素。完成检查后，将 x 插入到有序集合中，如果有序集合中元素数量超过了 k，
         * 将有序集合中最早被插入的元素删除即可。
         *
         * 如果当前有序集合中存在相同元素，那么此时程序将直接返回 true。因此本题中的有序集合无需处理相同元
         * 素的情况。
         *
         * 为防止整型 int 溢出，既可以使用长整型 long，也可以对查找区间 [x−t, x+t] 进行限制，使其落在
         * int 范围内。
         */
        int n = nums.length;
        // 使用有序集合维护一个大小为 k 的滑动窗口
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < n; ++i) {
            // The ceiling() method of java.util.TreeSet<E> class is used to
            // return the least element in this set greater than or equal to
            // the given element, or null if there is no such element.
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            // 在有序集合中如果存在大于等于 x−t 的最小的元素 y，且 y ≤ x+t，就找到了一对符合条件的元素
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            // 完成检查后，将 x 插入到有序集合中
            set.add((long) nums[i]);
            // 如果有序集合中元素数量超过了 k，将有序集合中最早被插入的元素删除
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 3, 0));
        System.out.println(new Solution().containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
    }
}
