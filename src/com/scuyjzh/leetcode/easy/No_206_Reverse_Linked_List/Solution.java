package com.scuyjzh.leetcode.easy.No_206_Reverse_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

class Solution {
    /**
     * Approach #1 (Iteration - Forward)
     */
    public ListNode reverseList1(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * Approach #2 (Recursion - Backward)
     */
    public ListNode reverseList2(ListNode head) {
        return helper(head);
    }

    private ListNode helper(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        ListNode newHead = helper(next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseList1(ListNode.initLinkedList("[1,2,3,4,5]")));
        System.out.println(solution.reverseList2(ListNode.initLinkedList("[1,2,3,4,5]")));
    }
}
