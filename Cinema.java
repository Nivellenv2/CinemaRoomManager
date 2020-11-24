package com.company;

import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        final int numberOfRows = s.nextInt();
        System.out.println("Enter the number of seats in each row:");
        final int numberOfSeats = s.nextInt();
        final int numberOfAllSeats = numberOfRows * numberOfSeats;

        String[][] cinema = new String[numberOfRows][numberOfSeats];
        int ticketCounter = 0;
        int rowNumber = 0;

        creatingCinema(numberOfRows, numberOfSeats, cinema);
        menu(numberOfRows, numberOfSeats, cinema, ticketCounter, numberOfAllSeats, rowNumber);
    }

    public static void creatingCinema(int numberOfRows, int numberOfSeats, String[][] cinema) {

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfSeats; j++) {
                cinema[i][j] = "S";
            }
        }

    }

    public static void menu(int numberOfRows, int numberOfSeats, String[][] cinema, int ticketCounter, int numberOfAllSeats, int rowNumber) {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int number = s.nextInt();
        int currentPrice = price(numberOfRows, numberOfSeats, rowNumber);
        int totalInput = totalPrice(numberOfRows, numberOfSeats, rowNumber);
        int currentIncome =+ currentPrice;

        switch(number) {
            case 1: showTheSeats(numberOfRows, numberOfSeats, cinema);
                menu(numberOfRows, numberOfSeats, cinema, ticketCounter, numberOfAllSeats, rowNumber);
            case 2: buyATicket(numberOfRows, numberOfSeats, cinema);
                ticketCounter++;
                menu(numberOfRows, numberOfSeats, cinema, ticketCounter, numberOfAllSeats, rowNumber);
            case 3: statistics(ticketCounter, numberOfAllSeats, currentPrice, totalInput, currentIncome);
                menu(numberOfRows, numberOfSeats, cinema, ticketCounter, numberOfAllSeats, rowNumber);
            case 0: System.exit(0);
            default: System.out.println("Wrong input!");
                menu(numberOfRows, numberOfSeats, cinema, ticketCounter, numberOfAllSeats, rowNumber) ;
        }
    }

    public static void showTheSeats (int numberOfRows, int numberOfSeats, String[][] cinema) {
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= numberOfSeats; i++) {
            System.out.print(" ");
            System.out.print(i);
        }

        System.out.println();

        for (int i = 0; i < numberOfRows; i++) {
            System.out.print(" ");
            System.out.print(i+1);
            for (int j = 0; j < numberOfSeats; j++) {
                System.out.print(" ");
                System.out.print(cinema[i][j]);
            }
            System.out.println();
        }
    }

    public static void buyATicket(int numberOfRows, int numberOfSeats, String[][] cinema) {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter a row number:");
        int rowNumber = s.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = s.nextInt();

        if (cinema[rowNumber - 1][seatNumber - 1].equals("B")) {
            System.out.println("That ticket has already been purchased");
            buyATicket(numberOfRows, numberOfSeats, cinema);
        } else {
            System.out.println("Ticket price: $" + price(numberOfRows, numberOfSeats, rowNumber));
            cinema[rowNumber-1][seatNumber-1] = "B";
        }
    }

    public static int price(int numberOfRows, int numberOfSeats, int rowNumber) {
        int ticketPrice = 10;
        int ticketPriceSecondHalf = 8;
        int price;

        if (numberOfRows * numberOfSeats <= 60) {
            price = ticketPrice;
        } else {
            if (numberOfRows % 2 == 0 && numberOfRows / 2 < rowNumber) {
                price = ticketPriceSecondHalf;
            } else if (numberOfRows % 2 == 0 && numberOfRows / 2 >= rowNumber) {
                price = ticketPrice;
            } else if (numberOfRows % 2 != 0 && (int) (Math.floor((double) numberOfRows / 2)) < rowNumber) {
                price = ticketPriceSecondHalf;
            } else {
                price = ticketPrice;
            }
        }
        return price;
    }

    public static void statistics (int ticketCounter, int allSeats,  int totalInput, int currentIncome) {
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println("Number of purchased tickets: " + ticketCounter);
        System.out.println("Percentage: " + df2.format(((double)ticketCounter / (double)allSeats) * (double)100) + "%");
        System.out.println("Current income: " + currentIncome + "$");
        System.out.println("Total income: " + totalInput + "$");
    }

    public static int totalPrice(int numberOfRows, int numberOfSeats, int rowNumber) {
        int ticketPrice = 10;
        int ticketPriceSecondHalf = 8;
        int price;

        if (numberOfRows * numberOfSeats <= 60) {
            price = ticketPrice;
        } else {
            if (numberOfRows % 2 == 0 && numberOfRows / 2 < rowNumber) {
                price = ticketPriceSecondHalf;
            } else if (numberOfRows % 2 == 0 && numberOfRows / 2 >= rowNumber) {
                price = ticketPrice;
            } else if (numberOfRows % 2 != 0 && (int) (Math.floor((double) numberOfRows / 2)) < rowNumber) {
                price = ticketPriceSecondHalf;
            } else {
                price = ticketPrice;
            }
        }
        return numberOfRows * numberOfSeats * price;
    }
}