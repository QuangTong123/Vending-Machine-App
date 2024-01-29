package com.techelevator;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;


public class VendingMachineTest {

    public VendingMachine vendingMachine;
    public Product product;

    @Before
    public void setup(){
        vendingMachine = new VendingMachine();
        product = new Product();
    }

    @Test
    public void itemReturnsCorrect(){

        Product chippos = new Product("A4", "Chippos", 3.85, "Munchy", 5);
        Product moonpie = new Product("C3","Moonpie",2.95,"Candy", 5);
        Product wonkaBar = new Product("B4","Wonka Bar",2.35,"Candy", 5);

        String expected = "Chippos";
        String actual = chippos.getName();
        assertEquals(expected,actual);

        String expected1 = "Moonpie";
        String actual1 = moonpie.getName();
        assertEquals(expected1, actual1);

        String expected2 = "Wonka Bar";
        String actual2 = wonkaBar.getName();
        assertEquals(expected2, actual2);


    }

    @Test
    public void slotRerturnsCorrect(){

        Product chippos = new Product("A4", "Chippos", 3.85, "Munchy", 5);
        Product moonpie = new Product("C3","Moonpie",2.95,"Candy", 5);
        Product wonkaBar = new Product("B4","Wonka Bar",2.35,"Candy", 5);

        String expected = "A4";
        String actual = chippos.getSlotLocation();
        assertEquals(expected, actual);

        String expected1 = "C3";
        String actual1 = moonpie.getSlotLocation();
        assertEquals(expected1, actual1);

        String expected2 = "B4";
        String actual2 = wonkaBar.getSlotLocation();
        assertEquals(expected2, actual2);

    }


    @Test
    public void priceReturnsCorrect(){

        Product chippos = new Product("A4", "Chippos", 3.85, "Munchy", 5);
        Product moonpie = new Product("C3","Moonpie",2.95,"Candy", 5);
        Product wonkaBar = new Product("B4","Wonka Bar",2.35,"Candy", 5);

        double expected = 3.85;
        double actual = chippos.getPrice();
        assertEquals(expected, actual, 0.000001);

        double expected1 = 2.95;
        double actual1 = moonpie.getPrice();
        assertEquals(expected1, actual1, 0.00001);

        double expected2 = 2.35;
        double actual2 = wonkaBar.getPrice();
        assertEquals(expected2, actual2, 0.000001);
    }


    @Test
    public void typeReturnsCorrect(){

        Product chippos = new Product("A4", "Chippos", 3.85, "Munchy", 5);
        Product moonpie = new Product("C3","Moonpie",2.95,"Candy", 5);
        Product wonkaBar = new Product("B4","Wonka Bar",2.35,"Candy", 5);

        String expected = "Munchy";
        String actual = chippos.getType();
        assertEquals(expected, actual);

        String expected1 = "Candy";
        String actual1 = moonpie.getType();
        assertEquals(expected1, actual1);

        String expected2 = "Candy";
        String actual2 = wonkaBar.getType();
        assertEquals(expected2, actual2);

    }

    @Test
    public void quantityReturnsCorrect(){

        Product chippos = new Product("A4", "Chippos", 3.85, "Munchy", 5);
        Product moonpie = new Product("C3","Moonpie",2.95,"Candy", 5);
        Product wonkaBar = new Product("B4","Wonka Bar",2.35,"Candy", 5);

        int expected = 5;
        int actual = chippos.getQuantity();
        assertEquals(expected, actual);

        int expected1 = 5;
        int actual1 = moonpie.getQuantity();
        assertEquals(expected1, actual1);

        int expected2 = 5;
        int actual2 = wonkaBar.getQuantity();
        assertEquals(expected2, actual2);

    }

    @Test
    public void test_Feed_Money_5_return_5() {
        // Arrange
        VendingMachine vendingMachine = new VendingMachine();
        double initialBalance = vendingMachine.getCurrentBalance();

        // Simulate user input of "5\n"
        String userInput = "5\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Act
        vendingMachine.feedMoney(scanner);

        // Assert
        double expectedBalance = initialBalance + 5.0;
        assertEquals(expectedBalance, vendingMachine.getCurrentBalance(), 0.01);
    }

    @Test
    public void test_Feed_Money_12_return_12() {
        VendingMachine vendingMachine = new VendingMachine();
        double initialBalance = vendingMachine.getCurrentBalance();

        String userInput = "12\n";
        InputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        vendingMachine.feedMoney(scanner);

        double expectedBalance = initialBalance + 12.0;
        assertEquals(expectedBalance, vendingMachine.getCurrentBalance(), 0.01);
    }


    @Test
    public void test_Return_Change_return_correct_amount() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Create a VendingMachine instance for testing
        VendingMachine vendingMachine = new VendingMachine();

        // Set a balance for testing (e.g., $1.45)
        vendingMachine.setCurrentBalance(1.45);

        // Call the returnChange method
        vendingMachine.returnChange();

        // Restore the standard output
        System.setOut(System.out);

        // Get the captured output
        String printedOutput = outContent.toString().trim();

        // Expected result based on $1.45: 5 quarters, 2 dimes, 0 nickels
        String expectedOutput = "Returning change : 5 quarters 2 dimes 0 nickels";

        assertEquals(expectedOutput, printedOutput);
    }

    @Test
    public void test_Return_Change_5_return_correct_amount() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        VendingMachine vendingMachine = new VendingMachine();

        vendingMachine.setCurrentBalance(5.00);

        vendingMachine.returnChange();

        System.setOut(System.out);

        String printedOutput = outContent.toString().trim();

        String expectedOutput = "Returning change : 20 quarters 0 dimes 0 nickels";

        assertEquals(expectedOutput, printedOutput);
    }







}

