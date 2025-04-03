package com.sparklesimply.trees;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class SumTreeVariants {

    /**
     * This method checks if the binary tree is sum tree
     * For every node we make two recursive calls to left and right subtrees and within recursive calls calculate the sum of left and right subtrees in constant time
     * With this we are traversing the nodes once
     * Time Complexity: O(n)
     *
     * @param root
     * @return true if binary tree is sum tree
     */
    public boolean isSumTree(TreeNode root) {
        if(root == null)
            return true;
        if(Utility.isLeaf(root))
            return true;
        int leftSum, rightSum;
        if(isSumTree(root.left) && isSumTree(root.right)) {
            if(root.left == null)
                leftSum = 0;
            else if(Utility.isLeaf(root.left))
                leftSum = root.data;
            else
                leftSum = 2*root.data;

            if(root.right == null)
                rightSum = 0;
            else if(Utility.isLeaf(root.right))
                rightSum = root.data;
            else
                rightSum = 2*root.data;

            if(leftSum + rightSum == root.data)
                return true;
            else
                return false;
        }
        return false;
    }

    /**
     * This method checks if the binary tree is sum tree
     * For every node we make two recursive calls and at each node calculate sum of both left and right subtrees
     * Time complexity: O(n^2)
     *
     * @param root
     * @return true if binary tree is sum tree
     */
    public boolean isSumTreeBruteApproach(TreeNode root) {
        if(root == null || Utility.isLeaf(root))
            return true;
        int leftSum = Utility.calculateSum(root.left);
        int rightSum = Utility.calculateSum(root.right);
        if(leftSum + rightSum == root.data && isSumTreeBruteApproach(root.left) && isSumTreeBruteApproach(root.right))
            return true;
        else
            return false;
    }
}
