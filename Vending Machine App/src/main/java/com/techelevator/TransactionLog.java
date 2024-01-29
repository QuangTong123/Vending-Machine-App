package com.techelevator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionLog {
    private static void transactionHistory (String activity, double amount, double newBalance){
        File output = new File("log.txt");

        try(FileWriter writer = new FileWriter(output,true)){

            SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); // -> this formatted date will print location in memory.

            String dateTimeInString = dateTime.format(new Date());

            writer.write(dateTimeInString + " " + activity + " " + amount + " " + newBalance + "\n");

        }catch (IOException e){
            System.out.println("Something Wrong!! Cant write to log.txt" + e.getMessage());
        }

    }

    public static void feedMoneyHistory( double amount, double newBalance){
        transactionHistory("FEED MONEY", amount, newBalance);
    }

    public static void giveChangeHistory( double amount, double newBalance){
        transactionHistory("GIVE CHANGE", amount, 0.00);
    }

    public static void giveProductHistory( Product product, double amount, double newBalance){
        transactionHistory(product.getName() + " " + product.getSlotLocation(), amount, newBalance);
    }
}

