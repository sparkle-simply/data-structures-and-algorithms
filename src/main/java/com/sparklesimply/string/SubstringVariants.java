package com.sparklesimply.string;

import java.util.*;

public class SubstringVariants {

    /**
     * Problem statement: Given a string s, find the length of the longest substring without duplicate characters.
     * Approach: traverse through characters of string, capture the start of character and if we are encountering the character again update the start index
     * update max len while visiting all unique characters based on start of character
     * Time complexity: O(n)
     * @param s string
     * @return length of longest substring with no repeating character
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLen = 0;
        int maxStart = 0;
        int start = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)) {
                start = Math.max(start, map.get(c)+1);
            }
            map.put(c, i);
            if(maxLen < i-start+1) {
                maxLen = i-start+1;
                maxStart = start;
            }
        }
        System.out.println(s.substring(maxStart, maxStart+maxLen));
        return maxLen;
    }
}
