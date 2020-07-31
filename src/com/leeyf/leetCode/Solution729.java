package com.leeyf.leetCode;

import java.util.*;

public class Solution729 {
	/**
	 *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		char[] chars = s.toCharArray();
		int begin = 0;
		int maxLength =1;
		if(s.length()<2){return s.length();}
		Set<Character> set = new HashSet<>();
		int num =0;
		while (begin<s.length()){
			for (int i = begin; i <s.length() ; i++) {
				if (!set.add(chars[i])){
					begin++;
				}else {
					num++;
				}
			}
			maxLength = Math.max(num,maxLength);
			begin++;
		}
		return maxLength;
	}

	public int lengthOfLongestSubstring2(String s) {
		char[] chars = s.toCharArray();
		int begin = 0;
		int maxLength =1;
		if(s.length()<2){return s.length();}
		while (begin<s.length()){
			Set<Character> set = new HashSet<>();
			int num =0;
			for (int i = begin; i <s.length() ; i++) {
				if (!set.add(chars[i])){
					break;
				}
				num++;
			}
			maxLength = Math.max(num,maxLength);
			begin++;
		}
		return maxLength;
	}

	/**
	 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
	 *
	 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
	 *
	 * 你可以假设 nums1 和 nums2 不会同时为空。
	 *
	 *  
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int nums1Len = nums1.length;
		int nums2Len = nums2.length;
		int[] nums = new int[nums1Len+nums2Len];
		int a1=0,a2 =0;
		for (int i = 0; i < nums.length; i++) {
			if(helper(nums1,a1)>helper(nums2,a2)){
				if(helper(nums2,a2)!=0.1){
					nums[i] = (int) helper(nums2,a2++);
				}else {
					nums[i] = (int) helper(nums1,a1++);
				}
			}else {
				if(helper(nums1,a1)!=0.1){
					nums[i] = (int) helper(nums1,a1++);
				}else {
					nums[i] = (int) helper(nums2,a2++);
				}
			}
		}

		if(nums.length%2==0){
			return (nums[nums.length/2-1]+nums[nums.length/2])/2.0;
		}else {
			return nums[nums.length/2];
		}

	}

	double helper(int[] arr,int index){
		if(index<arr.length){
			return arr[index];
		}else {
			return 0.1;
		}
	}

	public static void main(String[] args) {
		Solution729 s = new Solution729();
		System.out.println(s.findMedianSortedArrays(new int[]{1,4}, new int[]{2,3}));
	}
}
