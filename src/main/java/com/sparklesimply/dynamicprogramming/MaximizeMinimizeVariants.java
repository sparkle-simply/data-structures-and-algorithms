package com.sparklesimply.dynamicprogramming;

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
}
