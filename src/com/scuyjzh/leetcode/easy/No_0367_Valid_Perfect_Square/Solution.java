package com.scuyjzh.leetcode.easy.No_0367_Valid_Perfect_Square;

/**
 * 367. 有效的完全平方数
 *
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则
 * 返回 true ，否则返回 false 。
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 */
class Solution {
    /**
     * 方法一：二分查找
     */
    public boolean isPerfectSquare1(int num) {
        /*
         * 假如答案为 ans，那么以 ans 为分割点的数轴上具有二段性：
         *   • 小于 ans 的一段 x 必然不满足 x∗x ≥ num；
         *   • 大于等于 ans 的一段 x 必然满足 x∗x ≥ num。
         * 因此可以通过「二分」来找到分割点 ans。
         */
        long l = 0, r = num;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (mid * mid <= num) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r * r == num;
    }

    /**
     * 方法二：数学
     */
    public boolean isPerfectSquare2(int num) {
        /*
         * 对于一个完全平方数而言，可以写成如下形式：
         *     num = n^2 = 1+3+5+...+(2∗n−1)
         * 因此另外一种做法是对 num 进行不断的奇数试减，如果最终能够减到 0，
         * 说明 num 可展开成如 1+3+5+...+(2∗n−1) 的形式，num 为完全平方数。
         */
        int x = 1;
        while (num > 0) {
            num -= x;
            x += 2;
        }
        return num == 0;
    }

    /**
     * 方法三：牛顿迭代法
     */
    public boolean isPerfectSquare3(int num) {
        /*
         * 如果 num 为完全平方数，那么一定存在正整数 x 满足 x×x=num。于是写出如下方程：
         *     y=f(x)=x^2−num
         * 如果方程能够取得整数解，则说明存在满足 x×x=num 的正整数 x。这个方程可以通过牛顿迭代法求解。
         */
        double x0 = num;
        while (true) {
            double x1 = (x0 + num / x0) / 2;
            // 判断相邻两次迭代的结果的差值是否小于一个极小的非负数，迭代结束
            if (x0 - x1 < 1e-6) {
                break;
            }
            x0 = x1;
        }
        int x = (int) x0;
        return x * x == num;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPerfectSquare1(16));
        System.out.println(new Solution().isPerfectSquare2(14));
        System.out.println(new Solution().isPerfectSquare3(14));
    }
}
