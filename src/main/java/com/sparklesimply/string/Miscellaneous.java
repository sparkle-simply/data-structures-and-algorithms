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

    /**
     * Given a string containing only the characters (, ), {, }, [ and ], write a function to determine if the input  string has balanced brackets. The brackets must close in the correct order, meaning every opening  bracket has a corresponding closing bracket of the same type and in the correct order.
     * Input: "{[()]}"  Output: true
     * Input: "{[(])}" Output: false
     * Approach2: we maintain the map of brackets and check for validating at early stage while iterating string
     * Time complexity: O(n)
     * @param s
     * @return
     */
    boolean isBalancedApproach2(String s) {
        if(s == null)
            return true;
        Stack<Character> st = new Stack<>();
        Map<Character, Character> bracketsMap = Map.of(')', '(', '}', '{', ']', '[');
        for(char c : s.toCharArray()) {
            if(bracketsMap.containsValue(c)) {
                st.push(c);
            } else if(bracketsMap.containsKey(c)) {
                if(st.isEmpty() || bracketsMap.get(c) != st.peek())
                    return false;
            }
        }
        return st.isEmpty();
    }

    /**
     * Problem statement: Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * Each letter in magazine can only be used once in ransomNote.
     * Approach: using hashmap to capture magazine characters and checking with ransome note
     * Other approach can be using counter char array
     * Time complexity: O(n)
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length())
            return false;
        Map<Character, Integer> hm = new HashMap<>();
        for(char c : magazine.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0)+1);
        }
        for(char c : ransomNote.toCharArray()) {
            if(hm.get(c) == null || hm.get(c) <= 0)
                return false;
            hm.put(c, hm.getOrDefault(c, 0)-1);

        }
        return true;
    }

    /**
     * Problem statement: Given a pattern and a string s, find if s follows the same pattern.
     * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
     * Time complexity: O(n)
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split("\\s");
        if(pattern.length() != words.length)
            return false;
        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for(int i=0; i<pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if(!map1.containsKey(c)) {
                map1.put(c, word);
            } else if(!map1.get(c).equals(word)) {
                return false;
            }

            if(!map2.containsKey(word)) {
                map2.put(word, c);
            } else if(map2.get(word) != c) {
                return false;
            }
        }
        return true;
    }
}
