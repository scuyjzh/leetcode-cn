package com.scuyjzh.leetcode.hard.No_0023_Merge_k_Sorted_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 23. 合并K个升序链表
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
class Solution {
    /**
     * 方法一：分治合并
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        return partion(lists, 0, lists.length - 1);
    }

    private ListNode partion(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start > end) {
            return null;
        }
        int mid = ((end - start) >> 1) + start;
        return merge(partion(lists, start, mid), partion(lists, mid + 1, end));
    }

    private ListNode merge(ListNode l1, ListNode l2) {
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
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 方法二：使用优先队列合并
     * 时间复杂度：考虑优先队列中的元素不超过 k 个，那么插入和删除的时间代价为 O(log k)，这里最多有 kn 个点，对于每个点都被插入删除各一次，故总的时间代价即渐进时间复杂度为 O(kn × log k)。
     * 空间复杂度：这里用了优先队列，优先队列中的元素不超过 k 个，故渐进空间复杂度为 O(k)。
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            ListNode nextNode = queue.poll();
            cur.next = nextNode;
            cur = cur.next;
            if (nextNode.next != null) {
                queue.add(nextNode.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(ListNode.toString(solution.mergeKLists1(new ListNode[]{ListNode.initLinkedList("[1,4,5]"), ListNode.initLinkedList("[1,3,4]"), ListNode.initLinkedList("[2,6]")})));
        System.out.println(ListNode.toString(solution.mergeKLists2(new ListNode[]{ListNode.initLinkedList("[1,4,5]"), ListNode.initLinkedList("[1,3,4]"), ListNode.initLinkedList("[2,6]")})));
    }
}
