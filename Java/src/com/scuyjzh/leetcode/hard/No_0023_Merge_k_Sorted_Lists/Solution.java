package com.scuyjzh.leetcode.hard.No_0023_Merge_k_Sorted_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 23. 合并K个升序链表
 *
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
class Solution {
    /**
     * 方法一：分治合并
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        /*
         * 考虑用分治的方法进行合并。
         *   • 将 k 个链表配对并将同一对中的链表合并；
         *   • 第一轮合并以后，k 个链表被合并成了 k/2 个链表，平均长度为 2n/4，然后是 k/4 个链表，k/8 个链表等
         *     等；
         *   • 重复这一过程，直到得到了最终的有序链表。
         */
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
        ListNode l1 = partion(lists, start, mid);
        ListNode l2 = partion(lists, mid + 1, end);
        // 合并两个有序链表
        return merge(l1, l2);
    }

    /**
     * 「21. 合并两个有序链表」中的迭代法
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        // 设定一个哨兵节点 dummy，可以在最后比较容易地返回合并后的链表
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // 如果 l1 当前节点的值小于等于 l2 ，就把 l1 当前的节点接在 cur 节点的后面
                cur.next = l1;
                // 同时将 l1 指针往后移一位
                l1 = l1.next;
            } else {
                // 否则，对 l2 做同样的操作
                cur.next = l2;
                l2 = l2.next;
            }
            // 把 cur 向后移一位
            cur = cur.next;
        }
        // 在循环终止的时候， l1 和 l2 至多有一个是非空的
        // 将非空链表接在合并链表的后面
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 方法二：使用优先队列合并
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        /*
         * 这个方法需要维护当前每个链表没有被合并的元素的最前面一个，k 个链表就最多有 k 个满足这样条件的元素，
         * 每次在这些元素里面选取 val 属性最小的元素合并到答案中。
         *
         * 在选取最小元素的时候，可以用优先队列来优化这个过程。
         */
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                queue.offer(list);
            }
        }

        ListNode dummy = new ListNode(-1);
        // tail 记录答案链表的尾节点
        ListNode tail = dummy;
        while (!queue.isEmpty()) {
            // 选取优先队列中的最小元素
            ListNode curr = queue.poll();
            // 合并到答案链表中
            tail.next = curr;
            tail = tail.next;
            if (curr.next != null) {
                // 不断维护优先队列
                queue.offer(curr.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().mergeKLists1(new ListNode[]{ListNode.initLinkedList("[1,4,5]"), ListNode.initLinkedList("[1,3,4]"), ListNode.initLinkedList("[2,6]")})));
        System.out.println(ListNode.toString(new Solution().mergeKLists2(new ListNode[]{ListNode.initLinkedList("[1,4,5]"), ListNode.initLinkedList("[1,3,4]"), ListNode.initLinkedList("[2,6]")})));
    }
}
