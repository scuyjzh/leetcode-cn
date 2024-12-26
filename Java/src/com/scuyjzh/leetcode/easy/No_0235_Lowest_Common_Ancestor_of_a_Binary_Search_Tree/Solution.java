package com.scuyjzh.leetcode.easy.No_0235_Lowest_Common_Ancestor_of_a_Binary_Search_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 235. 二叉搜索树的最近公共祖先
 *
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近
 * 公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。”
 */
class Solution {
    /**
     * 方法一：两次遍历
     */
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 注意到题目中给出的是一棵「二叉搜索树」，因此可以快速地找出树中的某个节点以及从根节点到该节
         * 点的路径，例如需要找到节点 p：
         *   • 从根节点开始遍历；
         *   • 如果当前节点就是 p，那么成功地找到了节点；
         *   • 如果当前节点的值大于 p 的值，说明 p 应该在当前节点的左子树，因此将当前节点移动到它的左子节
         *     点；
         *   • 如果当前节点的值小于 p 的值，说明 p 应该在当前节点的右子树，因此将当前节点移动到它的右子节
         *     点。
         *
         * 对于节点 q 同理。在寻找节点的过程中，可以顺便记录经过的节点，这样就得到了从根节点到被寻找节
         * 点的路径。
         *
         * 当分别得到了从根节点到 p 和 q 的路径之后，就可以很方便地找到它们的最近公共祖先了。显然，
         * p 和 q 的最近公共祖先就是从根节点到它们路径上的「分岔点」，也就是最后一个相同的节点。因此，如果
         * 设从根节点到 p 的路径为数组 pathP，从根节点到 q 的路径为数组 pathQ，那么只要找出最大的编号 i
         * ，其满足
         *         pathP[i] = pathQ[i]
         * 那么对应的节点就是「分岔点」，即 p 和 q 的最近公共祖先就是 pathP[i]（或 pathQ[i]）。
         */
        List<TreeNode> pathP = getPath(root, p);
        List<TreeNode> pathQ = getPath(root, q);
        TreeNode ancestor = null;
        // p 和 q 的最近公共祖先就是从根节点到它们路径上的「分岔点」，也就是最后一个相同的节点
        for (int i = 0; i < pathP.size() && i < pathQ.size(); ++i) {
            // 找出最大的编号 i 满足 path_p[i] == path_q[i]
            if (pathP.get(i) == pathQ.get(i)) {
                ancestor = pathP.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode target) {
        List<TreeNode> path = new LinkedList<>();
        // 从根节点开始遍历
        TreeNode node = root;
        // 如果当前节点就是 p，那么成功地找到了节点
        while (node.val != target.val) {
            path.add(node);
            if (target.val < node.val) {
                // 如果当前节点的值大于 p 的值，说明 p 应该在当前节点的左子树，因此将当前节点移动到它的左子节点
                node = node.left;
            } else {
                // 如果当前节点的值小于 p 的值，说明 p 应该在当前节点的右子树，因此将当前节点移动到它的右子节点
                node = node.right;
            }
        }
        path.add(node);
        return path;
    }

    /**
     * 方法二：一次遍历
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        /*
         * 在方法一中，对从根节点开始，通过遍历找出到达节点 p 和 q 的路径，一共需要两次遍历。也可以
         * 考虑将这两个节点放在一起遍历。
         *
         * 整体的遍历过程与方法一中的类似：
         *   • 从根节点开始遍历；
         *   • 如果当前节点的值大于 p 和 q 的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节点移动到它
         *     的左子节点；
         *   • 如果当前节点的值小于 p 和 q 的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节点移动到它
         *     的右子节点；
         *   • 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」。此时，p 和 q 要么在当前
         *     节点的不同的子树中，要么其中一个就是当前节点。
         *
         * 可以发现，如果将这两个节点放在一起遍历，就省去了存储路径需要的空间。
         */
        TreeNode ancestor = root;
        // 从根节点开始遍历
        while (true) {
            if (p.val < ancestor.val && q.val < ancestor.val) {
                // 如果当前节点的值大于 p 和 q 的值，说明 p 和 q 应该在当前节点的左子树，因此将当前节点移动到它的左子节点
                ancestor = ancestor.left;
            } else if (p.val > ancestor.val && q.val > ancestor.val) {
                // 如果当前节点的值小于 p 和 q 的值，说明 p 和 q 应该在当前节点的右子树，因此将当前节点移动到它的右子节点
                ancestor = ancestor.right;
            } else {
                // 如果当前节点的值不满足上述两条要求，那么说明当前节点就是「分岔点」
                // 此时，p 和 q 要么在当前节点的不同的子树中，要么其中一个就是当前节点
                break;
            }
        }
        return ancestor;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.initBinaryTree("[6,2,8,0,4,7,9,null,null,3,5]");
        System.out.println(new Solution().lowestCommonAncestor1(root, new TreeNode(2), new TreeNode(8)).val);
        System.out.println(new Solution().lowestCommonAncestor2(root, new TreeNode(2), new TreeNode(4)).val);
    }
}
