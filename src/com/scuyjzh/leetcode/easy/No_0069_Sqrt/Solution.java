package com.scuyjzh.leetcode.easy.No_0069_Sqrt;

class Solution {
    /**
     * Approach #1 (Binary search solution)
     */
    public int mySqrt1(int x) {
        if (x <= 1) {
            return x;
        }
        int left = 1, right = (x + 1) / 2;
        while (left <= right) {
            // prevent '+' integer overflow
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                // prevent '*' integer overflow
                return mid;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * Approach #2 (Newton's method)
     */
    public int mySqrt2(int x) {
        long r = x;
        while (r * r > x) {
            r = (r + x / r) / 2;
        }
        return (int) r;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.mySqrt1(12));
        System.out.println(solution.mySqrt2(12));
    }
}
