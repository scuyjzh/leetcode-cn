package com.scuyjzh.leetcode.easy.No_0203_Remove_Linked_List_Elements;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 203. 移除链表元素
 *
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足
 * Node.val == val 的节点，并返回 新的头节点 。
 */
class Solution {
    /**
     * 方法一：递归
     */
    public ListNode removeElements1(ListNode head, int val) {
        /*
         * 链表的定义具有递归的性质，因此链表题目常可以用递归的方法求解。这道题要求删除链表中所有节点值等
         * 于特定值的节点，可以用递归实现。
         *
         * 对于给定的链表，首先对除了头节点 head 以外的节点进行删除操作，然后判断 head 的节点值是否等于给定
         * 的 val。如果 head 的节点值等于 val，则 head 需要被删除，因此删除操作后的头节点为 head.next；如果
         * head 的节点值不等于 val，则 head 保留，因此删除操作后的头节点还是 head。上述过程是一个递归的过
         * 程。
         *
         * 递归的终止条件是 head 为空，此时直接返回 head。当 head 不为空时，递归地进行删除操作，然后判断
         * head 的节点值是否等于 val 并决定是否要删除 head。
         */
        return remove(head, val);
    }

    private ListNode remove(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = remove(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * 方法二：迭代
     */
    public ListNode removeElements2(ListNode head, int val) {
        /*
         * 也可以用迭代的方法删除链表中所有节点值等于特定值的节点。
         *
         * 用 temp 表示当前节点。如果 temp 的下一个节点不为空且下一个节点的节点值等于给定的 val，则需要删除
         * 下一个节点。删除下一个节点可以通过以下做法实现：
         *         temp.next=temp.next.next
         *
         * 如果 temp 的下一个节点的节点值不等于给定的 val，则保留下一个节点，将 temp 移动到下一个节点即可。
         *
         * 当 temp 的下一个节点为空时，链表遍历结束，此时所有节点值等于 val 的节点都被删除。
         *
         * 具体实现方面，由于链表的头节点 head 有可能需要被删除，因此创建哑节点 dummyHead，令
         * dummyHead.next=head，初始化 temp=dummyHead，然后遍历链表进行删除操作。最终返回
         * dummyHead.next 即为删除操作后的头节点。
         */
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().removeElements1(ListNode.initLinkedList("[1,2,3,4,5,6]"), 6)));
        System.out.println(ListNode.toString(new Solution().removeElements2(ListNode.initLinkedList("[7,7,7,7]"), 7)));
    }
}
