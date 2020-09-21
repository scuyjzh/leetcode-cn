package com.scuyjzh.leetcode.hard.No_0023_Merge_k_Sorted_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return partion(lists, 0, lists.length - 1);
    }

    public ListNode partion(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        if (start < end) {
            int mid = ((end - start) >> 1) + start;
            ListNode l1 = partion(lists, start, mid);
            ListNode l2 = partion(lists, mid + 1, end);
            return merge(l1, l2);
        } else {
            return null;
        }
    }

    public ListNode merge(ListNode l1, ListNode l2) {
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
}
