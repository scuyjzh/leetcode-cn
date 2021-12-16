package com.scuyjzh.leetcode.hard.No_0025_Reverse_Nodes_in_kGroup;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 25. K 个一组翻转链表
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 *   • 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 *   • 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交
 *     换。
 */
class Solution {
    /**
     * 方法：模拟
     *
     * • 时间复杂度：O(n)，其中 n 为链表的长度。head 指针会在 O(⌊n/k⌋) 个节点上停留，每次停留需要进
     *   行一次 O(k) 的翻转操作。
     * • 空间复杂度：O(1)，只需要建立常数个变量。
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        /*
         * 本题的目标非常清晰易懂，不涉及复杂的算法，但是实现过程中需要考虑的细节比较多，容易写出冗长的代
         * 码。主要考查面试者设计的能力。
         *
         * 需要把链表节点按照 k 个一组分组，可以使用一个指针 head 依次指向每组的头节点。这个指针
         * 每次向前移动 k 步，直至链表结尾。对于每个分组，先判断它的长度是否大于等于 k。若是，
         * 就翻转这部分链表，否则不需要翻转。
         *
         * 接下来的问题就是如何翻转一个分组内的子链表。翻转一个链表并不难，过程可以参考「206. 反转链表」。
         * 但是对于一个子链表，除了翻转其本身之外，还需要将子链表的头部与上一个子链表连接，以及子链表的尾
         * 部与下一个子链表连接。
         *
         * 因此，在翻转子链表的时候，不仅需要子链表头节点 head，还需要有 head 的上一个节点 prev，以
         * 便翻转完后把子链表再接回 prev。
         */
        ListNode dummyHead = new ListNode(-1, head);
        ListNode prev = dummyHead;

        while (head != null) {
            ListNode tail = prev;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    // 最后剩余的节点保持原有顺序
                    return dummyHead.next;
                }
            }
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            prev.next = head;
            // 翻转下一个分组的子链表
            prev = tail;
            head = tail.next;
        }

        return dummyHead.next;
    }

    /**
     * 参考「206. 反转链表」中的迭代法
     */
    private ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode pre = tail.next;
        ListNode cur = head;
        while (pre != tail) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return new ListNode[]{tail, head};
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().reverseKGroup(ListNode.initLinkedList("[1,2,3,4,5]"), 2)));
        System.out.println(ListNode.toString(new Solution().reverseKGroup(ListNode.initLinkedList("[1,2,3,4,5]"), 3)));
        System.out.println(ListNode.toString(new Solution().reverseKGroup(ListNode.initLinkedList("[1,2,3,4,5]"), 1)));
        System.out.println(ListNode.toString(new Solution().reverseKGroup(ListNode.initLinkedList("[1]"), 1)));
    }
}
