package com.sparklesimply.trees;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class Utility {

    public static boolean isLeaf(TreeNode root) {
        if (root == null)
            return false;
        else
            return root.left == null && root.right == null;
    }

    public static int calculateSum(TreeNode root) {
        if(root == null)
            return 0;
        if(Utility.isLeaf(root))
            return root.data;
        return calculateSum(root.left) + root.data + calculateSum(root.right);
    }
}
