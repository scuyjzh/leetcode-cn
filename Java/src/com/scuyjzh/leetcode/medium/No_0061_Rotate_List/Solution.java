package com.scuyjzh.leetcode.medium.No_0061_Rotate_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 61. 旋转链表
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个
 * 位置。
 */
class Solution {
    /**
     * 方法：闭合为环
     *
     * • 时间复杂度：O(n)，最坏情况下，需要遍历该链表两次。
     * • 空间复杂度：O(1)，只需要常数的空间存储若干变量。
     */
    public ListNode rotateRight(ListNode head, int k) {
        /*
         * 记给定链表的长度为 n，注意到当向右移动的次数 k≥n 时，仅需要向右移动 k mod n 次即可。因为每
         * n 次移动都会让链表变为原状。这样可以知道，新链表的最后一个节点为原链表的第 (n−1)−(k mod n)
         * 个节点（从 0 开始计数）。
         *
         * 这样，可以先将给定的链表连接成环，然后将指定位置断开。
         *
         * 具体代码中，首先计算出链表的长度 n，并找到该链表的末尾节点，将其与头节点相连。这样就得到了
         * 闭合为环的链表。然后找到新链表的最后一个节点（即原链表的第 (n−1)−(k mod n) 个节点），将当
         * 前闭合为环的链表断开，即可得到所需要的结果。
         *
         * 特别地，当链表长度不大于 1，或者 k 为 n 的倍数时，新链表将与原链表相同，无需进行任何处理。
         */
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        ListNode tail = head;
        // 计算出链表的长度 n
        int n = 1;
        // 找到该链表的末尾节点
        while (tail.next != null) {
            tail = tail.next;
            ++n;
        }
        int add = n - k % n;
        // 当链表长度为 n 的倍数时，新链表将与原链表相同，无需进行任何处理
        if (add == n) {
            return head;
        }
        // 将表的末尾节点与头节点相连
        tail.next = head;
        // 找到新链表的最后一个节点
        while (add-- > 0) {
            tail = tail.next;
        }
        ListNode newHead = tail.next;
        // 将当前闭合为环的链表断开
        tail.next = null;
        // 返回新链表的头节点
        return newHead;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().rotateRight(ListNode.initLinkedList("[1,2,3,4,5]"), 2)));
        System.out.println(ListNode.toString(new Solution().rotateRight(ListNode.initLinkedList("[0,1,2]"), 4)));
    }
}
