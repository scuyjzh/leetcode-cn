package com.scuyjzh.leetcode.hard.No_0041_First_Missing_Positive;

/**
 * 41. 缺失的第一个正数
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 */
class Solution {
    /**
     * 方法：原地哈希
     * 时间复杂度：O(N)，其中 N 是数组的长度。
     * 空间复杂度：O(1)。
     */
    public int firstMissingPositive(int[] nums) {
        /*
         * 思路：
         * 缺失的第一个正数就在 [1, len + 1] 里，最后 len + 1 这个元素不用找。因为在前面的 len 个元素都找不到的情况下，才返回 len + 1。
         * 可以把 1 填充到 nums[0] 的位置，把 2 填充到 nums[1] 的位置，按照这种思路整理一遍数组。
         * 如果填充过程中，nums[i] < 1 && nums[i] > len，就直接舍弃。
         * 然后再遍历数组，第 1 个遇到的它的值不等于下标的那个数，就是要找的缺失的第一个正数。
         * 这个思想就相当于自己编写哈希函数，这个哈希函数的规则就是数值为 i 的数映射到下标为 i - 1 的位置。
         *
         * 比如 nums = [7, 8, 9, 10, 11], len = 5：
         * 数组中的元素都无法进行填充，直接舍弃跳过。
         * 在最终遍历数组的时候，发现 nums[0] != 0 + 1，那么第一个缺失的数就是 1。
         *
         * 比如 nums = [3, 1, 2], len = 3：
         * 填充后数组最终变成了 [1, 2, 3]，每个元素都对应了自己的位置，那么第一个缺失的数就是 len + 1 = 4。
         */
        int len = nums.length;
        for (int i = 0; i < len; ++i) {
            /*
             * 只有在以下情况都满足时才进行交换：
             * 1. nums[i] 是 [1, len] 之间的数，即满足 nums[i] > 0 && nums[i] <= len；
             * 2. nums[i] 不在自己应该呆的位置，即满足 nums[i] != i + 1；
             * 3. nums[i] 应该呆的位置没有被同伴占有（即存在重复值占有），即 nums[i] ！= nums[nums[i] - 1]。
             *
             * 为什么使用 while？因为交换后，原本 i 位置的 nums[i] 已经交换到了别的地方，交换后到此位置的新值不一定是适合这个位置，因此需要重新进行判断交换
             * 如果使用 if，那么进行一次交换后，i 就会 +1 进入下一个循环，那么交换过来的新值就可能无法去找到它该在的位置
             */
            while (nums[i] > 0 && nums[i] <= len && nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < len; ++i) {
            // 第一个“值不配位”的值所占的位置就是缺失的最小（第一个）正数
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstMissingPositive(new int[]{3, 4, -1, 1}));
    }
}
