package com.scuyjzh.leetcode.medium.No_0319_Bulb_Switcher;

/**
 * 319. 灯泡开关
 *
 * 初始时有n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来
 * 的第二轮，你将会每两个灯泡关闭一个。
 * 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打
 * 开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你
 * 只需要切换最后一个灯泡的开关。
 * 找出并返回 n轮后有多少个亮着的灯泡。
 */
class Solution {
    /**
     * 方法：数学
     */
    public int bulbSwitch(int n) {
        /*
         * 思路与算法：
         * 如果将所有的灯泡从左到右依次编号为 1,2,⋯,n，那么可以发现：
         *     在第 i 轮时，会将所有编号为 i 的倍数的灯泡进行切换。
         *
         * 因此，对于第 k 个灯泡，它被切换的次数恰好就是 k 的约数个数。如果 k 有偶数个约数，那么最终第 k 个灯
         * 泡的状态为暗；如果 k 有奇数个约数，那么最终第 k 个灯泡的状态为亮。
         *
         * 对于 k 而言，如果它有约数 x，那么一定有约数 k/x。因此只要当 x^2 不等于 k 时，约数都是「成对」出现的。这就
         * 说明，只有当 k 是「完全平方数」时，它才会有奇数个约数，否则一定有偶数个约数。
         *
         * 因此只需要找出 1,2,⋯,n 中的完全平方数的个数即可，答案即为 ⌊sqrt{n}⌋，其中 ⌊⋅⌋ 表示向下取整。
         */
        return (int) Math.sqrt(n + 0.5);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().bulbSwitch(3));
        System.out.println(new Solution().bulbSwitch(0));
        System.out.println(new Solution().bulbSwitch(1));
    }
}
