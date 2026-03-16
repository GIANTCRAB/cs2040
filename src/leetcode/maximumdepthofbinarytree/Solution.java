package leetcode.maximumdepthofbinarytree;

class Solution {
    public int maxDepth(TreeNode root) {
        // I am immediately thinking of using recursion
        // This is because it seems like a simple binary tree transversal
        // maybe is store the current depth within the function
        return genMaxDepth(root, 0);
    }

    private int genMaxDepth(TreeNode root, int depth) {
        if(root == null) {
            return depth;
        }
        return Math.max(genMaxDepth(root.left, depth + 1), genMaxDepth(root.right, depth +1));
    }
}
