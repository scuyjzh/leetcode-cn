package com.scuyjzh.leetcode.medium.No_0365_Water_and_Jug_Problem;

import java.util.*;

/**
 * 365. 水壶问题
 *
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使
 * 用这两个水壶，从而可以得到恰好 z升 的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * 你允许：
 *     • 装满任意一个水壶
 *     • 清空任意一个水壶
 *     • 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     *
     * • 时间复杂度：O(xy)，状态数最多有 (x+1)(y+1) 种，对每一种状态进行深度优先搜索的时间复杂度
     *   为 O(1)，因此总时间复杂度为 O(xy)。
     * • 空间复杂度：O(xy)，由于状态数最多有 (x+1)(y+1) 种，哈希集合中最多会有 (x+1)(y+1) 项，因
     *   此空间复杂度为 O(xy)。
     */
    public boolean canMeasureWater1(int x, int y, int z) {
        /*
         * 思路及算法：
         * 首先对题目进行建模。观察题目可知，在任意一个时刻，此问题的状态可以由两个数字决定：X 壶中的水
         * 量，以及 Y 壶中的水量。
         *
         * 在任意一个时刻，我们可以且仅可以采取以下几种操作：
         *   • 把 X 壶的水灌进 Y 壶，直至灌满或倒空；
         *   • 把 Y 壶的水灌进 X 壶，直至灌满或倒空；
         *   • 把 X 壶灌满；
         *   • 把 Y 壶灌满；
         *   • 把 X 壶倒空；
         *   • 把 Y 壶倒空；
         *
         * 因此，本题可以使用深度优先搜索来解决。搜索中的每一步以 remain_x, remain_y 作为状态，即表示 X
         * 壶和 Y 壶中的水量。在每一步搜索时，会依次尝试所有的操作，递归地搜索下去。这可能会导致陷
         * 入无止境的递归，因此还需要使用一个哈希结合（HashSet）存储所有已经搜索过的 remain_x,
         * remain_y 状态，保证每个状态至多只被搜索一次。
         *
         * 在实际的代码编写中，由于深度优先搜索导致的递归远远超过了 Python 的默认递归层数（可以使用 sys
         * 库更改递归层数，但不推荐这么做），因此下面的代码使用栈来模拟递归，避免了真正使用递归而导致的问
         * 题。
         */
        if (z < 0 || z > x + y) {
            return false;
        }
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{0, 0});
        Set<Long> seen = new HashSet<>();
        while (!stack.isEmpty()) {
            if (seen.contains(hash(stack.peek()))) {
                stack.pop();
                continue;
            }
            seen.add(hash(stack.peek()));

            int[] state = stack.pop();
            int remain_x = state[0], remain_y = state[1];
            if (remain_x == z || remain_y == z || remain_x + remain_y == z) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new int[]{x, remain_y});
            // 把 Y 壶灌满。
            stack.push(new int[]{remain_x, y});
            // 把 X 壶倒空。
            stack.push(new int[]{0, remain_y});
            // 把 Y 壶倒空。
            stack.push(new int[]{remain_x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new int[]{Math.max(0, remain_x - (y - remain_y)), Math.min(remain_x + remain_y, y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new int[]{Math.min(remain_x + remain_y, x), Math.max(0, remain_y - (x - remain_x))});
        }
        return false;
    }

    /**
     * 方法二：广度优先搜索
     */
    public boolean canMeasureWater2(int x, int y, int z) {
        if (z < 0 || z > x + y) {
            return false;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        Set<Long> seen = new HashSet<>();
        while (!queue.isEmpty()) {
            if (seen.contains(hash(queue.peek()))) {
                queue.poll();
                continue;
            }
            seen.add(hash(queue.peek()));

            int[] state = queue.poll();
            int remain_x = state[0], remain_y = state[1];
            if (remain_x == z || remain_y == z || remain_x + remain_y == z) {
                return true;
            }

            // 把 X 壶灌满。
            queue.offer(new int[]{x, remain_y});
            // 把 Y 壶灌满。
            queue.offer(new int[]{remain_x, y});
            // 把 X 壶倒空。
            queue.offer(new int[]{0, remain_y});
            // 把 Y 壶倒空。
            queue.offer(new int[]{remain_x, 0});
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            queue.offer(new int[]{Math.max(0, remain_x - (y - remain_y)), Math.min(remain_x + remain_y, y)});
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            queue.offer(new int[]{Math.min(remain_x + remain_y, x), Math.max(0, remain_y - (x - remain_x))});
        }
        return false;
    }

    private long hash(int[] state) {
        return (long) state[0] * 1000001 + state[1];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().canMeasureWater1(3, 5, 4));
        System.out.println(new Solution().canMeasureWater2(2, 6, 5));
    }
}
