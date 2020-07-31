package com.leeyf;


public class Test {

	public static void main(String[] args) {
		System.out.println(solution(1, 2));
	}

	public static int solution(int a,int b){
		int num1 ,num2;
		while(b!=0){
			num1 = a^b;
			num2 = (a&b)<<1;
			a = num1;
			b = num2;
		}
		return a;

	}
}
