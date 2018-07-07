//	In the cash register we will calculate the amount of change returned to a customer based on the purchase price and the amount tendered.
//	We will also notify the attendant how many of each piece of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) is needed to make the change for the customer.
//	Change should be provided using the largest bill and coin denominations as possible. Denominations that are not used should not be displayed.
//	
//	Hint: Mod operator
//	
//	User Story #1
//	The user is prompted asking for the price of the item.
//	
//	User Story #2
//	The user is then prompted asking how much money was tendered by the customer.
//	
//	User Story #3
//	Display an appropriate message if the customer provided too little money or the exact amount.
//	
//	User Story #4
//	If the amount tendered is more than the cost of the item, display the number of bills and coins that should be given to the customer.
//	
//	Grading
//	This is a graded project. You are expected to have your project completed by the start of class on Monday morning.
//	
//	You will be given either a pass or fail based on whether your code works given all of the following test conditions:
//	
//	Amount: .67, Tendered: .50, Result: Error message
//	Amount: .67, Tendered: 1.00, Result: 1 quarter, 1 nickel, 3 pennies.
//	Amount: .59, Tendered: 1.00, Result: 1 quarter, 1 dime, 1 nickel, 1 penny.
//	Amount: 3.96, Tendered: 20.00, Result: 1 ten dollar bill, 1 five dollar bill, 1 one dollar bill, 4 pennies.
//	Amount: any amount less than 20.00, Tendered: anything greater than amount: correct denominations for correct change.

package com.skilldistillery.makechange;

import java.util.Scanner;

public class MakeChange {
	public static void main(String[] args) {
		// Create scanner object to take input
		Scanner scanner = new Scanner(System.in);

		// Declare variables
		double purchasePrice;
		double amountTendered;

		purchasePrice = promptPrice(scanner);
		amountTendered = promptTendered(scanner);

		changeDue(purchasePrice, amountTendered, scanner);

		scanner.close();
	}

	public static double promptPrice(Scanner scanner) {
		System.out.print("What is the price of the item? \nPrice: ");
		double purchasePrice = scanner.nextDouble();

		return purchasePrice;
	}

	public static double promptTendered(Scanner scanner) {
		System.out.print("\nHow much to give cashier?" + "\nAmount tendered: ");
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
				System.out.println("\nAmount tendered: " + amountTendered + "\nPurchase price: " + purchasePrice
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
			System.out.println("\nAmount tendered: " + amountTendered + "\nPurchase price: " + purchasePrice
					+ "\n*** Exact amount given, no change due. ***");
		}
		
		
		double changeDue = amountTendered - purchasePrice;
		System.out.println("testing: " + changeDue);

	}

}
