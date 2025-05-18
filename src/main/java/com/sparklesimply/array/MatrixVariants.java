package com.sparklesimply.array;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class MatrixVariants {

    /**
     * This method finds the row having a maximum number of 1s in a binary matrix where each row is sorted
     * Time complexity: O(m+n) every column is visited atmost once
     * @param mat
     * @return
     */
    public int rowWithMaxOnes(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int j = n-1;
        int maxOnesCount = 0;
        int maxRowIndex = 0;
        for(int i=0; i<m; i++) {
            while(j>=0 && mat[i][j] == 1) {
                j--;
                maxRowIndex = i;
                maxOnesCount++;
            }
        }
        System.out.println("MaxRowIndex: "+maxRowIndex);
        System.out.println("MaxOnesCount: "+maxOnesCount);
        return maxRowIndex;
    }

    /**
     * Problem statement: You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     * Approach: Transpose and reverse elements of each row
     * Time complexity: O(n)
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        int k=0;
        while(k<n) {
            for(int i=0, j=n-1; i<=j; i++, j--) {
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
            k++;
        }
    }
}
