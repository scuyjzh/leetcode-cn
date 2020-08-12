package com.scuyjzh.leetcode.medium.No_651_4_Keys_Keyboard;

class Solution {
    public int maxA1(int N) {
        int ans = N;
        for (int i = 1; i < N - 2; ++i) {
            ans = Math.max(ans, maxA1(i) * (N - i - 1));
        }
        return ans;
    }

    public int maxA2(int N) {
        int[] dp = new int[N + 1];
        for (int i = 0; i <= N; ++i) {
            dp[i] = i;
            for (int j = 1; j <= i - 3; ++j) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }
        return dp[N];
    }

    public int maxA3(int N) {
        int[] dp = new int[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (i <= 5) {
                dp[i] = dp[i - 1] + 1;
            } else {
                for (int j = 3; j <= i - 3; j++) {
                    dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
                }
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxA1(11));
        System.out.println(solution.maxA2(11));
        System.out.println(solution.maxA3(11));
    }
}