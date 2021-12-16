package com.scuyjzh.leetcode.medium.No_0086_Partition_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 86. 分隔链表
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使
 * 得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        /*
         * 直观来说只需维护两个链表 small 和 large 即可，small 链表按顺序存储所有小于 x 的节点，large 链表
         * 按顺序存储所有大于等于 x 的节点。遍历完原链表后，只要将 small 链表尾节点指向 large 链表的头节
         * 点即能完成对链表的分隔。
         *
         * 为了实现上述思路，设 smallHead 和 largeHead 分别为两个链表的哑节点，即它们的 next 指针指向链表
         * 的头节点，这样做的目的是为了更方便地处理头节点为空的边界条件。同时设 small 和 large 节点指向当前链
         * 表的末尾节点。开始时 smallHead=small,largeHead=large。随后，从前往后遍历链表，判断当前链表的
         * 节点值是否小于 x，如果小于就将 small 的 next 指针指向该节点，否则将 large 的 next 指针指向该节点。
         *
         * 遍历结束后，将 large 的 next 指针置空，这是因为当前节点复用的是原链表的节点，而其 next 指针可能
         * 指向一个小于 x 的节点，需要切断这个引用。同时将 small 的 next 指针指向 largeHead 的 next 指针指
         * 向的节点，即真正意义上的 large 链表的头节点。最后返回 smallHead 的 next 指针即为要求的答案。
         */
        ListNode small = new ListNode(0);
        // 设 smallHead 为 small 链表的哑节点，small 链表按顺序存储所有小于 x 的节点
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        // 设 largeHead 为 large 链表的哑节点，large 链表按顺序存储所有大于等于 x 的节点
        ListNode largeHead = large;
        ListNode curr = head;
        // 从前往后遍历链表，判断当前链表的节点值是否小于 x
        while (curr != null) {
            if (curr.val < x) {
                // 如果小于就将 small 的 next 指针指向该节点
                small.next = curr;
                small = small.next;
            } else {
                // 否则将 large 的 next 指针指向该节点
                large.next = curr;
                large = large.next;
            }
            curr = curr.next;
        }
        // 遍历结束后，large 的 next 指针可能指向一个小于 x 的节点，需要切断这个引用
        large.next = null;
        // 将 small 的 next 指针指向 largeHead 的 next 指针指向的节点，即真正意义上的 large 链表的头节点
        small.next = largeHead.next;
        // 返回 smallHead 的 next 指针即为要求的答案
        return smallHead.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().partition(ListNode.initLinkedList("[1,4,3,2,5,2]"), 3)));
        System.out.println(ListNode.toString(new Solution().partition(ListNode.initLinkedList("[2,1]"), 2)));
    }
}
