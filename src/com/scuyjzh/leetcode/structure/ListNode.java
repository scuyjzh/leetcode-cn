package com.scuyjzh.leetcode.structure;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public static ListNode createList(String str) {
        if ("[]".equals(str)) {
            return null;
        }
        str = str.substring(1, str.length() - 1);
        String[] split = str.split(",");
        int len = split.length;
        ListNode[] listNodes = new ListNode[len + 1];
        listNodes[0] = new ListNode(Integer.valueOf(split[0]));
        for (int i = 1; i < len; i++) {
            listNodes[i] = new ListNode(Integer.valueOf(split[i]));
            listNodes[i - 1].next = listNodes[i];
        }
        return listNodes[0];
    }
}
