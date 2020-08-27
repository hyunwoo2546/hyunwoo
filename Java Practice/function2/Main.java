package com.hyunwoo.function2;

public class Main {

	public static int max(int a, int b) {
		return (a>b) ? a : b;
	}
	
	public static int function(int a,int b, int c) {
		int result = max(a,b);
		result = max(result,c);
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("세 값중 제일 큰 값은 : " + function(345,567,789));
	}

}
