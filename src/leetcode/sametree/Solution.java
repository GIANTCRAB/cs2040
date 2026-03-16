package leetcode.sametree;

class Solution {
    // Seems like a relatively simple recursion question
    // This is because they're binary trees
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if(p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
