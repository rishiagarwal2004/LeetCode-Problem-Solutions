/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int count = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return count;
    }

    // Returns an int array where index 0 is the sum, and index 1 is the node count
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[] {0, 0};
        }

        // Get sum and count from left and right subtrees
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int currentSum = root.val + left[0] + right[0];
        int currentCount = 1 + left[1] + right[1];

        // Check if current node value equals the average of its subtree
        if (currentSum / currentCount == root.val) {
            count++;
        }

        // Return the sum and count of this subtree to the parent
        return new int[] {currentSum, currentCount};
    }
}