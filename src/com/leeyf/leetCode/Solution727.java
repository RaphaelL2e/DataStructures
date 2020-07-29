package com.leeyf.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Solution727 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 * int val;
	 * ListNode next;
	 * ListNode(int x) { val = x; }
	 * }
	 */


	public boolean isPalindrome(ListNode head) {
		List<Integer> list = new ArrayList<>();
		while (head!=null){
			list.add(head.val);
			head = head.next;
		}
		int a = 0,b = list.size()-1;
		while (a<b){
			if(!list.get(a).equals(list.get(b))){
				return false;
			}else {
				a++;
				b--;
			}
		}
		return true;
	}

	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode tmpA = headA;

		while (tmpA!=null){
			ListNode tmpB = headB;
			while (tmpB!=null){
				if(tmpA.equals(tmpB)){
					if (helper(tmpA,tmpB)) {
						return tmpA;
					}
				}
				tmpB = tmpB.next;

			}
			tmpA = tmpA.next;
		}
		return null;
	}

	public boolean helper(ListNode node1,ListNode node2){

		while (node1!=null&&node2!=null){
			if(node1.val==node2.val){
				node1 = node1.next;
				node2 = node2.next;
			}else {
				return false;
			}
		}
		return node1.equals(node2);

	}

	public ListNode detectCycle(ListNode head) {
		//head为空 head。next为空
		if (head == null || head.next == null) {
			return null;
		}
		//快慢指针
		ListNode tmp1 = head;
		ListNode tmp2 = head;
		//快指针不为空 快指针的next不为空
		while (tmp2!=null&&tmp2.next!=null){

			tmp1 = tmp1.next;
			tmp2 = tmp2.next.next;
			if(tmp1 ==tmp2){
				break;
			}
		}
		//快慢指针不等循环正常结束
		if(tmp1!=tmp2){
			return null;
		}
		//慢指针归零
		tmp1 = head;
		//找到交点
		while (tmp1!=tmp2){
			tmp1 =tmp1.next;
			tmp2 = tmp2.next;
		}
		return  tmp1;

	}

	class Solution {
		public int findKthLargest(int[] nums, int k) {
			int heapSize = nums.length;
			buildMaxHeap(nums, heapSize);
			for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
				swap(nums, 0, i);
				--heapSize;
				maxHeapify(nums, 0, heapSize);
			}
			return nums[0];
		}

		public void buildMaxHeap(int[] a, int heapSize) {
			for (int i = heapSize / 2; i >= 0; --i) {
				maxHeapify(a, i, heapSize);
			}
		}

		public void maxHeapify(int[] a, int i, int heapSize) {
			int l = i * 2 + 1, r = i * 2 + 2, largest = i;
			if (l < heapSize && a[l] > a[largest]) {
				largest = l;
			}
			if (r < heapSize && a[r] > a[largest]) {
				largest = r;
			}
			if (largest != i) {
				swap(a, i, largest);
				maxHeapify(a, largest, heapSize);
			}
		}

		public void swap(int[] a, int i, int j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

}
