package com.sparklesimply.string;

import java.util.*;

public class Miscellaneous {

    /**
     * Problem statement: Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
     * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
     * Time complexity: O(4^n * n)
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();
        int n = digits.length();
        if(n == 0)
            return result;
        String[] keyPad = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};


        result.add("");

        // fetching digit
        for(char digit : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            // fetching letters corresponding to digit
            String letters = keyPad[digit-'0'];
            for(char letter : letters.toCharArray()) {
                // getting possible combinations
                for(String combination : result) {
                    temp.add(combination + letter);
                }
            }
            // updating result
            result = temp;
        }
        return result;
    }

    /**
     * Problem statement: Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     * Time complexity: O(n)
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String word : strs) {
            char[] wordArray = word.toCharArray();
            Arrays.sort(wordArray);
            String newWord = new String(wordArray);
            if(map.containsKey(newWord)) {
                map.get(newWord).add(word);
            } else {
                List<String> words = new ArrayList<>();
                words.add(word);
                map.put(newWord, words);
            }
        }

        List<List<String>> result = new ArrayList<>();
        for(String key : map.keySet()) {
            result.add(map.get(key));
        }
        return result;
    }

    /**
     * Given a string containing only the characters (, ), {, }, [ and ], write a function to determine if the input  string has balanced brackets. The brackets must close in the correct order, meaning every opening  bracket has a corresponding closing bracket of the same type and in the correct order.
     * Input: "{[()]}"  Output: true
     * Input: "{[(])}" Output: false
     * Time complexity: O(n)
     * @param s
     * @return
     */
    boolean isBalanced(String s) {
        if(s == null)
            return true;
        int n = s.length();
        int countCloseBrackets = 0;
        Stack<Character> st = new Stack<>();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else if(!st.isEmpty() && st.peek() == '(' && c == ')') {
                st.pop();
            } else if(!st.isEmpty() && st.peek() == '{' && c == '}') {
                st.pop();
            } else if(!st.isEmpty() && st.peek() == '[' && c == ']') {
                st.pop();
            } else {
                if(c == ')' || c == '}' || c == ']')
                    countCloseBrackets++;
            }
        }

        if(countCloseBrackets == n)
            return false;
        return st.isEmpty();
    }
}
