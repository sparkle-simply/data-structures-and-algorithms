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

    /**
     * Problem statement: Given an m x n 2D binary grid which represents a map of '1's (land) and '0's (water), return the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
     * Approach:
     * using dfs (flood fill) approach, we'll visit each cell -> we'll increment the counter for island for cell having '1' (land) and visit connected components (land part of island) in all four directions
     * Time complexity: O(m*n)
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int count = 0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j, r, c);
                }
            }
        }
        return count;
    }
    void dfs(char[][] grid, int i, int j, int r, int c) {
        if(i<0 || i>=r || j<0 || j>=c || grid[i][j] != '1')
            return;
        grid[i][j] = '0';
        dfs(grid, i+1, j, r, c);
        dfs(grid, i-1, j, r, c);
        dfs(grid, i, j+1, r, c);
        dfs(grid, i, j-1, r, c);
    }
}
