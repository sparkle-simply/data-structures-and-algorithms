package com.sparklesimply.dynamicprogramming;

import java.util.Arrays;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 * Reference: <a href="https://leetcode.com/discuss/post/662866/dp-for-beginners-problems-patterns-sampl-atdb/">Leetcode DP problem patterns</a>
 */
public class CoinChangeVariants {

    /**
     * This method returns the minimum number of coins required to make up the amount, assumption is having infinite number of each kind of coin
     *
     * @param coins : integer array representing coins of different denominations
     * @param amount :  integer array coins representing coins of different denominations
     * @return : fewest number of coins that you need to make up that amount
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        // traversing coins in reverse order
        for(int i=coins.length-1; i>=0; i--) {
            for(int j=1; j<=amount; j++) {
                int take = Integer.MAX_VALUE;
                int noTake = Integer.MAX_VALUE;

                // if we consider to take current coin
                if(j-coins[i] >= 0 && coins[i] > 0) {
                    take = dp[j-coins[i]];
                    // If possible to take coin, increment counter
                    if(take != Integer.MAX_VALUE)
                        take++;
                }

                // If there are more coins, consider not taking current coin
                if(i+1 < coins.length) {
                    noTake = dp[j];
                }

                dp[j] = Math.min(take, noTake);
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

    /**
     * Method will return that total number of ways in which provided coins can make up to the amount, assumption is having infinite number of each kind of coin
     *
     * @param amount : integer representing a total amount of money
     * @param coins :  integer array coins representing coins of different denominations
     * @return : integer (the number) of combinations that make up that amount
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        // base scenario when target sum is 0, only 1 way to consider to not include the coin
        dp[0] = 1;
        // including all coins one by one and updating dp table, values here are considered greater than the current value of the included coin
        for (int coin : coins) {
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }
        return dp[amount];
    }

    /**
     * This method returns the number of possible combinations that add up to target
     * Note: In this problem, we are required to count the duplicate results.
     * However, in coin change, 1 + 1 + 2 is the same with 1 + 2 + 1.
     * The order of the for loop actually makes these two different problems.
     *
     * @param nums : array of distinct integers
     * @param target : target integer
     * @return : the number of possible combinations that add up to target
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int i=1; i<=target; i++) {
            for(int num : nums) {
                if(i>=num) {
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    /**
     * This method returns least number of perfect square numbers that sum to n
     * Note: similar to coinChange problem
     *
     * @param n : target n
     * @return : the least number of perfect square numbers that sum to n
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=1; i*i<=n; i++) {
            int sq = i*i;
            // we'll include the perfect square value or not include to check if it's adding up to make n
            for(int j=sq; j<=n; j++) {
                dp[j] = Math.min(1+dp[j-sq], dp[j]);
            }
        }
        return dp[n];
    }
}
