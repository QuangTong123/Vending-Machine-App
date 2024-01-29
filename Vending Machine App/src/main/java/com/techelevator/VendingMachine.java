package com.techelevator;

import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    private static List<Product> products;
    private static double currentBalance;
    private double discount;
    private int discountCounter;

    public VendingMachine() {

    }
    public int getDiscountCounter() {
        return discountCounter;
    }

    public VendingMachine(List<Product> products) {
        this.products = products;
        this.currentBalance = 0.00;
        this.discount = 0.00;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public static void setCurrentBalance(double currentBalance) {
        VendingMachine.currentBalance = currentBalance;
    }

    public double getDiscount() {
        return discount;
    }

    public static void displayMain() {
        System.out.println("(1) Display Vending Machine Items");
        System.out.println("(2) Purchase");
        System.out.println("(3) Exit");
    }

    public void displayOption() {
        System.out.println("Current Money Provided:" + getCurrentBalance());
        System.out.println();
        System.out.println("(1) Feed Money");
        System.out.println("(2) Select product");
        System.out.println("(3) Finish Transaction");
    }

    public void displayProduct() {
        System.out.println("Items list: ");
        for (Product product : products) {
            System.out.println("Slot#: " + "| " + product.getSlotLocation() + " | " + "| " + product.getName() + " | " + "|" + "  Purchase Price:  " + product.getPrice() +
                    " | " + "| " + product.getType() + " | " + "| " + product.getQuantity() + " |");
        }
    }

    public static void feedMoney(Scanner scanner) {
        System.out.println("Enter amount to feed (Whole dollar only): ");


        while (!scanner.hasNextLine()) {

            System.out.println("Invalid Input. Please enter a whole number");
            scanner.next();
        }

        double amount = scanner.nextDouble();
        currentBalance += amount;
    }

    public void completeTransaction() {
        returnChange();
        currentBalance = 0.00;
        System.out.println("Transaction finished. Returning to main menu.");
    }

    public void returnChange() {
        int remainingBalance = (int) (currentBalance * 100);
        int quarters = remainingBalance / 25;
        remainingBalance %= 25;
        int dimes = remainingBalance / 10;
        remainingBalance %= 10;
        int nickels = remainingBalance / 5;

        System.out.println("Returning change : " + quarters + " quarters " + dimes + " dimes " + nickels + " nickels");

    }



    public void selectProduct (Scanner scanner) {
        displayProduct();

        System.out.println("Enter the slot code to select a product: ");
        String slotCode = scanner.next().toUpperCase();

        Product selectedProduct = findProductBySlotCode(slotCode);
        if (selectedProduct == null) {
            System.out.println("Invalid product code. Please try again.");
        } else if (selectedProduct.getQuantity() == 0) {
            System.out.println("Sorry, this items is SOLD OUT. Please choose another product.");
        } else if (currentBalance < selectedProduct.getPrice()) {
            System.out.println("Insufficient funds. Please feed more money.");
        } else if( discountCounter > 0){
            dispenseProduct(selectedProduct);
            TransactionLog.giveProductHistory(selectedProduct,selectedProduct.getPrice(),currentBalance);
            currentBalance -= applyDiscount(selectedProduct);
            System.out.printf("Dispensed: %s. Remaining Balance: $%.2f%n", selectedProduct.getName(), currentBalance);
            if (selectedProduct.getType().contains("Munchy")){
                System.out.println("Crunch Crunch, Yum!");
            } else if (selectedProduct.getType().contains("Drink")){
                System.out.println("Glug Glug, Yum!");
            } else if (selectedProduct.getType().contains("Gum")){
                System.out.println("Chew Chew, Yum!");
            } else if (selectedProduct.getType().contains("Candy")) {
                System.out.println("Yummy Yummy, So Sweet!");
            }
            System.out.println("Item was discounted $1.00 !");
        }else {
            dispenseProduct(selectedProduct);
            TransactionLog.giveProductHistory(selectedProduct,selectedProduct.getPrice(),currentBalance);
            currentBalance -= selectedProduct.getPrice();
            System.out.printf("Dispensed: %s. Remaining Balance: $%.2f%n", selectedProduct.getName(), currentBalance);
            if (selectedProduct.getType().contains("Munchy")){
                System.out.println("Crunch Crunch, Yum!");
            } else if (selectedProduct.getType().contains("Drink")){
                System.out.println("Glug Glug, Yum!");
            } else if (selectedProduct.getType().contains("Gum")){
                System.out.println("Chew Chew, Yum!");
            } else if (selectedProduct.getType().contains("Candy")) {
                System.out.println("Yummy Yummy, So Sweet!");
            }
        }

    }


    private Product findProductBySlotCode(String slotCode) {
        for (Product product : products) {
            if (product.getSlotLocation().equalsIgnoreCase(slotCode)) {
                return product;
            }
        }
        return null;
    }
    private void dispenseProduct(Product product) {
        System.out.println("Dispensing: " + product.getName());
        product.decreaseQuantity();
        discountCounter++;

    }


    private double applyDiscount(Product product) {

        if (discountCounter > 0) {
            double discountPrice =  product.getPrice() -1.00;
            discountCounter = 0; // Reset the counter only when there is a discount

            return discountPrice;
        }
        return product.getPrice();
    }
}

