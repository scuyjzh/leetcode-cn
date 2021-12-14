package com.scuyjzh.leetcode.easy.No_0141_Linked_List_Cycle;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 141. 环形链表
 *
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表
 * 中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示
 * 链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该
 * 链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的
 * 实际情况。
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 */
class Solution {
    /**
     * 方法：快慢指针
     */
    public boolean hasCycle(ListNode head) {
        /*
         * 本方法需要对「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解。
         *
         * 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。当「乌龟」和「兔子」从链表
         * 上的同一个节点开始移动时，如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；如果该链表
         * 中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。等到「乌龟」进入环时，由于「兔
         * 子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
         *
         * 可以根据上述思路来解决本题。具体地，定义两个指针，一快一满。慢指针每次只移动一步，而快
         * 指针每次移动两步。初始时，慢指针在位置 head，而快指针在位置 head.next。这样一来，如果在移动
         * 的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为
         * 环形链表。
         */
        if (head == null || head.next == null) {
            return false;
        }

        // 定义两个指针，一快一满
        // 初始时，慢指针在位置 head，而快指针在位置 head.next
        ListNode slow = head;
        ListNode fast = head.next;
        // 如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表
        while (slow != fast) {
            // 若快指针将到达链表尾部，说明该链表不为环形链表
            if (fast == null || fast.next == null) {
                return false;
            }
            // 慢指针每次只移动一步
            slow = slow.next;
            // 快指针每次移动两步
            fast = fast.next.next;
        }

        return true;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(new Solution().hasCycle(head));
    }
}
