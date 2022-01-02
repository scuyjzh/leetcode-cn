package com.scuyjzh.leetcode.easy.No_0160_Intersection_of_Two_Linked_Lists;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 160. 相交链表
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表
 * 相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方
 * 案？
 */
class Solution {
    /**
     * 方法一：哈希集合
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        /*
         * 判断两个链表是否相交，可以使用哈希集合存储链表节点。
         *
         * 首先遍历链表 headA，并将链表 headA 中的每个节点加入哈希集合中。然后遍历链表 headB，对于遍历到的
         * 每个节点，判断该节点是否在哈希集合中：
         *   • 如果当前节点不在哈希集合中，则继续遍历下一个节点；
         *   • 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都在两个链
         *     表的相交部分，因此在链表 headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，
         *     返回该节点。
         *
         * 如果链表 headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回 null。
         */
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 方法二：双指针
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        /*
         * pA 走过的路径为 A链 + B链，
         * pB 走过的路径为 B链 + A链。
         * pA 和 pB 走过的长度相同，都是 A链 和 B链 的长度之和。
         *
         * 即相当于将两个链表从尾端对齐，如果相交，则会提前在相交点相遇；
         * 如果没有相交点，则会在最后相遇。
         *
         * pA: 4->1->8->4->5->null->5->6->1->[8]->4->5->null
         * pB: 5->6->1->8->4->5->null->4->1->[8]->4->5->null
         */
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
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

        System.out.println(ListNode.toString(new Solution().getIntersectionNode1(headA, headB)));
        System.out.println(ListNode.toString(new Solution().getIntersectionNode2(headA, headB)));
    }
}
