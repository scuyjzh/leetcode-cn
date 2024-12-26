package com.scuyjzh.leetcode.medium.No_0437_Path_Sum_III;

import com.scuyjzh.leetcode.structure.TreeNode;

import java.util.*;

/**
 * 437. 路径总和 III
 *
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里
 * 节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须
 * 是向下的（只能从父节点到子节点）。
 */
class Solution {
    /**
     * 方法一：深度优先搜索
     */
    public int pathSum1(TreeNode root, int sum) {
        // array 数组存储某一次递归时所遍历节点的结果值，二叉树的节点个数的范围是 [0,1000]
        int[] array = new int[1000];
        // p 表示当前节点的位置，0 表示根节点
        int p = 0;
        return dfs(root, sum, array, p);
    }

    private int dfs(TreeNode root, int sum, int[] array, int p) {
        if (root == null) {
            return 0;
        }
        array[p] = root.val;
        int curSum = 0;
        int n = 0;
        for (int i = p; i >= 0; --i) {
            curSum += array[i];
            if (curSum == sum) {
                ++n;
                // 节点存放值可能为 0 或者负数，因此不能提前 break
            }
        }
        int left = dfs(root.left, sum, array, p + 1);
        int right = dfs(root.right, sum, array, p + 1);
        return n + left + right;
    }

    /**
     * 方法二: 前缀和
     */
    public int pathSum2(TreeNode root, int targetSum) {
        /*
         * 仔细思考一下，解法一中应该存在许多重复计算。定义节点的前缀和为：由根节点到当前节点的路
         * 径上所有节点的和。利用先序遍历二叉树，记录下根节点 root 到当前节点 p 的路径上除当前节点以外所
         * 有节点的前缀和，在已保存的路径前缀和中查找是否存在前缀和刚好等于当前节点到根节点的前缀和 curr
         * 减去 targetSum。
         *   • 对于空路径也需要保存预先处理一下，此时因为空路径不经过任何节点，因此它的前缀和为 0。
         *   • 假设根节点为 root，当前刚好访问节点 node，则此时从根节点 root 到节点 node 的路径（无重复
         *     节点）刚好为 root→p_1→p_2→...→p_k→node，此时可以已经保存了节点 p_1,p_2,p_3,...,p_k 的
         *     前缀和，并且计算出了节点 node 的前缀和。
         *   • 假设当前从根节点 root 到节点 node 的前缀和为 curr，则此时在已保存的前缀和查找是否存在前
         *     缀和刚好等于 curr−targetSum。假设从根节点 root 到节点 node 的路径中存在节点 p_i 到根节点 root
         *     的前缀和为 curr−targetSum，则节点 p_{i+1} 到 node 的路径上所有节点的和一定为 targetSum。
         *   • 利用深度搜索遍历树，当退出当前节点时，需要及时更新已经保存的前缀和。
         */
        HashMap<Long, Integer> prefix = new HashMap<>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    private int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ans;
        curr += root.val;

        ans = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ans += dfs(root.left, prefix, curr, targetSum);
        ans += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.get(curr) - 1);

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().pathSum1(TreeNode.initBinaryTree("[10,5,-3,3,2,null,11,3,-2,null,1]"), 8));
        System.out.println(new Solution().pathSum2(TreeNode.initBinaryTree("[5,4,8,11,null,13,4,7,2,null,null,5,1]"), 22));
    }
}
