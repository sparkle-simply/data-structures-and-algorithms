package com.sparklesimply.array;

import java.util.*;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class SlidingWindowVariants {

    /**
     * This method return the minimum length of sub array having sum >=target
     * Each element is added to sum once when i is incremented and each element is removed at most once when j is incremented
     * Time complexity: O(n)
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        int i = 0, j = 0;
        while(i<n) {
            sum += nums[i++];
            while(sum >= target) {
                minLen = Math.min(minLen, i-j);
                sum -= nums[j++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * This method return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int i = 0, j = 0;
        int count = 0;
        int product = 1;
        while(i<n) {
            product *= nums[i];
            while(product >= k && j<=i) {
                product /= nums[j];
                j++;
            }
            count = count + i - j + 1;
            i++;
        }
        return count;
    }

    /**
     * Problem statement: The frequency of an element is the number of times it occurs in an array.
     * You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.
     * Return the maximum possible frequency of an element after performing at most k operations.
     * Time complexity: O(n)
     * @param nums
     * @param k
     * @return maximum possible frequency after performing atmost k operations
     */
    public int maxFrequency(int[] nums, int k) {
        int n = nums.length;
        if(n == 0)
            return 0;
        long total = 0;
        int maxFreq = 1, l = 0;
        Arrays.sort(nums);

        for(int r=0; r<n; r++) {
            total += nums[r];
            // checking if total operations required is exceeding k for making all elements of window [l, r] to nums[r]
            while( (r-l+1)*(long)nums[r] - total > k) {
                total -= nums[l];
                l++;
            }

            maxFreq = Math.max(maxFreq, r-l+1);
        }
        return maxFreq;
    }

    /**
     * Problem statement: You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.
     * Return the length of the longest substring containing the same letter you can get after performing the above operations.
     * Time complexity: O(n)
     * @param s string
     * @param k replacements
     * @return max length of substring having same letter with at most k replacements
     */
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int l = 0, r = 0, currLen = 0, maxLen = 0, maxOcc = 0;;
        Map<Character, Integer> map = new HashMap<>();
        while(r < n) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c, 0)+1);
            maxOcc = Math.max(maxOcc, map.get(c));
            currLen = r - l + 1;
            while(currLen - maxOcc > k) {
                map.put(s.charAt(l), map.getOrDefault(s.charAt(l), 0) - 1);
                l++;
                currLen--;
            }
            maxLen = Math.max(maxLen, currLen);
            r++;
        }
        return maxLen;
    }

    /**
     * Problem statement: Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     * Time complexity: O(n)
     * @param nums  array
     * @param k replacements
     * @return max length having all 1s with replacing k 0s
     */
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int maxOnes = 0, maxLen = 0, currLen = 0, l = 0, r = 0;
        while(r < n) {
            if(nums[r] == 1)
                maxOnes++;
            currLen = r - l + 1;
            while(currLen - maxOnes > k) {
                if(nums[l] == 1)
                    maxOnes--;
                l++;
                currLen--;
            }
            maxLen = Math.max(maxLen, currLen);
            r++;
        }
        return maxLen;
    }

    /**
     * Problem statement: Given a binary array nums, you should delete one element from it.
     * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
     * Time complexity: O(n)
     * @param nums array
     * @return longest sub array of 1s after deleting one element
     */
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int maxZeroes = 0, maxLen = 0, l = 0, r = 0;
        while(r < n) {
            if(nums[r] == 0)
                maxZeroes++;
            while(maxZeroes > 1) {
                if(nums[l] == 0)
                    maxZeroes--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1 - maxZeroes);
            r++;
        }
        return (maxLen == n) ? n-1 : maxLen;
    }

    /**
     * Problem statement: You are given two strings s and t of the same length and an integer maxCost.
     * You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the absolute difference between the ASCII values of the characters).
     * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding substring from t, return 0.
     * Time complexity: O(n)
     * @param s string1
     * @param t string2
     * @param maxCost max cost
     * @return equal substring length within provided budget max cost
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int maxLen = 0, currCost = 0, l = 0, r = 0;
        while(r < n) {
            currCost += Math.abs(s.charAt(r) - t.charAt(r));
            while(currCost > maxCost) {
                currCost -= Math.abs(s.charAt(l) - t.charAt(l));
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
            r++;
        }
        return maxLen;
    }

    /**
     * Problem statements: A teacher is writing a test with n true/false questions, with 'T' denoting true and 'F' denoting false. He wants to confuse the students by maximizing the number of consecutive questions with the same answer (multiple trues or multiple falses in a row).
     * You are given a string answerKey, where answerKey[i] is the original answer to the ith question. In addition, you are given an integer k, the maximum number of times you may perform the following operation:
     * Change the answer key for any question to 'T' or 'F' (i.e., set answerKey[i] to 'T' or 'F').
     * Return the maximum number of consecutive 'T's or 'F's in the answer key after performing the operation at most k times.
     * Time complexity: O(n)
     * @param answerKey
     * @param k
     * @return
     */
    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int l = 0, r = 0, maxLen = 0, currLen = 0, trueCount = 0, falseCount = 0, maxOcc = 0;
        while(r < n) {
            if(answerKey.charAt(r) == 'T')
                trueCount++;
            else
                falseCount++;
            maxOcc = Math.max(trueCount, falseCount);
            currLen = r - l + 1;
            if(currLen - maxOcc > k) {
                if(answerKey.charAt(l) == 'T')
                    trueCount--;
                else
                    falseCount--;
                l++;
                currLen--;
            }
            maxLen = Math.max(maxLen, currLen);
            r++;
        }
        return maxLen;
    }

    /**
     * Problem statement: You are given an integer array nums. In one operation, you can replace any element in nums with any integer.
     * nums is considered continuous if both of the following conditions are fulfilled:
     * All elements in nums are unique.
     * The difference between the maximum element and the minimum element in nums equals nums.length - 1.
     * For example, nums = [4, 2, 5, 3] is continuous, but nums = [1, 2, 3, 5, 6] is not continuous.
     * Return the minimum number of operations to make nums continuous.
     * Time complexity: O(n log n)
     * @param nums
     * @return
     */
    public int minOperations(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        // considering unique elements in num
        int uniqueLen = 1;
        for(int i=1; i<n; i++) {
            if(nums[i] != nums[i-1]) {
                nums[uniqueLen++] = nums[i];
            }
        }

        int l = 0, maxLen = 0;
        for(int r = 0; r < uniqueLen; r++) {
            while(l <= r && nums[r] - nums[l] > n - 1) {
                l++;
            }
            maxLen = Math.max(maxLen, r-l+1);
        }
        // minimum operations will be difference of total length and max window length having max and min value difference as n-1
        return n-maxLen;
    }

    /**
     * Problem statement: Given an array arr[] and an integer K, the task is to find the maximize the sum of K elements in the Array by taking only corner elements.
     * Time complexity: O(n)
     * @param nums
     * @param k
     * @return
     */
    public int maximumSumWithKCornerElements(int[] nums, int k) {
        int n = nums.length;
        int l = 0, r = n-1;
        int currSum = 0, maxSum = 0;
        // initial window
        while(l<k)
            currSum += nums[l++];
        maxSum = Math.max(maxSum, currSum);
        // updating window to check for maximum sun with k corner elements
        while(k-->0) {
            currSum = currSum - nums[--l] + nums[r--];
            maxSum = Math.max(maxSum, currSum);
        }
        return maxSum;
    }

    /**
     * Problem statement: You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     * Return the max sliding window.
     * Time complexity: O(n) every element is processed atmost twice
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0 || k == 0)
            return nums;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[n-k+1];
        int resIndex = 0;
        for(int i=0; i<n; i++) {
            // checking if indices are out of bound for window
            if(!dq.isEmpty() && dq.peekFirst() <=  i-k)
                dq.pollFirst();
            // checking if current index value is greater then dq values
            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i])
                dq.pollLast();
            // adding the next max in decresing to dq
            dq.offerLast(i);
            // if window size, add the max value to result
            if(i>=k-1) {
                res[resIndex++]=nums[dq.peekFirst()];
            }
        }
        return res;
    }


}
