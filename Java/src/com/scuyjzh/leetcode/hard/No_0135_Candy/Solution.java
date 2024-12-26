package com.scuyjzh.leetcode.hard.No_0135_Candy;

/**
 * 135. 分发糖果
 * <p>
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * • 每个孩子至少分配到 1 个糖果。
 * • 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 */
class Solution {
    /**
     * 方法一：两次遍历
     * 时间复杂度：O(n)，其中 n 是孩子的数量。需要遍历两次数组以分别计算满足左规则或右规则的最少糖果数量。
     * 空间复杂度：O(n)，其中 n 是孩子的数量。需要保存所有的左规则对应的糖果数量。
     */
    public int candy1(int[] ratings) {
        /*
         * 思路及解法：
         * 可以将「相邻的孩子中，评分高的孩子必须获得更多的糖果」这句话拆分为两个规则，分别处理。
         *   • 左规则：当 ratings[i−1] < ratings[i] 时，i 号学生的糖果数量将比 i−1 号孩子的糖果数量多。
         *   • 右规则：当 ratings[i] > ratings[i+1] 时，i 号学生的糖果数量将比 i+1 号孩子的糖果数量多。
         *
         * 遍历该数组两次，处理出每一个学生分别满足左规则或右规则时，最少需要被分得的糖果数量。
         * 每个人最终分得的糖果数量即为这两个数量的最大值。
         *
         * 具体地，以左规则为例：
         * 从左到右遍历该数组，假设当前遍历到位置 i，如果有 ratings[i−1] < ratings[i]，
         * 那么 i 号学生的糖果数量将比 i−1 号孩子的糖果数量多，令 left[i] = left[i−1]+1 即可，否则令 left[i] = 1。
         *
         * 在实际代码中，先计算出左规则 left 数组，在计算右规则的时候只需要用单个变量记录当前位置的右规则，同时计算答案即可。
         */
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; --i) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }

    /**
     * 方法二：常数空间遍历
     * 时间复杂度：O(n)，其中 n 是孩子的数量。需要遍历两次数组以分别计算满足左规则或右规则的最少糖果数量。
     * 空间复杂度：O(1)。只需要常数的空间保存若干变量。
     */
    public int candy2(int[] ratings) {
        /*
         * 思路及解法：
         * 注意到糖果总是尽量少给，且从 1 开始累计，每次要么比相邻的同学多给一个，要么重新置为 1。
         *
         * 从左到右枚举每一个同学，记前一个同学分得的糖果数量为 pre：
         *   • 如果当前同学比上一个同学评分高，说明就在最近的递增序列中，直接分配给该同学 pre+1 个糖果即可。
         *   • 否则就在一个递减序列中，直接分配给当前同学一个糖果，并把该同学所在的递减序列中所有的同学都再多分配一个糖果，以保证糖果数量还是满足条件。
         *     - 无需显式地额外分配糖果，只需要记录当前的递减序列长度，即可知道需要额外分配的糖果数量。
         *     - 同时注意当当前的递减序列长度和上一个递增序列等长时，需要把最近的递增序列的最后一个同学也并进递减序列中。
         * 这样，只要记录当前递减序列的长度 dec，最近的递增序列的长度 inc 和前一个同学分得的糖果数量 pre 即可。
         */
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; ++i) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().candy1(new int[]{1, 0, 2}));
        System.out.println(new Solution().candy2(new int[]{1, 3, 5, 3, 2, 1}));
    }
}
