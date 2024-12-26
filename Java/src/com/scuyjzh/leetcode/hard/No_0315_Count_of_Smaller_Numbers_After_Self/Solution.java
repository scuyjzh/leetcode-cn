package com.scuyjzh.leetcode.hard.No_0315_Count_of_Smaller_Numbers_After_Self;

import java.util.*;

/**
 * 315. 计算右侧小于当前元素的个数
 *
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts
 * 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 */
class Solution {
    /**
     * 方法一：归并排序
     */
    private int[] index;
    private int[] tempIndex;
    private int[] tempNums;
    private int[] ans;

    public List<Integer> countSmaller1(int[] nums) {
        /*
         * 借鉴逆序对问题中的归并排序的方法。
         *
         * 「归并排序」是分治思想的典型应用，它包含这样三个步骤：
         *   • 分解： 待排序的区间为 [l,r]，令 m=⌊(l+r)/2⌋，把 [l,r] 分成 [l,m] 和 [m+1,r]
         *   • 解决： 使用归并排序递归地排序两个子序列
         *   • 合并： 把两个已经排好序的子序列 [l,m] 和 [m+1,r] 合并起来
         * 在待排序序列长度为 1 的时候，递归开始「回升」，因为默认长度为 1 的序列是排好序的。
         *
         * 那么求逆序对和归并排序又有什么关系呢？关键就在于「归并」当中「并」的过程。通过一个实例来看
         * 看。假设有两个已排序的序列等待合并，分别是 L={8,12,16,22,100} 和 R={9,26,55,64,91}。一开
         * 始用指针 lPtr = 0 指向 L 的首部，rPtr = 0 指向 R 的头部。记已经合并好的部分为 M。
         *   L = [8, 12, 16, 22, 100]   R = [9, 26, 55, 64, 91]  M = []
         *        |                          |
         *      lPtr                       rPtr
         * 发现 lPtr 指向的元素大于 rPtr 指向的元素，于是把 rPtr 指向的元素放入答案，并把 rPtr 后
         * 移一位。
         *   L = [8, 12, 16, 22, 100]   R = [9, 26, 55, 64, 91]  M = [8]
         *           |                       |
         *         lPtr                    rPtr
         * 这个时候把左边的 8 加入了答案，发现右边没有数比 8 小，所以 8 对逆序对总数的「贡献」为 0。
         * 接着继续合并，把 9 加入了答案，此时 lPtr 指向 12，rPtr 指向 26。
         *   L = [8, 12, 16, 22, 100]   R = [9, 26, 55, 64, 91]  M = [8,9]
         *           |                          |
         *         lPtr                       rPtr
         * 此时 lPtr 比 rPtr 小，把 lPtr 对应的数加入答案，并考虑它对逆序对总数的贡献为 rPtr 相对 R 首
         * 位置的偏移 1（即右边只有一个数比 12 小，所以只有它和 12 构成逆序对），以此类推。
         *
         * 发现用这种「算贡献」的思想在合并的过程中计算逆序对的数量的时候，只在 lPtr 右移的时候计算，
         * 是基于这样的事实：当前 lPtr 指向的数字比 rPtr 小，但是比 R 中 [0 ... rPtr - 1] 的其他数字
         * 大，[0 ... rPtr - 1] 的其他数字本应当排在 lPtr 对应数字的左边，但是它排在了右边，所以这里就
         * 贡献了 rPtr 个逆序对。
         *
         * 但是又遇到了新的问题，在「并」的过程中 8 的位置一直在发生改变，应该把计算的贡献保存到哪
         * 里呢？这个时候引入一个新的数组，来记录每个数字对应的原数组中的下标，例如：
         *       a = [8, 9, 1, 5, 2]
         *   index = [0, 1, 2, 3, 4]
         * 排序的时候原数组和这个下标数组同时变化，则排序后得到这样的两个数组：
         *       a = [1, 2, 5, 8, 9]
         *   index = [2, 4, 3, 0, 1]
         *
         * 用一个数组 ans 来记录贡献。对某个元素计算贡献的时候，如果它对应的下标为 p，只需
         * 要在 ans[p] 上加上贡献即可。
         */
        this.index = new int[nums.length];
        this.tempIndex = new int[nums.length];
        this.tempNums = new int[nums.length];
        this.ans = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            index[i] = i;
        }
        int left = 0, right = nums.length - 1;
        mergeSort(nums, left, right);
        List<Integer> list = new ArrayList<>();
        for (int num : ans) {
            list.add(num);
        }
        return list;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int i = left, j = mid + 1, p = left;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                tempNums[p] = nums[i];
                tempIndex[p] = index[i];
                ans[index[i]] += (j - mid - 1);
                ++i;
                ++p;
            } else {
                tempNums[p] = nums[j];
                tempIndex[p] = index[j];
                ++j;
                ++p;
            }
        }
        while (i <= mid) {
            tempNums[p] = nums[i];
            tempIndex[p] = index[i];
            ans[index[i]] += (j - mid - 1);
            ++i;
            ++p;
        }
        while (j <= right) {
            tempNums[p] = nums[j];
            tempIndex[p] = index[j];
            ++j;
            ++p;
        }
        for (int k = left; k <= right; ++k) {
            index[k] = tempIndex[k];
            nums[k] = tempNums[k];
        }
    }

    /**
     * 方法二：二分查找 + 插入排序
     */
    public List<Integer> countSmaller2(int[] nums) {
        int len = nums.length;
        // sorted 数组维护已排序的右边元素（LinkedList 超出时间限制）
        List<Integer> sorted = new ArrayList<>();
        Integer[] ans = new Integer[len];
        for (int i = len - 1; i >= 0; --i) {
            int target = nums[i];
            if (i == len - 1) {
                sorted.add(target);
                ans[i] = 0;
            } else {
                // 二分查找当前元素 target 在已排序数组 sorted 中应该插入的位置
                int index = binarySearch(sorted, target);
                // 将当前元素 target 插入已排序数组 sorted 的 index 处
                sorted.add(index, target);
                // index 的值即表示原数组中存在 index 个右边元素比当前元素 target 小
                ans[i] = index;
            }
        }
        return Arrays.asList(ans);
    }

    /**
     * 二分查找当前元素 target 在已排序数组 sorted 中应该插入的位置
     *
     * @param sorted 已排序数组
     * @param target 查找元素
     * @return 应该插入的位置
     */
    private int binarySearch(List<Integer> sorted, int target) {
        int left = 0, right = sorted.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sorted.get(mid) >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countSmaller1(new int[]{5, 2, 6, 1}));
        System.out.println(new Solution().countSmaller2(new int[]{5, 2, 6, 1}));
    }
}
