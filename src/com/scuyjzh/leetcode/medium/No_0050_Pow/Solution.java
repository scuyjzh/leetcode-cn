package com.scuyjzh.leetcode.medium.No_0050_Pow;

class Solution {
    public double myPow1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        int t = n / 2;
        if (n < 0) {
            t = -t;
            x = 1 / x;
        }
        double ans = myPow1(x, t);
        return n % 2 == 0 ? ans * ans : ans * ans * x;
    }

    public double myPow2(double x, int n) {
        // prevent overflow when n = -2147483648 (-2^31)
        int m = n < 0 ? -n - 1 : n;
        double p = 1.0, q = x;
        while (m > 0) {
            if ((m & 1) != 0) {
                // (m % 2) != 0
                p *= q;
            }
            q *= q;
            // m /= 2
            m >>= 1;
        }
        return n < 0 ? 1 / (p * x) : p;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow2(2.0, 13));
    }
}
