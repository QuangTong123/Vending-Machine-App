package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This class is provided to you as a *suggested* class to start
 * your project. Feel free to refactor this code as you see fit.
 */
public class VendingMachineCLI {
	private final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		VendingMachineCLI cli = new VendingMachineCLI();
		cli.run();
	}

	public void run() {
		List<Product> products = readProductFromFile("main.csv");
		System.out.println();
		VendingMachine vendingMachine = new VendingMachine(products);
		boolean exitProgram = false;
		while(!exitProgram){
			VendingMachine.displayMain();
			System.out.println("Please Select an option: ");
			int choice = scanner.nextInt();
			switch (choice){
				case 1:
					vendingMachine.displayProduct();
					break;
				case 2:
					handlePurchaseMenu(vendingMachine, scanner);
					break;
				case 3:
					exitProgram = true;
					break;
				default:
					System.out.println("Invalid option> Please try again");
			}

		}

	}

	private static List<Product> readProductFromFile(String filename) {
		List<Product> products = new ArrayList<>();

		File input = new File(filename);

		if (input.exists()&& input.isFile()){

			try(Scanner inputScanner = new Scanner(input)){
				while (inputScanner.hasNextLine()){

					String currentLine = inputScanner.nextLine();

					String[] splitValues = currentLine.split(",");

					String slotLocation = splitValues[0];

					String name  = splitValues[1];

					String price = splitValues[2];

					double priceDou = Double.parseDouble(price);

					String type = splitValues[3];

					Product product = new Product(slotLocation,name,priceDou,type,5);

					products.add(product);
				}

			}catch (FileNotFoundException e){
				System.out.println("Error" + e.getMessage());
			}
		}
		return products;
	}
	private static void handlePurchaseMenu(VendingMachine vendingMachine, Scanner scanner) {
		boolean goBack = false;

		while (!goBack) {
			vendingMachine.displayOption();
			System.out.println("Select an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice){
				case 1:
					vendingMachine.feedMoney(scanner);
					TransactionLog.feedMoneyHistory(vendingMachine.getCurrentBalance(), vendingMachine.getCurrentBalance());
					break;
				case 2:
					vendingMachine.selectProduct(scanner);
					break;
				case 3:
					TransactionLog.giveChangeHistory(vendingMachine.getCurrentBalance(), 0.00);
					vendingMachine.completeTransaction();
//					VendingMachine.displayMain();
					goBack = true;
					break;
				default:
					System.out.println("invalid option, Please try again.");
			}
		}
	}

}
