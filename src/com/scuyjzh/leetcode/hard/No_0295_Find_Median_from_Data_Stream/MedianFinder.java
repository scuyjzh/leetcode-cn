package com.scuyjzh.leetcode.hard.No_0295_Find_Median_from_Data_Stream;

import java.util.*;

/**
 * 295. 数据流的中位数
 * <p>
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * 例如，
 * [2,3,4] 的中位数是 3
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * 设计一个支持以下两种操作的数据结构：
 * • void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * • double findMedian() - 返回目前所有元素的中位数。
 */
class MedianFinder {
    /*
     * 思路和算法：
     * 用两个优先队列 queMax 和 queMin 分别记录大于中位数的数和小于等于中位数的数。
     * 当累计添加的数的数量为奇数时，queMin 中的数的数量比 queMax 多一个，此时中位数为 queMin 的队头。
     * 当累计添加的数的数量为偶数时，两个优先队列中的数的数量相同，此时中位数为它们的队头的平均值。
     *
     * 当尝试添加一个数 num 到数据结构中，需要分情况讨论：
     *   1.num ≤ max{queMin}
     *     此时 num 小于等于中位数，需要将该数添加到 queMin 中。新的中位数将小于等于原来的中位数，因此可能需要将 queMin 中最大的数移动到 queMax 中。
     *   2.num>max{queMin}
     *     此时 num 大于中位数，需要将该数添加到 queMax 中。新的中位数将大于等于原来的中位数，因此可能需要将 queMax 中最小的数移动到 queMin 中。
     *
     * 特别地，当累计添加的数的数量为 0 时，将 num 添加到 queMin 中。
     */

    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    public MedianFinder() {
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
    }

    public void addNum(int num) {
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder obj = new MedianFinder();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}