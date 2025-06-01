package com.sparklesimply.dynamicprogramming;

import java.util.List;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class MaximizeMinimizeVariants {

    /**
     * Problem Statement:
     * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
     * Train tickets are sold in three different ways:
     * a 1-day pass is sold for costs[0] dollars,
     * a 7-day pass is sold for costs[1] dollars, and
     * a 30-day pass is sold for costs[2] dollars.
     * The passes allow that many days of consecutive travel.
     * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
     * Return the minimum number of dollars you need to travel every day in the given list of days.
     * Approach:
     * This method return the minimum number of dollars you need to travel every day in the given list of days
     * Note: each travel day has a "cost state" influenced by the costs of previous days
     * to check for each travel day, which ticket (1-day, 7-day, or 30-day) provides the lowest cost
     * constructing the solution iteratively using a dp array, optimal decisions for all travel days can be evaluated
     *
     * @param days : days of the year in which you will travel are given as an integer array days, each day is an integer from 1 to 365
     * @param costs : train tickets sold in three different ways,
     * a 1-day pass is sold for costs[0] dollars,
     * a 7-day pass is sold for costs[1] dollars, and
     * a 30-day pass is sold for costs[2] dollars.
     * @return : the minimum number of dollars you need to travel every day in the given list of days
     */
    public int mincostTickets(int[] days, int[] costs) {
        int totalDays = days[days.length-1];
        boolean[] travelDays = new boolean[totalDays+1];

        for(int day : days) {
            travelDays[day] = true;
        }

        int[] dp = new int[totalDays+1];
        dp[0] = 0;

        for(int i=1; i<=totalDays; i++) {
            // if it's not a travel day, the cost is same a previous day
            if(!travelDays[i]) {
                dp[i] = dp[i-1];
                continue;
            }
            // if it's travel day, evaluate cost state for travel day
            // consider minimum of 1-day, 7-day and 30-day pass
            dp[i] = dp[i-1] + costs[0];
            dp[i] = Math.min(dp[Math.max(0, i-7)] + costs[1], dp[i]);
            dp[i] = Math.min(dp[Math.max(0, i-30)] + costs[2], dp[i]);
        }
        return dp[totalDays];
    }

    /**
     * Problem Statement:
     * You work as a consultant and have clients in cityA and cityB. On a given day,
     * Say i, you can either work in cityA and make Ai dollars or you can work in cityB and make Bi dollars.
     * You can also spend the day traveling between cityA and cityB in which case your earnings that day are 0.
     * Given A1, A2, …, An and B1, B2, …, Bn, return a schedule S of N days which maximizes your earnings, where S is a string of length N,
     * and Si = A/B/T where A means work in cityA, B means work in cityB, T means travel on day i. You can start either in cityA or cityB.
     * Example 1: A = [23, 4, 5, 10] B = [21, 1, 10, 100] The optimal schedule S here would be -> "ATBB". 23 + 0 + 10 + 100
     * Example 2: A = [25,10,15,10,70] B = [5,5,50,5,30] The optimal schedule S here would be -> "ATBTA"
     *
     * @param A
     * @param B
     * @param n
     * @return
     */
    public String maximizeEarnings(int[] A, int[] B, int n) {
        int[] maxEarningsA = new int[n];
        int[] maxEarningsB = new int[n];
        char[] schedule = new char[n];

        maxEarningsA[0] = A[0];
        maxEarningsB[0] = B[0];
        schedule[0] = A[0] > B[0] ? 'A' : 'B';
        int maxEarnings = Math.max(A[0], B[0]);

        for(int i=1; i<n; i++) {

            if(A[i]+maxEarningsA[i-1] > maxEarningsB[i-1]) {
                maxEarningsA[i] = A[i]+ maxEarningsA[i-1]; // earning in cityA
                schedule[i] = 'A';
            } else {
                maxEarningsA[i] = maxEarningsB[i-1];
                schedule[i] = 'T'; // travel from cityB to cityA
            }

            if(B[i] + maxEarningsB[i-1] > maxEarningsA[i-1]) {
                maxEarningsB[i] = B[i] + maxEarningsB[i-1]; // earning in cityB
                schedule[i] = 'B';
            } else {
                maxEarningsB[i] = maxEarningsA[i-1];
                schedule[i] = 'T'; // travel from cityA to cityB
            }

            maxEarnings = Math.max(maxEarningsA[i], maxEarningsB[i]);
        }

        System.out.println("Max Earnings: "+maxEarnings);
        return new String(schedule);
    }

    /**
     * Problem statement: You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
     * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
     * Approach:
     * If we rob ith index, then you won't be able to rob (i-1)th index because of the constraint provided
     * If we skip robbing ith index, then robbed amount until now is same as (i-1)th index
     * iteratively we can compute for n houses, if n=1 amount will be nums[0] and if n=2 then amount will be max of nums[0] and nums[1]
     * Time complexity: O(n)
     * @param nums amount in n houses
     * @return maximum amount as part of robbing houses
     */
    public long houseRobberUtil(int[] nums) {
        int n = nums.length;
        if(n == 0)
            return 0L;
        if(n == 1)
            return (long) nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<n; i++) {
            dp[i] = Math.max(dp[i-1], nums[i]+dp[i-2]);
        }
        return dp[n-1];
    }

    /**
     * Problem statement: Mr. X is a professional robber planning to rob houses along a street. Each house has a certain amount of money hidden.
     * All houses along this street are arranged in a circle. That means the first house is the neighbour of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses are broken into on the same night.
     * You are given an array/list of non-negative integers 'ARR' representing the amount of money of each house. Your task is to return the maximum amount of money Mr. X can rob tonight without alerting the police.
     * Approach: reusing linear house robbing flow and considering two scenarios where we skip first and last house to calculate the maximum robbed amount
     * Time complexity: O(n)
     * @param valueInHouse amount in n houses circularly connected
     * @return amount as part of robbing houses
     */
    long houseRobber(int[] valueInHouse) {
        int n = valueInHouse.length;
        if(n==0)
            return 0L;
        if(n==1)
            return (long) valueInHouse[0];

        // houses are connected in circular way
        int[] case1 = new int[n-1];
        int[] case2 = new int[n-1];

        // skipping last house to rob
        System.arraycopy(valueInHouse, 0, case1, 0, n-1);
        // skipping first house to rob
        System.arraycopy(valueInHouse, 1, case2, 0, n-1);

        long max1 = houseRobberUtil(case1);
        long max2 = houseRobberUtil(case2);

        return Math.max(max1, max2);
    }

    /**
     * Problem statement: Given a triangle array, return the minimum path sum from top to bottom.
     * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
     * triangle = [[2],[3,4],[6,5,7],[4,1,8,3]], minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11
     * Approach:
     * create a 1D array dp of size n to store the minimum path sum starting from the bottom, there's nowhere to go, so the minimum path starting from each element there is the element itself
     * we process the triangle from bottom to top, starting at the second-last row (n - 2)
     * for each element in that row: add its value to the minimum of the two possible paths below it (either straight up dp[col] or diagonally right dp[col + 1]), overwrite dp[col] with the new minimum sum at that position
     * @param triangle array
     * @return minimum sun from top to bottom of triangle array
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for(int i=0; i<n; i++)
            dp[i] = triangle.get(n-1).get(i); // from bottom row there is nowhere to go further to calculate minimum sum
        for(int row=n-2; row>=0;row--) {
            for(int col=0; col<=row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col+1]);
            }
        }
        return dp[0];
    }

    /**
     * Given m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
     * Note: You can only move either down or right at any point in time.
     * @param grid array
     * @return minimum path sum from top left to bottom right
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];

        for(int j=1; j<n; j++)
            dp[0][j] = dp[0][j-1] + grid[0][j];

        for(int i=1; i<m; i++)
            dp[i][0] = dp[i-1][0] + grid[i][0];

        for(int i=1; i<m; i++) {
            for(int j=1; j<n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }

    /**
     * Problem statement: Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
     * You have the following three operations permitted on a word:
     * Insert a character
     * Delete a character
     * Replace a character
     * Approach: dp[i][j] as the minimum number of operations required to convert the first i characters of word1 to the first j characters of word2
     * For each dp[i][j], we have three possible operations to consider:
     * Insertion: If we insert a character in word1 to match word2[j-1], the cost will be dp[i][j-1] + 1.
     * Deletion: If we delete a character from word1 to match word2[j-1], the cost will be dp[i-1][j] + 1.
     * Replacement: If we replace the character in word1[i-1] with word2[j-1], the cost will be dp[i-1][j-1] + (word1[i-1] == word2[j-1] ? 0 : 1) (if characters are the same, no replacement is needed).
     * Time complexity: O(m*n)
     * @param word1 word1
     * @param word2 word2
     * @return minimum number of operations to convert word1 to word2
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for(int i=0; i<=m; i++)
            dp[i][0] = i; // deleting all characters in word1 to convert empty word2 string
        for(int j=1; j<=n; j++)
            dp[0][j] = j; // adding all characters to word1 to convert to non-empty word2 string
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]; // no replacement
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Problem statement: Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     * Approach:
     * use dp, where  dp[i][j] represent the side length of the largest square that ends at position (i, j) in the matrix
     * if the value at matrix[i-1][j-1] is 1, then we check the squares formed by the neighboring cells to its left (dp[i][j-1]), top (dp[i-1][j]), and top-left diagonal (dp[i-1][j-1]). The square size at (i, j) will be the minimum of the three neighbors plus 1
     * Time complexity: O(m*n)
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        int maxSide = 0;
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide*maxSide;
    }

    /**
     * Problem statement: You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete at most two transactions.
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * Approach:
     * to keep track of the maximum profit achievable with up to two transactions
     * leftProfit[i]: max profit achievable with one transaction from day 0 to i
     * rightProfits[i]: max profit achievable with one transaction from day i to the end
     * add these profits for day to find the best possible combination of two transactions
     * Time complexity O(n)
     * @param prices array
     * @return max profit
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[] leftProfit = new int[n];
        int[] rightProfit = new int[n+1];
        int minPrice = prices[0];
        int maxPrice = prices[n-1];
        // getting profit if stock is bought on or before ith day
        for(int i=1; i<n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i-1], prices[i]-minPrice);
        }
        // getting profit if stock is sold on or after ith day
        for(int i=n-2; i>=0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i+1], maxPrice-prices[i]);
        }
        // combining both transactions to get max profit
        int maxProfit = 0;
        for(int i=0; i<n; i++) {
            maxProfit = Math.max(maxProfit, leftProfit[i]+rightProfit[i+1]);
        }
        return maxProfit;
    }

    /**
     * Problem statement: You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
     * Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times and sell at most k times.
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * Approach: track the maximum profit for up to k transactions, by updating daily profits based on the best past buy opportunity (maxDiff), we get the maximum profit
     * Time complexity: O(n*k)
     * @param k transactions
     * @param prices array
     * @return max profit
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        // checking if there is infinite transactions
        if(k >= n/2) {
            int profit = 0;
            for(int i=1; i<n; i++) {
                if(prices[i] > prices[i-1]) {
                    profit += prices[i] - prices[i-1];
                }
            }
            return profit;
        }

        int[] prev = new int[n];
        int[] curr = new int[n];
        for(int t=1; t<=k; t++) {
            int maxDiff = -prices[0];
            for(int d=1; d<n; d++) {
                // carry over yesterday’s profit: curr[d - 1] or sell at (prices[d]) + the best possible previous profit (maxDiff)
                curr[d] = Math.max(curr[d-1], prices[d] + maxDiff);
                // best opportunity to buy in the past, if buy on some earlier day, which one gives me the best total profit if sell today
                maxDiff = Math.max(maxDiff, prev[d] - prices[d]);
            }

            System.arraycopy(curr, 0, prev, 0, n);
        }
        return prev[n-1];
    }

    /**
     * Problem statement: You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
     * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
     * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
     * Time complexity: O(n)
     * @param prices
     * @return
     */
    public int maxProfitWithCoolPeriod(int[] prices) {
        int n = prices.length;
        if(n<1)
            return 0;
        int[] buy = new int[n];
        int[] sell = new int[n];
        int[] cool = new int[n];

        buy[0] = -prices[0];
        sell[0] = 0;
        cool[0] = 0;
        for(int i=1; i<n; i++) {
            // if we buy today, implies we were sitting idle or on cool period
            buy[i] = Math.max(buy[i-1], cool[i-1] - prices[i]);

            // if we sell today, implies yesterday we were holding stock
            sell[i] = buy[i-1] + prices[i];

            // if we are on cool period today, implies we were idle or sold stock yesterday
            cool[i] = Math.max(cool[i-1], sell[i-1]);
        }
        // On nth day, max profit will be maximum of (n-1)th day cool period or sell profit
        return Math.max(cool[n-1], sell[n-1]);
    }
}
