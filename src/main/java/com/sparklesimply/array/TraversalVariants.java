package com.sparklesimply.array;

import java.util.*;

public class TraversalVariants {

    /**
     * Problem statement: Given an array nums of size n, return the majority element.
     * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
     * Approach: evaluating the candidate with count>=0 way
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0, candidate = nums[0];
        for(int i=0; i<nums.length; i++) {
            if(count == 0)
                candidate = nums[i];
            if(nums[i] == candidate)
                count++;
            else
                count--;
        }
        return candidate;
    }

    /**
     * Problem statement: Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
     * Time complexity: O(n)
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] trackForward = new int[n];
        int[] trackBackward = new int[n];
        int[] trackResult = new int[n];
        trackForward[0] = 1;
        trackBackward[n-1] = 1;
        for(int i=1; i<n; i++) {
            trackForward[i] = trackForward[i-1] * nums[i-1];
        }
        for(int i=n-2; i>=0; i--) {
            trackBackward[i] = trackBackward[i+1] * nums[i+1];
        }
        for(int i=0; i<n; i++)
            trackResult[i] = trackForward[i]*trackBackward[i];
        return trackResult;
    }

    /**
     * Problem statement: Given an integer array nums of unique elements, return all possible subsets (the power set).
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     * Approach: using backtrack explore all possible combinations
     * Time complexity: O(2^n)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrackNums(result, new  ArrayList<Integer>(), nums, 0);
        return result;
    }
    private void backTrackNums(List<List<Integer>> result, List<Integer> combination, int[] nums, int start) {
        result.add(new ArrayList<>(combination));
        for(int i=start; i<nums.length; i++) {
            combination.add(nums[i]);
            backTrackNums(result, combination, nums, i+1);
            combination.remove(combination.size()-1);
        }
    }

}
