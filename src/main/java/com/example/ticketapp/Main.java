package com.example.ticketapp;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Login or Create new User: (l for login, c for create): ");

        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("l")){
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();

            System.out.println("Customer y/n: ");
            String customer = scanner.nextLine().toLowerCase();

            boolean validUser;
            if (customer.equalsIgnoreCase("y")) {
                validUser = Login_Util.validateLogin(username, password, true);
            } else {
                validUser = Login_Util.validateLogin(username, password, false);
            }

            if (validUser) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
        } else {
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            System.out.println("Enter password: ");
            String password = scanner.nextLine();

            System.out.println("Customer y/n: ");
            String customer = scanner.nextLine().toLowerCase();
            boolean registered;
            if (customer.equalsIgnoreCase("y")) {
                registered = Login_Util.registerUser(username, password, true);
                System.out.println("Registration successful");
            } else {
                registered = Login_Util.registerUser(username,password,false);
                System.out.println("Registration failed");
            }
        }

    }
}