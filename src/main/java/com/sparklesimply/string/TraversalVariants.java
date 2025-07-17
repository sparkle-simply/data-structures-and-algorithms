package com.sparklesimply.string;

import java.util.*;

public class TraversalVariants {

    /**
     * Problem statement: Given an input string s, reverse the order of the words.
     * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
     * Return a string of the words in reverse order concatenated by a single space.
     * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
     * Time complexity: O(n)
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for(int i=words.length-1; i>=0; i--) {
            sb.append(words[i]);
            if(i != 0)
                sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Compress the string using continous frequency count
     * string: ABC -> output: ABC
     * string: AABBBCCTT -> output: A2B3C2T2
     * Time complexity: O(n)
     * Approach: using two loops
     * @param s
     * @return
     */
    public String compressUtilApproach1(String s) {
        if(s == null || s.isEmpty())
            return null;
        int n = s.length();
        StringBuilder result = new StringBuilder();
        for(int i=0; i<n; i++) {
            int count = 1;
            while((i+1)<n && s.charAt(i) == s.charAt(i+1)) {
                count++;
                i++;
            }
            result.append(s.charAt(i)).append(count);
        }
        return (result.length() < n) ? result.toString() : s;
    }
    /**
     * Compress the string using continous frequency count
     * string: ABC -> output: ABC
     * string: AABBBCCTT -> output: A2B3C2T2
     * Time complexity: O(n)
     * Approach: using one loop0
     * @param s
     * @return
     */
    public String compressUtilApproach2(String s) {
        if(s == null || s.isEmpty())
            return null;
        int n = s.length();
        StringBuilder result = new StringBuilder();
        char current = s.charAt(0);
        int count = 1;
        for(int i=1; i<n; i++) {
            if(current == s.charAt(i)) {
                count++;
            } else {
                result.append(current).append(count);
                current = s.charAt(i);
                count = 1;
            }
        }
        result.append(current).append(count);
        return (result.length() < n) ? result.toString() : s;
    }

    /**
     * Problem statement: A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
     * Given a string s, return true if it is a palindrome, or false otherwise.
     * Time complexity: O(n)
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        StringBuilder updatedString = new StringBuilder();
        int n = s.length();
        s = s.toLowerCase();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9'))
                updatedString.append(c);
        }
        int len = updatedString.length();
        for(int i=0, j=len-1; i<=j; i++, j--) {
            if(updatedString.charAt(i) != updatedString.charAt(j))
                return false;
        }
        return true;
    }

    /**
     * Problem statement: Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * Time complexity: O(n)
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        char[] charCount = new char[26];
        for(int i=0; i<s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
            charCount[t.charAt(i) - 'a']--;
        }
        for(int i=0; i<26; i++) {
            if(charCount[i] != 0)
                return false;
        }
        return true;
    }
    public boolean isAnagramBruteApproach(String s, String t) {
        if(s.length() != t.length())
            return false;
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Arrays.sort(sArray);
        Arrays.sort(tArray);
        String s1 = new String(sArray);
        String t1 = new String(tArray);
        return s1.equals(t1);
    }


    /**
     * Roman to Integer
     * Time complexity: O(n)
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int n = s.length();
        int result = 0;
        for(int i=0; i<n; i++) {
            int current = decode(s.charAt(i));
            // if there are more characters and current value is less the next one
            if((i+1)<n && current < decode(s.charAt(i+1))) {
                result -= current;
            } else {
                result += current;
            }
        }
        return result;
    }
    private int decode(char c) {
        switch(c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return -1;
        }
    }

    /**
     * Problem statement: Given a string s consisting of words and spaces, return the length of the last word in the string.
     * Time complexity: O(n)
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        // Naive approach:
        // String[] words = s.split("\\s+");
        // return words[words.length-1].length();

        // Optimized approach:
        String trimString = s.trim();
        int n = trimString.length();
        int count = 0;
        for(int i=n-1; i>=0; i--) {
            if(trimString.charAt(i) == ' ')
                break;
            count++;
        }
        return count;
    }

}

