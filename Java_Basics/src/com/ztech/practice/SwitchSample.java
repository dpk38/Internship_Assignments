package com.ztech.practice;

import java.util.Scanner;

public class SwitchSample {
	static void printResponse(int value) {
		Scanner sc = new Scanner(System.in);
		switch (value) {
		case 1:
			System.out.print("Enter the integer: ");
			System.out.println("The integer you have entered is " + sc.nextInt());
			break;
		case 2:
			System.out.print("Enter the float: ");
			System.out.println("The floating point you have entered is " + sc.nextFloat());
			break;
		case 3:
			System.out.print("Enter the double: ");
			System.out.println("The double you have entered is " + sc.nextDouble());
			break;
		case 4:
			System.out.print("Enter the character: ");
			System.out.println("The character you have entered is " + sc.next().charAt(0));
			break;
		case 5:
			System.out.print("Enter the boolean value: ");
			System.out.println("The character you have entered is " + sc.nextBoolean());
			break;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char ch;
		do {
			System.out.print("Menu:\n1. Integer\n2. Float\n3. Double\n4. Char\n5. Boolean\nEnter your choice: ");
			int value = sc.nextInt();
			printResponse(value);
			System.out.print("Do you wish to continue (y/n)? ");
			ch = sc.next().charAt(0);
		} while (ch == 'y' || ch == 'Y');
		sc.close();
	}
}
