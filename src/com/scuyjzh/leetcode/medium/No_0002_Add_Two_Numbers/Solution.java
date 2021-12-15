package com.scuyjzh.leetcode.medium.No_0002_Add_Two_Numbers;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 2. 两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆
 * 序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /*
         * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
         *
         * 同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处
         * 相应位置的数字为 n1,n2，进位值为 carry，则它们的和为 n1+n2+carry；其中，答案链表处相应位置的
         * 数字为 (n1+n2+carry) mod 10，而新的进位值为 ⌊(n1+n2+carry)/10⌋。
         *
         * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 0。
         *
         * 此外，如果链表遍历结束后，有 carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry。
         */
        ListNode head = null, tail = null;
        // 设定进位值为 carry
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 同时遍历两个链表
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            // 逐位计算它们的和，并与当前位置的进位值相加
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            // 新的进位值
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().addTwoNumbers(ListNode.initLinkedList("[2,4,3]"), ListNode.initLinkedList("[5,6,4]"))));
    }
}
