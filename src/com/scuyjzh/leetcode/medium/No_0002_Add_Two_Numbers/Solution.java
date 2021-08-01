package com.scuyjzh.leetcode.medium.No_0002_Add_Two_Numbers;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 2. 两数相加
 * <p>
 * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 */
class Solution {
    /**
     * 方法：模拟
     * 时间复杂度：O(max(m,n))，其中 m、n 为两个链表的长度。要遍历两个链表的全部位置，而处理每个位置只需要 O(1) 的时间。
     * 空间复杂度：O(max(m,n))。答案链表的长度最多为较长链表的长度 + 1。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
        Solution solution = new Solution();
        ListNode l1 = ListNode.initLinkedList("[2,4,3]");
        ListNode l2 = ListNode.initLinkedList("[5,6,4]");
        System.out.println(ListNode.toString(solution.addTwoNumbers(l1, l2)));
    }
}
