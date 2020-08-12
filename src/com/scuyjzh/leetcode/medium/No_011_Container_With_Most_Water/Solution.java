package com.scuyjzh.leetcode.medium.No_011_Container_With_Most_Water;

class Solution {
    /**
     * 双指针解法缩减搜索空间
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{2, 4, 6, 5, 8}));
    }
}
