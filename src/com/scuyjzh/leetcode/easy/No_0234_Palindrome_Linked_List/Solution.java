package com.scuyjzh.leetcode.easy.No_0234_Palindrome_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

import java.util.*;

/**
 * 234. 回文链表
 *
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果
 * 是，返回 true ；否则，返回 false 。
 */
class Solution {
    public boolean isPalindrome1(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        // 1.复制链表值到数组列表中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 2.使用双指针法判断是否为回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            ++front;
            --back;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null) {
            return true;
        }

        // 1.找到前半部分链表的尾节点
        ListNode firstHalfEnd = endOfFirstHalf(head);

        // 2.反转后半部分链表
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 3.判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 4.恢复链表
        firstHalfEnd.next = reverseList(secondHalfStart);

        // 5.返回结果
        return result;
    }

    /**
     * 「206. 反转链表」中的迭代方法
     */
    private ListNode reverseList(ListNode head) {
        // 也可以使用递归反转一个链表
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome1(ListNode.initLinkedList("[1,2,2,1]")));
        System.out.println(new Solution().isPalindrome2(ListNode.initLinkedList("[1,2,2,1]")));
    }
}
