package com.scuyjzh.leetcode.medium.No_650_2_Keys_Keyboard;

class Solution {
    public int minSteps1(int n) {
        if (n <= 1) {
            return 0;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return i + minSteps1(n / i);
            }
        }
        return n;
    }

    public int minSteps2(int n) {
        if (n <= 1) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            // sub-problem dp[i] := minSteps(i)
            for (int j = 2; j <= i; j++) {
                // j := use j steps to create j copies
                if (i % j == 0) {
                    dp[i] = j + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public int minSteps3(int n) {
        int ans = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                ans += i;
                n = n / i;
            }
        }
        return n == 1 ? ans : ans + n;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSteps1(6));
    }
}
