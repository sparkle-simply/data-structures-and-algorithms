package com.sparklesimply.graph;

public class FloodFillVariants {

    /**
     * Problem statement: You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:
     * Connect: A cell is connected to adjacent cells horizontally or vertically.
     * Region: To form a region connect every 'O' cell.
     * Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
     * To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.
     * Time complexity: O(m*n)
     * @param board
     */
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // connecting boundaries and marking them visited
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                solveUtil(board, 0, j, m, n);
            if (board[m - 1][j] == 'O')
                solveUtil(board, m - 1, j, m, n);
        }
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                solveUtil(board, i, 0, m, n);
            if (board[i][n - 1] == 'O')
                solveUtil(board, i, n - 1, m, n);
        }

        // updating all other O to X, and resetting the visited cells to original values
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    private void solveUtil(char[][] board, int i, int j, int m, int n) {
        if(i<0 || i>m-1 || j<0 || j>n-1 || board[i][j] != 'O')
            return;
        // marking the cell as visited
        board[i][j] ='*';
        solveUtil(board, i+1, j, m, n);
        solveUtil(board, i-1, j, m, n);
        solveUtil(board, i, j+1, m, n);
        solveUtil(board, i, j-1, m, n);
    }
}
