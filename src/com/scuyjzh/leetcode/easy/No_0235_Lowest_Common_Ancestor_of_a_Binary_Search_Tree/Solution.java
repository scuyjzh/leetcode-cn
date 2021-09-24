package com.scuyjzh.leetcode.easy.No_0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 235. 二叉搜索树的最近公共祖先
 * <p>
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 */
class Solution {
    /**
     * 方法一：两次遍历
     * 时间复杂度：O(N)，其中 N 是给定的二叉搜索树中的节点个数。上述代码需要的时间与节点 p 和 q 在树中的深度线性相关，而在最坏的情况下，树呈现链式结构，p 和 q 一个是树的唯一叶子结点，一个是该叶子结点的父节点，此时时间复杂度为 O(N)。
     * 空间复杂度：O(N)，需要存储根节点到 p 和 q 的路径。和上面的分析方法相同，在最坏的情况下，路径的长度为 O(N)，因此需要 O(N) 的空间。
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 思路与算法：
         * 注意到题目中给出的是一棵「二叉搜索树」，因此可以快速地找出树中的某个节点以及从根节点到该
         * 节点的路径，例如需要找到节点 p：
         *   • 从根节点开始遍历；
         *   • 如果当前节点就是 p，那么成功地找到了节点；
         *   • 如果当前节点的值大于 p 的值，说明 p 应该在当前节点的左子树，因此将当前节点移动到它
         *     的左子节点；
         *   • 如果当前节点的值小于 p 的值，说明 p 应该在当前节点的右子树，因此将当前节点移动到它
         *     的右子节点。
         *
         * 对于节点 q 同理。在寻找节点的过程中，可以顺便记录经过的节点，这样就得到了从根节点到被寻找
         * 节点的路径。
         *
         * 当分别得到了从根节点到 p 和 q 的路径之后，就可以很方便地找到它们的最近公共祖先了。显然，
         * p 和 q 的最近公共祖先就是从根节点到它们路径上的「分岔点」，也就是最后一个相同的节点。因
         * 此，如果设从根节点到 p 的路径为数组 path_p，从根节点到 q 的路径为数组 path_q，那么只
         * 要找出最大的编号 i，其满足
         *     path_p[i] = path_q[i]
         *
         * 那么对应的节点就是「分岔点」，即 p 和 q 的最近公共祖先就是 path_p[i]（或 path_q[i]）。
         */
        List<TreeNode> path_p = getPath(root, p);
        List<TreeNode> path_q = getPath(root, q);
        TreeNode ancestor = null;
        // p 和 q 的最近公共祖先就是从根结点到它们路径上的「分岔点」，也就是最后一个相同的结点
        for (int i = 0; i < path_p.size() && i < path_q.size(); ++i) {
            // 找出最大的编号 i 满足 path_p[i] == path_q[i]
            if (path_p.get(i) == path_q.get(i)) {
                ancestor = path_p.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new LinkedList<>();
        // 从根结点开始遍历
        TreeNode node = root;
        // 如果当前结点就是 p，那么成功地找到了结点
        while (node.val != target.val) {
            path.add(node);
            if (target.val < node.val) {
                // 如果当前结点的值大于 p 的值，说明 p 应该在当前结点的左子树，因此将当前结点移动到它的左子结点
                node = node.left;
            } else {
                // 如果当前结点的值小于 p 的值，说明 p 应该在当前结点的右子树，因此将当前结点移动到它的右子结点
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 方法二：一次遍历
     * 时间复杂度：O(N)，其中 N 是给定的二叉搜索树中的节点个数。分析思路与方法一相同。
     * 空间复杂度：O(1)。
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 思路与算法：
         * 在方法一中，对从根节点开始，通过遍历找出到达节点 p 和 q 的路径，一共需要两次遍历。也可以
         * 考虑将这两个节点放在一起遍历。
         *
         * 整体的遍历过程与方法一中的类似：
         *   • 从根节点开始遍历；
         *   • 如果当前节点的值大于 p 和 q 的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节
         *     点移动到它的左子节点；
         *   • 如果当前节点的值小于 p 和 q 的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节
         *     点移动到它的右子节点；
         *   • 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，p 和 q 要
         *     么在当前节点的不同的子树中，要么其中一个就是当前节点。
         *
         * 可以发现，如果将这两个节点放在一起遍历，就省去了存储路径需要的空间。
         */
        TreeNode ancestor = root;
        // 从根节点开始遍历
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                // 如果当前结点的值大于 p 和 q 的值，说明 p 和 q 应该在当前结点的左子树，因此将当前结点移动到它的左子结点
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                // 如果当前结点的值小于 p 和 q 的值，说明 p 和 q 应该在当前结点的右子树，因此将当前结点移动到它的右子结点
                ancestor = ancestor.right;
            } else {
                // 如果当前结点的值不满足上述两条要求，那么说明当前结点就是「分岔点」
                // 此时，p 和 q 要么在当前结点的不同的子树中，要么其中一个就是当前结点
                break;
            }
        }
        return ancestor;
    }

    /**
     * 方法三：递归
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 分析思路与方法二相同。
         */
        return helper(root, p, q);
    }

    private TreeNode helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return helper(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return helper(root.left, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[6,2,8,0,4,7,9,null,null,3,5]");
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(8);
        System.out.println(solution.lowestCommonAncestor1(root, p, q).val);
        System.out.println(solution.lowestCommonAncestor2(root, p, q).val);
        System.out.println(solution.lowestCommonAncestor3(root, p, q).val);
    }
}