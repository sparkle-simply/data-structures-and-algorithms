package com.sparklesimply.array;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class Miscellaneous {

    /**
     * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
     * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:
     * 0 <= j <= nums[i] and
     * i + j < n
     * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
     * Approach:
     * Use a greedy approach where we expand range from l to r, and at each point, calculate the farthest position reachable
     * Time complexity: O(n) as every index is visited atmost once with r getting updated to farthest index
     * @param nums array
     * @return minimum number of jumps to reach nums[n-1]
     */
    public int jump(int[] nums) {
        int l=0, r=0, jumps=0, farthest=0;
        while(r < nums.length-1) {
            farthest=0;
            for(int i=l; i<=r; i++) {
                farthest = Math.max(farthest, i+nums[i]);
            }
            l = r+1;
            r = farthest;
            jumps = jumps+1;
        }
        return jumps;
    }

    /**
     * Problem Statement: There are n friends that are playing a game. The friends are sitting in a circle and are numbered from 1 to n in clockwise order. More formally, moving clockwise from the ith friend brings you to the (i+1)th friend for 1 <= i < n, and moving clockwise from the nth friend brings you to the 1st friend.
     * The rules of the game are as follows:
     * Start at the 1st friend.
     * Count the next k friends in the clockwise direction including the friend you started at. The counting wraps around the circle and may count some friends more than once.
     * The last friend you counted leaves the circle and loses the game.
     * If there is still more than one friend in the circle, go back to step 2 starting from the friend immediately clockwise of the friend who just lost and repeat.
     * Else, the last friend in the circle wins the game.
     * Given the number of friends, n, and an integer k, return the winner of the game.
     * Approach: simulating elimination process mathematically, instead of removing people from an actual list, we just keep track of the winner's position using simple arithmetic
     * @param n friends
     * @param k count
     * @return winner of the game
     */
    public int findTheWinner(int n, int k) {
        // base case when there’s only one person is that they are the winner
        int winner = 0;
        // updating the winner's position within bounds of the circle
        for(int i=2; i<=n; i++) {
            winner = (winner+k) % i;
        }
        return winner+1;
    }
}
