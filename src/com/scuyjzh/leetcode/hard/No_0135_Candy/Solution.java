package com.scuyjzh.leetcode.hard.No_0135_Candy;

class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }

        int[] candies = new int[ratings.length];
        candies[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            candies[i] = ratings[i] > ratings[i - 1] ? candies[i - 1] + 1 : 1;
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            candies[i] = ratings[i] > ratings[i + 1] ? Math.max(candies[i + 1] + 1, candies[i]) : candies[i];
        }
        int ans = 0;
        for (int i = 0; i < candies.length; i++) {
            ans += candies[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.candy(new int[]{5, 6, 2, 2, 4, 8, 9, 5, 4, 0, 5, 1}));
    }
}
