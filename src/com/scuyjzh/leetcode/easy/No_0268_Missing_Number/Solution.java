package com.scuyjzh.leetcode.easy.No_0268_Missing_Number;

import java.util.*;

/**
 * 268. 丢失的数字
 *
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围
 * 内没有出现在数组中的那个数。
 *
 * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题？
 */
class Solution {
    /**
     * 方法一：排序
     */
    public int missingNumber1(int[] nums) {
        // 将数组排序之后，即可根据数组中每个下标处的元素是否和下标相等，得到丢失的数字
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i) {
                return i;
            }
        }
        // 如果对任意 0≤i<n，都有 nums[i]=i，则缺失的数字是 n
        return nums.length;
    }

    /**
     * 方法二：哈希集合
     */
    public int missingNumber2(int[] nums) {
        // 使用哈希集合，可以将时间复杂度降低到 O(n)
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int missing = -1;
        for (int i = 0; i <= nums.length; ++i) {
            if (!set.contains(i)) {
                missing = i;
                break;
            }
        }
        return missing;
    }

    /**
     * 方法三：位运算
     */
    public int missingNumber3(int[] nums) {
        /*
         * 数组 nums 中有 n 个数，在这 n 个数的后面添加从 0 到 n 的每个整数，则添加了 n+1 个整数，共有 2n+1
         * 个整数。
         *
         * 在 2n+1 个整数中，丢失的数字只在后面 n+1 个整数中出现一次，其余的数字在前面 n 个整数中（即数组
         * 中）和后面 n+1 个整数中各出现一次，即其余的数字都出现了两次。
         *
         * 根据出现的次数的奇偶性，可以使用按位异或运算得到丢失的数字。按位异或运算 ⊕ 满足交换律和结合律，
         * 且对任意整数 x 都满足 x⊕x=0 和 x⊕0=x。
         *
         * 由于上述 2n+1 个整数中，丢失的数字出现了一次，其余的数字都出现了两次，因此对上述 2n+1 个整数
         * 进行按位异或运算，结果即为丢失的数字。
         */
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 0; i <= nums.length; ++i) {
            xor ^= i;
        }
        return xor;
    }

    /**
     * 方法四：数学
     */
    public int missingNumber4(int[] nums) {
        int n = nums.length;
        // 将从 0 到 n 的全部整数之和记为 total
        // 根据高斯求和公式，有：total = n(n + 1)/2
        int total = n * (n + 1) / 2;
        // 将数组 nums 的元素之和记为 arrSum
        int arrSum = 0;
        for (int num : nums) {
            arrSum += num;
        }
        // 则 arrSum 比 total 少了丢失的一个数字，因此丢失的数字即为 total 与 arrSum 之差
        return total - arrSum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().missingNumber1(new int[]{0, 1, 3}));
        System.out.println(new Solution().missingNumber2(new int[]{0, 1}));
        System.out.println(new Solution().missingNumber3(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        System.out.println(new Solution().missingNumber4(new int[]{0}));
    }
}
