package com.scuyjzh.leetcode.structure;

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode initLinkedList(String str) {
        if ("[]".equals(str)) {
            return null;
        }
        str = str.substring(1, str.length() - 1);
        String[] split = str.split(",");
        int len = split.length;
        ListNode[] listNodes = new ListNode[len + 1];
        listNodes[0] = new ListNode(Integer.parseInt(split[0]));
        for (int i = 1; i < len; i++) {
            listNodes[i] = new ListNode(Integer.parseInt(split[i]));
            listNodes[i - 1].next = listNodes[i];
        }
        return listNodes[0];
    }

    public static String toString(ListNode head) {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val).append(",");
            head = head.next;
        }
        sb.replace(sb.length() - 1, sb.length(), "]");
        return sb.toString();
    }
}
