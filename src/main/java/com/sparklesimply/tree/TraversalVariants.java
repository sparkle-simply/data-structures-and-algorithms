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

    /**
     * Problem statement: Given the root of a binary tree, return its maximum depth.
     * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int depth = 0;
        while(true) {
            int n = q.size();
            if(n == 0)
                return depth;
            while(n-- > 0) {
                TreeNode temp = q.poll();
                if(temp.left != null)
                    q.add(temp.left);
                if(temp.right != null)
                    q.add(temp.right);
            }
            depth++;
        }
    }

    /**
     * Problem statement: Given the roots of two binary trees p and q, write a function to check if they are the same or not.
     * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        if(p != null && q != null)
            return (p.data == q.data && isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        return false;
    }

    /**
     * Problem statement: Given the root of a binary tree, invert the tree, and return its root.
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(root == null) {
            return null;
        }
        TreeNode leftChild = root.left;
        TreeNode rightChild = root.right;
        // swapping left and right child for inverting tree, we can use BFS traversal also and while polling node - swap left and right child nodes
        root.left = invertTree(rightChild);
        root.right = invertTree(leftChild);
        return root;
    }

    /**
     * Problem statement: Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetricUtil(root.left, root.right);
    }
    private boolean isSymmetricUtil(TreeNode left, TreeNode right) {
        if(left == null && right == null)
            return true;
        if(left != null && right != null)
            return (left.data == right.data && isSymmetricUtil(left.left, right.right) && isSymmetricUtil(left.right, right.left));
        return false;
    }

    /**
     * Problem statement: Given the root of a complete binary tree, return the number of the nodes in the tree.
     * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
     * Design an algorithm that runs in less than O(n) time complexity.
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if(root == null)
            return 0;
        int leftHeight = calculateLeftHeight(root);
        int rightHeight = calculateRightHeight(root);
        if(leftHeight == rightHeight) {
            // for a perfect binary tree, total nodes are: 2^height-1, time complexity: O(log n * log n)
            return (1 << leftHeight) - 1;
        } else {
            // if tree iis not perfect, time complexity: O(n)
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
    private int calculateLeftHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            height++;
            root = root.left;
        }
        return height;
    }
    private int calculateRightHeight(TreeNode root) {
        int height = 0;
        while(root != null) {
            height++;
            root = root.right;
        }
        return height;
    }

    /**
     * Problem statement: Given the root of a binary tree, flatten the tree into a "linked list":
     * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
     * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
     * @param root node
     */
    public void flatten(TreeNode root) {
        if(root == null)
            return;
        TreeNode[] prev = new TreeNode[1];
        flattenUtil(root, prev);
    }
    private void flattenUtil(TreeNode root, TreeNode[] prev) {
        if(root == null)
            return;
        flattenUtil(root.right, prev);
        flattenUtil(root.left, prev);
        root.right = prev[0];
        root.left = null;
        prev[0] = root;
    }

    /**
     * Problem statement: Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averageOfLevels = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int n = q.size();
            int totalNodesAtCurrenLevel = n;
            double sum = 0;
            while(n-- > 0) {
                TreeNode temp = q.poll();
                sum += temp.data;
                if(temp.left != null)
                    q.add(temp.left);
                if(temp.right != null)
                    q.add(temp.right);
            }
            averageOfLevels.add(sum/totalNodesAtCurrenLevel);
        }
        return averageOfLevels;
    }

    /**
     * Problem statement: Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if(root == null)
            return 0;
        int[] minDiff = new int[1];
        minDiff[0] = Integer.MAX_VALUE;
        TreeNode[] prev = new TreeNode[1];
        prev[0] = null;
        getMinimumDiffUtil(root, prev, minDiff);
        return minDiff[0];
    }
    private void getMinimumDiffUtil(TreeNode root, TreeNode[] prev, int[]  minDiff) {
        if(root == null)
            return;
        getMinimumDiffUtil(root.left, prev, minDiff);
        if(prev[0] != null)
            minDiff[0] = Math.min(minDiff[0], Math.abs(root.data - prev[0].data));
        prev[0] = root;
        getMinimumDiffUtil(root.right, prev, minDiff);
    }

    /**
     * Problem statement: Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     * Time complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> currLevelNodes = new ArrayList<>();
            while(n-- > 0) {
                TreeNode temp = q.poll();
                currLevelNodes.add(temp.data);
                if(temp.left != null)
                    q.add(temp.left);
                if(temp.right != null)
                    q.add(temp.right);
            }
            result.add(currLevelNodes);
        }
        return result;
    }

    /**
     * Problem statement: Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
     * Time complexity: O(n)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offerFirst(root);
        boolean reverse = false;
        while(!q.isEmpty()) {
            int n = q.size();
            List<Integer> currLevelNodes = new ArrayList<>();
            if(!reverse) {
                while(n-- > 0) {
                    if(q.peekLast().left != null)
                        q.offerFirst(q.peekLast().left);
                    if(q.peekLast().right != null)
                        q.offerFirst(q.peekLast().right);
                    currLevelNodes.add(q.pollLast().data);
                }
                reverse = !reverse;
            } else {
                while(n-- > 0) {
                    if(q.peekFirst().right != null)
                        q.offerLast(q.peekFirst().right);
                    if(q.peekFirst().left != null)
                        q.offerLast(q.peekFirst().left);
                    currLevelNodes.add(q.pollFirst().data);
                }
                reverse = !reverse;
            }
            result.add(currLevelNodes);
        }
        return result;
    }
}
