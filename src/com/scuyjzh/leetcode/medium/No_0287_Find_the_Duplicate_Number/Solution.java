package com.scuyjzh.leetcode.medium.No_0287_Find_the_Duplicate_Number;

/**
 * 287. 寻找重复数
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 */
class Solution {
    /**
     * 方法一：二分查找
     * 时间复杂度：O(NlogN)，其中 N 为 nums 数组的长度。
     * 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public int findDuplicate1(int[] nums) {
        /*
         * 二分查找的思路是先猜一个数（有效范围 [left..right] 里位于中间的数 mid），然后统计原始数组中 小于等于 mid 的元素的个数 cnt：
         *   • 如果 cnt 严格大于 mid。根据抽屉原理，重复元素就在区间 [left..mid] 里；
         *   • 否则，重复元素就在区间 [mid + 1..right] 里。
         * 与绝大多数使用二分查找问题不同的是，这道题正着思考是容易的，即：思考哪边区间存在重复数是容易的，因为有抽屉原理做保证。
         */
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt += 1;
                }
            }

            // 根据抽屉原理，小于等于 4 的个数如果严格大于 4 个，此时重复元素一定出现在 [1..4] 区间里
            if (cnt > mid) {
                // 重复元素位于区间 [left..mid]
                right = mid;
            } else {
                // if 分析正确了以后，else 搜索的区间就是 if 的反面区间 [mid + 1..right]
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 方法二：快慢指针
     * 时间复杂度：O(n)。「Floyd 判圈算法」时间复杂度为线性的时间复杂度。
     * 空间复杂度：O(1)。只需要常数空间存放若干变量。
     */
    public int findDuplicate2(int[] nums) {
        /*
         * 本方法需要对 「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解，它是一个检测链表是否有环的算法。
         *
         * 思路和算法：
         * 对 nums 数组建图，每个位置 i 连一条 i→nums[i] 的边。
         * 由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，
         * 因此整张图一定存在环，且要找到的 target 就是这个环的入口，那么整个问题就等价于 142. 环形链表 II。
         *
         * 先设置慢指针 slow 和快指针 fast ，慢指针每次走一步，快指针每次走两步，
         * 根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时再将 slow 放置起点 0，两个指针每次同时移动一步，相遇的点就是答案。
         *
         * 这里简单解释为什么后面将 slow 放置起点后移动相遇的点就一定是答案了。
         * 假设环长为 L，从起点到环的入口的步数是 a，从环的入口继续走 b 步到达相遇位置，从相遇位置继续走 c 步回到环的入口，
         * 则有 b+c=L，其中 L、a、b、c 都是正整数。根据上述定义，慢指针走了 a+b 步，快指针走了 2(a+b) 步。
         *
         * 从另一个角度考虑，在相遇位置，快指针比慢指针多走了若干圈，因此快指针走的步数还可以表示成 a+b+kL，其中 k 表示快指针在环上走的圈数。
         * 联立等式，可以得到
         *         2(a+b) = a+b+kL
         * 解得 a = kL − b，整理可得
         *         a = (k−1)L+(L−b) = (k−1)L+c
         *
         * 从上述等式可知，如果慢指针从起点出发，快指针从相遇位置出发，每次两个指针都移动一步，
         * 则慢指针走了 a 步之后到达环的入口，快指针在环里走了 k−1 圈之后又走了 c 步，
         * 由于从相遇位置继续走 c 步即可回到环的入口，因此快指针也到达环的入口。
         * 两个指针在环的入口相遇，相遇点就是答案。
         */
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findDuplicate1(new int[]{1, 3, 4, 2, 2}));
        System.out.println(new Solution().findDuplicate2(new int[]{1, 3, 4, 2, 2}));
    }
}
