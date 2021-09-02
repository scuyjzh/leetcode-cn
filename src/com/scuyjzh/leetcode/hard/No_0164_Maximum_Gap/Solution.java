package com.scuyjzh.leetcode.hard.No_0164_Maximum_Gap;

import java.util.*;

/**
 * 164. 最大间距
 * <p>
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 * 如果数组元素个数小于 2，则返回 0。
 * 说明:
 * • 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
 * • 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
 */
class Solution {
    /**
     * 方法一：基数排序
     * 时间复杂度：O(N)，其中 N 是数组的长度。
     * 空间复杂度：O(N)，其中 N 是数组的长度。
     */
    public int maximumGap1(int[] nums) {
        /*
         * 思路与算法：
         * 一种最简单的思路是将数组排序后再找出最大间距，但传统的基于比较的排序算法（快速排序、归并排序等）均需要 O(NlogN) 的时间复杂度。
         * 如果要将时间复杂度降到 O(N)，就必须使用其他的排序算法。
         * 例如，基数排序可以在 O(N) 的时间内完成整数之间的排序。
         *
         * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。它是这样实现的：
         * • 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
         * • 然后，从最低位开始，依次进行一次排序。
         * • 这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列。
         */
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        // 找出最大的数
        int max = Arrays.stream(nums).max().getAsInt();
        // 计算最大数字的长度
        int maxDigitLength = 0;
        while (max != 0) {
            maxDigitLength++;
            max /= 10;
        }
        // 使用计数排序算法对基数（0~9）进行排序，下标 [0, 9] 对应基数 [0, 9]
        int[] counting = new int[10];
        int[] result = new int[nums.length];
        int dev = 1;
        for (int i = 0; i < maxDigitLength; ++i) {
            // 遍历 arr 中的每个元素
            for (int value : nums) {
                // 计算当前位的基数
                int radix = value / dev % 10;
                // 将每个基数出现的次数统计到计数数组中对应下标的位置
                counting[radix]++;
            }
            // 每个元素在结果数组中的最后一个下标位置 = 前面比自己小的数字的总数 + 自己的数量 - 1。将 counting[0] 先减去 1，后续 counting 直接累加即可
            counting[0]--;
            for (int j = 1; j < counting.length; ++j) {
                // 将 counting 计算成当前数字在结果中的最后一个下标位置。位置 = 前面比自己小的数字的总数 + 自己的数量 - 1
                // 由于 counting[0] 已经减了 1，所以后续的减 1 可以省略
                counting[j] += counting[j - 1];
            }
            // 从后往前遍历数组，通过 counting 中记录的下标位置，将 arr 中的元素放到 result 数组中
            for (int j = nums.length - 1; j >= 0; --j) {
                // 计算当前位的基数
                int radix = nums[j] / dev % 10;
                // counting[radix] 表示此元素在结果数组中的下标
                result[counting[radix]] = nums[j];
                // 更新 counting[radix]，指向此元素的前一个下标
                counting[radix]--;
            }
            // 计数排序完成后，将结果拷贝回 arr 数组
            System.arraycopy(result, 0, nums, 0, nums.length);
            // 将计数数组重置为 0
            Arrays.fill(counting, 0);
            dev *= 10;
        }

        int res = 0;
        for (int i = 1; i < n; i++) {
            res = Math.max(res, nums[i] - nums[i - 1]);
        }
        return res;
    }

    /**
     * 方法二：基于桶的算法
     * 时间复杂度：O(N)，其中 N 是数组的长度。
     * 空间复杂度：O(N)，其中 N 是数组的长度。
     */
    public int maximumGap2(int[] nums) {
        /*
         * 思路与算法：
         * 设长度为 N 的数组中最大值和最小值为 max,min，则不难发现相邻数字的最大间距不会小于 ⌈(max−min)/(N−1)⌉。
         * 为了说明这一点，使用反证法：
         * 假设相邻数字的间距都小于 ⌈(max−min)/(N−1)⌉，并记数组排序后从小到大的数字依次为 A_1, A_2, ..., A_N，则有
         *     A_N - A_1 = (A_N - A_{N-1}) + (A_{N-1} - A_{N-2}) + ... + (A_2 - A_1)
         *               < ⌈(max−min)/(N−1)⌉ + ⌈(max−min)/(N−1)⌉ + ... + ⌈(max−min)/(N−1)⌉
         *               < (N-1) * ⌈(max−min)/(N−1)⌉ = max - min
         * 但根据 A_1,A_N 的定义，一定有 A_1 = min，且 A_N = max，故上式会导出矛盾。
         *
         * 因此，可以选取整数 d = ⌊(max−min)/(N−1)⌋ < ⌈(max−min)/(N−1)⌉。
         * 随后，将整个区间划分为若干个大小为 d 的桶，并找出每个整数所在的桶。
         * 根据前面的结论，能够知道，元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
         *
         * 因此，在找出每个元素所在的桶之后，可以维护每个桶内元素的最大值与最小值。
         * 随后，只需从前到后不断比较相邻的桶，用后一个桶的最小值与前一个桶的最大值之差作为两个桶的间距，最终就能得到所求的答案。
         */
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        // 找出最大值和最小值
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        // 将整个区间划分为 bucketSize 个大小为 d 的桶
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
            Arrays.fill(bucket[i], -1);
        }
        for (int num : nums) {
            // 找出每个元素所在的桶
            int idx = (num - minVal) / d;
            // 维护每个桶内元素的最大值与最小值
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = num;
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], num);
                bucket[idx][1] = Math.max(bucket[idx][1], num);
            }
        }

        int res = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; ++i) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                res = Math.max(res, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maximumGap1(new int[]{3, 6, 9, 1}));
        System.out.println(new Solution().maximumGap2(new int[]{3, 6, 9, 1}));
    }
}
