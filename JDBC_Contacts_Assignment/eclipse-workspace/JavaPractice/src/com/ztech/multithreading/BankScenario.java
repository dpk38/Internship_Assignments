package com.ztech.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BankScenario {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of customers: ");
		int n = sc.nextInt();
		System.out.println("Enter the process time of each customer: ");
		Queue<Integer> customers = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			customers.add(sc.nextInt());
		}
	}
}
