package com.sparklesimply.tree;

import java.util.*;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub
 *         Profile</a>)
 */
public class TraversalVariants {

    /**
     * This method will return kth smallest element in BST
     * Time Complexity: O(n)
     *
     * @param root root node
     * @param k    kth limit
     * @return kth smallest element
     */
    public int kthSmallestElement(TreeNode root, int k) {
        int[] counter = new int[1];
        int[] result = new int[1];
        kthSmallestElementUtil(root, k, counter, result);
        return result[0];
    }

    private void kthSmallestElementUtil(TreeNode root, int k, int[] counter, int[] result) {
        if (root == null)
            return;
        kthSmallestElementUtil(root.left, k, counter, result);
        counter[0]++;
        if (counter[0] == k) {
            result[0] = root.data;
            return;
        }
        kthSmallestElementUtil(root.right, k, counter, result);
    }

    /**
     * This method returns the lowest common ancestor for provided nodes p and q
     * Here, we'll check recursively in left and right subtree and if one value is
     * present in left subtree and other in right subtree then the current root is
     * the lca for p and q nodes
     * If both the provided nodes are in one subtree, continue checking the same
     * subtree
     * Time Complexity: O(n)
     *
     * @param root root node
     * @param p    node1
     * @param q    node2
     * @return lowest common ancestor for node1 and node2
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        if (root.data == p.data || root.data == q.data)
            return root;
        TreeNode leftLCA = lowestCommonAncestor(root.left, p, q);
        TreeNode rightLCA = lowestCommonAncestor(root.right, p, q);
        if (leftLCA != null && rightLCA != null)
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
        if (root == null)
            return 0;
        int[] result = new int[1]; // mutable reference (using array) to calculate the subsequence length
        longestIncreasingSubsequenceInBinaryTreeUtil(root, 0, root.data, result);
        return result[0];
    }

    private void longestIncreasingSubsequenceInBinaryTreeUtil(TreeNode root, int currLen, int expected, int[] result) {
        if (root == null)
            return;
        if (root.data == expected)
            currLen++;
        else
            currLen = 1;
        result[0] = Math.max(result[0], currLen);
        longestIncreasingSubsequenceInBinaryTreeUtil(root.left, currLen, root.data + 1, result);
        longestIncreasingSubsequenceInBinaryTreeUtil(root.right, currLen, root.data + 1, result);
    }

    /**
     * This method corrects the BST by fixing two swapped nodes
     * Time Complexity: O(n)
     *
     * @param root root node
     */
    TreeNode prev = null, first = null, middle = null, last = null;

    public void fixBSTSwapNodes(TreeNode root) {
        if (root == null)
            return;
        fixBSTSwapNodesUtil(root);
        if (first != null && last != null) {
            int temp = first.data;
            first.data = last.data;
            last.data = temp;
        } else if (first != null && middle != null) {
            int temp = first.data;
            first.data = middle.data;
            middle.data = temp;
        }
    }

    private void fixBSTSwapNodesUtil(TreeNode root) {
        if ((root == null))
            return;
        fixBSTSwapNodesUtil(root.left);
        if (prev != null && prev.data > root.data) {
            if (first == null) {
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
     * Problem statement: A forest is represented with a hashmap. This hashmap has
     * this key -> value relationship: child -> parent.
     * Every node has a unique integer element. I needed to find out the largest
     * tree's root node. If there is a tie, return the smallest root.
     * Time complexity: O(m+n), m nodes and n edges
     * 
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
        for (Integer root : rootNodes) {
            int treeSize = dfs(root, forest);
            if ((maxTreeSize < treeSize) || (maxTreeSize == treeSize && root < largestTreeRoot)) {
                largestTreeRoot = root;
                maxTreeSize = treeSize;
            }
        }
        return largestTreeRoot;
    }

    private int dfs(Integer root, HashMap<Integer, Integer> forest) {
        int size = 1; // root itself
        for (Map.Entry<Integer, Integer> entry : forest.entrySet()) {
            if (Objects.equals(entry.getValue(), root)) {
                size += dfs(entry.getKey(), forest);
            }
        }
        return size;
    }

    /**
     * Problem statement: Given two integer arrays preorder and inorder where
     * preorder is the preorder traversal of a binary tree and inorder is the
     * inorder traversal of the same tree, construct and return the binary tree
     * Time complexity: O(n)
     * 
     * @param preorder traversal of tree
     * @param inorder  traversal of tree
     * @return tree constructed from above traversals
     */
    public TreeNode buildTreeWithPre(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        int[] preIndex = new int[] { 0 };
        return buildTreeWithPreUtil(map, preorder, preIndex, 0, inorder.length - 1);
    }
    private TreeNode buildTreeWithPreUtil(HashMap<Integer, Integer> map, int[] preOrder, int[] preIndex, int left, int right) {
        if (left > right)
            return null;
        int rootVal = preOrder[preIndex[0]];
        preIndex[0]++;
        TreeNode root = new TreeNode(rootVal);
        int index = map.get(rootVal);
        root.left = buildTreeWithPreUtil(map, preOrder, preIndex, left, index - 1);
        root.right = buildTreeWithPreUtil(map, preOrder, preIndex, index + 1, right);
        return root;
    }

    /**
     * Problem statement: Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
     * Time complexity: O(n)
     * @param inorder traversal of tree
     * @param postorder traversal of tree
     * @return tree constructed from above traversals
     */
    public TreeNode buildTreeWithPost(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<inorder.length; i++) {
            map.put(inorder[i], i);
        }
        int[] postIndex = new int[] {postorder.length-1};
        return buildTreeWithPostUtil(postorder, postIndex, map, 0, inorder.length-1);
    }
    private TreeNode buildTreeWithPostUtil(int[] postorder, int[] postIndex, Map<Integer, Integer> map, int left, int right) {
        if(left > right)
            return null;
        int rootVal = postorder[postIndex[0]];
        postIndex[0]--;
        TreeNode root = new TreeNode(rootVal);

        int index = map.get(rootVal);
        root.right = buildTreeWithPostUtil(postorder, postIndex, map, index+1, right);
        root.left = buildTreeWithPostUtil(postorder, postIndex, map, left, index-1);
        return root;

    }

    /**
     * Problem statement: Given the root of a binary tree, each node in the tree has
     * a distinct value.
     * After deleting all nodes with a value in to_delete, we are left with a forest
     * (a disjoint union of trees).
     * Return the roots of the trees in the remaining forest. You may return the
     * result in any order.
     * Time complexity: O(n)
     * 
     * @param root
     * @param to_delete
     * @return
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> deleteSet = new HashSet<>();
        for (int delNum : to_delete)
            deleteSet.add(delNum);
        List<TreeNode> forest = new ArrayList<>();
        if (!deleteSet.contains(root.data)) {
            forest.add(root);
        }
        delNodesUtil(root, deleteSet, forest);
        return forest;
    }

    private TreeNode delNodesUtil(TreeNode root, Set<Integer> deleteSet, List<TreeNode> forest) {
        if (root == null)
            return null;
        root.left = delNodesUtil(root.left, deleteSet, forest);
        root.right = delNodesUtil(root.right, deleteSet, forest);

        if (deleteSet.contains(root.data)) {
            if (root.left != null)
                forest.add(root.left);
            if (root.right != null)
                forest.add(root.right);
            return null;
        }
        return root;
    }
}
