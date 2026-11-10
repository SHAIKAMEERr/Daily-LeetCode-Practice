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
    
    // Pointers to track the out-of-order nodes and the previous node visited
    private TreeNode first = null;
    
    private TreeNode second = null;
    
    private TreeNode prev = null;

    public void recoverTree(TreeNode root) {
      
        // Step 1: Perform in-order traversal to identify the two swapped nodes
        traverse(root);
        
        // Step 2: Swap the values of the two mismatched nodes to restore the BST
        int temp = first.val;
      
        first.val = second.val;
      
        second.val = temp;
    }

    private void traverse(TreeNode root) {
      
        if (root == null) {
      
            return;
        }

        // Traverse the left subtree
        traverse(root.left);

        // Process the current node: check for a BST rule violation
        if (prev != null && prev.val > root.val) {
      
            // First violation: 'first' node is the larger one (prev)
            if (first == null) {
      
                first = prev;
            }

            second = root;
        }
        
        // Update the previous node pointer before moving right
        prev = root;

        // Traverse the right subtree
        traverse(root.right);
    }
}
