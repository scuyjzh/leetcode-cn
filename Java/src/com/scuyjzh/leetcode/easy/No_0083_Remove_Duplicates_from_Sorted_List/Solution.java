package com.scuyjzh.leetcode.easy.No_0083_Remove_Duplicates_from_Sorted_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 83. 删除排序链表中的重复元素
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所
 * 有重复的元素，使每个元素 只出现一次 。
 * 返回同样按升序排列的结果链表。
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        /*
         * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此只需要对链表进行一
         * 次遍历，就可以删除重复的元素。
         *
         * 具体地，从指针 cur 指向链表的头节点，随后开始对链表进行遍历。如果当前 cur 与 cur.next 对应的元
         * 素相同，那么就将 cur.next 从链表中移除；否则说明链表中已经不存在其它与 cur 对应的元素相同的节
         * 点，因此可以将 cur 指向 cur.next。
         *
         * 当遍历完整个链表之后，返回链表的头节点即可。
         */
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                // 如果当前 cur 与 cur.next 对应的元素相同，那么就将 cur.next 从链表中移除
                cur.next = cur.next.next;
            } else {
                // 否则将 cur 指向 cur.next
                cur = cur.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().deleteDuplicates(ListNode.initLinkedList("[1,1,2]"))));
        System.out.println(ListNode.toString(new Solution().deleteDuplicates(ListNode.initLinkedList("[1,1,2,3,3]"))));
    }
}
