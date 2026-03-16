package leetcode.invertbinarytree;

class Solution {
    // im thinking recursion
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode originalLeft = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(originalLeft);
        return root;
    }
}
