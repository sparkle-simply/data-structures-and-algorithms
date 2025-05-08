package com.sparklesimply.dynamicprogramming;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class Miscellaneous {

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * @param n steps of staircase
     * @return distinct ways to climb the stairs and reach top
     */
    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2; i<=n; i++) {
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**
     * Problem statement: Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     * Approach:
     * Use  dynamic programming, where dp[i] means whether the substring s[0...i-1] can be segmented.
     * For every index i, we check all previous j < i such that dp[j] is true and s[j...i-1] is in the dictionary.
     * If such a j exists, we mark dp[i] = true
     * Time complexity: O(n^3)
     * Note: can also be done with DFS+memoization with O(n^2)
     * @param s string
     * @param wordDict dictionary
     * @return true if s can be segmented into space separated sequence of one or more dictionary words
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        StringBuilder sbs = new StringBuilder(s);
        int n = sbs.length();
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++) {
            for(int j=0; j<i; j++) {
                if(dp[j] && set.contains(sbs.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }

    /**
     * Problem statement: Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
     * An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:
     * s = s1 + s2 + ... + sn
     * t = t1 + t2 + ... + tm
     * |n - m| <= 1
     * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
     * Note: a + b is the concatenation of strings a and b.
     * Approach: define a 2D dp[i][j] as:
     * true if s3[0...i+j-1] is an interleaving of s1[0...i-1] and s2[0...j-1]
     * Time complexity: O(m*n)
     * @param s1 string1
     * @param s2 string2
     * @param s3 string3
     * @return true if s3 in interleaved string for s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        if(s3.length() != m+n)
            return false;

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for(int i=1; i<=m; i++)
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1)==s3.charAt(i-1);

        for(int j=1; j<=n; j++)
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1)==s3.charAt(j-1);

        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                dp[i][j] = (dp[i-1][j] && s1.charAt(i-1)==s3.charAt(i+j-1)) || (dp[i][j-1] && s2.charAt(j-1)==s3.charAt(i+j-1));
            }
        }
        return dp[m][n];
    }

    /**
     * Problem statement: There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
     * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
     * The test cases are generated so that the answer will be less than or equal to 2 * 109.
     * Time complexity: O(m*n)
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        // dp[j] represents nums be of unique paths to reach column j in current row
        /**
         for m = 3, n = 2
         1 0 -> initialization
         1 1 -> i = 1
         1 2 -> i = 2
         1 3 -> i = 3
         */
        dp[0] = 1;
        for(int i=0; i<m; i++) {
            for(int j=1; j<n; j++)
                dp[j] += dp[j-1];
        }
        return dp[n-1];
    }
}
