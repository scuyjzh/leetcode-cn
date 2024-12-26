package com.scuyjzh.leetcode.medium.No_0143_Reorder_List;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 143. 重排链表
 *
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 */
class Solution {
    /**
     * 方法一：线性表
     */
    public void reorderList1(ListNode head) {
        /*
         * 因为链表不支持下标访问，所以无法随机访问链表中任意位置的元素。
         *
         * 因此比较容易想到的一个方法是，利用线性表存储该链表，然后利用线性表可以下标访问的特点，直接
         * 按顺序访问指定元素，重建该链表即可。
         */
        if (head == null) {
            return;
        }

        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }

        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            ++i;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            --j;
        }
        list.get(i).next = null;
    }

    /**
     * 方法二：寻找链表中点 + 链表逆序 + 合并链表
     */
    public void reorderList2(ListNode head) {
        /*
         * 注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
         *
         * 这样任务即可划分为三步：
         *   1.找到原链表的中点（参考「876. 链表的中间节点」）。
         *       • 可以使用快慢指针来 O(N) 地找到链表的中间节点。
         *   2.将原链表的右半端反转（参考「206. 反转链表」）。
         *       • 可以使用迭代法实现链表的反转。
         *   3.将原链表的两端合并。
         *       • 因为两链表长度相差不超过 1，因此直接合并即可。
         */
        if (head == null) {
            return;
        }

        // 1.找到原链表的中点
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // 注意：切断链接
        mid.next = null;

        // 2.将原链表的右半端反转
        l2 = reverseList(l2);

        // 3.将原链表的两端合并
        mergeList(l1, l2);
    }

    /**
     * 「876. 链表的中间节点」中的快慢指针法
     */
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 「206. 反转链表」中的迭代法
     */
    private ListNode reverseList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 将原链表的两端交叉合并
     */
    private void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }

    public static void main(String[] args) {
        ListNode head1 = ListNode.initLinkedList("[1,2,3,4]");
        new Solution().reorderList1(head1);
        System.out.println(ListNode.toString(head1));

        ListNode head2 = ListNode.initLinkedList("[1,2,3,4,5]");
        new Solution().reorderList2(head2);
        System.out.println(ListNode.toString(head2));
    }
}
