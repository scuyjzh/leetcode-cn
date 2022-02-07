package com.scuyjzh.leetcode.medium.No_0658_Find_K_Closest_Elements;

import java.util.*;

/**
 * 658. 找到 K 个最接近的元素
 *
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近
 * x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *   • |a - x| < |b - x| 或者
 *   • |a - x| == |b - x| 且 a < b
 */
class Solution {
    /**
     * 方法：滑动窗口
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int n = arr.length - k;
        int left;
        for (left = 0; left < n; ++left) {
            if (arr[left] >= x) {
                break;
            }
            if (arr[left + k] < x) {
                continue;
            }
            if (Math.abs(arr[left] - x) <= Math.abs(arr[left + k] - x)) {
                break;
            }
        }

        for (int i = 0; i < k; ++i) {
            res.add(arr[left + i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, 3));
        System.out.println(new Solution().findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1));
    }
}
