package com.scuyjzh.leetcode.medium.No_0402_Remove_K_Digits;

import java.util.*;

/**
 * 402. 移掉 K 位数字
 * <p>
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 */
class Solution {
    /**
     * 方法：贪心 + 单调栈
     * 时间复杂度：O(n)，其中 n 为字符串的长度。尽管存在嵌套循环，但内部循环最多运行 k 次。由于 0<k≤n，主循环的时间复杂度被限制在 2n 以内。对于主循环之外的逻辑，它们的时间复杂度是 O(n)，因此总时间复杂度为 O(n)。
     * 空间复杂度：O(n)。栈存储数字需要线性的空间。
     */
    public String removeKdigits(String num, int k) {
        /*
         * 对于两个相同长度的数字序列，最左边不同的数字决定了这两个数字的大小，例如，对于 A=1axxx，B=1bxxx，如果 a>b 则 A>B。
         * 基于此，可以知道，若要使得剩下的数字最小，需要保证靠前的数字尽可能小。
         *
         * 基于上述分析，可以得出「删除一个数字」的贪心策略：
         * 给定一个长度为 n 的数字序列 [D_0D_1D_2D_3…D_{n-1}]，从左往右找到第一个位置 i（i>0）使得 D_i<D_{i-1}，并删去 D_{i-1}；
         * 如果不存在，说明整个数字序列单调不降，删去最后一个数字即可。
         *
         * 基于此，可以每次对整个数字序列执行一次这个策略；
         * 删去一个字符后，剩下的 n−1 长度的数字序列就形成了新的子问题，可以继续使用同样的策略，直至删除 k 次。
         * 然而暴力的实现复杂度最差会达到 O(nk)（考虑整个数字序列是单调不降的），因此需要加速这个过程。
         *
         * 考虑从左往右增量的构造最后的答案。
         * 可以用一个栈维护当前的答案序列，栈中的元素代表截止到当前位置，删除不超过 k 次个数字后，所能得到的最小整数。
         * 根据之前的讨论：在使用 k 个删除次数之前，栈中的序列从栈底到栈顶单调不降。
         * 因此，对于每个数字，如果该数字小于栈顶元素，就不断地弹出栈顶元素，直到：
         *   • 栈为空
         *   • 或者新的栈顶元素不大于当前数字
         *   • 或者已经删除了 k 位数字
         * 上述步骤结束后还需要针对一些情况做额外的处理：
         *   • 如果删除了 m 个数字且 m<k，这种情况下需要从序列尾部删除额外的 k−m 个数字。
         *   • 如果最终的数字序列存在前导零，要删去前导零。
         *   • 如果最终数字序列为空，应该返回 0。
         * 最终，从栈底到栈顶的答案序列即为最小数。
         *
         * 考虑到栈的特点是后进先出，如果通过栈实现，则需要将栈内元素依次弹出然后进行翻转才能得到最小数。
         * 为了避免翻转操作，可以使用双端队列代替栈的实现。
         */
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        // 用一个栈维护当前的答案序列，栈中的序列从栈底到栈顶单调不降
        for (int i = 0; i < length; ++i) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit) {
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        // 如果删除了 m 个数字且 m<k，这种情况下需要从序列尾部删除额外的 k−m 个数字
        for (int i = 0; i < k; ++i) {
            deque.pollLast();
        }
        // 如果最终的数字序列存在前导零，要删去前导零
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()) {
            char digit = deque.pollFirst();
            if (leadingZero && digit == '0') {
                continue;
            }
            leadingZero = false;
            ret.append(digit);
        }
        // 如果最终数字序列为空，应该返回 0
        return ret.length() == 0 ? "0" : ret.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().removeKdigits("12345264", 4));
    }
}