package com.scuyjzh.leetcode.easy.No_0876_Middle_of_the_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 876. 链表的中间结点
 *
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 */
class Solution {
    /**
     * 方法：双指针
     */
    public ListNode middleNode(ListNode head) {
        // 用两个指针 slow 与 fast 一起遍历链表
        ListNode slow = head, fast = head;
        // 当 fast 到达链表的末尾时，slow 必然位于中间
        while (fast != null && fast.next != null) {
            // slow 一次走一步，fast 一次走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().middleNode(ListNode.initLinkedList("[1,2,3,4,5]"))));
        System.out.println(ListNode.toString(new Solution().middleNode(ListNode.initLinkedList("[1,2,3,4,5,6]"))));
    }
}
