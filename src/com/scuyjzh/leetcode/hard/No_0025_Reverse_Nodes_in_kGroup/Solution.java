package com.scuyjzh.leetcode.hard.No_0025_Reverse_Nodes_in_kGroup;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 25. K 个一组翻转链表
 * <p>
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
class Solution {
    /**
     * 方法一：模拟
     * 时间复杂度：O(n)，其中 n 为链表的长度。head 指针会在 O(⌊n/k⌋) 个节点上停留，每次停留需要进行一次 O(k) 的翻转操作。
     * 空间复杂度：O(1)，我们只需要建立常数个变量。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        /*
         * 思路：
         * 把链表节点按照 k 个一组分组，可以使用一个指针 head 依次指向每组的头节点。这个指针每次向前移动 k 步，直至链表结尾。
         * 对于每个分组，我们先判断它的长度是否大于等于 k。若是，我们就翻转这部分链表，否则不需要翻转。
         * 但是对于一个分组内的子链表，除了翻转其本身之外，还需要将子链表的头部与上一个子链表连接，以及子链表的尾部与下一个子链表连接。
         */
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = dummyHead;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return dummyHead.next;
                }
            }
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            // 翻转下一个分组的子链表
            pre = tail;
            head = tail.next;
        }

        return dummyHead.next;
    }

    private ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode cur = head;
        while (pre != tail) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return new ListNode[]{tail, head};
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(ListNode.toString(solution.reverseKGroup(ListNode.initLinkedList("[1,2,3,4,5]"), 1)));
        System.out.println(ListNode.toString(solution.reverseKGroup(ListNode.initLinkedList("[1,2,3,4,5]"), 2)));
        System.out.println(ListNode.toString(solution.reverseKGroup(ListNode.initLinkedList("[1,2,3,4,5]"), 3)));
    }
}
