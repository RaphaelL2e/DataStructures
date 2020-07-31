package com.leeyf.leetCode;

public class Solution730 {
	/**
	 * 统计给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
	 * @param n
	 * @return
	 */
	public int integerBreak(int n) {
		int three = 0;
		int two = 0;
		if(n<=3){
			return n-1;
		}
		three = n/3; two = n%3;
		if(two==0){
			return (int) Math.pow(3,three);
		}
		if(two==1){
			return (int) (Math.pow(3,three-1)*4);
		}
		return (int) (Math.pow(3,three)*2);

	}

	/**
	 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		int ans =0;
		while (x!=0){
			int pop = x%10;
			if(ans>Integer.MAX_VALUE/10||(ans==Integer.MAX_VALUE/10)||pop>7) return 0;
			if(ans<Integer.MIN_VALUE/10||(ans == Integer.MIN_VALUE/10)||pop<-8) return 0;
			ans = ans*10+pop;
		}
		return ans;
	}

	public static void main(String[] args) {
		Solution730 s = new Solution730();
//		System.out.println(s.reverse(1534236469));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
	}
}
