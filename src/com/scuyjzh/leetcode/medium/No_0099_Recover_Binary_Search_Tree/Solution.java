package com.scuyjzh.leetcode.medium.No_0099_Recover_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 99. 恢复二叉搜索树
 *
 * 给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其
 * 结构的情况下，恢复这棵树。
 * 进阶：使用 O(n) 空间复杂度的解法很容易实现。你能想出一个只使用 O(1) 空间
 * 的解决方案吗？
 */
class Solution {
    /**
     * 方法一：显式中序遍历
     */
    public void recoverTree1(TreeNode root) {
        /*
         * 需要考虑两个节点被错误地交换后对原二叉搜索树造成了什么影响。对于二叉搜索树，知道如果对
         * 其进行中序遍历，得到的值序列是递增有序的，而如果错误地交换了两个节点，等价于在这个值序列中
         * 交换了两个值，破坏了值序列的递增性。
         *
         * 来看下如果在一个递增的序列中交换两个值会造成什么影响。假设有一个递增序列 a=[1,2,3,4,5,6,7]
         * 。如果交换两个不相邻的数字，例如 2 和 6，原序列变成了 a=[1,6,3,4,5,2,7]，那么显然序列中有两
         * 个位置不满足 a_i<a_{i+1}，在这个序列中体现为 6>3，5>2，因此只要找到这两个位置，即可找到被错
         * 误交换的两个节点。如果交换两个相邻的数字，例如 2 和 3，此时交换后的序列只有一个位置不满足
         * a_i<a_{i+1}。因此整个值序列中不满足条件的位置或者有两个，或者有一个。
         *
         * 至此，解题方法已经呼之欲出了：
         *   1.找到二叉搜索树中序遍历得到值序列的不满足条件的位置。
         *   2.如果有两个，记为 i 和 j（i<j 且 a_i>a_{i+1} && a_j>a_{j+1})，那么对应被错误交换的节点即为 a_i
         *     对应的节点和 a_{j+1} 对应的节点，分别记为 x 和 y。
         *   3.如果有一个，记为 i，那么对应被错误交换的节点即为 a_i 对应的节点和 a_{i+1} 对应的节点，分
         *     别记为 x 和 y。
         *   4.交换 x 和 y 两个节点即可。
         *
         * 实现部分，本方法开辟一个新数组 nums 来记录中序遍历得到的值序列，然后线性遍历找到两个位置 i 和 j
         * ，并重新遍历原二叉搜索树修改对应节点的值完成修复。
         */
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        int[] swapped = findTwoSwapped(nums);
        recover(root, 2, swapped[0], swapped[1]);
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) {
            return;
        }
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }

    private int[] findTwoSwapped(List<Integer> nums) {
        int n = nums.size();
        int index1 = -1, index2 = -1;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1) < nums.get(i)) {
                index2 = i + 1;
                if (index1 == -1) {
                    index1 = i;
                } else {
                    break;
                }
            }
        }
        int x = nums.get(index1), y = nums.get(index2);
        return new int[]{x, y};
    }

    private void recover(TreeNode root, int count, int x, int y) {
        if (root != null) {
            if (root.val == x || root.val == y) {
                root.val = root.val == x ? y : x;
                if (--count == 0) {
                    return;
                }
            }
            recover(root.right, count, x, y);
            recover(root.left, count, x, y);
        }
    }

    /**
     * 方法二：隐式中序遍历
     */
    public void recoverTree2(TreeNode root) {
        /*
         * 方法一是显式地将中序遍历的值序列保存在一个 nums 数组中，然后再去寻找被错误交换的节点，但也
         * 可以隐式地在中序遍历的过程就找到被错误交换的节点 x 和 y。
         *
         * 具体来说，由于只关心中序遍历的值序列中每个相邻的位置的大小关系是否满足条件，且错误交换后最
         * 多两个位置不满足条件，因此在中序遍历的过程只需要维护当前中序遍历到的最后一个节点 pred，然后
         * 在遍历到下一个节点的时候，看两个节点的值是否满足前者小于后者即可，如果不满足说明找到了一个交换
         * 的节点，且在找到两次以后就可以终止遍历。
         *
         * 这样就可以在中序遍历中直接找到被错误交换的两个节点 x 和 y，不用显式建立 nums 数组。
         *
         * 中序遍历的实现有迭代和递归两种等价的写法，在本方法中提供迭代实现的写法。使用迭代实现中序遍历需
         * 要手动维护栈。
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode x = null, y = null, pre = null;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && cur.val < pre.val) {
                y = cur;
                if (x == null) {
                    x = pre;
                } else {
                    break;
                }
            }
            pre = cur;
            cur = cur.right;
        }
        swap(x, y);
    }

    /**
     * 方法三：Morris 中序遍历
     */
    public void recoverTree3(TreeNode root) {
        /*
         * 方法二中不再显示的用数组存储中序遍历的值序列，但是会发现仍需要 O(H) 的栈空间，无法
         * 满足题目的进阶要求，那么该怎么办呢？这里向大家介绍一种不同于平常递归或迭代的遍历二叉树的方法：
         * Morris 遍历算法，该算法能将非递归的中序遍历空间复杂度降为 O(1)。
         *
         * Morris 遍历算法整体步骤如下（假设当前遍历到的节点为 cur）：
         *   1.如果 cur 无左孩子，则访问 cur 的右孩子，即 cur=cur.right。
         *   2.如果 cur 有左孩子，则找到 cur 左子树上最右的节点（即左子树中序遍历的最后一个节点，cur 在中序遍历
         *     中的前驱节点），记为 predecessor。根据 predecessor 的右孩子是否为空，进行如下操作。
         *       • 如果 predecessor 的右孩子为空，则将其右孩子指向 cur，然后访问 cur 的左孩子，即 cur=cur.left。
         *       • 如果 predecessor 的右孩子不为空，则此时其右孩子指向 cur，说明已经遍历完 cur 的左子树，
         *         将 predecessor 的右孩子置空，然后访问 cur 的右孩子，即 cur=cur.right。
         *   3.重复上述操作，直至访问完整棵树。
         *
         * 其实整个过程我们就多做一步：将当前节点左子树中最右边的节点指向它，这样在左子树遍历完成后我们通
         * 过这个指向走回了 xx，且能再通过这个知晓我们已经遍历完成了左子树，而不用再通过栈来维护，省去了栈
         * 的空间复杂度。
         *
         * 了解完这个算法以后，其他地方与方法二并无不同，同样也是维护一个 pre 变量去比较即可，具体实现
         * 可以看下面的代码，这里不再赘述。
         */
        TreeNode x = null, y = null, pre = null;
        TreeNode cur = root, predecessor;
        while (cur != null) {
            if (cur.left != null) {
                // predecessor 节点就是当前 cur 节点向左走一步，然后一直向右走至无法走为止
                predecessor = cur.left;
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }

                // 让 predecessor 的右指针指向 cur，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = cur;
                    cur = cur.left;
                }
                // 说明左子树已经访问完了，需要断开链接
                else {
                    if (pre != null && cur.val < pre.val) {
                        y = cur;
                        if (x == null) {
                            x = pre;
                        } else {
                            // predecessor 的右孩子还没有置空，所以这里无法直接 break
                        }
                    }
                    pre = cur;

                    predecessor.right = null;
                    cur = cur.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                if (pre != null && cur.val < pre.val) {
                    y = cur;
                    if (x == null) {
                        x = pre;
                    } else {
                        // predecessor 的右孩子还没有置空，所以这里无法直接 break
                    }
                }
                pre = cur;
                cur = cur.right;
            }
        }
        swap(x, y);
    }

    private void swap(TreeNode x, TreeNode y) {
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
    }

    public static void main(String[] args) {
        new Solution().recoverTree1(TreeNode.initBinaryTree("[1,3,null,null,2]"));
        new Solution().recoverTree2(TreeNode.initBinaryTree("[3,1,4,null,null,2]"));
        new Solution().recoverTree3(TreeNode.initBinaryTree("[3,1,4,null,null,2]"));
    }
}
