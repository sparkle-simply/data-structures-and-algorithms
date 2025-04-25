package com.sparklesimply.array;

import java.util.Stack;

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

    /**
     * Problem statement: Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
     * Approach:
     * water trapped at each index depends on the minimum of the maximum height to its left and to its right, minus the height at that index
     * Time complexity: O(n)
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n-1;
        int leftMax = height[left];
        int rightMax = height[right];
        int waterTrap = 0;
        while(left < right) {
            if(leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
                waterTrap += (Math.max(0, leftMax-height[left]));
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
                waterTrap += (Math.max(0, rightMax-height[right]));
            }
        }
        return waterTrap;
    }

    /**
     * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
     * Find two lines that together with the x-axis form a container, such that the container contains the most water.
     * Return the maximum amount of water a container can store.
     * Time complexity: O(n)
     * @param height array
     * @return max water area
     */
    public int maxArea(int[] height) {
        int n = height.length;
        int maxWaterArea = 0;
        for(int i=0, j=n-1; i<j;) {
            int h = height[i] <= height[j] ? height[i++] : height[j--];
            maxWaterArea = Math.max(maxWaterArea, (j-i+1)*h);
        }
        return maxWaterArea;
    }

    /**
     * Problem statement: Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
     * Time complexity: O(n) as all temperatures index are processed at most once
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] days = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!st.isEmpty() && temperatures[st.peek()] < temperatures[i]) {
                days[st.peek()] = i - st.peek();
                st.pop();
            }
            st.push(i);
        }
        return days;
    }
}
