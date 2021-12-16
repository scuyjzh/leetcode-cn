package com.scuyjzh.leetcode.medium.No_0148_Sort_List;

import com.scuyjzh.leetcode.structure.ListNode;

/**
 * 148. 排序链表
 *
 * 给你链表的头节点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：
 *   • 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表
 * 进行排序吗？
 */
class Solution {
    /**
     * 方法一：自顶向下归并排序
     *
     * • 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * • 空间复杂度：O(logn)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间。
     */
    public ListNode sortList1(ListNode head) {
        /*
         * 「147. 对链表进行插入排序」要求使用插入排序的方法对链表进行排序，插入排序的时间复杂度是 O(n^2)，
         * 其中 n 是链表的长度。这道题考虑时间复杂度更低的排序算法。题目的进阶问题要求达到 O(nlogn) 的时间
         * 复杂度和 O(1) 的空间复杂度，时间复杂度是 O(nlogn) 的排序算法包括归并排序、堆排序和快速排序（快
         * 速排序的最差时间复杂度是 O(n^2)），其中最适合链表的排序算法是归并排序。
         *
         * 归并排序基于分治算法。最容易想到的实现方式是自顶向下的递归实现，考虑到递归调用的栈空间，自顶向
         * 下归并排序的空间复杂度是 O(logn)。如果要达到 O(1) 的空间复杂度，则需要使用自底向上的实现方式。
         *
         * 对链表自顶向下归并排序的过程如下。
         *   1.找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做
         *     法，快指针每次移动 2 步，慢指针每次移动 1 步，当快指针到达链表末尾时，慢指针指向的链表节点
         *     即为链表的中点。
         *   2.对两个子链表分别排序。
         *   3.将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，
         *     将两个有序的子链表进行合并。
         *
         * 上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于 1，即当链表为空或者链表只包
         * 含 1 个节点时，不需要对链表进行拆分和排序。
         */
        return sort(head);
    }

    private ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1.找到链表的中点，以中点为分界，将链表拆分成两个子链表
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // 注意：切断链接
        mid.next = null;

        // 2.对两个子链表分别排序
        l1 = sort(l1);
        l2 = sort(l2);

        // 3.将两个排序后的子链表合并，得到完整的排序后的链表
        return merge(l1, l2);
    }

    /**
     * 「876. 链表的中间节点」中的快慢指针法
     */
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 「21. 合并两个有序链表」中的迭代法
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        // 设定一个哨兵节点 dummy，可以在最后比较容易地返回合并后的链表
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                // 如果 l1 当前节点的值小于等于 l2 ，就把 l1 当前的节点接在 cur 节点的后面
                cur.next = l1;
                // 同时将 l1 指针往后移一位
                l1 = l1.next;
            } else {
                // 否则，对 l2 做同样的操作
                cur.next = l2;
                l2 = l2.next;
            }
            // 把 cur 向后移一位
            cur = cur.next;
        }
        // 在循环终止的时候， l1 和 l2 至多有一个是非空的
        // 将非空链表接在合并链表的后面
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 方法二：自底向上归并排序
     *
     * • 时间复杂度：O(nlogn)，其中 n 是链表的长度。
     * • 空间复杂度：O(1)。
     */
    public ListNode sortList2(ListNode head) {
        /*
         * 使用自底向上的方法实现归并排序，则可以达到 O(1) 的空间复杂度。
         * 首先求得链表的长度 length，然后将链表拆分成子链表进行合并。
         * 具体做法如下。
         *   1.用 subLength 表示每次需要排序的子链表的长度，初始时 subLength=1。
         *   2.每次将链表拆分成若干个长度为 subLength 的子链表（最后一个子链表的长度可以小于 subLength），
         *     按照每两个子链表一组进行合并，合并后即可得到若干个长度为 subLength×2 的有序子链表（最后一
         *     个子链表的长度可以小于 subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做
         *     法。
         *   3.将 subLength 的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大
         *     于或等于 length，整个链表排序完毕。
         *
         * 如何保证每次合并之后得到的子链表都是有序的呢？可以通过数学归纳法证明。
         *   1.初始时 subLength=1，每个长度为 1 的子链表都是有序的。
         *   2.如果每个长度为 subLength 的子链表已经有序，合并两个长度为 subLength 的有序子链表，得到长度为
         *     subLength×2 的子链表，一定也是有序的。
         *   3.当最后一个子链表的长度小于 subLength 时，该子链表也是有序的，合并两个有序子链表之后得到的子
         *     链表一定也是有序的。
         *
         * 因此可以保证最后得到的链表是有序的。
         */
        if (head == null) {
            return null;
        }
        // 1.求得链表的长度 length
        int length = 0;
        ListNode node = head;
        while (node != null) {
            ++length;
            node = node.next;
        }
        // 2.引入哑节点 dummyHead
        ListNode dummyHead = new ListNode(0, head);
        // 3.每次将链表拆分成若干个长度为 subLength 的子链表，按照每两个子链表一组进行合并
        for (int subLength = 1; subLength < length; subLength <<= 1) {
            // prev 记录已排序链表的结尾位置
            ListNode prev = dummyHead;
            // curr 记录待拆分链表的开头位置
            ListNode curr = dummyHead.next;
            // 循环结束的条件是链表已拆分完
            while (curr != null) {
                // 3.1.拆分第一个子链表
                ListNode head1 = curr;
                for (int i = 1; i < subLength && curr.next != null; ++i) {
                    curr = curr.next;
                }
                // 3.2.拆分第二个子链表
                ListNode head2 = curr.next;
                // 切断第一个子链表
                curr.next = null;
                curr = head2;
                for (int i = 1; i < subLength && curr != null && curr.next != null; ++i) {
                    curr = curr.next;
                }
                ListNode next = null;
                if (curr != null) {
                    next = curr.next;
                    // 切断第二个子链表
                    curr.next = null;
                }
                // 3.3.合并两个子链表，并拼接到已排序链表中
                prev.next = merge(head1, head2);
                while (prev.next != null) {
                    prev = prev.next;
                }
                curr = next;
            }
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        System.out.println(ListNode.toString(new Solution().sortList1(ListNode.initLinkedList("[4,2,1,3]"))));
        System.out.println(ListNode.toString(new Solution().sortList2(ListNode.initLinkedList("[-1,5,3,4,0]"))));
    }
}
