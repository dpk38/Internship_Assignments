package io.ztech.multithreading;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankScenario {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of counters: ");
		int numberOfCounters = sc.nextInt();
		System.out.print("\nEnter the number of customers: ");
		int numberOfCustomers = sc.nextInt();
		System.out.print("\nEnter the process time of each customer in seconds: ");
		Queue<Integer> processTime = new LinkedList<>();
		for (int i = 0; i < numberOfCustomers; i++) {
			processTime.add(sc.nextInt());
		}
		System.out.println();
		ExecutorService executor = Executors.newFixedThreadPool(numberOfCounters);
		for (int i = 0; i < numberOfCustomers; i++) {
			Customer newCustomer = new Customer((int) processTime.poll(), (i + 1));
			executor.execute(newCustomer);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {}

		System.out.println("\nFinished servicing all customers!");
	}
}
