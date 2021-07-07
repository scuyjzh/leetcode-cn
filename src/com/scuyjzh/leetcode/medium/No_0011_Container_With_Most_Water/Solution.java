package com.scuyjzh.leetcode.medium.No_0011_Container_With_Most_Water;

/**
 * 11. 盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 */
class Solution {
    /**
     * 方法一：双指针
     * 时间复杂度：O(N)，双指针总计最多遍历整个数组一次。
     * 空间复杂度：O(1)，只需要额外的常数级别的空间。
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, res = 0;
        while (l < r) {
            res = height[l] < height[r] ?
                    Math.max(res, (r - l) * height[l++]) :
                    Math.max(res, (r - l) * height[r--]);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxArea(new int[]{2, 4, 6, 5, 8}));
    }
}
