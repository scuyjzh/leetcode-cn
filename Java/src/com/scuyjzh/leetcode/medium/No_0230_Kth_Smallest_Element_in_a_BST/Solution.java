package com.scuyjzh.leetcode.medium.No_0230_Kth_Smallest_Element_in_a_BST;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 230. 二叉搜索树中第K小的元素
 *
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法
 * 查找其中第 k 个最小元素（从 1 开始计数）。
 */
class Solution {
    /**
     * 方法一：中序遍历
     */
    public int kthSmallest1(TreeNode root, int k) {
        /*
         * 二叉搜索树具有如下性质：
         *   • 节点的左子树只包含小于当前节点的数。
         *   • 节点的右子树只包含大于当前节点的数。
         *   • 所有左子树和右子树自身必须也是二叉搜索树。
         *
         * 二叉树的中序遍历即按照访问左子树——根节点——右子树的方式遍历二叉树；在访问其左子树和右子树
         * 时，也按照同样的方式遍历；直到遍历完整棵树。
         *
         * 因为二叉搜索树和中序遍历的性质，所以二叉搜索树的中序遍历是按照键增加的顺序进行的。于是，可
         * 以通过中序遍历找到第 k 个最小元素。
         *
         * 「二叉树的中序遍历」可以参考「94. 二叉树的中序遍历的官方题解」，具体地，使用迭代方法，这样
         * 可以在找到答案后停止，不需要遍历整棵树。
         */
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            cur = cur.right;
        }
        return cur.val;
    }

    class MyBST {
        TreeNode root;
        Map<TreeNode, Integer> nodeNum;

        public MyBST(TreeNode root) {
            this.root = root;
            this.nodeNum = new HashMap<>();
            countNodeNum(root);
        }

        // 返回二叉搜索树中第 k 小的元素
        public int kthSmallest(int k) {
            TreeNode node = root;
            while (node != null) {
                int left = nodeNum.getOrDefault(node.left, 0);
                if (left < k - 1) {
                    node = node.right;
                    k -= left + 1;
                } else if (left == k - 1) {
                    break;
                } else {
                    node = node.left;
                }
            }
            return node.val;
        }

        // 统计以 node 为根节点的子树的节点数
        private int countNodeNum(TreeNode node) {
            if (node == null) {
                return 0;
            }
            nodeNum.put(node, 1 + countNodeNum(node.left) + countNodeNum(node.right));
            return nodeNum.get(node);
        }
    }

    /**
     * 方法二：记录子树的节点数
     */
    public int kthSmallest2(TreeNode root, int k) {
        /*
         * 如果需要频繁地查找第 k 小的值，将如何优化算法？
         *
         * 在方法一中，之所以需要中序遍历前 k 个元素，是因为不知道子树的节点数量，不得不通过遍历子
         * 树的方式来获知。
         *
         * 因此，可以记录下以每个节点为根节点的子树的节点数，并在查找第 k 小的值时，使用如下方法搜索：
         *   • 令 node 等于根节点，开始搜索。
         *   • 对当前节点 node 进行如下操作：
         *       ○ 如果 node 的左子树的节点数 left 小于 k−1，则第 k 小的元素一定在 node 的右子树中，令 node
         *         等于其右子节点，k 等于 k−left−1，并继续搜索；
         *       ○ 如果 node 的左子树的节点数 left 等于 k−1，则第 k 小的元素即为 node，结束搜索并返回 node
         *         即可；
         *       ○ 如果 node 的左子树的节点数 left 大于 k−1，则第 k 小的元素一定在 node 的左子树中，令 node
         *         等于其左子节点，并继续搜索。
         *
         * 在实现中，既可以将以每个节点为根节点的子树的节点数存储在节点中，也可以将其记录在哈希表中。
         */
        MyBST bst = new MyBST(root);
        return bst.kthSmallest(k);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().kthSmallest1(TreeNode.initBinaryTree("[3,1,4,null,2]"), 1));
        System.out.println(new Solution().kthSmallest2(TreeNode.initBinaryTree("[3,1,4,null,2]"), 1));
    }
}
