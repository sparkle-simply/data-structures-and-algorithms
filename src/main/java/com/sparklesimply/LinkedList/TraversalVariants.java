package com.sparklesimply.linkedList;

import java.util.*;

public class TraversalVariants {

    /**
     * Problem statement: Given the head of a singly linked list and two integers
     * left and right where left <= right, reverse the nodes of the list from
     * position left to position right, and return the reversed list.
     * Time complexity: O(n)
     * 
     * @param head  node
     * @param left  position
     * @param right position
     * @return updated linked list
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;
        // dummy node to handle edge cases where head node is also part of to be
        // reversed sublist
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        // getting the prev point just before left position
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;

        }
        return dummy.next;
    }

    /**
     * Problem statement: Given the head of a linked list, rotate the list to the
     * right by k places.
     * Time complexity: O(n)
     * 
     * @param head node
     * @param k    rotations
     * @return rotated list
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;
        int len = 1;
        ListNode temp = head;
        // calculating total length of linked list
        while (temp.next != null) {
            len++;
            temp = temp.next;
        }
        // making the linked list circular
        temp.next = head;
        // ensuring k is in bounds
        k = k % len;
        k = len - k;
        // moving k (len - k) steps
        while (k-- > 0) {
            temp = temp.next;
        }
        // updating head and removing circular link
        head = temp.next;
        temp.next = null;
        return head;
    }

    /**
     * Problem statement: Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
     * Time complexity: O(n)
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
            return head;
        ListNode dummyNode = new ListNode(0);
        ListNode curr = head;
        ListNode prev = dummyNode;
        while(curr != null && curr.next != null) {
            ListNode nextNode = curr.next;
            curr.next = nextNode.next;
            nextNode.next = curr;
            prev.next = nextNode;
            prev = curr;
            curr = curr.next;
        }
        return dummyNode.next;
    }
}
