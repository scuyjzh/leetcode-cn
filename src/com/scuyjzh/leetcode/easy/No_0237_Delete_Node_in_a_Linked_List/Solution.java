package com.scuyjzh.leetcode.easy.No_0237_Delete_Node_in_a_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 237. 删除链表中的节点
 *
 * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注
 * 意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点 。
 * 题目数据保证需要删除的节点 不是末尾节点 。
 */
class Solution {
    public void deleteNode(ListNode node) {
        /*
         * 删除链表中的节点的常见的方法是定位到待删除节点的上一个节点，修改上一个节点的 next 指针，使其指向
         * 待删除节点的下一个节点，即可完成删除操作。
         *
         * 这道题中，传入的参数 node 为要被删除的节点，无法定位到该节点的上一个节点。注意到要被删除的节点
         * 不是链表的末尾节点，因此 node.next 不为空，可以通过对 node 和 node.next 进行操作实现删除节点。
         *
         * 在给定节点 node 的情况下，可以通过修改 node 的 next 指针的指向，删除 node 的下一个节点。但是题目要
         * 求删除 node，为了达到删除 node 的效果，只要在删除节点之前，将 node 的节点值修改为 node.next 的节点
         * 值即可。
         *
         * 例如，给定链表 4→5→1→9，要被删除的节点是 5，即链表中的第 2 个节点。可以通过如下两步操作实
         * 现删除节点的操作。
         *   1.将第 2 个节点的值修改为第 3 个节点的值，即将节点 5 的值修改为 1，此时链表如下：
         *             4→1→1→9
         *   2.删除第 3 个节点，此时链表如下：
         *             4→1→9
         * 达到删除节点 5 的效果。
         */
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.initLinkedList("[3,2,0,-4]");
        new Solution().deleteNode(head.next.next);
        System.out.println(ListNode.toString(head));
    }
}
