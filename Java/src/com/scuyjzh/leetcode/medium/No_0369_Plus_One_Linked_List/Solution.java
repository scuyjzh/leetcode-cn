package com.scuyjzh.leetcode.medium.No_0369_Plus_One_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 369. 给单链表加一
 *
 * 用一个 非空 单链表来表示一个非负整数，然后将这个整数加一。
 * 你可以假设这个整数除了 0 本身，没有任何前导的 0。
 * 这个整数的各个数位按照 高位在链表头部、低位在链表尾部 的顺序排列。
 * 示例:
 *   输入: [1,2,3]
 *   输出: [1,2,4]
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        /*
         * 树和链表问题中经常会用到哨兵节点，它们的主要目的是将边缘数据的处理常规化。
         *
         * 算法：
         *   • 初始化哨兵节点 ListNode(0)，同时将它设为新的头节点：sentinel.next = head。
         *   • 找到最靠右的数值不为 9 的节点。
         *   • 将该节点的数值加 1。
         *   • 将该节点之后所有节点数值改为 0。
         *   • 如果哨兵节点的数值为 1，直接返回哨兵节点，否则返回原始头节点 sentinel.next。
         */
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode notNine = sentinel;

        // 找到最靠右的数值不为 9 的节点
        while (head != null) {
            if (head.val != 9) {
                notNine = head;
            }
            head = head.next;
        }

        // 将该节点的数值加 1
        notNine.val++;

        // 将该节点之后所有节点数值改为 0
        notNine = notNine.next;
        while (notNine != null) {
            notNine.val = 0;
            notNine = notNine.next;
        }

        return sentinel.val != 0 ? sentinel : sentinel.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().plusOne(ListNode.initLinkedList("[1,2,3]"))));
        System.out.println(ListNode.toString(new Solution().plusOne(ListNode.initLinkedList("[9]"))));
    }
}
