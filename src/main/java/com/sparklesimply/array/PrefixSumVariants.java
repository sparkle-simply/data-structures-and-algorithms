package com.sparklesimply.array;

import java.util.HashMap;

/**
 * @author Simran Sharma (<a href="https://github.com/sparkle-simply">GitHub Profile</a>)
 */
public class PrefixSumVariants {

    /**
     * This method return true if we have subarray that sums to multiple of k and length>=2
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // handling 0 modulos scenario
        int sum = 0;
        for(int i=0; i<n; i++) {
            sum += nums[i];
            sum %= k;
            if(map.containsKey(sum)) { // if we got same modulos again, this implies that we have already got value that is multiple of k
                if(i-map.get(sum) > 1)
                    return true;
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    /**
     * This method will give count of all sub arrays where sum of elements is equal to k
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n];
        // cumulative sum
        prefixSum[0] = nums[0];
        for(int i=1; i<n; i++)
            prefixSum[i] = prefixSum[i-1] + nums[i];

        HashMap<Integer, Integer> prefixSumFrequency = new HashMap<>();
        int count=0;
        for(int i=0; i<n; i++) {
            // checking if sum until now is equal to k
            if(prefixSum[i] == k)
                count++;
            // checking if there are prev sums which constitute to sub array of sum k
            int prevSum = prefixSum[i] - k;
            if(prefixSumFrequency.containsKey(prevSum)) {
                count = count + prefixSumFrequency.get(prevSum);
            }

            // update prefix sum frequency
            prefixSumFrequency.put(prefixSum[i], prefixSumFrequency.getOrDefault(prefixSum[i], 0)+1);
        }
        return count;
    }

    /**
     * This method will return the max length of sub array with sum k
     * @param nums
     * @param k
     * @return
     */
    public int maxSubArrayLengthWithSunK(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0, sum = 0;
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        for(int i=0; i<n; i++) {
            sum += nums[i];
            if(sum == k)
                maxLen = i+1;
            // checking if we encountered <prevSum+k=sum>, implies that we encountered sub array values making sum k
            Integer prevSumIndex = prefixSum.get(sum - k);
            if(prevSumIndex != null) {
                maxLen = Math.max(maxLen,i-prevSumIndex);
            }

            if(!prefixSum.containsKey(sum)) {
                prefixSum.put(sum, i);
            }
        }
        return maxLen;
    }

    /**
     * This method will return the max length of sub array with sum k
     * @param nums
     * @return
     */
    public int maxSubArrayLengthWithSumZero(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for(int i=0; i<n; i++) {
            sum += nums[i];
            if(sum == 0)
                maxLen = i+1;
            Integer prevIndex = map.get(sum);
            // checking is tracking sum in encountered again, implying that we have sub array making sum further zero
            if(prevIndex != null) {
                maxLen = Math.max(maxLen, i-prevIndex);
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }
}
