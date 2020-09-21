package com.scuyjzh.leetcode.hard.No_0042_Trapping_Rain_Water;

class Solution {
    public int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int h = 0;
        int[] containers = new int[height.length];
        // 先从左往右扫描，维护每一个bar左边的最大高度
        for (int i = 0; i < height.length; i++) {
            containers[i] = h;
            h = Math.max(h, height[i]);
        }
        h = 0;
        // 再从右往左扫描，维护每一个bar右边的最大高度，并记录当前bar两边较小的最大高度
        for (int i = height.length - 1; i >= 0; i--) {
            containers[i] = Math.min(h, containers[i]);
            h = Math.max(h, height[i]);
        }
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            if (containers[i] > height[i]) {
                ans += containers[i] - height[i];
            }
        }
        return ans;
    }

    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int left = height[l], right = height[r];
            if (height[l] <= height[r]) {
                while (l < r && height[++l] <= left) {
                    ans += left - height[l];
                }
            } else {
                while (l < r && height[--r] <= right) {
                    ans += right - height[r];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.trap1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
