package com.scuyjzh.leetcode.medium.No_0337_House_Robber_III;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 337. 打家劫舍 III
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的
 * 地区。这个地区只有一个入口，称之为“根”。 除了“根”之外，每栋房子
 * 有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个
 * 地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同
 * 一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 */
class Solution {
    Map<TreeNode, Integer> f = new HashMap<>();
    Map<TreeNode, Integer> g = new HashMap<>();

    /**
     * 方法一：动态规划
     */
    private int rob1(TreeNode root) {
        /*
         * 简化一下这个问题：一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），问
         * 在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。
         *
         * 可以用 f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；g(o) 表示不选择 o
         * 节点的情况下，o 节点的子树上被选择的节点的最大权值和；l 和 r 代表 o 的左右孩子。
         *   • 当 o 被选中时，o 的左右孩子都不能被选中，故 o 被选中情况下子树上被选中点的最大权值和为 l 和 r
         *     不被选中的最大权值和相加，即 f(o)=g(l)+g(r)。
         *   • 当 o 不被选中时，o 的左右孩子可以被选中，也可以不被选中。对于 o 的某个具体的孩子 x，它对 o 的
         *     贡献是 x 被选中和不被选中情况下权值和的较大值。故 g(o)=max{f(l),g(l)}+max{f(r),g(r)}。
         *
         * 至此，可以用哈希表来存 f 和 g 的函数值，用深度优先搜索的办法后序遍历这棵二叉树，就可以得
         * 到每一个节点的 f 和 g。根节点的 f 和 g 的最大值就是要找的答案。
         */
        postorder1(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    private void postorder1(TreeNode node) {
        if (node == null) {
            return;
        }
        postorder1(node.left);
        postorder1(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }

    /**
     * 方法二：动态规划 + 空间优化
     */
    public int rob2(TreeNode root) {
        /*
         * 假设二叉树的节点个数为 n。
         *
         * 可以看出，以上的算法对二叉树做了一次后序遍历，时间复杂度是 O(n)；由于递归会使用到栈空间，空
         * 间代价是 O(n)，哈希表的空间代价也是 O(n)，故空间复杂度也是 O(n)。
         *
         * 可以做一个小小的优化，发现无论是 f(o) 还是 g(o)，他们最终的值只和 f(l)、g(l)、f(r)、g(r) 有
         * 关，所以对于每个节点，只关心它的孩子节点们的 f 和 g 是多少。可以设计一个结构，表示某个节
         * 点的 f 和 g 值，在每次递归返回的时候，都把这个点对应的 f 和 g 返回给上一级调用，这样可以省去哈希表
         * 的空间。
         */
        int[] rootStatus = postorder2(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    private int[] postorder2(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] left = postorder2(node.left);
        int[] right = postorder2(node.right);
        int selected = node.val + left[1] + right[1];
        int notSelected = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{selected, notSelected};
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rob1(TreeNode.initBinaryTree("[3,2,3,null,3,null,1]")));
        System.out.println(new Solution().rob2(TreeNode.initBinaryTree("[3,4,5,1,3,null,1]")));
    }
}
