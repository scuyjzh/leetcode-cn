package com.scuyjzh.leetcode.medium.No_0328_Odd_Even_Linked_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 328. 奇偶链表
 *
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这
 * 里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度
 * 应为 O(nodes)，nodes 为节点总数。
 */
class Solution {
    /**
     * 方法：分离节点后合并
     */
    public ListNode oddEvenList(ListNode head) {
        /*
         * 如果链表为空，则直接返回链表。
         *
         * 对于原始链表，每个节点都是奇数节点或偶数节点。头节点是奇数节点，头节点的后一个节点是偶数节点，
         * 相邻节点的奇偶性不同。因此可以将奇数节点和偶数节点分离成奇数链表和偶数链表，然后将偶数链表连接
         * 在奇数链表之后，合并后的链表即为结果链表。
         *
         * 原始链表的头节点 head 也是奇数链表的头节点以及结果链表的头节点，head 的后一个节点是偶数链表
         * 的头节点。令 evenHead = head.next，则 evenHead 是偶数链表的头节点。
         *
         * 维护两个指针 odd 和 even 分别指向奇数节点和偶数节点，初始时 odd = head，even = evenHead。
         * 通过迭代的方式将奇数节点和偶数节点分离成两个链表，每一步首先更新奇数节点，然后更新偶数节点。
         *   • 更新奇数节点时，奇数节点的后一个节点需要指向偶数节点的后一个节点，因此令 odd.next =
         *     even.next，然后令 odd = odd.next，此时 odd 变成 even 的后一个节点。
         *   • 更新偶数节点时，偶数节点的后一个节点需要指向奇数节点的后一个节点，因此令 even.next =
         *     odd.next，然后令 even = even.next，此时 even 变成 odd 的后一个节点。
         *
         * 在上述操作之后，即完成了对一个奇数节点和一个偶数节点的分离。重复上述操作，直到全部节点分离完
         * 毕。全部节点分离完毕的条件是 even 为空节点或者 even.next 为空节点，此时 odd 指向最后一个奇数
         * 节点（即奇数链表的最后一个节点）。
         *
         * 最后令 odd.next = evenHead，将偶数链表连接在奇数链表之后，即完成了奇数链表和偶数链表的合并，
         * 结果链表的头节点仍然是 head。
         */
        if (head == null) {
            return null;
        }
        // 记录偶数链表的头节点
        ListNode evenHead = head.next;
        ListNode odd = head, even = evenHead;
        // 将奇数节点和偶数节点分离成奇数链表和偶数链表
        while (even != null && even.next != null) {
            // 更新奇数节点
            odd.next = even.next;
            odd = odd.next;
            // 更新偶数节点
            even.next = odd.next;
            even = even.next;
        }
        // 将偶数链表连接在奇数链表之后
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().oddEvenList(ListNode.initLinkedList("[1,2,3,4,5]"))));
        System.out.println(ListNode.toString(new Solution().oddEvenList(ListNode.initLinkedList("[2,1,3,5,6,4,7]"))));
    }
}
