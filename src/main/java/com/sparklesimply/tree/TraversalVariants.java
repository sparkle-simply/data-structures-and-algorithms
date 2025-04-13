package com.sparklesimply.tree;

import java.util.*;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class TraversalVariants {

    /**
     * This method will return kth smallest element in BST
     * Time Complexity: O(n)
     *
     * @param root root node
     * @param k kth limit
     * @return kth smallest element
     */
    public int kthSmallestElement(TreeNode root, int k) {
        int[] counter = new int[1];
        int[] result = new int[1];
        kthSmallestElementUtil(root, k, counter, result);
        return result[0];
    }
    private void kthSmallestElementUtil(TreeNode root, int k, int[] counter, int[] result) {
        if(root == null)
            return;
        kthSmallestElementUtil(root.left, k, counter, result);
        counter[0]++;
        if(counter[0] == k) {
            result[0] = root.data;
            return;
        }
        kthSmallestElementUtil(root.right, k, counter, result);
    }

    /**
     * This method returns the lowest common ancestor for provided nodes p and q
     * Here, we'll check recursively in left and right subtree and if one value is present in left subtree and other in right subtree then the current root is the lca for p and q nodes
     * If both the provided nodes are in one subtree, continue checking the same subtree
     * Time Complexity: O(n)
     *
     * @param root root node
     * @param p node1
     * @param q node2
     * @return lowest common ancestor for node1 and node2
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return null;
        if(root.data == p.data || root.data == q.data)
            return root;
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);
        if(leftLCA != null && rightLCA != null)
            return root;
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    /**
     * This method will calculate the longest subsequence within binary tree
     * Time Complexity: O(n)
     *
     * @param root root node
     * @return length of longest subsequence
     */
    public int longestIncreasingSubsequenceInBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
        int[] result = new int[1]; // mutable reference (using array) to calculate the subsequence length
        longestIncreasingSubsequenceInBinaryTreeUtil(root, 0, root.data, result);
        return result[0];
    }
    private void longestIncreasingSubsequenceInBinaryTreeUtil(TreeNode root, int currLen, int expected, int[] result) {
        if(root == null)
            return;
        if(root.data == expected)
            currLen++;
        else
            currLen = 1;
        result[0] = Math.max(result[0], currLen);
        longestIncreasingSubsequenceInBinaryTreeUtil(root.left, currLen, root.data+1,result);
        longestIncreasingSubsequenceInBinaryTreeUtil(root.right, currLen, root.data+1,result);
    }

    /**
     * This method corrects the BST by fixing two swapped nodes
     * Time Complexity: O(n)
     *
     * @param root root node
     */
    TreeNode prev=null, first=null, middle=null, last=null;
    public void fixBSTSwapNodes(TreeNode root) {
        if(root == null)
            return;
        fixBSTSwapNodesUtil(root);
        if(first!=null && last!=null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        } else if(first!=null && middle!=null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }
    private void fixBSTSwapNodesUtil(TreeNode root) {
        if ((root == null))
            return;
        fixBSTSwapNodesUtil(root.left);
        if(prev != null && prev.data > root.data) {
            if(first == null) {
                first = prev;
                middle = root;
            } else {
                last = root;
            }
        }
        prev = root;
        fixBSTSwapNodesUtil(root.right);
    }

    /**
     * Problem statement: A forest is represented with a hashmap. This hashmap has this key -> value relationship: child -> parent.
     * Every node has a unique integer element. I needed to find out the largest tree's root node. If there is a tie, return the smallest root.
     * Time complexity: O(m+n), m nodes and n edges
     * @param forest group of trees
     * @return largest tree root node
     */
    public int largestTreeRootNode(HashMap<Integer, Integer> forest) {
        // getting all child nodes
        Set<Integer> childNodes = new HashSet<>(forest.keySet());
        // getting all nodes
        Set<Integer> allNodes = new HashSet<>(forest.values());
        // extracting root nodes from all nodes
        Set<Integer> rootNodes = new HashSet<>(allNodes);
        rootNodes.removeAll(childNodes);
        // checking all roots and their size
        int largestTreeRoot = Integer.MIN_VALUE;
        int maxTreeSize = 0;
        for(Integer root : rootNodes) {
            int treeSize = dfs(root, forest);
            if((maxTreeSize < treeSize) || (maxTreeSize == treeSize && root < largestTreeRoot)) {
                largestTreeRoot = root;
                maxTreeSize = treeSize;
            }
        }
        return largestTreeRoot;
    }
    private int dfs(Integer root, HashMap<Integer, Integer> forest) {
        int size = 1; // root itself
        for(Map.Entry<Integer, Integer> entry : forest.entrySet()) {
            if(Objects.equals(entry.getValue(), root)) {
                size += dfs(entry.getKey(), forest);
            }
        }
        return size;
    }
}
