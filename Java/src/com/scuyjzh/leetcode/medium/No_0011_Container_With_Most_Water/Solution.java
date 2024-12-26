package com.scuyjzh.leetcode.medium.No_0011_Container_With_Most_Water;

/**
 * 11. 盛最多水的容器
 *
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public int maxArea(int[] height) {
        /*
         * 思路：
         * 设两指针 i、j，指向的水槽板高度分别为 h[i]、h[j]，此状态下水槽面积为 S(i,j)。
         * 由于可容纳水的高度由两板中的 短板 决定，因此可得如下面积公式 ：
         *                 S(i,j) = min(h[i], h[j]) × (j − i)
         *
         * 在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 -1 变短：
         *   • 若向内 移动短板，水槽的短板 min(h[i], h[j]) 可能变大，因此下个水槽的面积 可能增大 。
         *   • 若向内 移动长板，水槽的短板 min(h[i], h[j]) 不变或变小，因此下个水槽的面积 一定变小 。
         *
         * 因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相
         * 遇时跳出；即可获得最大面积。
         */
        int res = 0;
        // 1.初始化：双指针 left , right 分列水槽左右两端；
        int left = 0, right = height.length - 1;
        // 2.循环收窄：直至双指针相遇时跳出；
        while (left < right) {
            // 2.1.更新面积最大值 res ；
            int area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, area);
            // 2.2.选定两板高度中的短板，向中间收窄一格；
            if (height[left] <= height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        //3.返回值：返回面积最大值 res 即可；
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
