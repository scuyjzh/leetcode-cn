package com.scuyjzh.leetcode.easy.No_0206_Reverse_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 206. 反转链表
 *
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
class Solution {
    /**
     * 方法一：迭代
     */
    public ListNode reverseList1(ListNode head) {
        /*
         * 假设链表为 1→2→3→∅，想要把它改成 ∅←1←2←3。
         *
         * 在遍历链表时，将当前节点的 next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事
         * 先存储其前一个节点。在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
         */
        ListNode prev = null;
        ListNode curr = head;
        // 每次循环，都将当前节点的 next 指针指向它的前一个节点，然后将前一个节点和当前节点后移
        while (curr != null) {
            // 临时节点，暂存当前节点的后一个节点，用于后移
            ListNode next = curr.next;
            // 将当前节点的 next 指针指向它的前一个节点（切断当前节点与原来后续节点的链接）
            curr.next = prev;
            // 前一个节点后移
            prev = curr;
            // 当前节点后移
            curr = next;
        }
        return prev;
    }

    /**
     * 方法二：递归
     */
    public ListNode reverseList2(ListNode head) {
        /*
         * 递归版本稍微复杂一些，其关键在于反向工作。假设链表的其余部分已经被反转，现在应该如何反转它前面
         * 的部分？
         *
         * 假设链表为：
         *         n_1 → … → n_{k-1} → n_k → n_{k+1} → … → n_m → ∅
         *
         * 若从节点 n_{k+1} 到 n_m 已经被反转，而当前正处于 n_k。
         *         n_1 → … → n_{k-1} → n_k → n_{k+1} ← … ← n_m
         *
         * 希望 n_{k+1} 的下一个节点指向 n_k。
         * 所以，n_k.next.next=n_k。
         *
         * 需要注意的是 n_1 的下一个节点必须指向 ∅。如果忽略了这一点，链表中可能会产生环。
         */
        return reverse(head);
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().reverseList1(ListNode.initLinkedList("[1,2,3,4,5]"))));
        System.out.println(ListNode.toString(new Solution().reverseList2(ListNode.initLinkedList("[1,2,3,4,5]"))));
    }
}
