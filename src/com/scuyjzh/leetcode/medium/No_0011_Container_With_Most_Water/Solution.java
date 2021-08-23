package com.scuyjzh.leetcode.medium.No_0011_Container_With_Most_Water;

/**
 * 11. 盛最多水的容器
 * <p>
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 */
class Solution {
    /**
     * 方法：双指针
     * 时间复杂度：O(N)，双指针总计最多遍历整个数组一次。
     * 空间复杂度：O(1)，只需要额外的常数级别的空间。
     */
    public int maxArea(int[] height) {
        /*
         * 思路：
         * 设两指针 i、j，指向的水槽板高度分别为 h[i]、h[j]，此状态下水槽面积为 S(i,j)。
         * 由于可容纳水的高度由两板中的 短板 决定，因此可得如下面积公式 ：
         *     S(i,j) = min(h[i], h[j]) × (j − i)
         * 在每个状态下，无论长板或短板向中间收窄一格，都会导致水槽 底边宽度 -1 变短：
         *   • 若向内 移动短板，水槽的短板 min(h[i], h[j]) 可能变大，因此下个水槽的面积 可能增大 。
         *   • 若向内 移动长板，水槽的短板 min(h[i], h[j]) 不变或变小，因此下个水槽的面积 一定变小 。
         * 因此，初始化双指针分列水槽左右两端，循环每轮将短板向内移动一格，并更新面积最大值，直到两指针相遇时跳出；即可获得最大面积。
         *
         * 正确性证明：
         * 若暴力枚举，水槽两板围成面积 S(i,j) 的状态总数为 C(n,2)。
         * 假设状态 S(i,j) 下 h[i]<h[j]，在向内移动短板至 S(i+1,j)，则相当于消去了 S(i,j−1), S(i,j−2), ..., S(i,i+1) 状态集合。
         * 而所有消去状态的面积一定都小于当前面积（即小于 S(i,j)），因为这些状态：
         *   • 短板高度：相比 S(i,j) 相同或更短（即小于等于 h[i]）；
         *   • 底边宽度：S(i,j) 更短；
         * 因此，每轮向内移动短板，所有消去的状态都不会导致面积最大值丢失，证毕。
         */
        int res = 0;
        int l = 0, r = height.length - 1;
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
