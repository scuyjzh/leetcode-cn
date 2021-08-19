package com.scuyjzh.leetcode.medium.No_0134_Gas_Station;

/**
 * 134. 加油站
 * <p>
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 */
class Solution {
    /**
     * 方法：贪心
     * 时间复杂度：O(N)，其中 N 为数组的长度。对数组进行了单次遍历。
     * 空间复杂度：O(1)。
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int rest = 0;
        int run = 0;
        int start = 0;
        for (int i = 0; i < gas.length; ++i) {
            run += gas[i] - cost[i];
            // 两个数组之差的总和必须大于等于0，否则无法完成绕行
            rest += gas[i] - cost[i];
            if (run < 0) {
                // 一个站的收益如果小于0，肯定不能作为起点；而连续的多个站也可以等效地看做一个站，如果其累积收益小于0，就跳过，寻找下一个
                start = i + 1;
                // 还原到初始状态
                run = 0;
            }
        }
        return rest >= 0 ? start : -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
    }
}
