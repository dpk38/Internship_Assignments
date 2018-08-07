package com.ztech.practice;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {
	static void merge(int[] arr, int l, int m, int r) {
		int[] sorted = new int[r - l + 1];
		int i = l, j = m, k = 0;
		while (i < m && j <= r) {
			if (arr[i] < arr[j]) {
				sorted[k++] = arr[i];
				i++;
			} else {
				sorted[k++] = arr[j];
				j++;
			}
		}
		while (i < m) {
			sorted[k++] = arr[i++];
		}
		while (j <= r) {
			sorted[k++] = arr[j++];
		}
		k = 0;
		System.out.println("---" + Arrays.toString(sorted));
		for (i = l; i <= r; i++) {
			arr[i] = sorted[k++];
		}
	}

	static void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			int m = l + (r - l) / 2;
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		mergeSort(arr, 0, n-1);
		System.out.println(Arrays.toString(arr));
	}
}
