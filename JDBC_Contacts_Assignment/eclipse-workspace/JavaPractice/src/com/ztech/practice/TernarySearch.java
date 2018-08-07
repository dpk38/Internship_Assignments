package com.ztech.practice;

import java.util.Arrays;
import java.util.Scanner;

public class TernarySearch {
	static int ternarySearch(int[] arr, int key, int left, int right) {
		while (left <= right) {
			int m1 = left + (right - left) / 3;
			int m2 = left + (right - m1) / 3;
			if (arr[m1] == key) {
				System.out.println("It is present!");
				return m1;
			} else if (arr[m2] == key) {
				System.out.println("It is present!");
				return m2;
			} else if (key < arr[m1]) {
				return ternarySearch(arr, key, left, m1 - 1);
			} else if (key > arr[m2]) {
				return ternarySearch(arr, key, m2 + 1, right);
			} else {
				return ternarySearch(arr, key, m1 + 1, m2 - 1);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		int key = sc.nextInt();
		System.out.println(ternarySearch(arr, key, 0, n - 1));
		sc.close();
	}
}
