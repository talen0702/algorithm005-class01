//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
//
//
//
//
//
// 示例 1:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//
//
// 示例 2:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
//
//
//
//
// 说明:
//
//
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。
//
// Related Topics 树



//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private TreeNode mAncestor;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        /*isFind(root, p, q);
        return mAncestor;*/
        return lowestCommonAncestorByLyParent(root, p, q);
    }

    private boolean isFind (TreeNode node, TreeNode p, TreeNode q) {
        if (node==null) {
            return false;
        }
        int left = isFind(node.left, p, q)? 1 : 0; //左子树是否找到p或q
        int right = isFind(node.right, p, q)? 1 : 0; //右子树是否找到p或q
        int mid = (node==p || node==q)? 1 : 0;
        if (mid+left+right>=2) {
            mAncestor = node;
            return true;
        }
        return (left+mid+right>0);
    }

    private TreeNode lowestCommonAncestorByLyParent (TreeNode node, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        parentMap.put(node, null);
        stack.push(node);
        while (!parentMap.containsKey(p) || !parentMap.containsKey(q)) {
            node = stack.pop();
            if (node.left!=null) {
                parentMap.put(node.left, node);
                stack.push(node.left);
            }
            if (node.right!=null) {
                parentMap.put(node.right, node);
                stack.push(node.right);
            }
        }
        Set<TreeNode> pParentSet = new HashSet<TreeNode>();
        while (p!=null) {
            pParentSet.add(p);
            p = parentMap.get(p);
        }
        while (!pParentSet.contains(q)) {
            q = parentMap.get(q);
        }
        return q;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
