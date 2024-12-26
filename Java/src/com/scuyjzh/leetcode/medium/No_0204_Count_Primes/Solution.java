package com.scuyjzh.leetcode.medium.No_0204_Count_Primes;

import java.util.*;

/**
 * 204. 计数质数
 *
 * 统计所有小于非负整数 n 的质数的数量。
 */
class Solution {
    /**
     * 方法一：枚举
     */
    public int countPrimes1(int n) {
        /*
         * 很直观的思路是枚举每个数判断其是不是质数。
         *
         * 考虑质数的定义：在大于 1 的自然数中，除了 1 和它本身以外不再有其他因数的自然数。因此对于每个数 x
         * ，可以从小到大枚举 [2,x−1] 中的每个数 y，判断 y 是否为 x 的因数。但这样判断一个数是否为质数的
         * 时间复杂度最差情况下会到 O(n)，无法通过所有测试数据。
         *
         * 考虑到如果 y 是 x 的因数，那么 x/y 也必然是 x 的因数，因此只要校验 y 或者 x/y 即可。而如果每次
         * 选择校验两者中的较小数，则不难发现较小数一定落在 [2,sqrt{x}] 的区间中，因此只需要枚举 [2,sqrt{x}] 中的
         * 所有数即可，这样单次检查的时间复杂度从 O(n) 降低至了 O(sqrt{n})。
         */
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            ans += isPrime(i) ? 1 : 0;
        }
        return ans;
    }

    private boolean isPrime(int x) {
        for (int i = 2; i * i <= x; ++i) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法二：埃氏筛
     */
    public int countPrimes2(int n) {
        // 设 isPrime[i] 表示数 i 是不是质数，如果是质数则为 1，否则为 0
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        int ans = 0;
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                ans += 1;
                // 如果这个数为质数，则将其所有的倍数都标记为合数
                if ((long) i * i < n) {
                    // 对于一个质数 x，应该直接从 x⋅x 开始标记，因为 2x,3x,… 这些数一定在 x 之前就被其他数的倍数标记过了
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 0;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().countPrimes1(10));
        System.out.println(new Solution().countPrimes2(0));
        System.out.println(new Solution().countPrimes2(1));
    }
}
