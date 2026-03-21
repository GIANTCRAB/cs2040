package leetcode.subtreeofanothertree;

class Solution {
    // First of all, I am thinking this is either a BFS or DFS problem
    // I think I can try out the DFS approach
    // As this problem is trivial, i do not need to construct additional structure for holding data
    // I also need some form of back-tracking and restart
    // this means I need to maintain the start of the subRoot node, perhaps as trueSubRoot
    // If the transversal returns false and if applicable, i'll then retry against the trueSubRoot left/right node
    // otherwise, i will start over against left and right nodes, doing an OR conditional
    // OR conditional is important here has we should understand that this is divergent
    // and its sub nodes from either side could return true or false
    // we only need one true
    // the last true conditional here is that both root and subRoot returns null
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return isSubtree(root, subRoot, subRoot);
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot, TreeNode trueSubRoot) {
        if (root != null && subRoot != null) {
            if(root.val == subRoot.val) {
                boolean result = isSubtree(root.left, subRoot.left, trueSubRoot) && isSubtree(root.right, subRoot.right, trueSubRoot);
                if(result) {
                    return true;
                }
            }
            if(root.val == trueSubRoot.val) {
                boolean result = isSubtree(root.left, trueSubRoot.left, trueSubRoot) && isSubtree(root.right, trueSubRoot.right, trueSubRoot);
                if(result) {
                    return true;
                }
            }
            return isSubtree(root.left, trueSubRoot, trueSubRoot) || isSubtree(root.right, trueSubRoot, trueSubRoot);
        }
        if(root == null && subRoot == null) {
            return true;
        }

        return false;
    }
}
