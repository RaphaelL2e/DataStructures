package com.leeyf.leetCode;

import com.leeyf.tree.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution728 {
	/**
	 * 最长回文子串
	 * 暴力法
	 *
	 * @param s string
	 * @return 最长回文子串
	 */
	public String longestPalindrome(String s) {
		if (s.length() < 2) {
			return s;
		}
		int maxLength = 1;
		int begin = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if (helper(s, i, j)) {
					if (maxLength < j - i + 1) {
						begin = i;
						maxLength = j - i + 1;
					}
				}
			}
		}
		return s.substring(begin, begin + maxLength);
	}

	/**
	 * 判断是否回文
	 *
	 * @param s
	 * @return
	 */
	public boolean helper(String s, int i, int j) {
		int l = i, r = j;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			} else {
				l++;
				r--;
			}
		}
		return true;
	}

	/**
	 * 动态规划
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if(s.length()<2){return s;}
		int begin = 0;
		int maxLength =1;
		boolean[][] dp = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			dp[i][i] = true;
		}
		for (int j = 1; j < s.length(); j++) {
			for (int i = 0; i < j; i++) {
				if(s.charAt(j)!=s.charAt(i)){
					dp[i][j] = false;
				}else {
					if(j - 1 - (i + 1) + 1 < 2){
						dp[i][j] = true;
					}else {
						dp[i][j]=dp[i + 1][j - 1];
					}
				}
				if(dp[i][j] &j-i+1>maxLength){
					maxLength = j-i+1;
					begin = i;
				}
			}
		}
		return s.substring(begin,begin+maxLength);
	}

	/**
	 *  二叉树的最大深度
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		return level(root);
	}

	public int level(TreeNode node){
		if(node==null)return 0;
		int leftDepth = level(node.left);
		int rightDepth = level(node.right);
		return Math.max(leftDepth,rightDepth)+1;
	}

	/**
	 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
	 *
	 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
	 *
	 *  
	 *
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/two-sum
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer> map = new HashMap();
		int i =0;
		while (i<nums.length){
			if(map.containsKey(target-nums[i])){
				int a =map.get(target-nums[i]);
				int b = i;
				return new int[]{a,b};
			}else {
				map.put(nums[i],i);
			}
			i++;
		}
		return null;
	}

	/**
	 *给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
	 *
	 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
	 *
	 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/add-two-numbers
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode tmp = l1;
		while (tmp!=null&&l2!=null){
			if(tmp.val+l2.val>=10){
				if(tmp.next!=null){
					tmp.next.val +=1;
					if(l2.next==null){
						l2.next = new ListNode(0);
					}
				}else {
					tmp.next = new ListNode(1);
				}
				tmp.val = (tmp.val+l2.val)%10;
			}else {
				tmp.val +=l2.val;
			}
			tmp =tmp.next;
			l2 = l2.next;
		}

		if(l2!=null){
			ListNode cur = l1;
			while (cur!=null&&cur.next!=null){
				cur = cur.next;
			}
			cur.next = l2;
		}

		return l1;

	}

	public static void main(String[] args) {
		Solution728 s = new Solution728();
//		System.out.println(s.longestPalindrome2(
//				"babab"));
//		int[] ints = s.twoSum(new int[]{3, 2, 4}, 6);
//		for (int num :ints){
//			System.out.println(num);
//		}
		ListNode a=new ListNode(9);
			a	.next = new ListNode(9);

	s.addTwoNumbers(a,new ListNode(1));

	}

}
