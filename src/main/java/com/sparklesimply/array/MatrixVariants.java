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
}
