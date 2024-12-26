package com.scuyjzh.leetcode.hard.No_0154_Find_Minimum_in_Rotated_Sorted_Array_II;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 *
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转
 * 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能
 * 得到：
 *   • 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 *   • 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组
 * [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数
 * 组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 */
class Solution {
    /**
     * 方法：二分查找
     *
     * • 时间复杂度：平均时间复杂度为 O(log n)，其中 n 是数组 nums 的长度。如果数组是随机生成的，那么
     *   数组中包含相同元素的概率很低，在二分查找的过程中，大部分情况都会忽略一半的区间。而在最坏情
     *   况下，如果数组中的元素完全相同，那么 while 循环就需要执行 n 次，每次忽略区间的右端点，时间
     *   复杂度为 O(n)。
     * • 空间复杂度：O(1)。
     */
    public int findMin(int[] nums) {
        /*
         * 本题是「153. 寻找旋转排序数组中的最小值」的延伸。
         *
         * 考虑数组中的最后一个元素 x：在最小值右侧的元素，它们的值一定都小于等于 x；而在最小值左侧的
         * 元素，它们的值一定都大于等于 x。因此，可以根据这一条性质，通过二分查找的方法找出最小值。
         *
         * 在二分查找的每一步中，左边界为 left，右边界为 right，区间的中点为 mid，最小值就在该区间内。
         * 将中轴元素 nums[mid] 与右边界元素 nums[right] 进行比较，可能会有以下的三种情况：
         *   1.第一种情况是 nums[mid] < nums[right]。这说明 nums[mid] 是最小值右侧的元素，因此
         *     可以忽略二分查找区间的右半部分。
         *   2.第二种情况是 nums[mid] > nums[right]。这说明 nums[mid] 是最小值左侧的元素，因此
         *     可以忽略二分查找区间的左半部分。
         *   3.第三种情况是 nums[mid] = nums[right]。由于重复元素的存在，并不能确定 nums[mid]
         *     究竟在最小值的左侧还是右侧，因此不能莽撞地忽略某一部分的元素。唯一可以知道的是，由于它
         *     们的值相同，所以无论 nums[right] 是不是最小值，都有一个它的「替代品」nums[mid]，因此可以忽略
         *     二分查找区间的右端点。
         *
         * 当二分查找结束时，就得到了最小值所在的位置。
         *
         * 理解三个问题：
         * 一、为什么 --right 不会对结果产生影响？
         *   • 产生影响条件：删除的元素为唯一最小元素
         *   • 执行条件：nums[mid] == nums[right]
         *   • 矛盾
         *
         * 二、为什么 right = mid 而 left = mid + 1？
         *   • 一般二分查找：left = mid + 1 right = mid + 1
         *     原因：确定 mid 所指元素并非查找元素
         *   • nums[mid] > nums[right] 时，mid 所指一定不是最小值，
         *     因为一定比 nums[right] 大，所以 left = mid + 1；
         *   • nums[mid] < nums[right] 时，mid 所指可能是最小值，
         *     所以 right = mid；
         *
         * 三、时间/空间复杂度是多少？
         *   • 时间复杂度
         *     - 一般情况：与二分相同为 O(logN)
         *     - 特殊情况：始终执行 --right，复杂度为 O(N)
         *   • 空间复杂度
         *     - 指针：left right mid，复杂度 O(1)
         */
        int left = 0;
        int right = nums.length - 1;

        if (right == 0) {
            return nums[0];
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            // 如果 nums[mid] < nums[right]，后半部分一定顺序，分界点在 mid 左边
            if (nums[mid] < nums[right]) {
                right = mid;
            }
            // 如果 nums[mid] > nums[right]，前半部分一定顺序，分界点在 mid 右边
            else if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // 如果 nums[mid] == nums[right]，无法二分，暴力缩小范围
            else if (nums[mid] == nums[right]) {
                --right;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findMin(new int[]{1, 3, 5}));
        System.out.println(new Solution().findMin(new int[]{2, 2, 2, 0, 1}));
    }
}
