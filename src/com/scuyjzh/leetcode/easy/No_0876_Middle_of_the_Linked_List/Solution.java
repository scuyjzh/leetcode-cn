package com.scuyjzh.leetcode.easy.No_0876_Middle_of_the_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 876. 链表的中间节点
 *
 * 给定一个头节点为 head 的非空单链表，返回链表的中间节点。
 * 如果有两个中间节点，则返回第二个中间节点。
 */
class Solution {
    /**
     * 方法：快慢指针法
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
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
