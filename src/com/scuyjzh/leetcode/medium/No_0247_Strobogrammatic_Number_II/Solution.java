package com.scuyjzh.leetcode.medium.No_0247_Strobogrammatic_Number_II;

import java.util.*;

/**
 * 247. 中心对称数 II
 *
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者
 * 上下颠倒地看）。
 * 找到所有长度为 n 的中心对称数。
 */
class Solution {
    public List<String> findStrobogrammatic(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        if (n == 1) {
            return Arrays.asList("0", "1", "8");
        }
        if (n == 2) {
            return Arrays.asList("11", "69", "88", "96");
        }
        // arr[n] 表示所有长度为 n 的中心对称数
        List<String>[] arr = new List[n + 1];
        arr[1] = Arrays.asList("0", "1", "8");
        arr[2] = Arrays.asList("00", "11", "69", "88", "96");
        for (int i = 3; i <= n; ++i) {
            // 随着 i 不断增长，只需要在所有长度为 i-2 的中心对称数两边添加 "11"、"69"、"88"、"96" 即可
            List<String> tmp = new ArrayList<>();
            for (String s : arr[i - 2]) {
                if (i != n) {
                    tmp.add("0" + s + "0");
                }
                tmp.add("1" + s + "1");
                tmp.add("6" + s + "9");
                tmp.add("8" + s + "8");
                tmp.add("9" + s + "6");
            }
            arr[i] = tmp;
        }
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findStrobogrammatic(4));
    }
}
