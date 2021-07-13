package com.scuyjzh.leetcode.medium.No_0019_Remove_Nth_Node_From_End_of_List;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 19. 删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
class Solution {
    /**
     * 方法一：栈
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     * 空间复杂度：O(L)，其中 L 是链表的长度。主要为栈的开销。
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        /*
         * 思路与算法：
         * 在遍历链表的同时将所有节点依次入栈。
         * 根据栈「先进后出」的原则，弹出栈的第 n 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点。
         */
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        // 删除该节点
        prev.next = prev.next.next;
        return dummy.next;
    }

    /**
     * 方法二：双指针
     * 时间复杂度：O(L)，其中 L 是链表的长度。
     * 空间复杂度：O(1)。
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        /*
         * 思路与算法：
         * 在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
         * 由于需要找到倒数第 n 个节点，因此可以使用两个指针 first 和 second 同时对链表进行遍历，并且 first 比 second 超前 n 个节点。
         * 当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
         */
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(ListNode.toString(solution.removeNthFromEnd1(ListNode.initLinkedList("[1]"), 1)));
        System.out.println(ListNode.toString(solution.removeNthFromEnd2(ListNode.initLinkedList("[1,2]"), 1)));
    }
}