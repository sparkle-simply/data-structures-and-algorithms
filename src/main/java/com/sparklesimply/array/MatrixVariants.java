package com.sparklesimply.array;

import java.util.ArrayList;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class MatrixVariants {

    /**
     * Approach1: This method finds the row having a maximum number of 1s in a binary matrix where each row is sorted
     * Time complexity: O(m+n) every column is visited atmost once
     */
    public int rowWithMaxOnesApproach1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int j = n-1;
        int maxOnesCount = 0;
        int maxRowIndex = -1;
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
     * Approach2: You are given a 2D matrix 'ARR' (containing either ‘0’ or ‘1’) of size 'N' x 'M', where each row is in sorted order.
     * Find the 0-based index of the first row with the maximum number of 1's.
     * Time complexity: O(n+m)
     */
    public static int maximumOnesRowApproach2(ArrayList<ArrayList<Integer>> matrix, int n, int m)
    {
        int maxRowIndex = -1;
        int i=0, j=m-1;
        while(i<n && j>=0) {
            if(matrix.get(i).get(j) == 1) {
                j--;
                maxRowIndex = i;
            } else {
                i++;
            }
        }
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

    /**
     * Problem statement: Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
     * Time complexity: O(m*n)
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[m];
        int[] col = new int[n];
        // calculating row and col where 0 is present
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }
        // if current row or col is 1 (with 0) then mark element of current row or col  as 0 while matrix traversal
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void rotateMatrix(ArrayList<ArrayList<Integer>> mat, int n, int m) {
        int top = 0, bottom = n-1, left = 0, right = m-1;
        while(top < bottom && left < right) {
            int prev = mat.get(top+1).get(left);
            for(int i=left; i<=right; i++) {
                int temp = mat.get(top).get(i);
                mat.get(top).set(i, prev);
                prev = temp;
            }
            top++;
            for(int i=top; i<=bottom; i++) {
                int temp = mat.get(i).get(right);
                mat.get(i).set(right, prev);
                prev = temp;
            }
            right--;
            if(top <= bottom) {
                for(int i=right; i>=left; i--) {
                    int temp = mat.get(bottom).get(i);
                    mat.get(bottom).set(i, prev);
                    prev = temp;
                }
                bottom--;
            }
            if(left <= right) {
                for(int i=bottom; i>=top; i--) {
                    int temp = mat.get(i).get(left);
                    mat.get(i).set(left, prev);
                    prev = temp;
                }
                left++;
            }
        }
    }
}
