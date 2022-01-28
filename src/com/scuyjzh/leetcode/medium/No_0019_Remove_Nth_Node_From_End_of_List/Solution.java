package com.scuyjzh.leetcode.medium.No_0019_Remove_Nth_Node_From_End_of_List;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 19. 删除链表的倒数第 N 个节点
 *
 * 给你一个链表，删除链表的倒数第 n 个节点，并且返回链表的头节点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
class Solution {
    /**
     * 方法一：计算链表长度
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        // 添加一个哑节点，它的 next 指针指向链表的头节点。这样一来，就不需要对头节点进行特殊的判断了
        ListNode dummy = new ListNode(0, head);
        // 首先从头节点开始对链表进行一次遍历，得到链表的长度 L
        int length = getLength(head);
        // 从哑节点开始遍历 L−n+1 个节点，它的下一个节点就是需要删除的节点
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        // 只需要修改一次指针，就能完成删除操作
        cur.next = cur.next.next;
        return dummy.next;
    }

    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }

    /**
     * 方法二：栈
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        // 哑节点
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = dummy;
        // 在遍历链表的同时将所有节点依次入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 根据栈「先进后出」的原则，弹出栈的第 n 个节点就是需要删除的节点
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        // 目前栈顶节点就是待删除节点的前驱节点
        ListNode pre = stack.peek();
        // 删除该节点
        pre.next = pre.next.next;
        return dummy.next;
    }

    /**
     * 方法三：双指针
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        // 哑节点
        ListNode dummy = new ListNode(0, head);
        // 将 first 指向头节点
        ListNode first = head;
        // 将 second 指向哑节点
        ListNode second = dummy;
        // 首先使用 first 对链表进行遍历，遍历的次数为 n
        // 此时，first 和 second 之间间隔了 n 个节点，即 first 比 second 超前了 n+1 个节点
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        // 在这之后，同时使用 first 和 second 对链表进行遍历
        // 当 first 遍历到链表的末尾时，second 的下一个节点就是需要删除的节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 此时 second 指向倒数第 n 个节点的前驱节点
        second.next = second.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().removeNthFromEnd1(ListNode.initLinkedList("[1,2,3,4,5]"), 2)));
        System.out.println(ListNode.toString(new Solution().removeNthFromEnd2(ListNode.initLinkedList("[1,2,3,4,5]"), 2)));
        System.out.println(ListNode.toString(new Solution().removeNthFromEnd3(ListNode.initLinkedList("[1,2,3,4,5]"), 2)));
    }
}
