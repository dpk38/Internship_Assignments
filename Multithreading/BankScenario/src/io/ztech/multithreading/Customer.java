package io.ztech.multithreading;

public class Customer implements Runnable {
	int customerId;
	int serviceTime;
	
	Customer (int serviceTime, int customerId) {
		this.serviceTime = serviceTime;
		this.customerId = customerId;
	}
	
	public void run() {
		System.out.println("Customer " + customerId + " being attended!");
		attendCounter();
		System.out.println("Customer " + customerId + " has left the counter!");
	}
	
	private void attendCounter() {
		try {
			Thread.sleep(serviceTime * 1000);
		} catch (InterruptedException e) {
			System.out.println("Exception caught: ");
			e.printStackTrace();
		}
	}
}
