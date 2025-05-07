package com.sparklesimply.array;

import java.util.*;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class Miscellaneous {

    /**
     * Problem statement: You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
     * Return true if you can reach the last index, or false otherwise.
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int maxReach = 0;
        for(int i=0; i<n; i++) {
            if(i > maxReach)
                return false;
            maxReach = Math.max(maxReach, i+nums[i]);
            if(maxReach >= n-1)
                return true;
        }
        // for empty arrays
        return true;
    }

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

    /**
     * Problem statement: Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
     * Return the kth positive integer that is missing from this array.
     * Time complexity: O(log n)
     * @param arr array
     * @param k missing positive number
     * @return kth missing positive number
     */
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int l = 0, r = n-1;
        while(l <= r) {
            int mid = (l + r)/2;
            int missingCount = arr[mid] - (mid+1);
            if(missingCount < k)
                l = mid + 1;
            else
                r = mid - 1;

        }
        return l + k;
    }

    /**
     * Problem statement: Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        if(n<2)
            return n;
        int max = 1;
        double slope;
        for(int i=0; i<points.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for(int j=0; j<points.length; j++) {
                if(i==j)
                    continue;
                if(points[j][0] == points[i][0])
                    slope = Double.POSITIVE_INFINITY;
                else {
                    slope = (double) (points[j][1] - points[i][1]) / (points[j][0] - points[i][0]);
                }

                int count = map.getOrDefault(slope, 0) + 1;
                map.put(slope, count);
                max = Math.max(max, count);
            }
        }
        return max+1;
    }

    /**
     * Problem statement: Implement the class SubrectangleQueries which receives a rows x cols rectangle as a matrix of integers in the constructor and supports two methods:
     * 1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)
     * Updates all values with newValue in the subrectangle whose upper left coordinate is (row1,col1) and bottom right coordinate is (row2,col2).
     * 2. getValue(int row, int col)
     * Returns the current value of the coordinate (row,col) from the rectangle.
     */
    class SubrectangleQueries {

        private int[][] matrix;

        public SubrectangleQueries(int[][] rectangle) {
            this.matrix = new int[rectangle.length][rectangle[0].length];
            for(int i=0; i<rectangle.length; i++)
                System.arraycopy(rectangle[i], 0, matrix[i], 0, rectangle[i].length);
        }

        public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
            if(row1<0 || row1>=this.matrix.length || row2<0 || row2>=this.matrix.length || col1<0 || col1>=this.matrix[0].length || col2<0 || col2>=this.matrix[0].length)
                return;
            for(int i=row1; i<=row2; i++) {
                for(int j=col1; j<=col2; j++)
                    this.matrix[i][j] = newValue;
            }
        }

        public int getValue(int row, int col) {
            return this.matrix[row][col];
        }
    }

    /**
     * Problem statement: Given an integer n, return a string array answer (1-indexed) where:
     * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
     * answer[i] == "Fizz" if i is divisible by 3.
     * answer[i] == "Buzz" if i is divisible by 5.
     * answer[i] == i (as a string) if none of the above conditions are true.
     * Focus areas:
     * SOLID Principles
     * Object Oriented Design
     * Extensible code with working demo
     */
    class FizzBuzzExample {
        public interface DivTest {
            public boolean check();
            public String encode();
        }

        public class DivWith3Test implements DivTest {
            private int num;
            public DivWith3Test(int x) {
                this.num = x;
            }
            @Override
            public boolean check() {
                return (this.num % 3) == 0;
            }
            @Override
            public String encode() {
                return "Fizz";
            }
        }

        public class DivWith5Test implements DivTest {
            private int num;
            public DivWith5Test(int x) {
                this.num = x;
            }
            @Override
            public boolean check() {
                return (this.num % 5) == 0;
            }
            @Override
            public String encode() {
                return "Buzz";
            }
        }

        /**
         * Problem statement: There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
         * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
         * Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
         * Time complexity: O(n)
         * @param gas
         * @param cost
         * @return
         */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int totalGas = 0, totalCost = 0;
        for(int x : gas) {
            totalGas += x;
        }
        for(int x : cost) {
            totalCost += x;
        }
        if(totalGas < totalCost)
            return -1;
        int currGas = 0;
        int start = 0;
        for(int i=0; i<n; i++) {
            currGas = currGas + gas[i] - cost[i];
            if(currGas < 0) {
                currGas = 0;
                start = i+1;
            }
        }
        return start;
    }
}
