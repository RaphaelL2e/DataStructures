package com.leeyf.leetCode;

import java.util.ArrayList;
import java.util.List;

public class Solution731 {
	public int findMagicIndex(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			if(i == nums[i]){
				return i;
			}
			if(i<=nums[i]){
				i = nums[i];
			}
		}
		return -1;
	}

	public String longestPalindrome(String s) {
		int max =0;
		String ans = "";
		String s1;
		if(s.length()<2){
			return s;
		}
		for (int i = 0; i <=s.length() - 1; i++) {
			for (int j = i; j <=s.length()-1 ; j++) {
				s1 = s.substring(i,j+1);
				System.out.println(s1);
				if(flag(s1)){
					if(max<s1.length()){
						max = s1.length();
						ans = s1;
					}
				}
			}
		}
		return ans;
	}




	public boolean flag(String s){
		int i=0,j = s.length()-1;
		while (i<j){
			if (s.charAt(i)!=s.charAt(j)){
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	public String longestPalindrome2(String s) {
		if (s == null || s.length() < 1) return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}

	public String convert(String s, int numRows) {
		int len = s.length();
		List<StringBuilder> builderList = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			builderList.add(new StringBuilder());
		}
		int i=0,flag=-1;
		for (char c:s.toCharArray()){
			builderList.get(i).append(c);
			if(i==0||i==numRows-1){
				flag = -flag;
			}
			i+=flag;
		}
		StringBuilder res = new StringBuilder();
		for (StringBuilder stringBuilder:builderList){
			res.append(stringBuilder);
		}
		return res.toString();
	}

	public boolean isPalindrome(int x) {
		int div =1;
		while (x/div>=10) div*=10;
		while (x>0){
			int a = x/div;//首位
			int b = a%10;//末位
			x = (x%div)/10;
			div /=100;
			if(a!=b){
				return false;
			}
		}
		return true;
	}


	public static void main(String[] args) {
		Solution731 s = new Solution731();
//		System.out.println(s.longestPalindrome2("cbbd"));

	}

}
