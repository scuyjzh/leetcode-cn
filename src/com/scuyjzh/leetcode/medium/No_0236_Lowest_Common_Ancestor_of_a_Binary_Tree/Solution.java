package com.scuyjzh.leetcode.medium.No_0236_Lowest_Common_Ancestor_of_a_Binary_Tree;

import com.scuyjzh.leetcode.structure.TreeNode;

class Solution {
    private TreeNode ans;

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        /*
            思路：
            f(x) 表示 x 结点的子树中是否包含 p 结点或 q 结点，如果包含为 true，否则为 false
            那么符合条件的最近公共祖先 x 一定满足如下条件：
            (f(lson) && f(rson)) || ((x == p || x == q) && (f(lson) || f(rson)))
            其中 lson 和 rson 分别代表 x 结点的左孩子和右孩子
            1.f(lson) && f(rson) 说明左子树和右子树均包含 p 结点或 q 结点，如果左子树包含的是 p 结点，那么右子树只能包含 q 结点，
              反之亦然，因为 p 结点和 q 结点都是不同且唯一的结点，因此如果满足这个判断条件即可说明 x 就是我们要找的最近公共祖先
            2.第二个判断条件考虑了 x 恰好是 p 结点或 q 结点且它的左子树或右子树有一个包含了另一个结点的情况，
              因此如果满足这个判断条件亦可说明 x 就是我们要找的最近公共祖先
        */
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans = root;
        }
        /*
            由于是自底向上从叶子结点开始更新，所以在所有满足条件的公共祖先中一定是深度最大的祖先先被访问到，
            且由于 f(x) 本身的定义很巧妙，在找到最近公共祖先 x 以后，f(x) 按定义被设置为 true，
            即假定了这个子树中只有一个 p 结点或 q 结点，因此其他公共祖先不会再被判断为符合条件
        */
        return lson || rson || (root.val == p.val || root.val == q.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode root = TreeNode.initBinaryTree("[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]");
        TreeNode p = new TreeNode(9);
        TreeNode q = new TreeNode(11);
        System.out.println(solution.lowestCommonAncestor(root, p, q).val);
    }
}
