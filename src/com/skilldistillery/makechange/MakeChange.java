
package com.skilldistillery.makechange;

import java.util.Scanner;

public class MakeChange {
	public static void main(String[] args) {
		// Create scanner object to take input
		Scanner scanner = new Scanner(System.in);

		// Declare variables
		double purchasePrice;
		double amountTendered;

		// Run methods
		purchasePrice = promptPrice(scanner);
		amountTendered = promptTendered(scanner);
		changeDue(purchasePrice, amountTendered, scanner);

		scanner.close();
	}

	public static double promptPrice(Scanner scanner) {
		System.out.print("What is the price of the item? \nPrice: $");
		double purchasePrice = scanner.nextDouble();

		return purchasePrice;
	}

	public static double promptTendered(Scanner scanner) {
		System.out.print("\nHow much to give cashier?" + "\nAmount tendered: $");
		double amountTendered = scanner.nextDouble();

		return amountTendered;
	}

	public static void changeDue(double purchasePrice, double amountTendered, Scanner scanner) {
		// Check if the amount tendered is less than the purchase price. If so, prompt
		// the user if they'd like to continue the transaction - i.e. change the amount
		// tendered, or quit.
		if (amountTendered < purchasePrice) {
			boolean condition = true;

			do {
				System.out.println("\nAmount tendered: $" + amountTendered + "\nPurchase price: $" + purchasePrice
						+ "\n*** Error: The amount tendered does not cover the purchase price. ***");

				System.out.println("\nWould you like to quit transaction? (N) No, (Q) Quit: ");
				String quitOrNot = scanner.next();

				if (quitOrNot.equalsIgnoreCase("N")) {
					amountTendered = promptTendered(scanner);
					if (amountTendered < purchasePrice) {
						condition = true;
					} else if (amountTendered >= purchasePrice) {
						condition = false;
					}
				} else if (quitOrNot.equalsIgnoreCase("Q")) {
					System.exit(0);
				} else {
					System.out.println("Command not recognized.");
				}

			} while (condition);

		}

		// No change is given if true
		if (amountTendered == purchasePrice) {
			System.out.println("\nAmount tendered: $" + amountTendered + "\nPurchase price: $" + purchasePrice
					+ "\n*** Exact amount given, no change due. ***");
		}

		// Calculate change due if amountTendered > purchasePrice
		if (amountTendered > purchasePrice) {

			// The non-rounded version of changeDue
			double changeDue = amountTendered - purchasePrice;

			// Round the number by adding 0.05. We make changeNoDecimal an integer so that
			// we can do modulus operations.
			int changeNoDecimal = (int) (changeDue * 100 + 0.05);

			// Change changeDue to reflect the rounded value
			changeDue = changeNoDecimal / 100.0;

			// Show the correct rounded value version of changeDue
			System.out.println("Change due: $" + changeDue);

			int[] denominationArray = { 2000, 1000, 500, 100, 25, 10, 5, 1 };
			int[] changeArray = new int[8];

			// Divide changeNoDecimal by denominationArray elements and assign that result
			// to the changeArray which will hold how many of each element of
			// denominationArray was able to go into changeNoDecimal - i.e. get how many of each denomination
			// goes into changeNoDecimal.
			for (int i = 0; i < denominationArray.length; i++) {
				changeArray[i] = changeNoDecimal / denominationArray[i];
				changeNoDecimal %= denominationArray[i];
			}

			displayChange(changeArray);
		}

	}

	public static void displayChange(int[] changeArray) {
		System.out.println("\n******************************");
		System.out.println("$20 Bill: " + changeArray[0]);
		System.out.println("$10 Bill: " + changeArray[1]);
		System.out.println("$5 Bill: " + changeArray[2]);
		System.out.println("$1 Bill: " + changeArray[3]);
		System.out.println("Quarters: " + changeArray[4]);
		System.out.println("Dimes: " + changeArray[5]);
		System.out.println("Nickels: " + changeArray[6]);
		System.out.println("Pennies: " + changeArray[7]);
		System.out.println("******************************");
	}

}
