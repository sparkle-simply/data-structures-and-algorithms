package com.sparklesimply.tree;

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

    /**
     * Problem statement: A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
     * The path sum of a path is the sum of the node's values in the path.
     * Given the root of a binary tree, return the maximum path sum of any non-empty path.
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        int[] result = new int[1];
        result[0] = Integer.MIN_VALUE;
        maxPathSumUtil(root, result);
        return result[0];
    }
    public int maxPathSumUtil(TreeNode root, int[] result) {
        if(root == null) {
            return 0;
        }
        int leftSum = maxPathSumUtil(root.left, result);
        int rightSum = maxPathSumUtil(root.right, result);
        // calculating max path sum with curr node or combining current node to best child path sum
        int maxSingle = Math.max(root.data, Math.max(leftSum, rightSum) + root.data);
        // calculating max path sum with combining curr node to best child path or curr node to both child path
        int maxTop = Math.max(maxSingle, leftSum + rightSum + root.data);
        result[0] = Math.max(result[0], maxTop);
        return maxSingle;
    }

    /**
     * Problem statement: Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * A leaf is a node with no children.
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null)
            return false;
        return hasPathSumUtil(root, 0, targetSum);
    }
    private boolean hasPathSumUtil(TreeNode root, int currSum, int targetSum) {
        if(root == null)
            return false;
        currSum += root.data;
        if(Utility.isLeaf(root) && currSum == targetSum)
            return true;
        boolean l = hasPathSumUtil(root.left, currSum, targetSum);
        boolean r = hasPathSumUtil(root.right, currSum, targetSum);
        return l || r;
    }

    /**
     * Problem statement: You are given the root of a binary tree containing digits from 0 to 9 only.
     * Each root-to-leaf path in the tree represents a number.
     * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
     * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
     * A leaf node is a node with no children.
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if(root == null)
            return 0;
        return sumNumbersUtil(root, 0);
    }
    private int sumNumbersUtil(TreeNode root, int currSum) {
        if(root == null)
            return 0;
        currSum = currSum*10 + root.data;
        if(root.left == null && root.right == null)
            return currSum;
        return sumNumbersUtil(root.left, currSum) + sumNumbersUtil(root.right, currSum);
    }

}
