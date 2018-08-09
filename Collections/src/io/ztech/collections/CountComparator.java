package io.ztech.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class CountComparator implements Comparator {

	@Override
	public int compare(Object arg0, Object arg1) {
		ArrayList<Integer> listA = (ArrayList<Integer>)((Map.Entry)(arg0)).getValue();
		ArrayList<Integer> listB = (ArrayList)((Map.Entry)(arg1)).getValue();
		
		int listASum = 0, listBSum = 0;
		for (Integer num : listA) {
			listASum += num;
		}
		for (Integer num : listB) {
			listBSum += num;
		}
		return listBSum - listASum;
	}
}
