package com.sparklesimply.array;

public class MedianVariants {

    /**
     * Problem statement: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * Time complexity: O(log(m+n))
     * @param nums1 array
     * @param nums2 array
     * @return median of two arrays
     */
    public double findMedianSortedArraysOptimal(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if(n1 > n2)
            return findMedianSortedArraysOptimal(nums2, nums1);
        int low = 0, high = n1;
        double med = 0;
        while(low <= high) {
            int i = (low + high) / 2; // partition index for nums1
            int j = (n1 + n2 + 1)/2 - i; // partition index for nums2

            int maxLeftN1 = (i==0) ? Integer.MIN_VALUE : nums1[i-1];
            int minRightN1 = (i==n1) ? Integer.MAX_VALUE : nums1[i];
            int maxLeftN2 = (j==0) ? Integer.MIN_VALUE: nums2[j-1];
            int minRightN2 = (j==n2) ? Integer.MAX_VALUE : nums2[j];

            // Check if we have found the correct partition
            if(maxLeftN1 <= minRightN2 && maxLeftN2 <= minRightN1) {
                if((n1+n2)%2 == 0) {
                    med = (Math.max(maxLeftN1,maxLeftN2) + Math.min(minRightN1, minRightN2))/2.0;
                } else {
                    med = Math.max(maxLeftN1, maxLeftN2);
                }
                break;
            }
            else if(maxLeftN1 > minRightN2) {
                // We are too far right in nums1, need to move left
                high = i - 1;
            } else {
                // We are too far left in nums1, need to move right
                low = i + 1;
            }
        }
        return med;
    }

    /**
     * Problem statement: Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     * Time complexity: O(m+n)
     * @param nums1 array
     * @param nums2 array
     * @return median of two sorted arrays
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        double m1=0, m2=0;
        int i=0,j=0;

        if((n1+n2)%2 == 0) {
            for(int count=0; count<=(n1+n2)/2; count++) {
                m2 = m1;
                if(i != n1 && j != n2)
                    m1 = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
                else if(i != n1)
                    m1 = nums1[i++];
                else if(j != n2)
                    m1 = nums2[j++];
            }
            return (m1+m2)/2;
        } else {
            for(int count=0; count<=(n1+n2)/2; count++) {
                if(i != n1 && j != n2)
                    m1 = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
                else if(i != n1)
                    m1 = nums1[i++];
                else if(j != n2)
                    m1 = nums2[j++];
            }
            return m1;
        }
    }
}
