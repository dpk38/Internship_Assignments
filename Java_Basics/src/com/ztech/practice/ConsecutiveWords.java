package com.ztech.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsecutiveWords {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sentence = sc.nextLine();
		String[] arr = sentence.split(" ");
		ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i).equals(list.get(i + 1))) {
				list.remove(list.get(i));
				i--;
			}
		}
		String[] newArr = list.toArray(new String[list.size()]);
		String str = String.join(" ", newArr);
		System.out.println(str);
		sc.close();
	}
}
