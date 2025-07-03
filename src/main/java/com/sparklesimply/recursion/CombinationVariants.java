package com.sparklesimply.recursion;

import java.util.*;

public class CombinationVariants {

    /**
     * Problem statement: Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     * Approach: back track and generate valid combination of parentheses
     * Time complexity: O(2^2n) -> checking for possible valid combination
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backTrack(result, "", 0, 0, n);
        return result;
    }
    private void backTrack(List<String> result, String combination, int open, int closed, int max) {
        if(combination.length() == max*2) {
            result.add(combination);
            return;
        }
        if(open < max)
            backTrack(result, combination + "(", open+1, closed, max);
        if(closed < open)
            backTrack(result, combination + ")", open, closed+1, max);
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

    /**
     * Problem statement: Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
     * Approach: check all permutations with backtracking
     * Time complexity: O(n*n!)
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrackPermute(result, new ArrayList<>(), nums);
        return result;
    }
    private void backTrackPermute(List<List<Integer>> result, List<Integer> combination, int[] nums) {
        if(combination.size() == nums.length)
            result.add(new ArrayList<>(combination));
        else {
            for(int i=0; i<nums.length; i++) {
                if(combination.contains(nums[i]))
                    continue;
                combination.add(nums[i]);
                backTrackPermute(result, combination, nums);
                combination.remove(combination.size()-1);
            }
        }
    }

    /**
     * Problem statement: Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
     * Approach: using backtracking, check for possible palindromes
     * Time complexity: O(n*2^n)
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backTrackPalin(result, new ArrayList<>(), 0, s);
        return result;
    }
    private void backTrackPalin(List<List<String>> result, List<String> combination, int start, String s) {
        if(start == s.length()) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for(int end = start+1; end <= s.length(); end++) {
            if(isPalin(s, start, end-1)) {
                combination.add(s.substring(start, end));
                backTrackPalin(result, combination, end, s);
                combination.remove(combination.size()-1);
            }
        }
    }
    private boolean isPalin(String s, int start, int end) {
        while(start < end) {
            if(s.charAt(start++) != s.charAt(end--))
                return false;
        }
        return true;
    }

    /**
     * Problem statement: Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
     * Each number in candidates may only be used once in the combination.
     * Note: The solution set must not contain duplicate combinations.
     * Time complexity: O(2^n)
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backTrackCombination(result, new ArrayList<>(), 0, target, candidates);
        return result;
    }
    private void backTrackCombination(List<List<Integer>> result, List<Integer> combination, int start, int target, int[] nums) {
        if(target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for(int i=start; i<nums.length; i++) {
            if(i>start && nums[i] == nums[i-1]) continue;
            if(nums[i] > target) break;
            combination.add(nums[i]);
            backTrackCombination(result, combination, i+1, target-nums[i], nums);
            combination.remove(combination.size()-1);
        }
    }

    /**
     * Problem statement: Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
     * Time complexity: O(2^n)
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<expression.length(); i++) {
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
                List<Integer> leftList = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightList = diffWaysToCompute(expression.substring(i+1));

                for(int left : leftList) {
                    for(int right : rightList) {
                        int current;
                        switch(expression.charAt(i)) {
                            case '+' : current = left + right; break;
                            case '-' : current = left - right; break;
                            case '*' : current = left * right; break;
                            default: current = 0;
                        }
                        result.add(current);
                    }
                }
            }
        }
        if(result.size() == 0) {
            result.add(Integer.valueOf(expression));
        }
        return result;
    }
}
