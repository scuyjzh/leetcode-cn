package com.scuyjzh.leetcode.easy.No_0021_Merge_Two_Sorted_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 */
class Solution {
    /**
     * Approach #1 (Iteration)
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }

    /**
     * Approach #2 (Recursion)
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode l1 = ListNode.initLinkedList("[1,2,4]");
        ListNode l2 = ListNode.initLinkedList("[1,3,4]");
        System.out.println(ListNode.toString(solution.mergeTwoLists1(l1, l2)));
        System.out.println(ListNode.toString(solution.mergeTwoLists2(l1, l2)));
    }
}
