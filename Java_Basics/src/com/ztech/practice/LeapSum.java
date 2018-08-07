package com.ztech.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LeapSum {
	static boolean leapSum(int n, int[] arr, int leap) {
		boolean[] visited = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		while (!q.isEmpty()) {
			int index = q.poll();
			if (index + 1 < n && arr[index + 1] == 0) {
				if (index + 1 == n - 1) {
					return true;
				}
				if (visited[index + 1] == false) {
					q.add(index + 1);
					visited[index + 1] = true;
				}
			}
			if (index - 1 >= 0 && arr[index - 1] == 0) {
				if (visited[index - 1] == false) {
					q.add(index - 1);
					visited[index - 1] = true;
				}
			}
			if (index + leap >= n) {
				return true;
			} else if (arr[index + leap] == 0) {
				if (index + leap >= n - 1) {
					return true;
				}
				if (visited[index + leap] == false) {
					q.add(index + leap);
					visited[index + leap] = true;
				}
			}
			if (index - leap >= 0 && arr[index - leap] == 0) {
				if (visited[index - leap] == false) {
					q.add(index - leap);
					visited[index - leap] = true;
				}
			}
		}
		return false;
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int leap = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		if (leapSum(n, arr, leap)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		sc.close();
	}
}
