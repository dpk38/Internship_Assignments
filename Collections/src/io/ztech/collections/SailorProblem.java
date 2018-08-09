package io.ztech.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SailorProblem {

	private HashMap<Integer, ArrayList<Integer>> groupA = new HashMap<>();
	private HashMap<Integer, ArrayList<Integer>> groupB = new HashMap<>();
	private HashMap<Integer, String> fruitMap = new HashMap<>();
	private HashMap<Integer, String> fishMap = new HashMap<>();

	public void getCount(int numberOfSailors) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter the number of fruits: ");
		int numberOfFruits = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter the fruit types: ");
		for (int i = 0; i < numberOfFruits; i++) {
			fruitMap.put(i, sc.nextLine());
		}
		for (int i = 0; i < numberOfSailors / 2; i++) {
			System.out.println("\nSailor " + i + ":");
			ArrayList<Integer> list = new ArrayList<>();
			for (int j = 0; j < numberOfFruits; j++) {
				System.out.print("Enter the number of " + fruitMap.get(j) + " you have found: ");
				list.add(sc.nextInt());
			}
			groupA.put(i, list);
		}

		System.out.println("\nEnter the number of fish: ");
		int numberOfFish = sc.nextInt();
		sc.nextLine();
		System.out.println("\nEnter the fish types: ");
		for (int i = 0; i < numberOfFish; i++) {
			fishMap.put(i, sc.nextLine());
		}

		for (int i = numberOfSailors / 2; i < numberOfSailors; i++) {
			System.out.println("\nSailor " + i + ":");
			ArrayList<Integer> list = new ArrayList<>();
			for (int j = 0; j < numberOfFish; j++) {
				System.out.print("Enter the number of " + fishMap.get(j) + " you have found: ");
				list.add(sc.nextInt());
			}
			groupB.put(i, list);
		}
	}

	public void printCount() {
		groupA = sortByValues(groupA);
		groupB = sortByValues(groupB);

		int groupATotal = 0, groupBTotal = 0;
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> itr = groupA.entrySet().iterator();
		System.out.println("\nGroup A:");
		while (itr.hasNext()) {
			Map.Entry<Integer, ArrayList<Integer>> m = itr.next();
			ArrayList<Integer> list = m.getValue();
			System.out.println("\nSailor " + m.getKey() + ":\n----------");
			int totalFruits = 0;
			for (int i = 0; i < list.size(); i++) {
				System.out.println(fruitMap.get(i) + ": " + list.get(i));
				totalFruits += list.get(i);
			}
			System.out.println("Total number of fruits: " + totalFruits);
			groupATotal += totalFruits;
		}

		itr = groupB.entrySet().iterator();
		System.out.println("\nGroup B:");
		while (itr.hasNext()) {
			Map.Entry<Integer, ArrayList<Integer>> m = itr.next();
			ArrayList<Integer> list = m.getValue();
			System.out.println("\nSailor " + m.getKey() + ":\n----------");
			int totalFish = 0;
			for (int i = 0; i < list.size(); i++) {
				System.out.println(fishMap.get(i) + ": " + list.get(i));
				totalFish += list.get(i);
			}
			System.out.println("Total number of fish: " + totalFish);
			groupBTotal += totalFish;
		}
		System.out.println("\nTotal number of fruits collected = " + groupATotal);
		System.out.println("Total number of fish collected = " + groupBTotal);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static HashMap<Integer, ArrayList<Integer>> sortByValues(HashMap<Integer, ArrayList<Integer>> map) {
		List list = new LinkedList(map.entrySet());
		
		Collections.sort(list, new CountComparator());

		HashMap sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}
		return sortedHashMap;
	}

/*	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HashMap sort(HashMap group) {
		List list = new LinkedList(group.entrySet());
		Collections.sort(list, new CountComparator());

		HashMap<Integer, ArrayList<Integer>> sortedGroup = new HashMap<Integer, ArrayList<Integer>>();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedGroup.put((Integer) entry.getKey(), (ArrayList<Integer>) entry.getValue());
		}
		return sortedGroup;
	}
*/
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the total number of sailors: ");
		int numberOfSailors = sc.nextInt();
		SailorProblem sp = new SailorProblem();
		sp.getCount(numberOfSailors);
		sp.printCount();
	}
}
