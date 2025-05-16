package com.sparklesimply.tree;

public class BSTVariants {

    /**
     * Problem statement: Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
     * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
     * Time complexity: O(n)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null)
            return root;
        if(root.data > p.data && root.data > q.data)
            return lowestCommonAncestor(root.left, p, q);
        if(root.data < p.data && root.data < q.data)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }
}
