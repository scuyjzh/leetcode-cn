package com.scuyjzh.leetcode.easy.No_0167_Two_Sum_II;

import java.util.*;

/**
 * 167. 两数之和 II - 输入有序数组
 *
 * 给定一个已按照 非递减顺序排列 的整数数组numbers ，请你从数组中找
 * 出两个数满足相加之和等于目标数target 。
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers
 * 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] <
 * answer[1] <= numbers.length 。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的
 * 元素。
 */
class Solution {
    /**
     * 方法一：二分查找
     *
     * • 时间复杂度：O(NlogN)，其中 N 是数组的长度。需要遍历数组一次确定第一个数，时间复杂度是 O(N)
     *   ，寻找第二个数使用二分查找，时间复杂度是 O(logN)，因此总时间复杂度是 O(NlogN)。
     * • 空间复杂度：O(1)。
     */
    public int[] twoSum1(int[] numbers, int target) {
        /*
         * 在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等
         * 于目标值减去第一个数的差。利用数组的有序性质，可以通过二分查找的方法寻找第二个数。为了避免重复
         * 寻找，在寻找第二个数时，只在第一个数的右侧寻找。
         */
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 方法二：双指针
     *
     * • 时间复杂度：O(N)，其中 N 是数组的长度。两个指针移动的总次数最多为 N 次。
     * • 空间复杂度：O(1)。
     */
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null) {
            return null;
        }
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().twoSum1(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(new Solution().twoSum2(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(new Solution().twoSum2(new int[]{-1, 0}, -1)));
    }
}
