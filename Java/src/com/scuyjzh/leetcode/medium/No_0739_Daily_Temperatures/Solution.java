package com.scuyjzh.leetcode.medium.No_0739_Daily_Temperatures;

import java.util.*;

/**
 * 739. 每日温度
 *
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才
 * 会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
class Solution {
    /**
     * 方法一：暴力
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        /*
         * 对于温度列表中的每个元素 temperatures[i]，需要找到最小的下标 j，使得 i < j 且
         * temperatures[i] < temperatures[j]。
         *
         * 由于温度范围在 [30, 100] 之内，因此可以维护一个数组 next 记录每个温度第一次出现的下标。数组
         * next 中的元素初始化为无穷大，在遍历温度列表的过程中更新 next 的值。
         *
         * 反向遍历温度列表。对于每个元素 temperatures[i]，在数组 next 中找到从 temperatures[i] + 1 到
         * 100 中每个温度第一次出现的下标，将其中的最小下标记为 warmerIndex，则 warmerIndex 为下一次温
         * 度比当天高的下标。如果 warmerIndex 不为无穷大，则 warmerIndex - i 即为下一次温度比当天高的等
         * 待天数，最后令 next[temperatures[i]] = i。
         *
         * 为什么上述做法可以保证正确呢？因为遍历温度列表的方向是反向，当遍历到元素 temperatures[i] 时，
         * 只有 temperatures[i] 后面的元素被访问过，即对于任意 t，当 next[t] 不为无穷大时，一定存在 j
         * 使得 temperatures[j] == t 且 i < j。又由于遍历到温度列表中的每个元素时都会更新数组 next 中
         * 的对应温度的元素值，因此对于任意 t，当 next[t] 不为无穷大时，令 j = next[t]，则 j 是满足
         * temperatures[j] == t 且 i < j 的最小下标。
         */
        int length = temperatures.length;
        int[] ans = new int[length];
        // 记录每个温度第一次出现的下标（温度范围在 [30, 100] 之内）
        int[] next = new int[101];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; --i) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int t = temperatures[i] + 1; t <= 100; ++t) {
                if (next[t] < warmerIndex) {
                    warmerIndex = next[t];
                }
            }
            if (warmerIndex < Integer.MAX_VALUE) {
                ans[i] = warmerIndex - i;
            }
            next[temperatures[i]] = i;
        }
        return ans;
    }

    /**
     * 方法二：单调栈
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        /*
         * 可以维护一个存储下标的单调栈，从栈底到栈顶的下标对应的温度列表中的温度依次递减。如果一个下标在
         * 单调栈里，则表示尚未找到下一次温度更高的下标。
         *
         * 正向遍历温度列表。对于温度列表中的每个元素 temperatures[i]，如果栈为空，则直接将 i 进栈，如
         * 果栈不为空，则比较栈顶元素 prevIndex 对应的温度 temperatures[prevIndex] 和当前温度
         * temperatures[i]，如果 temperatures[i] > temperatures[prevIndex]，则将 prevIndex 移除，并将
         * prevIndex 对应的等待天数赋为 i - prevIndex，重复上述操作直到栈为空或者栈顶元素对应的温度小于
         * 等于当前温度，然后将 i 进栈。
         *
         * 为什么可以在弹栈的时候更新 ans[prevIndex] 呢？因为在这种情况下，即将进栈的 i 对应的
         * temperatures[i] 一定是 temperatures[prevIndex] 右边第一个比它大的元素，试想如果 prevIndex
         * 和 i 有比它大的元素，假设下标为 j，那么 prevIndex 一定会在下标 j 的那一轮被弹掉。
         *
         * 由于单调栈满足从栈底到栈顶元素对应的温度递减，因此每次有元素进栈时，会将温度更低的元素全部移
         * 除，并更新出栈元素对应的等待天数，这样可以确保等待天数一定是最小的。
         */
        int length = temperatures.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < length; ++i) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().dailyTemperatures1(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(Arrays.toString(new Solution().dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }
}
