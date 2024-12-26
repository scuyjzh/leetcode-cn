package com.scuyjzh.leetcode.easy.No_0160_Intersection_of_Two_Linked_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 160. 相交链表
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表
 * 相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 *
 * 题目数据 保证 整个链式结构中不存在环。
 *
 * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
 */
class Solution {
    /**
     * 方法：双指针
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 首先判断链表 headA 和 headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回 null
        if (headA == null || headB == null) {
            return null;
        }
        // 创建两个指针 pA 和 pB，初始时分别指向两个链表的头节点 headA 和 headB
        ListNode pA = headA, pB = headB;
        // 然后将两个指针依次遍历两个链表的每个节点，每步操作需要同时更新指针 pA 和 pB
        while (pA != pB) {
            // 如果指针 pA 不为空，则将指针 pA 移到下一个节点；
            // 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点
            pA = pA == null ? headB : pA.next;
            // 如果指针 pB 不为空，则将指针 pB 移到下一个节点；
            // 如果指针 pB 为空，则将指针 pB 移到链表 headA 的头节点
            pB = pB == null ? headA : pB.next;
        }
        // 当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者 null
        return pA;
    }

    public static void main(String[] args) {
        ListNode intersectionNode = new ListNode(8);
        intersectionNode.next = new ListNode(4);
        intersectionNode.next.next = new ListNode(5);

        ListNode headA = new ListNode(4);
        headA.next = new ListNode(1);
        headA.next.next = intersectionNode;

        ListNode headB = new ListNode(5);
        headB.next = new ListNode(6);
        headB.next.next = new ListNode(1);
        headB.next.next.next = intersectionNode;

        System.out.println(new Solution().getIntersectionNode(headA, headB).val);
    }
}
