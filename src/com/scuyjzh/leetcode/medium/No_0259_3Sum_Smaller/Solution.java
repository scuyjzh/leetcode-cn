package com.scuyjzh.leetcode.medium.No_0259_3Sum_Smaller;

import java.util.*;

/**
 * 259. 较小的三数之和
 *
 * 给定一个长度为 n 的整数数组和一个目标值 target，寻找能够使条
 * 件nums[i] + nums[j] + nums[k] < target成立的三元组 i, j, k个数
 * （0 <= i < j < k < n）。
 */
class Solution {
    /**
     * 方法一：二分查找
     *
     * • 时间复杂度：O(N^2logN)。枚举 i 和 j 各需要 O(N) 的时间复杂度，二分查找 k 需要 O(logN) 的时间复
     *   杂度，因此总的时间复杂度为 O(N^2logN)。
     * • 空间复杂度：O(1)。
     */
    public int threeSumSmaller1(int[] nums, int target) {
        /*
         * 首先考虑更简单的情况。题目中要求的是三元组 (i,j,k)，可以首先尝试一个简单的版本，求出二元
         * 组 (i,j)。即
         *     给定一个长度为 n 的数组 nums，找出所有的二元组 (i,j) 的数目，满足 0≤i<j<n 且 nums[i]+
         *     nums[j]<target。
         *
         * 在这个简化的问题中，首先对数组进行排序，随后从小到大枚举 i，并用二分查找，找出最大的满足
         * nums[i]+nums[j]<target 的 j，那么就可以知道对于当前的 i，有 j−i 对二元组是满足要求的。
         *
         * 解决了二元组的问题后，可以发现，在问题变成三元组时，只要在二元组的算法框架外部再套上一层循
         * 环就行了。也就是说，从小到大枚举 i 和 j，规定 i<j，随后在数组上二分查找，找出最大的满足
         * nums[i]+nums[j]+nums[k]<target 的 k，那么对于当前的 i 和 j，有 k−j 对三元组是满足要求的。
         */
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; ++i) {
            sum += twoSumSmaller1(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller1(int[] nums, int startIndex, int target) {
        int sum = 0;
        for (int i = startIndex; i < nums.length - 1; ++i) {
            int j = binarySearch(nums, i, target - nums[i]);
            sum += j - i;
        }
        return sum;
    }

    private int binarySearch(int[] nums, int startIndex, int target) {
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 方法二：双指针
     *
     * • 时间复杂度：O(N^2)。在每一步中，要么 left 向右移动一位，要么 right 向左移动一位。当 left≥right
     *   时循环结束，因此它的时间复杂度为 O(N)。加上外层的循环，总的时间复杂度为 O(N^2)。
     * • 空间复杂度：O(1)。
     */
    public int threeSumSmaller2(int[] nums, int target) {
        /*
         * 在方法一中，首先解决了一个简化版的问题，随后在外部套上一层循环，就可以解决当前的问题。如果
         * 能找出更好的解决简化版的问题的方法，就能在更低的时间复杂度内解决当前的问题。
         *
         * 首先仍然对数组进行排序，例如 nums=[3,5,2,8,1] 排序后变成 [1,2,3,5,8]。target 的值为 7。
         *     [1, 2, 3, 5, 8]
         *      ↑           ↑
         *     left       right
         * 用两个指针 left 和 right 分别指向数组中的第一个和最后一个元素，它们的和为 1+8=9>target，这
         * 说明 right 不能出现在二元组中（因为 left 只向左移动，如果此时二者的和已经大于 target，那么在 left 移
         * 动的过程中，二者的和就不可能小于 target 了），因此需要将 right 向左移动一位。
         *     [1, 2, 3, 5, 8]
         *      ↑        ↑
         *     left    right
         * 现在二者的和为 1+5=6<target，那么对于当前的 left，应当有 right−left=3 对二元组满足要求，它们
         * 分别为 (1,2),(1,3),(1,5)。在这之后，将 left 向右移动一位。
         */
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller2(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller2(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().threeSumSmaller1(new int[]{-2, 0, 1, 3}, 2));
        System.out.println(new Solution().threeSumSmaller2(new int[]{-2, 0, 1, 3}, 2));
    }
}
