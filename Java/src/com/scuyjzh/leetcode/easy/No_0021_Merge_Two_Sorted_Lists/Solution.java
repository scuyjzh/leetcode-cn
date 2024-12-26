package com.scuyjzh.leetcode.easy.No_0021_Merge_Two_Sorted_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 21. 合并两个有序链表
 *
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定
 * 的两个链表的所有节点组成的。
 */
class Solution {
    /**
     * 方法一：递归
     */
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        /*
         * 可以如下递归地定义两个链表里的 merge 操作（忽略边界情况，比如空链表等）：
         *     list1[0]+merge(list1[1:],list2)  list1[0]<list2[0]
         *     list2[0]+merge(list1,list2[1:])  otherwise
         * 也就是说，两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并。
         */
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    /**
     * 方法二：迭代
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
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

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().mergeTwoLists1(ListNode.initLinkedList("[1,2,5]"), ListNode.initLinkedList("[1,3,6]"))));
        System.out.println(ListNode.toString(new Solution().mergeTwoLists2(ListNode.initLinkedList("[1,2,5]"), ListNode.initLinkedList("[1,3,6]"))));
    }
}
