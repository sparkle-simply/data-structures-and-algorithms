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

    /**
     * Problem statement: A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
     * Implement the Trie class:
     * Trie() Initializes the trie object.
     * void insert(String word) Inserts the string word into the trie.
     * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
     * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
     */
    class Trie {

        class TrieNode {
            TrieNode[] childrens;
            boolean isEndOfWord;
            public TrieNode() {
                childrens = new TrieNode[26]; // for all lowercase alphabets
                isEndOfWord = false;
            }
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            for(char c : word.toCharArray()) {
                int index = c -'a';
                if(current.childrens[index] == null) {
                    current.childrens[index] = new TrieNode();
                }
                current = current.childrens[index];
            }
            current.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode searchResult = searchNode(word);
            return (searchResult != null && searchResult.isEndOfWord);

        }

        public boolean startsWith(String prefix) {
            return (searchNode(prefix) != null);

        }

        private TrieNode searchNode(String word) {
            TrieNode current = root;
            for(char c : word.toCharArray()) {
                int index = c - 'a';
                if(current.childrens[index] == null)
                    return null;
                current = current.childrens[index];
            }
            return current;
        }
    }

    /**
     * Problem statement: Design an algorithm to serialize and deserialize a binary tree.
     */
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if(root == null)
                return "";
            StringBuilder result = new StringBuilder();
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()) {
                TreeNode temp = q.poll();
                if(temp == null) {
                    result.append("n").append(" ");
                    continue;
                }
                result.append(temp.data).append(" ");
                q.add(temp.left);
                q.add(temp.right);
            }
            return result.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if(data == "" || data == "n")
                return null;
            String[] values = data.split(" ");
            Queue<TreeNode> q = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            q.add(root);
            for(int i=1; i<values.length; i++) {
                TreeNode temp = q.poll();
                if(!values[i].equals("n")) {
                    TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                    temp.left = left;
                    q.add(left);
                }
                if(!values[++i].equals("n")) {
                    TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                    temp.right = right;
                    q.add(right);
                }
            }
            return root;
        }
    }


}
