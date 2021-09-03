package com.scuyjzh.leetcode.easy.No_0141_Linked_List_Cycle;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 141. 环形链表
 * <p>
 * 给定一个链表，判断链表中是否有环。如果链表中存在环，则返回 true 。 否则，返回 false 。
 */
class Solution {
    /**
     * 方法：快慢指针
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。
     * 空间复杂度：O(1)。只使用了两个指针的额外空间。
     */
    public boolean hasCycle(ListNode head) {
        /*
         * 本方法需要对「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解。
         * 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。
         * 当「乌龟」和「兔子」从链表上的同一个节点开始移动时，如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；
         * 如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。
         * 等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
         *
         * 可以根据上述思路来解决本题。具体地，定义两个指针，一快一满。
         * 慢指针每次只移动一步，而快指针每次移动两步。初始时，慢指针在位置 head，而快指针在位置 head.next。
         * 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。
         */
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
