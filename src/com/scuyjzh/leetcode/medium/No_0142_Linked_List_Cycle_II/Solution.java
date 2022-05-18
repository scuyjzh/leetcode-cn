package com.scuyjzh.leetcode.medium.No_0142_Linked_List_Cycle_II;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 142. 环形链表 II
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中
 * 的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。
 * 注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * 不允许修改 链表。
 *
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 */
class Solution {
    /**
     * 方法：双指针
     */
    public ListNode detectCycle(ListNode head) {
        // 使用两个指针，fast 与 slow。它们起始都位于链表的头部
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // 随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置
            fast = fast.next.next;
            slow = slow.next;
            // 如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇
            if (slow == fast) {
                // 额外使用一个指针 ptr 指向链表头部
                ListNode ptr = head;
                // ptr 和 slow 每次向后移动一个位置。最终，它们会在入环点相遇
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                // 在入环点相遇
                return ptr;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(new Solution().detectCycle(head).val);
    }
}
