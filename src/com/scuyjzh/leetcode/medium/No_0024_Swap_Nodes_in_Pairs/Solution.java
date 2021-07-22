package com.scuyjzh.leetcode.medium.No_0024_Swap_Nodes_in_Pairs;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 24. 两两交换链表中的节点
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
class Solution {
    /**
     * 方法一：递归
     * 时间复杂度：O(n)，其中 n 是链表的节点数量。需要对每个节点进行更新指针的操作。
     * 空间复杂度：O(n)，其中 n 是链表的节点数量。空间复杂度主要取决于递归调用的栈空间。
     */
    public ListNode swapPairs1(ListNode head) {
        return helper(head);
    }

    private ListNode helper(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode one = head;
        ListNode two = head.next;
        ListNode three = two.next;

        two.next = one;
        one.next = helper(three);
        return two;
    }

    /**
     * 方法二：迭代
     * 时间复杂度：O(n)，其中 n 是链表的节点数量。需要对每个节点进行更新指针的操作。
     * 空间复杂度：O(1)。
     */
    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(ListNode.toString(solution.swapPairs1(ListNode.initLinkedList("[1,2,3,4]"))));
        System.out.println(ListNode.toString(solution.swapPairs2(ListNode.initLinkedList("[1,2,3,4]"))));
    }
}
