package com.scuyjzh.leetcode.medium.No_0082_Remove_Duplicates_from_Sorted_List_II;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 *
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链
 * 表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数
 * 字。
 * 返回同样按升序排列的结果链表。
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        /*
         * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此只需要对链表进行一
         * 次遍历，就可以删除重复的元素。由于链表的头节点可能会被删除，因此需要额外使用一个哑节点
         * （dummy node）指向链表的头节点。
         *
         * 具体地，从指针 cur 指向链表的哑节点，随后开始对链表进行遍历。如果当前 cur.next 与 cur.next.next
         * 对应的元素相同，那么就需要将 cur.next 以及所有后面拥有相同元素值的链表节点全部删除。记下
         * 这个元素值 x，随后不断将 cur.next 从链表中移除，直到 cur.next 为空节点或者其元素值不等于 x 为止。此
         * 时，将链表中所有元素值为 x 的节点全部删除。
         *
         * 如果当前 cur.next 与 cur.next.next 对应的元素不相同，那么说明链表中只有一个元素值为 cur.next 的节点，
         * 那么就可以将 cur 指向 cur.next。
         *
         * 当遍历完整个链表之后，返回链表的的哑节点的下一个节点 dummy.next 即可。
         */
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                // 记下这个元素值 x
                int x = cur.next.val;
                // 随后不断将 cur.next 从链表中移除，直到 cur.next 为空节点或者其元素值不等于 x 为止
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                // 链表中只有一个元素值为 cur.next 的节点，将 cur 指向 cur.next
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().deleteDuplicates(ListNode.initLinkedList("[1,2,3,3,4,4,5]"))));
        System.out.println(ListNode.toString(new Solution().deleteDuplicates(ListNode.initLinkedList("[1,1,1,2,3]"))));
    }
}
