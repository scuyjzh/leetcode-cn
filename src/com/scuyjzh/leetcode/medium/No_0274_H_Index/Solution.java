package com.scuyjzh.leetcode.medium.No_0274_H_Index;

import java.util.*;

/**
 * 274. H 指数
 * <p>
 * 给你一个整数数组 citations ，其中 citations[i] 表示研究者的第 i 篇论文被引用的次数。计算并返回该研究者的 h 指数。
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （n 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 n - h篇论文每篇被引用次数不超过 h 次。
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * 提示：如果 h 有多种可能的值，h 指数 是其中最大的那个。
 */
class Solution {
    /**
     * 方法一：排序
     * 时间复杂度：O(n*log n)，其中 n 为数组 citations 的长度。即为排序的时间复杂度。
     * 空间复杂度：O(log n)，其中 n 为数组 citations 的长度。即为排序的空间复杂度。
     */
    public int hIndex1(int[] citations) {
        // 将初始的 H 指数 h 设为 0，然后将引用次数排序，并且对排序后的数组从大到小遍历
        int h = 0, i = citations.length - 1;
        Arrays.sort(citations);
        // 如果当前 H 指数为 h 并且在遍历过程中找到当前值 citations[i]>h，则说明找到了一篇被引用了至少 h+1 次的论文，所以将现有的 h 值加 1
        while (i >= 0 && citations[i] > h) {
            // 遍历直到 h 无法继续增大
            h++;
            i--;
        }
        // 最后返回 h 作为最终答案
        return h;
    }

    /**
     * 方法二：计数排序
     * 时间复杂度：O(n)，其中 n 为数组 citations 的长度。需要遍历数组 citations 一次，以及遍历长度为 n+1 的数组 counter 一次。
     * 空间复杂度：O(n)，其中 n 为数组 citations 的长度。需要创建长度为 n+1 的数组 counter。
     */
    public int hIndex2(int[] citations) {
        int n = citations.length;
        // 新建并维护一个数组 counter 用来记录当前引用次数的论文有几篇
        int[] counter = new int[n + 1];
        for (int citation : citations) {
            // 根据定义，可以发现 H 指数不可能大于总的论文发表数，所以对于引用次数超过论文发表数的情况，将其按照总的论文发表数来计算即可
            // 这样可以限制参与排序的数的大小为 [0,n]（其中 n 为总的论文发表数），使得计数排序的时间复杂度降低到 O(n)
            if (citation >= n) {
                counter[n]++;
            } else {
                counter[citation]++;
            }
        }
        int tot = 0;
        // 最后从后向前遍历数组 counter，对于每个 0≤i≤n，在数组 counter 中得到大于或等于当前引用次数 i 的总论文数
        for (int i = n; i >= 0; --i) {
            tot += counter[i];
            // 当找到一个 H 指数时跳出循环，并返回结果
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }

    /**
     * 方法三：二分查找
     * 时间复杂度：O(n*log n)。
     * 空间复杂度：O(1)。
     */
    public int hIndex3(int[] citations) {
        /*
         * 确定二分查找的整数范围：
         * • 最差情况下，所有的论文被引用的次数都为 0；
         * • 最好情况下，所有的论文被引用的次数 >= 总论文篇数。
         * 因此整数区间为 [0, len]，这里 len 是输入数组的长度。
         */
        int len = citations.length;
        int left = 0;
        int right = len;
        while (left < right) {
            // 猜论文篇数
            int mid = (left + right + 1) / 2;
            // 满足高引用的特点是：被引用次数 >= 论文篇数
            // count 的含义是：大于等于 mid 的论文篇数
            int count = 0;
            for (int citation : citations) {
                if (citation >= mid) {
                    count++;
                }
            }
            // 如果大于等于 mid 的论文篇数大于等于 mid ，说明 h 指数至少是 mid，最终答案落在区间 [mid, right] 里，此时设置 left = mid
            if (count >= mid) {
                left = mid;
            }
            // 反面情况，最终答案落在区间 [mid, right] 的反面区间 [left, mid - 1] 里，此时设置 right = mid - 1
            else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().hIndex1(new int[]{3, 0, 6, 1, 5}));
        System.out.println(new Solution().hIndex2(new int[]{3, 0, 6, 1, 5}));
        System.out.println(new Solution().hIndex3(new int[]{3, 0, 6, 1, 5}));
    }
}
