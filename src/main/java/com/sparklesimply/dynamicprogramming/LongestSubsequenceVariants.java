package com.sparklesimply.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class LongestSubsequenceVariants {

    /**
     * This method returns the max length of longest increasing subsequence using dp tabulation approach
     * Time complexity: O(n log n)
     * @param nums
     * @return max length of longest increasing subsequence
     */
    public int LISLength(int[] nums) {
        int n = nums.length;
        if(n <= 1)
            return n;
        List<Integer> dp = new ArrayList<>();
        dp.add(nums[0]);
        for(int i=0; i<n; i++) {
            if(nums[i] > dp.get(dp.size()-1)) {
                dp.add(nums[i]);
            } else {
                int lb=0, ub=dp.size()-1, mid=0;
                while (lb <= ub) {
                    mid = (lb+ub)/2;
                    if(nums[i] == dp.get(mid))
                        break;
                    else if(nums[i] < dp.get(mid))
                        ub = mid-1;
                    else
                        lb = mid+1;
                }
                if(mid>=0 && mid<dp.size() && nums[i] == dp.get(mid))
                    continue;
                dp.add(ub+1, nums[i]);
            }
        }
        return dp.size();
    }

    /**
     * This method return the list of largest divisible set such that nums[i]%nums[j]==0 or vice versa
     * Here, we'll sort to have elements in increasing order as lower numbers are easily divisible to greater numbers
     * we'll maintain dp array to have the maximum length of divisible subset until index i, dp[i] = d[j]+1 if nums[i]%nums[j]==0
     * also prev array maintained for backtrack, to have the list of elements that are divisible
     * Time Complexity: O(n^2)
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[n];
        int[] prev = new int[n];
        Arrays.fill(prev, -1);
        int maxIndex = 0;
        List<Integer> result = new ArrayList<Integer>();
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(nums[i]%nums[j] == 0 && dp[i] < dp[j]+1) {
                    dp[i] = dp[j]+1;
                    prev[i] = j;
                }
            }
            if(dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }
        while(maxIndex >= 0) {
            result.add(nums[maxIndex]);
            maxIndex = prev[maxIndex];

        }
        return result;
    }

    /**
     * Problem statement: Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
     * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
     * For example, "ace" is a subsequence of "abcde".
     * A common subsequence of two strings is a subsequence that is common to both strings.
     * Time complexity: O(m*n)
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        // dp[i][j] represent longest common subsequence in first i characters of text1 and first j characters in text2
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
