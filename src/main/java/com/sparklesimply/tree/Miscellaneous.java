package com.sparklesimply.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Miscellaneous {

    /**
     * Problem statement: Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
     * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
     * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
     * int next() Moves the pointer to the right, then returns the number at the pointer.
     * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
     * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
     */
    class BSTIterator {

        Queue<TreeNode> q;

        public BSTIterator(TreeNode root) {
            this.q = new LinkedList<TreeNode>();
            inorder(root);
        }
        private void inorder(TreeNode root) {
            if (root == null)
                return;
            inorder(root.left);
            q.add(root);
            inorder(root.right);
        }
        public int next() {
            return q.poll().data;
        }
        public boolean hasNext() {
            return !q.isEmpty();
        }
    }
}
