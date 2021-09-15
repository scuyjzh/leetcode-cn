package com.scuyjzh.leetcode.hard.No_0352_Data_Stream_as_Disjoint_Intervals;

import java.util.*;

/**
 * 352. 将数据流变为多个不相交区间
 * <p>
 * 给定一个非负整数的数据流输入 a1，a2，…，an，…，将到目前为止看到的数字总结为不相交的区间列表。
 * 例如，假设数据流中的整数为 1，3，7，2，6，…，每次的总结为：
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * 进阶：
 * 如果有很多合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 */
class SummaryRanges {

    private Set<Integer> set;

    /**
     * Initialize your data structure here.
     */
    public SummaryRanges() {
        // 使用有序集合对数据流输入进行排序与去重
        set = new TreeSet<>();
    }

    public void addNum(int val) {
        set.add(val);
    }

    public int[][] getIntervals() {
        List<int[]> ret = new ArrayList<>();
        Iterator<Integer> iterator = set.iterator();
        // 遍历有序集合中的相邻元素
        int begin = iterator.next(), end = begin;
        while (iterator.hasNext()) {
            int tmp = iterator.next();
            // 如果下一个元素和当前 end 的差值不为 1（说明不连续），就把当前区间放到返回值中，然后再重新开辟新的区间
            if (tmp != end + 1) {
                ret.add(new int[]{begin, end});
                begin = tmp;
                end = begin;
            }
            // 否则的话，就更新当前区间的 end
            else {
                end = tmp;
            }
        }
        // 最后需要把剩余的区间放到返回值中
        ret.add(new int[]{begin, end});
        return ret.toArray(new int[ret.size()][]);
    }

    public static void main(String[] args) {
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
        obj.addNum(3);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
        obj.addNum(7);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
        obj.addNum(2);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
        obj.addNum(6);
        System.out.println(Arrays.deepToString(obj.getIntervals()));
    }
}