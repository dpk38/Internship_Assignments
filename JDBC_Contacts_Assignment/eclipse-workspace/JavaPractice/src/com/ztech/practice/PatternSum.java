package com.ztech.practice;

import java.util.Scanner;

public class PatternSum {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int n = sc.nextInt();
		int sum = a * n;
		for (int i = 0; i < n; i++) {
			sum += (Math.pow(2, i) * b) * (n - i);
		}
		System.out.println(sum);
		sc.close();
	}
}
