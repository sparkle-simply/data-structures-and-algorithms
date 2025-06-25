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
}
